 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS give_away_crud.give_away CASCADE;
DROP TABLE IF EXISTS give_away_crud.container CASCADE;


CREATE SEQUENCE give_away_crud.give_away_id_seq;

CREATE TABLE give_away_crud.give_away (
                id INTEGER NOT NULL DEFAULT nextval('give_away_crud.give_away_id_seq'),
                organisation_id INTEGER NOT NULL,
                available_to_be_collected_from TIMESTAMP NOT NULL,
                collection_date TIMESTAMP,
                is_collected BOOLEAN,
                spot_name VARCHAR NOT NULL,
                street_number VARCHAR NOT NULL,
                street_name VARCHAR NOT NULL,
                city VARCHAR NOT NULL,
                post_code VARCHAR NOT NULL,
                CONSTRAINT give_away_pk PRIMARY KEY (id)
);


ALTER SEQUENCE give_away_crud.give_away_id_seq OWNED BY give_away_crud.give_away.id;

CREATE SEQUENCE give_away_crud.container_id_seq;

CREATE TABLE give_away_crud.container (
                id INTEGER NOT NULL DEFAULT nextval('give_away_crud.container_id_seq'),
                description VARCHAR NOT NULL,
                accepted BOOLEAN,
                collection_run_id INTEGER,
                is_collected BOOLEAN,
                give_away_id INTEGER,
                CONSTRAINT container_pk PRIMARY KEY (id)
);


ALTER SEQUENCE give_away_crud.container_id_seq OWNED BY give_away_crud.container.id;

ALTER TABLE give_away_crud.container ADD CONSTRAINT give_away_container_fk
FOREIGN KEY (give_away_id)
REFERENCES give_away_crud.give_away (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER SEQUENCE give_away_crud.give_away_id_seq RESTART WITH 4;
ALTER SEQUENCE give_away_crud.container_id_seq RESTART WITH 5;

/*TRANSACTION END*/
COMMIT;