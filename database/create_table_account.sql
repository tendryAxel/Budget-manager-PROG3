CREATE TABLE IF NOT EXISTS "account" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    id_currency INT REFERENCES "currency"(id)
);