set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT DISTINCT movies.title
FROM movies
INNER JOIN roles on movies.mid = roles.mid
INNER JOIN actors on roles.aid = actors.aid
GROUP BY movies.title
HAVING SUM(CASE WHEN actors.gender = 'F' THEN 1 ELSE 0 END)=0;