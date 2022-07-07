--Creating objects

CREATE SCHEMA IF NOT EXISTS auth;

CREATE SCHEMA IF NOT EXISTS exchange_rate;

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS auth."user"
(
    id bigint NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    first_name text,
    last_name text,
    CONSTRAINT "PK_USER" PRIMARY KEY (id),
    CONSTRAINT "UN_USERNAME" UNIQUE (username)
);

COMMENT ON TABLE auth."user"
    IS 'User table for authentication';

CREATE SEQUENCE IF NOT EXISTS auth.seq_user INCREMENT 2 START 1
    OWNED BY auth.user.id;

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS auth.role
(
    id bigint NOT NULL,
    name text NOT NULL,
    CONSTRAINT "PK_ROLE" PRIMARY KEY (id),
    CONSTRAINT "UN_ROLE_NAME" UNIQUE (name)
);

COMMENT ON TABLE auth."role"
    IS 'Role table for authorization';

CREATE SEQUENCE IF NOT EXISTS auth.seq_role INCREMENT 2 START 1
    OWNED BY auth.role.id;

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS auth.user_role
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT "PK_USER_ROLE" PRIMARY KEY (user_id, role_id)
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

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS exchange_rate.currency
(
    id integer NOT NULL,
    codigo text,
    etiqueta text,
    fecha date,
    CONSTRAINT "PK_CURRENCY" PRIMARY KEY (id)
);

COMMENT ON TABLE exchange_rate.currency
    IS 'Currency table for exchange rate';
    
CREATE SEQUENCE IF NOT EXISTS exchange_rate.seq_currency INCREMENT 2 START 1
    OWNED BY exchange_rate.currency.id;

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS exchange_rate.exchange_type
(
    id bigint NOT NULL,
    id_local_currency integer,
    id_foreign_currency integer,
    purchase_value numeric,
    sales_value numeric,
    creation_date date,
    update_date date,
    CONSTRAINT "PK_EXCHANGE_TYPE" PRIMARY KEY (id_local_currency)
);

COMMENT ON TABLE exchange_rate.exchange_type
    IS 'Exhange type table for exchange rate';
    
CREATE SEQUENCE IF NOT EXISTS exchange_rate.seq_exchange_type INCREMENT 2 START 1
    OWNED BY exchange_rate.exchange_type.id;

