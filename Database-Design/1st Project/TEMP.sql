set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
select title
from movies
where pyear between 1990 and 2000


checkpoint
dbcc dropcleanbuffers
select pyear,title
from movies 
where pyear between 1990 and 2000


checkpoint
dbcc dropcleanbuffers
select title,pyear  
from movies 
where pyear between 1900 and 2000 order by pyear,title
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
select mid,count(rating)
from user_movies group by mid order by mid

checkpoint
dbcc dropcleanbuffers
select userid,count(rating)
from user_movies group by userid order by userid

CREATE INDEX sec_index ON user_movies(mid,userid) INCLUDE (rating)
DROP INDEX sec_index ON user_movies
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
select title 
from movies,movies_genre 
where movies.mid=movies_genre.mid and genre='Adventure'

UNION

select title 
from movies,movies_genre 
where movies.mid=movies_genre.mid and genre ='Action'

lis

set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT DISTINCT title 
 FROM movies
 INNER JOIN movies_genre ON movies.mid=movies_genre.mid 
WHERE genre='Adventure' OR genre = 'Action'

	
CREATE CLUSTERED INDEX index1 ON movies_genre(genre)
CREATE NONCLUSTERED INDEX index3 ON movies(mid) INCLUDE (title)


//------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT DISTINCT movies.title
FROM movies
INNER JOIN roles on movies.mid = roles.mid
INNER JOIN actors on roles.aid = actors.aid
EXCEPT
SELECT DISTINCT movies.title
FROM movies
INNER JOIN roles on movies.mid = roles.mid
INNER JOIN actors on roles.aid = actors.aid
WHERE(EXISTS(SELECT movies.mid
							FROM actors
							WHERE movies.mid = roles.mid AND roles.aid = actors.aid AND actors.gender ='F'));

CREATE NONCLUSTERED INDEX index1 ON movies(mid) INCLUDE (title)
CREATE NONCLUSTERED INDEX index2 ON actors(gender)
CREATE NONCLUSTERED INDEX index3 ON roles(mid)

set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT movies.title
FROM movies
INNER JOIN roles on movies.mid = roles.mid
INNER JOIN actors on roles.aid = actors.aid
GROUP BY movies.title
HAVING SUM(CASE WHEN actors.gender = 'F' THEN 1 ELSE 0 END)=0;




//επέστρεψε τους τίτλους των ταινιών που έχουν κατηγορία 'Mystery' και βαθμολογία >=4

set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT DISTINCT movies.title
FROM movies
INNER JOIN movies_genre ON movies_genre.mid = movies.mid
INNER JOIN user_movies ON user_movies.mid = movies.mid
WHERE movies_genre.genre = 'Mystery' AND user_movies.rating>=4;

CREATE CLUSTERED INDEX index1 ON movies_genre(genre)
CREATE NONCLUSTERED INDEX index2 ON user_movies(mid)
CREATE NONCLUSTERED INDEX index3 ON movies(mid) INCLUDE (title)

//επέστρεψε τα ονοματεπώνυμα όλων των σκηνοθετών που σκηνοθέτησαν ακριβώς ή περισσότερες από 10 ταινίες στη λίστα


set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT DISTINCT
    d.firstName, d.lastname
FROM
    directors d
WHERE
    d.did IN (SELECT 
            m.did
			FROM
            movie_directors m
			GROUP BY m.did
			HAVING COUNT(m.did) >10)
ORDER BY d.lastname;

CREATE NONCLUSTERED INDEX index1 ON movie_directors(did)
