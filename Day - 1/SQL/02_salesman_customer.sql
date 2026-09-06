/*

Consider the following tables:

    SalesMan -> sid	 sname	 scity

    Customer -> cid	 cname	 ccity

Write SQL queries for the following:

*/


-- I. Find number of salesman living in the same city as Davis
SELECT COUNT(*)
FROM SalesMan
WHERE scity = (
    SELECT scity
    FROM SalesMan
    WHERE sname = 'Davis'
);


-- II. Find salespeople who are NOT living in the same city as Cameron, Green, Johns
SELECT sname
FROM SalesMan
WHERE scity NOT IN (
    SELECT ccity
    FROM Customer
    WHERE cname IN ('Cameron', 'Green', 'Johns')
);