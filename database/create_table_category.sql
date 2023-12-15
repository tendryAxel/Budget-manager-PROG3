CREATE type TransactionType AS ENUM ('DEBIT', 'CREDIT');

CREATE TABLE IF NOT EXISTS "category"(
    id_category SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE
);

-- Insertions pour la table "category"
INSERT INTO "category" (name) VALUES
  ('food'),
  ('purchase'),
  ('housing'),
  ('transport'),
  ('car'),
  ('recreation'),
  ('multimedia'),
  ('fiscal'),
  ('investment'),
  ('income');



CREATE TABLE IF NOT EXISTS subcategory (
    id_subcategory SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE,
    type TransactionType,
    id_category INT REFERENCES category(id_category)
);


-- Insertions pour la table "subcategory"
INSERT INTO "subcategory" (name, type, id_category) VALUES
  -- Food
  ('food', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'food')),
  ('coffee', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'food')),
  ('resto', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'food')),

  -- Purchase
  ('pets', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('jewelry', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('gift', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('kids', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('house', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('stationery', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('pharmacy', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('health', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('free_time', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('clothing', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),
  ('electronics', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'purchase')),

  -- Housing
  ('house_insurance', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'housing')),
  ('house_maintenance', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'housing')),
  ('rent', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'housing')),
  ('mortgage', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'housing')),
  ('services', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'housing')),
  ('energy', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'housing')),

  -- Transport
  ('long_distance', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'transport')),
  ('taxi', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'transport')),
  ('public_transport', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'transport')),
  ('business_trip', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'transport')),

  -- Car
  ('car_insurance', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'car')),
  ('fuel', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'car')),
  ('leasing', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'car')),
  ('car_maintenance', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'car')),
  ('locations', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'car')),
  ('parking', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'car')),

  -- Recreation
  ('alcohol_well_being', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('event', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('donations', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('hobby', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('subscription', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('lottery', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('doctor', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('sport', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('tv', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),
  ('vacation', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'recreation')),

  -- Multimedia
  ('internet', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'multimedia')),
  ('software', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'multimedia')),
  ('post', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'multimedia')),
  ('phone', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'multimedia')),

  -- Fiscal
  ('allowances', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'fiscal')),
  ('fine', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'fiscal')),
  ('fees', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'fiscal')),
  ('advisor', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'fiscal')),
  ('loan', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'fiscal')),
  ('taxes', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'fiscal')),

  -- Investment
  ('property', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'investment')),
  ('collections', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'investment')),
  ('financial_investment', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'investment')),
  ('economy', 'DEBIT', (SELECT id_category FROM "category" WHERE name = 'investment'));
