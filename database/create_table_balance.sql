CREATE TABLE IF NOT EXISTS "balance" (
    id_account REFERENCES "account"(id),
    datetime timestamp default current_timestamp,
    value double precision,
    PRIMARY KEY(id_account, datetime)
);

INSERT INTO "balance" (id_account, datetime, value) VALUES
  (1, '2023-01-01 12:00:00', 1000.50),
  (1, '2023-02-01 12:30:00', 1200.75),
  (2, '2023-01-01 08:45:00', 800.30),
  (2, '2023-02-01 09:15:00', 950.20),
  (3, '2023-01-01 10:30:00', 300.00),
  (3, '2023-02-01 11:00:00', 450.50);
