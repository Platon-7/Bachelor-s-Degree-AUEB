CREATE FUNCTION incr_host_lstng_count()
	RETURNS trigger AS $hi$
BEGIN
	UPDATE host
	SET listings_count = listings_count+1
	WHERE new.host_id=Host.id
	RETURN NEW;
END;
$hi$ LANGUAGE plpgsql;

CREATE FUNCTION decr_host_lstng_count()
	RETURNS trigger AS $$
BEGIN
	UPDATE host
	SET listings_count = listings_count-1
	WHERE id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_listing_count
	AFTER DELETE ON listing
	FOR EACH ROW
	EXECUTE PROCEDURE decr_host_lstng_count();

CREATE TRIGGER insert_listing_count
	AFTER INSERT ON listing
	FOR EACH ROW
	EXECUTE PROCEDURE incr_host_lstng_count();


CREATE FUNCTION incr_comment()
	RETURNS trigger AS $$
BEGIN 
	UPDATE review
	SET comments = comments+1
	WHERE id = NEW.id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION decr_comment()
	RETURNS trigger AS $$ 
BEGIN 
	UPDATE review
	SET comments = comments-1
	WHERE id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_comment 
	AFTER DELETE ON listing
	FOR EACH ROW
	EXECUTE PROCEDURE decr_comment();

CREATE TRIGGER insert_comment
	AFTER INSERT ON listing
	FOR EACH ROW
	EXECUTE PROCEDURE incr_comment();
	
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
	
	

CREATE TRIGGER lstng_cnt
	AFTER INSERT ON listing
	FOR EACH ROW
	EXECUTE PROCEDURE listing_count();

insert into listing(id,host_id) values (9000,37177);
DELETE FROM LISTING WHERE id='90000' and host_id='31177';

CREATE OR REPLACE FUNCTION guestes()
	RETURNS trigger AS 
$$
BEGIN	
	IF (TG_OP = 'INSERT') THEN 
		UPDATE PRICE
		SET guests_included = guests_included + 1
		WHERE NEW. =listing_id ;
		RETURN NEW;
	END IF;
	IF (TG_OP = 'DELETE') THEN
		UPDATE HOST
		SET guests_included = guests_included - 1
		WHERE OLD.listing_id = listing_id;
		RETURN OLD;
	END IF;
	RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER guestestrig
	AFTER INSERT OR DELETE ON room
	FOR EACH ROW
	EXECUTE PROCEDURE guestes();

INSERT INTO REVIEWS(comments,listing_id,id) VALUES ('it sucks',10995,189305);
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
	AFTER INSERT OR DELETE ON reviews
	FOR EACH ROW
	EXECUTE PROCEDURE numrev();