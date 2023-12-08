CREATE type TransactionType AS ENUM ('DEBIT', 'CREDIT')

-- Création de la table
CREATE TABLE IF NOT EXISTS "transaction" (
    id SERIAL PRIMARY KEY,
    value INT NOT NULL,
    label VARCHAR(100) NOT NULL,
    amount BigDecimal NOT NULL,
    transaction_date timestamp NOT NULL DEFAULT current_timestamp,
    type TransactionType NOT NULL,
    id_account INT REFERENCES "account"(id)
);

-- Insertion 1
INSERT INTO "transaction" (value, label, amount, type, id_account, transaction_date)
VALUES (100, 'Deposit', 100.0, 'CREDIT', 1, '2023-12-02 10:00:00');

-- Insertion 2
INSERT INTO "transaction" (value, label, amount, type, id_account, transaction_date)
VALUES (-50, 'Withdrawal', 50.0, 'DEBIT', 2, '2023-12-02 12:30:00');

-- Insertion 3
INSERT INTO "transaction" (value, label, amount, type, id_account, transaction_date)
VALUES (200, 'Deposit', 200.0, 'CREDIT', 3, '2023-12-02 15:45:00');
