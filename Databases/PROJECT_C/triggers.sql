CREATE OR REPLACE FUNCTION listing_count()
	RETURNS trigger AS 
$$
BEGIN	
	IF (TG_OP = 'INSERT') THEN 
		UPDATE HOST
		SET listings_count = listings_count + 1
		WHERE NEW.HOST_ID = HOST.ID;
		RETURN NEW;
	END IF;
	IF (TG_OP = 'DELETE') THEN
		UPDATE HOST
		SET listings_count = listings_count - 1
		WHERE OLD.HOST_ID = HOST.ID;
		RETURN OLD;
	END IF;
	RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER lstng_cnt
	AFTER INSERT OR DELETE ON listing
	FOR EACH ROW
	EXECUTE PROCEDURE listing_count();

/* Όταν προστίθεται/διαγράφεται ένα καινούργιο review για ενα airbnb τότε γίνεται ανανέωση στο number_of_reviews του πίνακα listing για το συγκεκριμένο airbnb*/
CREATE OR REPLACE FUNCTION numrev()
	RETURNS trigger AS 
$$
BEGIN	
	IF (TG_OP = 'INSERT') THEN 
		UPDATE LISTING
		SET number_of_reviews = number_of_reviews + 1
		WHERE NEW.listing_id =id ;
		RETURN NEW;
	END IF;
	IF (TG_OP = 'DELETE') THEN
		UPDATE LISTING
		SET number_of_reviews = number_of_reviews - 1
		WHERE OLD.listing_id = id;
		RETURN OLD;
	END IF;
	RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER numrevtrig
	AFTER INSERT OR DELETE ON review
	FOR EACH ROW
	EXECUTE PROCEDURE numrev();








