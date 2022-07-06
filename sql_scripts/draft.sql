CREATE SCHEMA IF NOT EXISTS auth;

CREATE SCHEMA IF NOT EXISTS exchange_rate;

CREATE TABLE IF NOT EXISTS auth."USER"
(
    "ID" bigint NOT NULL,
    "USERNAME" text NOT NULL,
    "PASSWORD" text NOT NULL,
    "FIRST_NAME" text,
    "LAST_NAME" text,
    CONSTRAINT "PK_USER" PRIMARY KEY ("ID"),
    CONSTRAINT "UN_USERNAME" UNIQUE ("USERNAME")
);

COMMENT ON TABLE auth."USER"
    IS 'User table for authentication';

CREATE TABLE IF NOT EXISTS auth."ROLE"
(
    "ID" bigint NOT NULL,
    "NAME" text NOT NULL,
    CONSTRAINT "PK_ROLE" PRIMARY KEY ("ID"),
    CONSTRAINT "UN_ROLE_NAME" UNIQUE ("NAME")
);

COMMENT ON TABLE auth."ROLE"
    IS 'Role table for authorization';

CREATE TABLE IF NOT EXISTS auth."USER_ROLE"
(
    "ID" integer NOT NULL,
    "USER_ID" bigint,
    "ROLE_ID" bigint,
    CONSTRAINT "PK_USER_ROLE" PRIMARY KEY ("ID")
);

COMMENT ON TABLE auth."USER_ROLE"
    IS 'User Role table for relation between USER and ROLE';

ALTER TABLE IF EXISTS auth."USER_ROLE"
    ADD CONSTRAINT "FK_USER" FOREIGN KEY ("USER_ID")
    REFERENCES auth."USER" ("ID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS auth."USER_ROLE"
    ADD CONSTRAINT "FK_ROLE" FOREIGN KEY ("ROLE_ID")
    REFERENCES auth."ROLE" ("ID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


--password $admin2: $2a$10$qIcGJhdTAgVStOIeYiF3quth4qWJoLe51r5IUeyozA6qBhmOo6EpK
--password $user123: $2a$10$mAsOAJoQV9lB1SvBMX0XYu9l3LRH4DFmY72fjB1W9DY9orXDkkIyu
