CREATE type AccountType AS ENUM ('Banque', 'Espèce' , 'MobileMoney');

CREATE TABLE IF NOT EXISTS "account" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    balance DECIMAL (10 , 2) NOT NULL,
    updatedDate TIMESTAMP NOT NULL,
    id_currency INT REFERENCES "currency"(id),
    type AccountType NOT NULL
);

-- Insertion 1
INSERT INTO "account" (name, balance, updatedDate, id_currency, type)
VALUES ('Savings Account', 45000.0, CURRENT_TIMESTAMP, 1, 'Banque');

-- Insertion 2
INSERT INTO "account" (name, balance, updatedDate, id_currency, type)
VALUES ('Checking Account', 23000.0, CURRENT_TIMESTAMP, 2, 'Espèce');

-- Insertion 3
INSERT INTO "account" (name, balance, updatedDate, id_currency, type)
VALUES ('Investment Account', 12000.0, CURRENT_TIMESTAMP, 3, 'MobileMoney');