ALTER TABLE IF EXISTS exchange_rate.exchange_type
    ADD CONSTRAINT "FK_LOCAL_CURRENCY" FOREIGN KEY (id_local_currency)
    REFERENCES exchange_rate.currency (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS exchange_rate.exchange_type
    ADD CONSTRAINT "FK_FOREIGN_CURRENCY" FOREIGN KEY (id_foreign_currency)
    REFERENCES exchange_rate.currency (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS exchange_rate.account
(
    id bigint NOT NULL,
    codigo text NOT NULL,
    etiqueta text,
    id_cliente bigint,
    id_currency integer,
    saldo numeric,
    fecha_creacion date,
    CONSTRAINT "PK_CUENTA" PRIMARY KEY (id),
    CONSTRAINT "UN_CODIGO_CUENTA" UNIQUE (codigo)
);

COMMENT ON TABLE exchange_rate.account
    IS 'Account table for exchange rate processing.';

CREATE SEQUENCE IF NOT EXISTS exchange_rate.seq_account INCREMENT 2 START 1
    OWNED BY exchange_rate.account.id;
    
ALTER TABLE IF EXISTS exchange_rate.account
    ADD CONSTRAINT "FK_USU_CUENTA" FOREIGN KEY (id_cliente)
    REFERENCES auth.user ("id") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS exchange_rate.account
    ADD CONSTRAINT "FK_CUR_CUENTA" FOREIGN KEY (id_currency)
    REFERENCES exchange_rate.currency (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID

---------------------------------------------------------

CREATE TABLE IF NOT EXISTS exchange_rate.transaction
(
    id bigint NOT NULL,
    id_cliente bigint NOT NULL,
    codigo_cliente text NOT NULL,
	id_source_account bigint NOT NULL,
    codigo_source_account text NOT NULL,
	id_source_currency integer NOT NULL,
	codigo_source_currency text NOT NULL,
    id_target_account bigint NOT NULL,
    codigo_target_account text NOT NULL,
    id_target_currency integer NOT NULL,
    codigo_target_currency text NOT NULL,
    source_amount numeric NOT NULL,
    target_amount numeric NOT NULL,
    exchange_rate numeric NOT NULL,
    fecha date,
    CONSTRAINT "PK_TRANSACTION" PRIMARY KEY (id)
);

COMMENT ON TABLE exchange_rate.transaction
    IS 'Transaction table for exchange rate processing.';
	
CREATE SEQUENCE IF NOT EXISTS exchange_rate.seq_transaction INCREMENT 2 START 1
	OWNED BY exchange_rate.transaction.id;

---------------------------------------------------------

--Adding records

INSERT INTO auth."role"(id, name)
VALUES((SELECT nextval('auth.seq_role')), 'ROLE_ADMIN');

INSERT INTO auth."role"(id, name)
VALUES((SELECT nextval('auth.seq_role')), 'ROLE_USER');

INSERT INTO auth."user"(id, username, password, first_name, last_name)
VALUES((SELECT nextval('auth.seq_user')), '12344321', '$2a$10$qIcGJhdTAgVStOIeYiF3quth4qWJoLe51r5IUeyozA6qBhmOo6EpK', 'Gustavo', 'Ramos');

INSERT INTO auth."user_role"(user_id, role_id)
VALUES((SELECT id FROM auth."user" WHERE username = '12344321'), 
        SELECT id FROM auth."role" WHERE name = 'ROLE_ADMIN');

INSERT INTO auth."user"(id, username, password, first_name, last_name)
VALUES((SELECT nextval('auth.seq_user')), '12345678', '$2a$10$mAsOAJoQV9lB1SvBMX0XYu9l3LRH4DFmY72fjB1W9DY9orXDkkIyu', 'Andres', 'Montalvo');

INSERT INTO auth."user_role"(user_id, role_id)
VALUES((SELECT id FROM auth."user" WHERE username = '12345678'), 
        SELECT id FROM auth."role" WHERE name = 'ROLE_USER');

INSERT INTO auth."user"(id, username, password, first_name, last_name)
VALUES((SELECT nextval('auth.seq_user')), '14784512', '$2a$10$mAsOAJoQV9lB1SvBMX0XYu9l3LRH4DFmY72fjB1W9DY9orXDkkIyu', 'Ronald', 'Gutierrez');

INSERT INTO auth."user_role"(user_id, role_id)
VALUES((SELECT id FROM auth."user" WHERE username = '14784512'), 
        SELECT id FROM auth."role" WHERE name = 'ROLE_USER');

INSERT INTO exchange_rate.currency(id, codigo, etiqueta, fecha)
VALUES((SELECT nextval('exchange_rate.seq_currency')), 'PEN', 'Soles peruanos', now());

INSERT INTO exchange_rate.currency(id, codigo, etiqueta, fecha)
VALUES((SELECT nextval('exchange_rate.seq_currency')), 'USD', 'Dólares americanos', now());

INSERT INTO exchange_rate.account(id, codigo, etiqueta, id_cliente, id_currency, saldo, fecha_creacion)
VALUES((SELECT nextval('exchange_rate.seq_account')), '19132490031041', 'Cuenta de Ahorros',
        (SELECT id FROM auth.user WHERE username = '12345678'), 
        (SELECT id FROM exchange_rate.currency WHERE codigo = 'PEN'), 
        5000.50, now());

INSERT INTO exchange_rate.account(id, codigo, etiqueta, id_cliente, id_currency, saldo, fecha_creacion)
VALUES((SELECT nextval('exchange_rate.seq_account')), '19165790173495', 'Cuenta de Ahorros',
        (SELECT id FROM auth.user WHERE username = '12345678'), 
        (SELECT id FROM exchange_rate.currency WHERE codigo = 'USD'),  
        100.00, now());

COMMIT;

