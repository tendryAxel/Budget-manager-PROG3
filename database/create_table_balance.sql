CREATE TABLE IF NOT EXISTS "balance" (
    id_account REFERENCES "account"(id),
    datetime timestamp default current_timestamp,
    value double precision,
    PRIMARY KEY(id_account, datetime)
);