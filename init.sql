-- SEQUENCE: example."DocumentData_DocumentData_id_seq"

-- DROP SEQUENCE example."DocumentData_DocumentData_id_seq";

CREATE SEQUENCE example."DocumentData_DocumentData_id_seq"
    INCREMENT 1
    START 74
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE example."DocumentData_DocumentData_id_seq"
    OWNER TO postgres;

-- Table: example."DocumentData"

-- DROP TABLE example."DocumentData";

CREATE TABLE example."DocumentData"
(
    "DocumentData_id" integer NOT NULL DEFAULT nextval('example."DocumentData_DocumentData_id_seq"'::regclass),
    "DocumentData_data" text COLLATE pg_catalog."default" DEFAULT 'default data'::text,
    CONSTRAINT pk_documentdata_id PRIMARY KEY ("DocumentData_id"),
    CONSTRAINT notnull_documentdata_id CHECK ("DocumentData_id" IS NOT NULL),
    CONSTRAINT notnull_documentdata_data CHECK ("DocumentData_data" IS NOT NULL)
)

TABLESPACE pg_default;

ALTER TABLE example."DocumentData"
    OWNER to postgres;