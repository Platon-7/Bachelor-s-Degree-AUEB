UPDATE 
   CALENDAR
SET 
   price = REPLACE(price,',','');
   
UPDATE 
   CALENDAR
SET 
   price = REPLACE(price,'$','');   
   
ALTER TABLE PRICE ALTER COLUMN CALENDAR TYPE varchar USING (trim(price)::varchar);// για να σβηστούν πιθανά κενά και να μην δημιουργηθεί θέμα με την μετατροπή   
ALTER TABLE PRICE ALTER COLUMN CALENDAR TYPE decimal USING (price::decimal); // αυτή την εντολή για price, adjustable price
