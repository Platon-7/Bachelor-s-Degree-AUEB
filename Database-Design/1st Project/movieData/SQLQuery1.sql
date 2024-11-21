BULK INSERT actors
FROM 'C:\Users\Platon\Desktop\OPA\DATABASES\1st Project\movieData\actors.txt'
WITH(FIRSTROW=2,FIELDTERMINATOR= '|', ROWTERMINATOR = '\n');