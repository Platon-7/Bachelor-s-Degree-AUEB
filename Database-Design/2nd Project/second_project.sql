CREATE TABLE campdata(
custID integer,
fname varchar(30),
lname varchar(30),
cID integer ,
country varchar(30),
bookID integer,
bookDate date ,
campCode char(3),
campName Varchar(50) ,
empno integer ,
catCode char(1) ,
category varchar(20) ,
unitCost numeric(4,2),
startDate date ,
overnights integer,
persons integer );

BULK INSERT campdata
FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\2nd Project\CAMPDATA.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR='|', ROWTERMINATOR = '\n');

SELECT a.custID, a.bookID, a.campCode, a.startDate
FROM campdata a
JOIN (SELECT custID, bookID, campCode, startDate, COUNT(*) as c
FROM campdata
GROUP BY custID, bookID,campCode, startDate
HAVING count(*) > 1 ) b
ON a.custID= b.custID
AND a.bookID = b.bookID
AND a.campCode = b.campCode
AND a.startDate = b.startDate
GROUP BY a.custID, a.bookID, a.campCode, a.startDate, a.empno



SELECT custID, campCode, bookID, startDate, catCode, empno
FROM campdata



//Pinakas Theseis

SELECT a.category, a.campCode, a.catCode, a.empno
FROM campdata a
JOIN (SELECT category, campCode, catCode, empno, COUNT(*) as c
FROM campdata
GROUP BY category, campCode, catCode, empno
HAVING count(*) > 1 ) b
ON a.category= b.category
AND a.campCode = b.campCode
AND a.catCode = b.catCode
AND a.empno = b.empno
GROUP BY a.category, a.campCode, a.catCode, a.empno
ORDER BY a.campCode

//Pinakas Toyristiko Grafeio

SELECT a.custID, a.fname, a.lname, a.cID, a.country
FROM campdata a
JOIN (SELECT custID, fname, lname, cID, country, COUNT(*) as c
FROM campdata
GROUP BY custID, fname, lname, cID, country
HAVING count(*) > 1 ) b
ON a.custID= b.custID
AND a.fname = b.fname
AND a.lname = b.lname
AND a.cID = b.cID
AND a.country = b.country
GROUP BY a.custID, a.fname, a.lname, a.cID, a.country

//Pinakas Krathseis

SELECT a.category, a.campCode, a.catCode, a.empno
FROM campdata a
JOIN (SELECT category, campCode, catCode, empno, COUNT(*) as c
FROM campdata
GROUP BY category, campCode, catCode, empno
HAVING count(*) > 1 ) b
ON a.category= b.category
AND a.campCode = b.campCode
AND a.catCode = b.catCode
AND a.empno = b.empno
GROUP BY a.category, a.campCode, a.catCode, a.empno
ORDER BY a.campCode


SELECT a.custID, a.bookID, a.campCode, a.startDate
FROM campdata a
JOIN (SELECT custID, bookID, campCode, startDate, COUNT(*) as c
FROM campdata
GROUP BY custID, bookID, catCode, campCode
HAVING count(*) > 1 ) b
ON a.custID= b.custID
AND a.bookID = b.bookID
AND a.campCode = b.campCode
AND a.startDate = b.startDate
GROUP BY a.custID, a.bookID, a.campCode, a.startDate
//-----------------------------------------------------------------------------------------------------
CREATE TABLE BOOKING_INFO(
bookID int,
bookDate date
PRIMARY KEY(bookID));

create table TIME_INFO(
startDate date,
t_year int,
t_month int,
t_dayofmonth int,
t_quarter int,
t_week int,
t_dayofyear int,
t_dayofweek int
PRIMARY KEY(startDate));


CREATE TABLE ROOMS(
empno int,
catCode char(1),
category VARCHAR(20)
PRIMARY KEY(empno));


CREATE TABLE CAMP(
campCode char(3),
campName VARCHAR(50)
PRIMARY KEY(campCode));

CREATE TABLE TOURIST_OFFICE(
custID int,
fname VARCHAR(30),
lname VARCHAR(30),
cID int,
country VARCHAR(30)
PRIMARY KEY(custID));

