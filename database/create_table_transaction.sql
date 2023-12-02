CREATE TABLE IF NOT EXISTS "transaction" (
    id SERIAL PRIMARY KEY,
    value INT NOT NULL,
    description TEXT,
    id_account INT REFERENCES "account"(id),
    transaction_date timestamp NOT NULL DEFAULT current_timestamp
);