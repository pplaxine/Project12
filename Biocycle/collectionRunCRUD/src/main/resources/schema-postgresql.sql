 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS collection_run_crud.collection_run CASCADE;
DROP TABLE IF EXISTS collection_run_crud.collection_run_container_mapping CASCADE;

CREATE SEQUENCE collection_run_crud.collection_run_id_seq;

CREATE TABLE collection_run_crud.collection_run (
                id INTEGER NOT NULL DEFAULT nextval('collection_run_crud.collection_run_id_seq'),
                collection_run_date TIMESTAMP NOT NULL,
                collector_id INTEGER,
                global_estimated_time TIME,
                start_time TIMESTAMP,
                end_time TIMESTAMP,
                CONSTRAINT collection_run_pk PRIMARY KEY (id)
);


ALTER SEQUENCE collection_run_crud.collection_run_id_seq OWNED BY collection_run_crud.collection_run.id;

CREATE TABLE collection_run_crud.collection_run_container_mapping (
                collection_run_id INTEGER NOT NULL,
                container_id INTEGER NOT NULL,
                CONSTRAINT collection_run_container_mapping_pk PRIMARY KEY (collection_run_id, container_id)
);


ALTER TABLE collection_run_crud.collection_run_container_mapping ADD CONSTRAINT collection_run_collection_run_container_mapping_fk
FOREIGN KEY (collection_run_id)
REFERENCES collection_run_crud.collection_run (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER SEQUENCE collection_run_crud.collection_run_id_seq RESTART WITH 5;

/*TRANSACTION END*/
COMMIT;