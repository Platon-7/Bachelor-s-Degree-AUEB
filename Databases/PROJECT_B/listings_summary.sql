CREATE TABLE LISTINGS_SUMMARY(
	id INT,
	name CHAR(100),
	host_id INT,
	host_name VARCHAR(40),
	neighbourhood_group VARCHAR(100),
	neighbourhood VARCHAR(100),
	latitude DECIMAL(10,6),
	longitude DECIMAL(10,6),
	room_type VARCHAR(30),
	price INT,
	minimum_nights INT,
	number_of_reviews INT,
	last_review date,
	reviews_per_month DECIMAL(5,2),
	calculated_host_listings_count INT,
	availability_365 INT
	);
	
ALTER TABLE LISTINGS_SUMMARY(
	ADD FOREIGN KEY(id) REFERENCES LISTINGS(id),

	);