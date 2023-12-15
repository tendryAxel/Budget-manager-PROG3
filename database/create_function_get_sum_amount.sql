DROP FUNCTION IF EXISTS get_sum_amount;
CREATE OR REPLACE FUNCTION get_sum_amount(
    account_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP
) RETURNS TABLE(
    total_credit DOUBLE PRECISION,
    total_debit DOUBLE PRECISION
)
AS
$$
BEGIN
    RETURN QUERY
    SELECT
        COALESCE(SUM(CASE WHEN t.type = 'CREDIT' THEN amount ELSE 0 END), 0) AS total_credit,
        COALESCE(SUM(CASE WHEN t.type = 'DEBIT' THEN amount ELSE 0 END), 0) AS total_debit
    FROM
        "account" a
    LEFT JOIN
        "transaction" t ON t.id_account = a.id_account AND
        t.transaction_date BETWEEN start_date AND end_date
    WHERE
        a.id_account = account_id;
END;
$$ LANGUAGE plpgsql;

--Select--
SELECT * FROM get_sum_amount (1 , '2023-12-02 08:30:00' , '2023-12-03 13:30:00');