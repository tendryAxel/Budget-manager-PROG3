CREATE TABLE IF NOT EXISTS "currency" (
    id_currency SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(100) NOT NULL
);

-- Insertion 1
INSERT INTO "currency" (name, code) VALUES ('US Dollar', 'USD');

-- Insertion 2
INSERT INTO "currency" (name, code) VALUES ('Euro', 'EUR');

-- Insertion 3
INSERT INTO "currency" (name, code) VALUES ('Japanese Yen', 'JPY');