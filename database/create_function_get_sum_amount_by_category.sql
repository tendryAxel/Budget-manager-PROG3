 DROP FUNCTION IF EXISTS get_sum_amount_by_category;

 CREATE OR REPLACE FUNCTION get_sum_amount_by_category(
   account_id INT,
   start_date TIMESTAMP,
    end_date TIMESTAMP
) RETURNS TABLE (
   restaurant DOUBLE PRECISION,
   salary DOUBLE PRECISION
)
 AS
 $$
BEGIN
  RETURN QUERY
   SELECT
        COALESCE(SUM(CASE WHEN c.name = 'Restaurant' THEN t.amount ELSE 0 END), 0)  As restaurant ,
       COALESCE(SUM(CASE WHEN c.name = 'Salaire' THEN t.amount ELSE 0 END), 0) As salary
    FROM
        "account" a
   LEFT JOIN
        "transaction" t ON t.id_account = a.id_account
    LEFT JOIN
        "subcategory" s ON s.id_subcategory = t.id_subcategory
    LEFT JOIN
       "category" c ON s.id_category = c.id_category
    WHERE
        a.id_account = account_id AND
        t.transaction_date BETWEEN start_date AND end_date;
   END;
 $$ LANGUAGE plpgsql;

 --Select--
 SELECT * FROM get_sum_amount (1 , '2023-12-02 08:30:00' , '2023-12-03 13:30:00');