CREATE TABLE IF NOT EXISTS "account" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    id_currency INT REFERENCES "currency"(id)
);

-- Insertion 1
INSERT INTO "account" (name, id_currency) VALUES ('Savings Account', 1);

-- Insertion 2
INSERT INTO "account" (name, id_currency) VALUES ('Checking Account', 2);

-- Insertion 3
INSERT INTO "account" (name, id_currency) VALUES ('Investment Account', 3);
