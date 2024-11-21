CREATE TABLE LOCATION(
    listing_id int,
    street varchar(70),
    neighbourhood varchar(20),
    neighbourhood_cleansed varchar(40),
    city varchar(40),
    state varchar(40),
    zipcode varchar(20),
    market varchar(30),
    smart_location varchar(40),
    country_code varchar(10),
    country varchar(10),
    latitude varchar(10),
    longitude varchar(10),
    is_location_exact boolean
);

insert into location(listing_id,street,neighbourhood,neighbourhood_cleansed,city,state,zipcode,market,smart_location,country_code,country,latitude,longitude,is_location_exact)
select id,street,neighbourhood,neighbourhood_cleansed,city,state,zipcode,market,smart_location,country_code,country,latitude,longitude,is_location_exact from listing;

ALTER TABLE LOCATION
ADD PRIMARY KEY(listing_id);

ALTER TABLE LOCATION
ADD FOREIGN KEY(listing_id) REFERENCES LISTINGS(id);

ALTER TABLE LISTING
DROP COLUMN street,DROP COLUMN neighbourhood,DROP COLUMN neighbourhood_cleansed,DROP COLUMN city,DROP COLUMN state,DROP COLUMN zipcode,
DROP COLUMN market,DROP COLUMN smart_location,DROP COLUMN country_code,DROP COLUMN country,DROP COLUMN latitude,DROP COLUMN longitude,DROP COLUMN is_location_exact;