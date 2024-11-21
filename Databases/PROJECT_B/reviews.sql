CREATE TABLE REVIEWS(
	listing_id INT,
	id INT,
	date date,
	reviewer_id INT,
	reviewer_name VARCHAR(50),
	comments text
	);
	
ALTER TABLE REVIEWS(
	ADD PRIMARY KEY(id),
	ADD FOREIGN KEY(listing_id) REFERENCES LISTINGS(id),
	ADD FOREIGN KEY(date) REFERENCES CALENDAR(date),
	);