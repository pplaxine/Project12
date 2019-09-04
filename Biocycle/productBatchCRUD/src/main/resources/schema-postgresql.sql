 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS product_batch_crud.product_batch CASCADE;
DROP TABLE IF EXISTS product_batch_crud.product_batch_storage_container_mapping CASCADE;


CREATE SEQUENCE product_batch_crud.product_batch_id_seq;

CREATE TABLE product_batch_crud.product_batch (
                id INTEGER NOT NULL DEFAULT nextval('product_batch_crud.product_batch_id_seq'),
                name VARCHAR NOT NULL,
                description VARCHAR,
                donor_id INTEGER NOT NULL,
                date_of_collection TIMESTAMP NOT NULL,
                to_be_used_by TIMESTAMP NOT NULL,
                quantity NUMERIC NOT NULL,
                unit_of_measure VARCHAR NOT NULL,
                is_available BOOLEAN,
                is_awaiting_for_collection BOOLEAN,
                CONSTRAINT product_batch_pk PRIMARY KEY (id)
);


ALTER SEQUENCE product_batch_crud.product_batch_id_seq OWNED BY product_batch_crud.product_batch.id;

CREATE TABLE product_batch_crud.product_batch_storage_container_mapping (
                product_batch_id INTEGER NOT NULL,
                storage_container_id INTEGER NOT NULL,
                CONSTRAINT product_batch_storage_container_mapping_pk PRIMARY KEY (product_batch_id, storage_container_id)
);


ALTER TABLE product_batch_crud.product_batch_storage_container_mapping ADD CONSTRAINT product_batch_product_batch_storage_container_mapping_fk
FOREIGN KEY (product_batch_id)
REFERENCES product_batch_crud.product_batch (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER SEQUENCE product_batch_crud.product_batch_id_seq RESTART WITH 6;


/*TRANSACTION END*/
COMMIT;