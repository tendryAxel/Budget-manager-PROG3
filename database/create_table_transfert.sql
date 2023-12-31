CREATE TABLE IF NOT EXISTS "transfert" (
    id INT NOT NUll,
    id_debtor INT REFERENCES "transaction"(id_transaction),
    id_credit INT REFERENCES "transaction"(id_transaction),
    transfer_date TIMESTAMP NOT NULL
);

-- Insérer des données dans la table "transfert"
INSERT INTO "transfert" (id, id_debtor, id_credit, transfer_date) VALUES
(1, 1, 2, '2023-01-01 12:00:00'),
(2, 1, 2, '2023-02-15 08:30:00'),
(3, 3, 1, '2023-03-20 16:45:00');
