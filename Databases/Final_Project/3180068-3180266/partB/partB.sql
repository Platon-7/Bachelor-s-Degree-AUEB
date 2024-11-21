//----------------------------------------------------------------------------------------------------------------1
SELECT COUNT(id) AS "original_titles", to_char(release_date, 'YYYY') AS "year"
FROM Movies_Metadata 
WHERE release_date IS NOT NULL
GROUP BY "year" 
ORDER BY "year";
//----------------------------------------------------------------------------------------------------------------2
UPDATE 
   Movies_Metadata
SET 
   genres = REPLACE(genres,'''','"');
   
ALTER TABLE Movies_Metadata ALTER COLUMN genres TYPE varchar(500) USING (genres::varchar(500));
   
ALTER TABLE Movies_Metadata ALTER COLUMN genres TYPE json USING (genres::json); 

SELECT
  y.x->'name' "name"
FROM movies_metadata, 
LATERAL (SELECT jsonb_array_elements(movies_metadata.genres) x) y

ALTER TABLE Movies_Metadata
  ALTER COLUMN genres
  SET DATA TYPE jsonb
  USING genres::jsonb;
// δημιουργω ενα query που μου επιστρεφει 2 στηλες, η μια περιεχει μοναδικα τα ειδη ταινιων και η αλλη το αθροισμα εμφανισης αυτων των ειδων συνολικα
SELECT a.name, COUNT(*) AS NUM
FROM(
	SELECT
 	y.x->'name' "name"
	FROM movies_metadata, 
	LATERAL (SELECT jsonb_array_elements(movies_metadata.genres) x) y) a
GROUP BY a.name;
//-------------------------------------------------------------------------------------------------------------------3

// δημιουργω 3 στηλες οπου η 1 εχει το ειδος της ταινιας, η 2 εχει το αθροισμα των ταινιων που εγιναν το συγκεκριμενο ετος και η 3 αναγραφει το ετος
SELECT   y.x->'name' "name", COUNT(id) AS "sum", to_char(release_date, 'YYYY') AS "year"
FROM Movies_Metadata,
LATERAL (SELECT jsonb_array_elements(movies_metadata.genres) x) y
WHERE release_date IS NOT NULL
GROUP BY "year",name
ORDER BY "year";


//----------------------------------------------------------------------------------------------------------------4

// δημιουργω ενα selection οπου μου δινει 1 column με τα ειδη των ταινιων (μοναδικα παντα) και 1 column με τον μεσο ορο rating του καθε ειδους
SELECT
 	y.x->'name' "name", AVG(rating)
FROM Movies_Metadata
INNER JOIN Ratings ON Movies_metadata.id = Ratings.movieID,
LATERAL (SELECT jsonb_array_elements(movies_metadata.genres) x) y
GROUP BY name;

	
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
total_ratings int,
average_rating numeric
);
INSERT INTO VIEW_Table(userID,total_ratings,average_rating)
SELECT userID, COUNT(*) AS NUM, AVG(rating)
FROM Ratings
GROUP BY userID;
// μέσω αυτού του πίνακα και του διαγράμματος του, συμπεραίνουμε ότι όσο πιο έμπειρος γίνεται ένας reviewer μικραίνει η διασπορά των Ratings του 
// επισης, οι ταινίες που εκρινε συγκλινουν προς τις μεσαιες τιμες μεταξυ 3-4