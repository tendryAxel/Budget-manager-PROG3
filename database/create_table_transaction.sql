-- Creation de la table
CREATE TABLE IF NOT EXISTS "transaction" (
    id_transaction SERIAL PRIMARY KEY,
    label VARCHAR(100) NOT NULL,
    amount double precision NOT NULL,
    transaction_date timestamp NOT NULL DEFAULT current_timestamp,
    type TransactionType NOT NULL,
    id_account INT REFERENCES "account"(id_account),
    id_subcategory INT REFERENCES "subcategory"(id_subcategory),
    id_currency INT REFERENCES "currency" (id_currency)
);

-- Insertion 1
INSERT INTO "transaction" (label, amount, type, id_account, transaction_date , id_subcategory , id_currency)
VALUES ('Deposit', 100.0, 'CREDIT', 1, '2023-12-02 10:00:00' ,1, 2);

-- Insertion 2
INSERT INTO "transaction" (label, amount, type, id_account, transaction_date , id_subcategory ,id_currency)
VALUES ('Withdrawal', 50.0, 'DEBIT', 2, '2023-12-02 12:30:00' ,2, 2);

-- Insertion 3
INSERT INTO "transaction" (label, amount, type, id_account, transaction_date , id_subcategory , id_currency)
VALUES ('Deposit', 200.0, 'CREDIT', 3, '2023-12-02 15:45:00' , 3,2);
