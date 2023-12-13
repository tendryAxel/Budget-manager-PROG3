CREATE TABLE IF NOT EXISTS "transfert" (
    id INT NOT NUll,
    id_debtor INT REFERENCES account(id),
    id_credit INT REFERENCES account(id),
    transfer_date TIMESTAMP NOT NULL
);

-- Insérer des données dans la table "transfert"
INSERT INTO transfert (id, id_debtor, id_credit, transfer_date) VALUES
(1, 1001, 2001, '2023-01-01 12:00:00'),
(2, 1002, 2002, '2023-02-15 08:30:00'),
(3, 1003, 2003, '2023-03-20 16:45:00');
