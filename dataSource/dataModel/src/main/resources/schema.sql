DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS access_level CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;

CREATE TABLE access_level
(
    id    bigint      NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    level varchar(16) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE accounts
(
    id             bigint      NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    login          varchar(30) NOT NULL,
    password       varchar(240) NOT NULL,
    first_name     varchar(30) NOT NULL,
    last_name      varchar(50) NOT NULL,
    account_number varchar(30),
    account_state  double precision,
    debt           double precision,
    currency       ENUM('USD', 'PLN', 'EUR', 'CHF', 'GBP') DEFAULT 'PLN',
    access_level   bigint      NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT login_unique UNIQUE (login),
    CONSTRAINT fk_access_level_id FOREIGN KEY (access_level) REFERENCES access_level (id)
);

CREATE TABLE transactions
(
    id     bigint                              NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    "from"   bigint                              NOT NULL,
    "to"     bigint                              NOT NULL,
    amount double precision                    NOT NULL,
    date   timestamp DEFAULT current_timestamp NOT NULL,
    rate    double precision                    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_from_id FOREIGN KEY ("from") REFERENCES accounts (id),
    CONSTRAINT fk_to_id FOREIGN KEY ("to") REFERENCES accounts (id)
)
