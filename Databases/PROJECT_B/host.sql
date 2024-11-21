   CREATE TABLE HOST(
   listing_id INT,
   id int,
   url varchar(50),
   name varchar(40),
   since date,
   location varchar(100),
   about text,
   response_time varchar(20),
   response_rate varchar(10),
   acceptance_rate varchar(10),
   is_superhost boolean,
   thumbnail_url varchar(110),
   picture_url varchar(110),
   neighbourhood varchar(30),
   listings_count int,
   total_listings_count int,
   verifications varchar(150),
   has_profile_pic boolean,
   identity_verified boolean,
   calculated_listings_count int
   );
insert into host(listing_id,id,url,name,since,location,about,response_time,response_rate,acceptance_rate,is_superhost,thumbnail_url,picture_url,
neighbourhood,listings_count,total_listings_count,verifications,has_profile_pic,identity_verified,calculated_listings_count)
select id,host_id,host_url,host_name,host_since,host_location,host_about,host_response_time,host_response_rate,host_acceptance_rate,
host_is_superhost,host_thumbnail_url,host_picture_url,host_neighbourhood,host_listings_count,host_total_listings_count,host_verifications,
host_has_profile_pic,host_identity_verified,calculated_host_listings_count from listings;




DELETE FROM CREDITS
WHERE temp IN
    (SELECT temp
    FROM 
        (SELECT temp,
         ROW_NUMBER() OVER( PARTITION BY id
        ORDER BY  id DESC ) AS row_num
        FROM CREDITS ) t
        WHERE t.row_num > 1 );
		
ALTER TABLE LISTINGS
ADD FOREIGN KEY (host_id) PREFERENCES HOST(id);

ALTER TABLE HOST
ADD PRIMARY KEY (id);

ALTER TABLE LISTING DROP COLUMN host_url,DROP COLUMN host_name,DROP COLUMN host_since,DROP COLUMN host_location,DROP COLUMN host_about,DROP COLUMN host_response_time,DROP COLUMN host_response_rate,DROP COLUMN host_acceptance_rate,
DROP COLUMN host_is_superhost,DROP COLUMN host_thumbnail_url,DROP COLUMN host_picture_url,DROP COLUMN host_neighbourhood,DROP COLUMN host_listings_count,DROP COLUMN host_total_listings_count,DROP COLUMN host_verifications,
DROP COLUMN host_has_profile_pic,DROP COLUMN host_identity_verified,DROP COLUMN calculated_host_listings_count; 