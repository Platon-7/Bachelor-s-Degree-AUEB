CREATE TABLE PRICE(
	listing_id INT,
	price varchar(10),
	weekly_price varchar(10),
	monthly_price varchar(10),
	security_deposit varchar(10),
	cleaning_fee varchar(10),
	guests_included int,
	extra_people varchar(10),
	minimum_nights int,
	maximum_nights int,
	minimum_minimum_nights int,
	maximum_minimum_nights int,
	minimum_maximum_nights int,
	maximum_maximum_nights int,
	minimum_nights_avg_ntm varchar(10),
	maximum_nights_avg_ntm varchar(10)
	);

	


INSERT INTO PRICE(
listing_id, price, weekly_price, monthly_price, security_deposit, cleaning_fee, guests_included, extra_people, minimum_nights, maximum_nights,
minimum_minimum_nights, maximum_minimum_nights,minimum_maximum_nights, maximum_maximum_nights, minimum_nights_avg_ntm, maximum_nights_avg_ntm)
SELECT id, price, weekly_price, monthly_price, security_deposit, cleaning_fee, guests_included, extra_people, minimum_nights, maximum_nights,
minimum_minimum_nights, maximum_minimum_nights,minimum_maximum_nights, maximum_maximum_nights, minimum_nights_avg_ntm, maximum_nights_avg_ntm
FROM LISTINGS;

ALTER TABLE PRICE
ADD PRIMARY KEY (listing_id)

ALTER TABLE PRICE
ADD FOREIGN KEY (listing_id) REFERENCES LISTINGS(id);

UPDATE 
   PRICE
SET 
   price = REPLACE(price,',','');
   
UPDATE 
   PRICE
SET 
   price = REPLACE(price,'$','');   

   
ALTER TABLE PRICE ALTER COLUMN price TYPE varchar USING (trim(price)::varchar);// για να σβηστούν πιθανά κενά και να μην δημιουργηθεί θέμα με την μετατροπή   
ALTER TABLE PRICE ALTER COLUMN price TYPE decimal USING (price::decimal); // αυτή την εντολή για price, weekly_price, monthly_price, security_deposit, cleaning_fee, extra_people
ALTER TABLE PRICE ALTER COLUMN price TYPE real USING (minimum_nights_avg_ntm::real); //αυτή την εντολή για maximum_nights_avg_ntm, minimum_nights_avg_ntm

ALTER TABLE LISTING
DROP COLUMN cleaning_fee,DROP COLUMN guests_included,DROP COLUMN extra_people,DROP COLUMN minimum_nights,DROP COLUMN maximum_nights,
DROP COLUMN minimum_minimum_nights,DROP COLUMN maximum_minimum_nights,DROP COLUMN minimum_maximum_nights,DROP COLUMN maximum_maximum_nights,DROP COLUMN minimum_nights_avg_ntm,DROP COLUMN maximum_nights_avg_ntm;