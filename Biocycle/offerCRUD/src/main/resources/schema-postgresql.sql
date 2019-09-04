 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS offer_crud.offer CASCADE;
DROP TABLE IF EXISTS offer_crud.offer_product_batch_mapping CASCADE;

CREATE SEQUENCE offer_crud.offer_id_seq;

CREATE TABLE offer_crud.offer (
                id INTEGER NOT NULL DEFAULT nextval('offer_crud.offer_id_seq'),
                available_for_collection TIMESTAMP NOT NULL,
                offer_ending_date TIMESTAMP NOT NULL,
                is_accepted BOOLEAN,
                CONSTRAINT offer_pk PRIMARY KEY (id)
);


ALTER SEQUENCE offer_crud.offer_id_seq OWNED BY offer_crud.offer.id;

CREATE TABLE offer_crud.offer_product_batch_mapping (
                offer_id INTEGER NOT NULL,
                product_batch_id INTEGER NOT NULL,
                CONSTRAINT offer_product_batch_mapping_pk PRIMARY KEY (offer_id, product_batch_id)
);


ALTER TABLE offer_crud.offer_product_batch_mapping ADD CONSTRAINT offer_offer_product_batch_mapping_fk
FOREIGN KEY (offer_id)
REFERENCES offer_crud.offer (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER SEQUENCE offer_crud.offer_id_seq RESTART WITH 5;


/*TRANSACTION END*/
COMMIT;