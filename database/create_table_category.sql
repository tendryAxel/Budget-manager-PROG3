CREATE TABLE IF NOT EXISTS "category"(
    id SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE,
    type TransactionType
);