SELECT listing_id, amenities, COUNT(*)
FROM CONNECTION
GROUP BY listing_id, amenities
HAVING COUNT(*) > 1;

DELETE FROM CONNECTION // σβήνω τα διπλότυπα καθώς και δημιουργούν πρόβλημα στο primary key, αλλά και δεν είναι σωστό να υπάρχει ένας host πάνω από μία φορά
WHERE id IN // βαζω ενα εξτρα column id serial 
    (SELECT id
    FROM 
        (SELECT id,
         ROW_NUMBER() OVER( PARTITION BY listing_id,amenities
        ORDER BY  amenities DESC ) AS row_num
        FROM CONNECTION ) t
        WHERE t.row_num > 1 );
		