CREATE TABLE RESERVATIONS(
custID int, 
bookID int,
startDate date,
empno int,
campCode char(3),
overnights int,
persons int,
unitCost numeric(4,2)

PRIMARY KEY(custID,bookID,empno,startDate,campCode),
FOREIGN KEY(custID) REFERENCES TOURIST_OFFICE(custID),
FOREIGN KEY(bookID) REFERENCES BOOKING_INFO(bookID),
FOREIGN KEY(empno) REFERENCES ROOMS(empno),
FOREIGN KEY(startDate) REFERENCES TIME_INFO(startDate),
FOREIGN KEY(campCode) REFERENCES CAMP(campCode));

INSERT INTO TOURIST_OFFICE
	SELECT DISTINCT custID, fname, lname, cID, country    
	FROM campdata;
INSERT INTO CAMP
	SELECT DISTINCT campCode, campName
	FROM campdata;
INSERT INTO ROOMS
	SELECT DISTINCT empno, catCode, category
	FROM campdata;
INSERT INTO BOOKING_INFO
	SELECT DISTINCT bookID, bookDate
	FROM campdata;
INSERT INTO RESERVATIONS
	SELECT custID, bookID, startDate, empno, campCode, overnights, persons, unitCost
	FROM campdata;

SET DATEFIRST 1;
INSERT INTO TIME_INFO
SELECT DISTINCT startDate, DATEPART(YEAR, startDate), DATEPART(MONTH, startDate),
DATEPART(DAY,startDate),DATEPART(QUARTER,startDate),
DATEPART(WEEK,startDate),DATEPART(DAYOFYEAR,startDate),
DATEPART(dw,startDate)
FROM campdata;
//-----------------------------------------------------------------------------------------------------------
PARTOU




//--------------------------------------------------------------------------------------------------------1
SELECT TOP 100 country, fname, lname, SUM(unitCost*persons*overnights) AS total_cost
FROM TOURIST_OFFICE, RESERVATIONS
WHERE RESERVATIONS.custID = TOURIST_OFFICE.custID
GROUP BY country, fname, lname
ORDER BY total_cost DESC;
//--------------------------------------------------------------------------------------------------------2

SELECT campName, category, SUM(unitCost*persons*overnights) AS total_cost
FROM RESERVATIONS, CAMP, ROOMS, TIME_INFO
WHERE RESERVATIONS.empno = ROOMS.empno AND RESERVATIONS.campCode = CAMP.campCode AND RESERVATIONS.startDate = TIME_INFO.startDate AND t_year = 2000
GROUP BY campName, category
ORDER BY campName;
//--------------------------------------------------------------------------------------------------------3
SELECT campName,t_month, SUM(unitCost*persons*overnights) AS total_cost
FROM RESERVATIONS, CAMP, ROOMS, TIME_INFO
WHERE RESERVATIONS.empno = ROOMS.empno AND RESERVATIONS.campCode = CAMP.campCode AND RESERVATIONS.startDate = TIME_INFO.startDate AND t_year = 2018
GROUP BY campName, t_month
ORDER BY campName;
//--------------------------------------------------------------------------------------------------------4
SELECT t_year, campName, category, SUM(persons) as total_tenants
FROM RESERVATIONS,TIME_INFO, CAMP, ROOMS
WHERE RESERVATIONS.startDate = TIME_INFO.startDate AND RESERVATIONS.campCode = CAMP.campCode AND RESERVATIONS.empno = ROOMS.empno
GROUP BY ROLLUP(t_year,campName,category);
//--------------------------------------------------------------------------------------------------------5
CREATE VIEW [PER_YEAR_TENANTS] AS
SELECT campName, t_year, SUM(persons) as total_tenants
FROM RESERVATIONS,TIME_INFO, CAMP
WHERE RESERVATIONS.startDate = TIME_INFO.startDate AND RESERVATIONS.campCode = CAMP.campCode
GROUP BY campName, t_year;

SELECT DISTINCT table1.campName
FROM [PER_YEAR_TENANTS] as table1, [PER_YEAR_TENANTS] as table2
WHERE table1.campName = table2.campName AND table1.t_year = 2018 AND table2.t_year = 2017 AND (table1.total_tenants > table2.total_tenants);

//---------------------------------------------------------------------------------------------------------6
SELECT RESERVATIONS.campCode, catCode, t_year, SUM(persons) as total_tenants
FROM RESERVATIONS, CAMP, ROOMS, TIME_INFO
WHERE RESERVATIONS.campCode = CAMP.campCode AND RESERVATIONS.empno = ROOMS.empno AND RESERVATIONS.startDate = TIME_INFO.startDate
GROUP BY CUBE(RESERVATIONS.campCode,catCode,t_year);

SELECT country
FROM TOURIST_OFFICE
