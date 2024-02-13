CREATE TABLE administrators
(
    id            BIGINT       NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    patronymic    VARCHAR(255),
    position      VARCHAR(255) NOT NULL,
    login         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    CONSTRAINT pk_administrators PRIMARY KEY (id)
);

CREATE TABLE clients
(
    id            BIGINT       NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    patronymic    VARCHAR(255),
    email         VARCHAR(255) NOT NULL,
    phone         INTEGER      NOT NULL,
    login         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255),
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

ALTER TABLE administrators
    ADD CONSTRAINT uc_administrators_login UNIQUE (login);

ALTER TABLE clients
    ADD CONSTRAINT uc_clients_login UNIQUE (login);