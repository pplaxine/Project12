 /*TRANSACTION START*/
BEGIN TRANSACTION;
DROP TABLE IF EXISTS staff_crud.staff CASCADE;

CREATE SEQUENCE staff_crud.staff_id_seq;

CREATE TABLE staff_crud.staff (
                id INTEGER NOT NULL DEFAULT nextval('staff_crud.staff_id_seq'),
                user_name VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                name VARCHAR NOT NULL,
                surname VARCHAR NOT NULL,
                role VARCHAR NOT NULL,
                access VARCHAR NOT NULL,
                CONSTRAINT staff_pk PRIMARY KEY (id)
);


ALTER SEQUENCE staff_crud.staff_id_seq OWNED BY staff_crud.staff.id;
ALTER SEQUENCE staff_crud.staff_id_seq RESTART WITH 9;

/*TRANSACTION END*/
COMMIT;