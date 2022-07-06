--Creating objects
CREATE SCHEMA IF NOT EXISTS auth;

CREATE SCHEMA IF NOT EXISTS exchange_rate;

CREATE TABLE IF NOT EXISTS auth."user"
(
    "id" bigint NOT NULL,
    "username" text NOT NULL,
    "password" text NOT NULL,
    "first_name" text,
    "last_name" text,
    CONSTRAINT "PK_USER" PRIMARY KEY ("id"),
    CONSTRAINT "UN_USERNAME" UNIQUE ("username")
);

COMMENT ON TABLE auth."user"
    IS 'User table for authentication';

CREATE TABLE IF NOT EXISTS auth."role"
(
    "id" bigint NOT NULL,
    "name" text NOT NULL,
    CONSTRAINT "PK_ROLE" PRIMARY KEY ("id"),
    CONSTRAINT "UN_ROLE_NAME" UNIQUE ("name")
);

COMMENT ON TABLE auth."role"
    IS 'Role table for authorization';

CREATE TABLE IF NOT EXISTS auth."user_role"
(
    "user_id" bigint,
    "role_id" bigint,
    CONSTRAINT "PK_USER_ROLE" PRIMARY KEY ("user_id","role_id")
);

COMMENT ON TABLE auth."user_role"
    IS 'User Role table for relation between USER and ROLE';

ALTER TABLE IF EXISTS auth."user_role"
    ADD CONSTRAINT "FK_USER" FOREIGN KEY ("user_id")
    REFERENCES auth."user" ("id") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS auth."user_role"
    ADD CONSTRAINT "FK_ROLE" FOREIGN KEY ("role_id")
    REFERENCES auth."role" ("id") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

--Adding records

INSERT INTO auth."role"(id, name)
VALUES(1, 'ROLE_ADMIN');

INSERT INTO auth."role"(id, name)
VALUES(2, 'ROLE_USER');

INSERT INTO auth."user"(id, username, password, first_name, last_name)
VALUES(1, '12344321', '$2a$10$qIcGJhdTAgVStOIeYiF3quth4qWJoLe51r5IUeyozA6qBhmOo6EpK', 'Gustavo', 'Ramos');

INSERT INTO auth."user_role"(user_id, role_id)
VALUES(1, 1);

INSERT INTO auth."user"(id, username, password, first_name, last_name)
VALUES(2, '12345678', '$2a$10$mAsOAJoQV9lB1SvBMX0XYu9l3LRH4DFmY72fjB1W9DY9orXDkkIyu', 'Andres', 'Montalvo');

INSERT INTO auth."user_role"(user_id, role_id)
VALUES(2, 2);

INSERT INTO auth."user"(id, username, password, first_name, last_name)
VALUES(3, '14784512', '$2a$10$mAsOAJoQV9lB1SvBMX0XYu9l3LRH4DFmY72fjB1W9DY9orXDkkIyu', 'Ronald', 'Gutierrez');

INSERT INTO auth."user_role"(user_id, role_id)
VALUES(3, 2);

COMMIT;