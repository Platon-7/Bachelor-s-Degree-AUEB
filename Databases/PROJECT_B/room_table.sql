CREATE TABLE ROOM(
    listing_id int,
    accommodates int,
    bathrooms varchar(10),
    bedrooms int,
    beds int,
    bed_type varchar(20),
    amenities varchar(1660),
    square_feet varchar(10),
    price varchar(10),
    weekly_price varchar(10),
    monthly_price varchar(10),
    security_deposit varchar(10)
);

insert into room(listing_id,accommodates,bathrooms,bedrooms,beds,bed_type,amenities,square_feet,price,weekly_price,monthly_price,security_deposit)
select id,accommodates,bathrooms,bedrooms,beds,bed_type,amenities,square_feet,price,weekly_price,monthly_price,security_deposit from listing;

ALTER TABLE ROOM
ADD FOREIGN KEY (listing_id) REFERENCES  LISTINGS (id);

ALTER TABLE ROOM
ADD PRIMARY KEY (listing_id);

ALTER TABLE LISTING
DROP COLUMN accommodates,DROP COLUMN bathrooms,DROP COLUMN bedrooms,DROP COLUMN beds,DROP COLUMN bed_type,DROP COLUMN amenities,DROP COLUMN square_feet,DROP COLUMN price,
DROP COLUMN weekly_price,DROP COLUMN monthly_price,DROP COLUMN security_deposit;