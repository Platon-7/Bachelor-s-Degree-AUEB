BULK INSERT actors
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\actors.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT directors
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\directors.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT movies
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\movies.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT movie_directors
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\movie_directors.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT movies_genre
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\movies_genre.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT roles
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\roles.txt'
WITH ( FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT users
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\users.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

BULK INSERT user_movies
 FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\user_movies.txt'
WITH (FIRSTROW =2, FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');

