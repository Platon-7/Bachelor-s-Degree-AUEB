QUERY ΕΚΦΩΝΗΣΗΣ 1 
CREATE INDEX ekquery
ON HOST(id);

CREATE INDEX ekquery2
ON LISTING(host_id);

QUERY ΕΚΦΩΝΗΣΗΣ 2
CREATE INDEX ek2query
ON LISTING(guests_included);

CREATE INDEX ek2query2
ON PRICE(price);
QUERY 1
CREATE INDEX query1a
ON ROOM (bedrooms);

CREATE INDEX query1b 
ON PRICE (price); 

QUERY 2
CREATE INDEX query2b
ON LOCATION(neighbourhood_cleansed);

CREATE INDEX query2a
ON PRICE(price);

QUERY 3
CREATE INDEX query3a
ON ROOM(security_deposit);

CREATE INDEX query3b
ON LOCATION(neighbourhood_cleansed);

QUERY 4
CREATE INDEX query4a
ON LOCATION(is_location_exact);

QUERY 5
CREATE INDEX query5a
ON HOST(id,listings_count);