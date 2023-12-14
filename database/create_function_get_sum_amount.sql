CREATE OR REPLACE FUNCTION get_sum_amount(
    account_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP
) RETURNS DOUBLE PRECISION
AS
$$
DECLARE
    total_amount DOUBLE PRECISION;
BEGIN
    SELECT SUM(amount)
    INTO total_amount
    FROM "transaction"
    WHERE id_account = account_id
        AND transaction_date BETWEEN start_date AND end_date;
    RETURN total_amount;
END;
$$ LANGUAGE plpgsql;