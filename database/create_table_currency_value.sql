CREATE TABLE IF NOT EXISTS "currency-value" (
    id SERIAL PRIMARY KEY NOT NULL,
    id_currency_source INT NOT NULL,
    id_currency_destination INT NOT NULL,
    amount DECIMAL(18, 2) NOT NULL,
    date_effet DATE NOT NULL
);

-- Insertion 1
INSERT INTO "currency-value" (id_currency_source, id_currency_destination, amount, date_effet)
VALUES (1, 2, 1000.50, '2023-12-13');

-- Insertion 2
INSERT INTO "currency-value" (id_currency_source, id_currency_destination, amount, date_effet)
VALUES (3, 1, 750.25, '2023-12-14');

-- Insertion 3
INSERT INTO "currency-value" (id_currency_source, id_currency_destination, amount, date_effet)
VALUES (2, 3, 1200.75, '2023-12-15');
