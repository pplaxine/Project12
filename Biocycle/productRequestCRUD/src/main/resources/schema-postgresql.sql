 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS product_request_crud.product_request CASCADE;

CREATE SEQUENCE product_request_crud.product_request_id_seq;

CREATE TABLE product_request_crud.product_request (
                id INTEGER NOT NULL DEFAULT nextval('product_request_crud.product_request_id_seq'),
                product_requested VARCHAR NOT NULL,
                quantity NUMERIC NOT NULL,
                unit_of_measure VARCHAR NOT NULL,
                is_accepted BOOLEAN,
                CONSTRAINT product_request_pk PRIMARY KEY (id)
);


ALTER SEQUENCE product_request_crud.product_request_id_seq OWNED BY product_request_crud.product_request.id;

ALTER SEQUENCE product_request_crud.product_request_id_seq RESTART WITH 5;

/*TRANSACTION END*/
COMMIT;