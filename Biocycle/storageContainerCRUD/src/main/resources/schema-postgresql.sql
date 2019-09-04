 /*TRANSACTION START*/
BEGIN TRANSACTION;

DROP TABLE IF EXISTS storage_container_crud.storage_container CASCADE;

CREATE SEQUENCE storage_container_crud.storage_container_id_seq;

CREATE TABLE storage_container_crud.storage_container (
                id INTEGER NOT NULL DEFAULT nextval('storage_container_crud.storage_container_id_seq'),
                row_ref INTEGER NOT NULL,
                shelf_ref VARCHAR NOT NULL,
                level_ref INTEGER NOT NULL,
                is_available BOOLEAN,
                CONSTRAINT storage_container_pk PRIMARY KEY (id)
);


ALTER SEQUENCE storage_container_crud.storage_container_id_seq OWNED BY storage_container_crud.storage_container.id;


ALTER SEQUENCE storage_container_crud.storage_container_id_seq RESTART WITH 31;

/*TRANSACTION END*/
COMMIT;