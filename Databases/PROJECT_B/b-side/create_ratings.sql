create table Ratings(
   userId int,
   movieId int,
   rating varchar(10),
   timestamp int
);
//----------------------------------------------------------------------------------------------------------------1
SELECT COUNT(id) AS "original_titles", to_char(release_date, 'YYYY') AS "year"
FROM Movies_Metadata 
WHERE release_date IS NOT NULL
GROUP BY "year" 
ORDER BY "year";
//----------------------------------------------------------------------------------------------------------------2
CREATE TABLE GENRE(
id int,
name varchar,
genre varchar);

'id' : tade , 'name' : tade 

INSERT INTO GENRE(col1,col2,col3,col4)
SELECT split_part(genres, ',', 1)
     , split_part(genres, ',', 2)
     , split_part(genres, ',', 3)
     , split_part(genres, ',', 4)
FROM  Movies_Metadata;
regexp_split_to_table(), regexp_replace()
// ----------------------------------------------------------------------------------------------------------------5
// βρισκω ετσι ενα selection με 1 column με τα userID και 1 column με τα συνολικα Ratings που εκανε ο εκαστοτε user
SELECT userID, COUNT(*) AS NUM 
FROM Ratings
GROUP BY userID; 

/*SELECT AVG(a.num) FROM
(SELECT userID, COUNT(*) AS NUM 
FROM RATINGS r
group by userid) a;*/ // εδω γίνεται ο συνολικος μεσος ορος ratings (43.008) ο οποιος τελικα υστερα απο αλλαγη της εκφωνησης δεν χρειαστηκε
//----------------------------------------------------------------------------------------------------------------------6
// δημιουργειται ενα selection με το id του user και το μεσο Rating που υπολογιστηκε απο τα συνολικα του ratings 
SELECT userID, AVG(rating)
FROM RATINGS
GROUP BY userID;

// δημιουργία του view_table 
CREATE TABLE View_Table(
userID int,
total_ratings int, totalratings,
average_rating numeric
);
INSERT INTO VIEW_Table(userID,total_ratings,average_rating)
SELECT userID, COUNT(*) AS NUM, AVG(rating)
FROM Ratings
GROUP BY userID;
// μέσω αυτού του πίνακα και του διαγράμματος του, συμπεραίνουμε ότι όσο πιο έμπειρος γίνεται ένας reviewer μικραίνει η διασπορά των Ratings του 
// επισης, οι ταινίες που εκρινε συγκλινουν προς τις μεσαιες τιμες μεταξυ 3-4