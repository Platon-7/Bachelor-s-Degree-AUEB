/* FIND EVERY REVIEW IN ΑΜΠΕΛΟΚΗΠΟΙ IN 2019 AND ORDER BY DATE 
6147 OUTPUTS */
SELECT REVIEWS.id, REVIEWS.date,REVIEWS.comments
FROM REVIEWS
INNER JOIN LISTINGS ON REVIEWS.listing_id=LISTINGS.id
WHERE REVIEWS.date BETWEEN '2019-01-01' AND '2019-12-31' AND LISTINGS.neighbourhood='Ambelokipi'
ORDER BY REVIEWS.date;


/* FIND EVERY AIRBNB IN ΚΟΛΩΝΑΚΙ THAT IS FREE BETWEEN 2020-06-01 TO 2020-08-31 AND ORDER BY DATE*/
SELECT DISTINCT CALENDAR.date, LISTINGS.id
FROM CALENDAR
LEFT OUTER JOIN LISTINGS ON CALENDAR.listing_id=LISTINGS.id
WHERE CALENDAR.available='t' AND CALENDAR.date BETWEEN '2020-06-01' AND '2020-08-31' AND LISTINGS.neighbourhood_cleansed='ΚΟΛΩΝΑΚΙ'
ORDER BY CALENDAR.date;

/*FIND THE 10 MOST EXPENSIVE AIRBNB'S WITH A PRICE>300 THAT HAD A REVIEW IN 2018*/
SELECT REVIEWS_SUMMARY.listing_id, REVIEWS_SUMMARY.date, LISTINGS_SUMMARY.price
FROM REVIEWS_SUMMARY
INNER JOIN LISTINGS_SUMMARY ON REVIEWS_SUMMARY.listing_id=LISTINGS_SUMMARY.id
WHERE REVIEWS_SUMMARY.date BETWEEN '2018-01-01' AND '2018-12-31'
ORDER BY LISTINGS_SUMMARY.price DESC
LIMIT 10;

/*FIND THE AIRBNBS IN AKROPOLI THAT HAVE WIFI, CAN ACCOMODATE MORE THAN 6 GUESTS AND HAVE AT LEAST ONE FREE DATE DURING SUMMER 2020*/
SELECT DISTINCT LISTINGS.id
FROM CALENDAR
INNER JOIN LISTINGS ON CALENDAR.listing_id=LISTINGS.id
WHERE CALENDAR.available='t' AND CALENDAR.date BETWEEN '2020-06-15' AND '2020-08-31' AND LISTINGS.neighbourhood_cleansed='ΑΚΡΟΠΟΛΗ'AND LISTINGS.guests_included>6
AND LISTINGS.amenities LIKE '%Internet%' OR LISTINGS.amenities LIKE '%Wifi%';

/*FIND AIRBNBS THAT ALLOW PARTIES, HAVE VACANCIES DURING SUMMER 2020 AND ALLOW MORE THAN 4 GUESTS */
SELECT LISTINGS.neighbourhood_cleansed, LISTINGS.price, LISTINGS.guests_included
FROM CALENDAR
INNER JOIN LISTINGS ON CALENDAR.listing_id=LISTINGS.id
WHERE CALENDAR.available='t' AND CALENDAR.date BETWEEN '2020-06-15' AND '2020-08-31' AND LISTINGS.house_rules NOT LIKE '%Parties%'
AND LISTINGS.description LIKE '%balcony%' ANDs LISTINGS.guests_included>4
ORDER BY neighbourhood_cleansed,guests_included;

/*FIND THE COORDINATES OF THE NEIGHBOURHOOD 'ΝΕΟΣ ΚΟΣΜΟΣ' */
SELECT* FROM GEOLOCATION
INNER JOIN NEIGHBOURHOODS ON NEIGHBOURHOODS.neighbourhood = GEOLOCATION.properties_neighbourhood
WHERE NEIGHBOURHOODS.neighbourhood = 'ΝΕΟΣ ΚΟΣΜΟΣ';

/* FIND THE MINIMUM AIRBNB PRICE IN 'ΝΕΟΣ ΚΟΣΜΟΣ' DURING THE 2020 EASTER*/
SELECT MIN(LISTINGS_SUMMARY.price)
FROM LISTINGS_SUMMARY
INNER JOIN CALENDAR ON CALENDAR.listing_id=LISTINGS_SUMMARY.id
WHERE LISTINGS_SUMMARY.neighbourhood = 'ΝΕΟΣ ΚΟΣΜΟΣ' AND CALENDAR.date BETWEEN '2020-04-17' AND '2020-04-22' AND CALENDAR.available='t';

/* FIND THE HIGHEST RATED AIRBNB'S DURING 2020 CHRISTMAS IN 'ΚΟΛΩΝΑΚΙ'  */
SELECT LISTINGS.id, LISTINGS.listing_url
FROM CALENDAR
INNER JOIN LISTINGS ON LISTINGS.id=CALENDAR.listing_id
WHERE CALENDAR.date BETWEEN '2020-12-15' AND '2021-01-10'AND CALENDAR.available='t' AND LISTINGS.neighbourhood_cleansed='ΚΟΛΩΝΑΚΙ' AND LISTINGS.review_scores_rating='100';

/*FIND THE MAXIMUM NUMBER OF REVIEWS FOR AN AIRBNB */
SELECT ΜΑΧ(LISTINGS.number_of_reviews)
FROM LISTINGS
WHERE LISTINGS.neighbourhood='ΑΚΡΟΠΟΛΗ';

/*FIND EVERY AIRBNB IN 'ΓΚΑΖΙ' IN A SPECIFIC DATE THAT HAS 9 OR 10 CLEANLINESS RATING AND A SUPERHOST */
SELECT DISTINCT id,neighbourhood_cleansed
FROM listings
INNER JOIN calendar on calendar.listing_id = listings.id
WHERE host_is_superhost= true AND CAST(review_scores_cleanliness AS INT) >=9 AND calendar.date BETWEEN '2020-05-08' AND '2020-05-10' AND (neighbourhood_cleansed='ΚΕΡΑΜΕΙΚΟΣ' OR  neighbourhood_cleansed='ΓΚΑΖΙ')
AND CALENDAR.available='t';

/* FIND THE AVERAGE RATING IN ALL AIRBNB'S IN 'ΑΚΡΟΠΟΛΗ'*/
SELECT AVG(CAST (LISTINGS.review_scores_rating AS INT))
FROM LISTINGS
WHERE LISTINGS.neighbourhood_cleansed='ΑΚΡΟΠΟΛΗ';

/* RETURN THE URL OF THE LISTING THAT MEETS MY CRITERIA */
SELECT DISTINCT LISTINGS.listing_url
FROM LISTINGS
LEFT OUTER JOIN CALENDAR ON CALENDAR.listing_id = LISTINGS.id
WHERE LISTINGS.cancellation_policy = 'flexible' AND LISTINGS.require_guest_phone_verification = false AND LISTINGS.require_guest_profile_picture = 'false' AND LISTINGS.neighbourhood_cleansed = 'ΠΕΤΡΑΛΩΝΑ' AND CALENDAR.date BETWEEN '2020-08-10' AND '2020-08-17';