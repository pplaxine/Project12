 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS redistribution_crud.redistribution CASCADE;
DROP TABLE IF EXISTS redistribution_crud.redistribution_product_request_mapping CASCADE;

CREATE SEQUENCE redistribution_crud.redistribution_id_seq;

CREATE TABLE redistribution_crud.redistribution (
                id INTEGER NOT NULL DEFAULT nextval('redistribution_crud.redistribution_id_seq'),
                organisation_id INTEGER NOT NULL,
                offer_id INTEGER,
                is_completed BOOLEAN,
                CONSTRAINT redistribution_pk PRIMARY KEY (id)
);


ALTER SEQUENCE redistribution_crud.redistribution_id_seq OWNED BY redistribution_crud.redistribution.id;

CREATE TABLE redistribution_crud.redistribution_product_request_mapping (
                redistribution_id INTEGER NOT NULL,
                product_request_id INTEGER NOT NULL,
                CONSTRAINT redistribution_product_request_mapping_pk PRIMARY KEY (redistribution_id, product_request_id)
);


ALTER TABLE redistribution_crud.redistribution_product_request_mapping ADD CONSTRAINT redistribution_redistribution_product_request_mapping_fk
FOREIGN KEY (redistribution_id)
REFERENCES redistribution_crud.redistribution (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER SEQUENCE redistribution_crud.redistribution_id_seq RESTART WITH 5;

/*TRANSACTION END*/
COMMIT;