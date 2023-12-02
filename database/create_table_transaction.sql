CREATE TABLE IF NOT EXISTS "transaction" (
    id SERIAL PRIMARY KEY,
    value INT NOT NULL,
    description TEXT,
    id_account INT REFERENCES "account"(id),
    transaction_date timestamp NOT NULL DEFAULT current_timestamp
);

-- Insertion 1
INSERT INTO "transaction" (value, description, id_account, transaction_date) VALUES (100, 'Deposit', 1, '2023-12-02 10:00:00');

-- Insertion 2
INSERT INTO "transaction" (value, description, id_account, transaction_date) VALUES (-50, 'Withdrawal', 2, '2023-12-02 12:30:00');

-- Insertion 3
INSERT INTO "transaction" (value, description, id_account, transaction_date) VALUES (200, 'Deposit', 3, '2023-12-02 15:45:00');
