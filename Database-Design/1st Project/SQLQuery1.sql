set statistics io on
set statistics time on
checkpoint
dbcc dropcleanbuffers
SELECT movies.title
FROM movies
INNER JOIN roles on movies.mid = roles.mid
INNER JOIN actors on roles.aid = actors.aid
WHERE movies.mid