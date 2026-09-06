/*
 * SQL Question:
 *
 * Consider the following table:
 *
 * product(
 *     product_name,
 *     price,
 *     quantity
 * )
 *
 * The total value of a product is calculated as:
 *
 *     total_value = price * quantity
 *
 * Write SQL queries to find the product(s) having the
 * 3rd highest total value (price * quantity).
 *
 * Solve the problem using the following approaches:
 *
 * 1. ORDER BY with LIMIT and OFFSET
 *
 * 2. DENSE_RANK()
 *
 * 3. DISTINCT with ORDER BY and OFFSET
 *
 * 4. Nested subqueries using MAX()
 *
 *
 * Note:
 * If multiple products have the same total value, they should
 * be considered as having the same rank.
 */



-- ORDER BY + LIMIT/OFFSET
SELECT product_name
FROM product
ORDER BY price * quantity DESC
LIMIT 1 OFFSET 2;


-- DENSE_RANK()
SELECT product_name
FROM (
    SELECT
        product_name,
        price * quantity AS total_value,
        DENSE_RANK() OVER (
            ORDER BY price * quantity DESC
        ) AS rnk
    FROM product
) t
WHERE rnk = 3;


-- DISTINCT + ORDER BY + OFFSET
SELECT product_name
FROM product
WHERE price * quantity = (
    SELECT DISTINCT price * quantity
    FROM product
    ORDER BY price * quantity DESC
    LIMIT 1 OFFSET 2
);


-- Using subqueries
SELECT MAX(price * quantity)
FROM product
WHERE price * quantity < (
    SELECT MAX(price * quantity)
    FROM product
    WHERE price * quantity < (
        SELECT MAX(price * quantity)
        FROM product
    )
);