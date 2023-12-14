CREATE OR REPLACE FUNCTION get_sum_amount_by_category(
    account_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP
) RETURNS TABLE (
    category_name VARCHAR(100),
    total_amount DOUBLE PRECISION
)
AS
$$
BEGIN
    RETURN QUERY
    SELECT
        c.name AS category_name,
        COALESCE(SUM(t.amount), 0) AS total_amount
    FROM
        "category" c
    LEFT JOIN
        "subcategory" s ON c.id_category = s.id_category
    LEFT JOIN
        "transaction" t ON s.id_subcategory = t.id_subcategory
    WHERE
        t.id_account = account_id
        AND t.transaction_date BETWEEN start_date AND end_date
    GROUP BY
        c.name;
END;
$$ LANGUAGE plpgsql;
