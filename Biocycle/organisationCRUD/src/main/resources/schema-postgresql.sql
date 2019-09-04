 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS organisation_crud.collection_spot_address CASCADE;
DROP TABLE IF EXISTS organisation_crud.organisation CASCADE;
DROP TABLE IF EXISTS organisation_crud.organisation_address_mapping CASCADE;

CREATE SEQUENCE organisation_crud.collection_spot_address_id_seq;

CREATE TABLE organisation_crud.collection_spot_address (
                id INTEGER NOT NULL DEFAULT nextval('organisation_crud.collection_spot_address_id_seq'),
                city VARCHAR,
                post_code VARCHAR,
                spot_name VARCHAR,
                street_name VARCHAR,
                street_number VARCHAR,
                CONSTRAINT collection_spot_address_pk PRIMARY KEY (id)
);


ALTER SEQUENCE organisation_crud.collection_spot_address_id_seq OWNED BY organisation_crud.collection_spot_address.id;

CREATE SEQUENCE organisation_crud.organisation_id_seq;

CREATE TABLE organisation_crud.organisation (
                id INTEGER NOT NULL DEFAULT nextval('organisation_crud.organisation_id_seq'),
                organisation_name VARCHAR,
                email_address VARCHAR,
                password VARCHAR,
                phone_number VARCHAR,
                is_donor BOOLEAN,
                is_validated BOOLEAN NOT NULL,
                street_number VARCHAR,
                street_name VARCHAR,
                city VARCHAR,
                post_code VARCHAR,
                CONSTRAINT organisation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE organisation_crud.organisation_id_seq OWNED BY organisation_crud.organisation.id;

CREATE TABLE organisation_crud.organisation_address_mapping (
                organisation_id INTEGER NOT NULL,
                address_id INTEGER NOT NULL,
                CONSTRAINT organisation_address_mapping_pk PRIMARY KEY (organisation_id, address_id)
);


ALTER TABLE organisation_crud.organisation_address_mapping ADD CONSTRAINT collection_spot_address_organisation_address_mapping_fk
FOREIGN KEY (organisation_id)
REFERENCES organisation_crud.collection_spot_address (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE organisation_crud.organisation_address_mapping ADD CONSTRAINT collection_spot_address_organisation_address_mapping_fk1
FOREIGN KEY (address_id)
REFERENCES organisation_crud.collection_spot_address (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE organisation_crud.organisation_address_mapping ADD CONSTRAINT organisation_organisation_address_mapping_fk
FOREIGN KEY (organisation_id)
REFERENCES organisation_crud.organisation (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER SEQUENCE organisation_crud.collection_spot_address_id_seq RESTART WITH 4;
ALTER SEQUENCE organisation_crud.organisation_id_seq RESTART WITH 3;

/*TRANSACTION END*/
COMMIT;