// find every airbnb that has 3 or more bedrooms, initial price is less than 400 and return these info along with the cost of extra guests
Output: 938 rows
SELECT room.listing_id, room.bedrooms,price.extra_people
FROM ROOM
LEFT OUTER JOIN PRICE
ON room.listing_id = price.listing_id
WHERE bedrooms >= 3 AND price.price<400;

// find the avg price in every location and group by location
Output: 45 rows
SELECT AVG(price.price), location.neighbourhood_cleansed
FROM PRICE
LEFT OUTER JOIN LOCATION
ON price.listing_id=location.listing_id
GROUP BY location.neighbourhood_cleansed;

// find the airbnbs that demand more than 20 euro security_deposit count them and group them by their neighbourhood
Output: 37 rows
SELECT COUNT(room.security_deposit), neighbourhood_cleansed
FROM ROOM
INNER JOIN LOCATION on location.listing_id = room.listing_id
GROUP BY location.neighbourhood_cleansed
HAVING COUNT(room.security_deposit)> 20;

// find every airbnb location that is inexact
Output: 1707 rows
SELECT * FROM GEOLOCATION
INNER JOIN LOCATION ON LOCATION.neighbourhood_cleansed = GEOLOCATION.properties_neighbourhood
WHERE LOCATION.neighbourhood_cleansed = GEOLOCATION.properties_neighbourhood AND location.is_location_exact = false;

// find every host that has more than 100 airbnb
Output: 17 rows
select host.id,listing.calculated_host_listings_count_entire_homes
from(select host.id, avg(host.listings_count) as calculated_host_listings_count_entire_homes
from host
group by host.id
having avg(host.listings_count)>100)
listing join host on host.id = listing.id;