create table Links(
   movieId int,
   imdbId int,
   tmdbId int
);
create table Credits(
   thecast text,
   crew text,
   id int
);
create table Keywords(
   id int,
   keywords text
);
create table Movies_Metadata(
   adult varchar(10),
   belongs_to_collection varchar(190),
   budget int,
   genres varchar(270),
   homepage varchar(250),
   id int,
   imdb_id varchar(10),
   original_language varchar(10),
   original_title varchar(110),
   overview varchar(1000),
   popularity varchar(10),
   poster_path varchar(40),
   production_companies varchar(1260),
   production_countries varchar(1040),
   release_date date,
   revenue bigint,
   runtime varchar(10),
   spoken_languages varchar(770),
   status varchar(20),
   tagline varchar(300),
   title varchar(110),
   video varchar(10),
   vote_average varchar(10),
   vote_count int
);
create table Ratings(
   userId int,
   movieId int,
   rating varchar(10),
   timestamp int
);
//------------------------------------------LINKS-------------------------------------
ALTER TABLE Links
ADD COLUMN ids SERIAL;

DELETE FROM Links 
WHERE ids IN
    (SELECT ids
    FROM 
        (SELECT ids,
         ROW_NUMBER() OVER( PARTITION BY tmdbid
        ORDER BY  tmdbid DESC ) AS row_num
        FROM Links ) t
        WHERE t.row_num > 1 );
		
ALTER TABLE Links
DROP COLUMN ids;

DELETE FROM Links
 WHERE tmdbID NOT IN (SELECT f.id 
                        FROM Movies_Metadata f);
						
DELETE FROM Links WHERE tmdb IS NULL;

ALTER TABLE Links
ADD PRIMARY KEY(tmdbid);

ALTER TABLE Links
ADD FOREIGN KEY(tmdbid) REFERENCES Movies_Metadata(id);	
//------------------------------------------CREDITS---------------------------------------------
ALTER TABLE Credits
ADD COLUMN ids SERIAL;

DELETE FROM Credits 
WHERE ids IN
    (SELECT ids
    FROM 
        (SELECT ids,
         ROW_NUMBER() OVER( PARTITION BY id
        ORDER BY  id DESC ) AS row_num
        FROM Credits ) t
        WHERE t.row_num > 1 );
		
ALTER TABLE Credits
DROP COLUMN ids;

DELETE FROM Credits
 WHERE id NOT IN (SELECT f.id 
                        FROM Movies_Metadata f);

ALTER TABLE Credits
ADD PRIMARY KEY(id);

ALTER TABLE Credits
ADD FOREIGN KEY(id) REFERENCES Movies_Metadata(id);	
//---------------------------------------------KEYWORDS-----------------------------------------------
ALTER TABLE Keywords
ADD COLUMN ids SERIAL;

DELETE FROM Keywords
WHERE ids IN
    (SELECT ids
    FROM 
        (SELECT ids,
         ROW_NUMBER() OVER( PARTITION BY id
        ORDER BY  id DESC ) AS row_num
        FROM Keywords ) t
        WHERE t.row_num > 1 );
		
ALTER TABLE Keywords
DROP COLUMN ids;

DELETE FROM Keywords
 WHERE id NOT IN (SELECT f.id 
                        FROM Movies_Metadata f);

ALTER TABLE Keywords
ADD PRIMARY KEY(id);

ALTER TABLE Keywords
ADD FOREIGN KEY(id) REFERENCES Movies_Metadata(id);
//-------------------------------------------------RATINGS------------------------------------------------

DELETE FROM Ratings
 WHERE movieid NOT IN (SELECT f.id 
                        FROM Movies_Metadata f);

ALTER TABLE Ratings
ADD FOREIGN KEY(movieid) REFERENCES Movies_Metadata(id);
//--------------------------------------------------MOVIES_METADATA-----------------------------------------
ALTER TABLE Movies_Metadata
ADD COLUMN ids SERIAL;

DELETE FROM Movies_Metadata
WHERE ids IN
    (SELECT ids
    FROM 
        (SELECT ids,
         ROW_NUMBER() OVER( PARTITION BY id
        ORDER BY  id DESC ) AS row_num
        FROM Movies_Metadata ) t
        WHERE t.row_num > 1 );
		
ALTER TABLE Movies_Metadata
DROP COLUMN ids;

ALTER TABLE MOVIES_METADATA
ADD PRIMARY KEY(id);