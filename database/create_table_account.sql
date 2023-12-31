CREATE type AccountType AS ENUM ('Banque', 'Espece' , 'Mobile_Money');

CREATE TABLE IF NOT EXISTS "account" (
    id_account SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    updatedDate TIMESTAMP NOT NULL,
    id_currency INT REFERENCES "currency"(id_currency),
    type AccountType NOT NULL
);

-- Insertion 1
INSERT INTO "account" (name,  updatedDate, id_currency, type)
VALUES ('Savings Account',  CURRENT_TIMESTAMP, 1, 'Banque');

-- Insertion 2
INSERT INTO "account" (name,  updatedDate, id_currency, type)
VALUES ('Savings Account',  CURRENT_TIMESTAMP, 2, 'Espece');

-- Insertion 3
INSERT INTO "account" (name,  updatedDate, id_currency, type)
VALUES ('Investement Account',  CURRENT_TIMESTAMP, 3, 'Mobile_Money');

