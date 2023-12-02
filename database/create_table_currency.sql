CREATE TABLE IF NOT EXISTS "currency" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

-- Insertion 1
INSERT INTO "currency" (name) VALUES ('US Dollar');

-- Insertion 2
INSERT INTO "currency" (name) VALUES ('Euro');

-- Insertion 3
INSERT INTO "currency" (name) VALUES ('Japanese Yen');
