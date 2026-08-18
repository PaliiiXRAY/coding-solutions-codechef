# LOKPL01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given the following tables: 1.Customers 2.Orders 3.Payments

Write an SQL query to display the details of customers whose payment amount is greater than or equal to the corresponding order amount. Conditions 1.A customer must have placed at least one order. 2.Every displayed order must have a payment. 3.Display only those records where PaymentAmount ≥ OrderAmount. 4.Display the following columns CustomerID CustomerName OrderID OrderAmount PaymentAmount

## Customers
CustomerID	CustomerName
1	Rahul
2	Priya
3	Amit
4	Neha
## Orders
OrderID	CustomerID	OrderAmount
101	1	5000
102	2	7000
103	3	4000
104	1	9000
105	4	6000
## Payments
PaymentID	OrderID	PaymentAmount
1	101	5000
2	102	6500
3	103	4000
4	104	9500
5	105	6000
## Output
CustomerID	CustomerName	OrderID	OrderAmount	PaymentAmount
1	Rahul	104	9000	9500
4	Neha	105	6000	6000
1	Rahul	101	5000	5000
3	Amit	103	4000	4000

Sort the output Sort the result by OrderAmount in descending order.

## Solution

**Language:** SQL  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-18T17:56:23.587Z  

```sql
CREATE TABLE Customers (
    CustomerID INTEGER PRIMARY KEY,
    CustomerName TEXT
);

CREATE TABLE Orders (
    OrderID INTEGER PRIMARY KEY,
    CustomerID INTEGER,
    OrderAmount INTEGER
);

CREATE TABLE Payments (
    PaymentID INTEGER PRIMARY KEY,
    OrderID INTEGER,
    PaymentAmount INTEGER
);

INSERT INTO Customers VALUES
(1,'Rahul'),
(2,'Priya'),
(3,'Amit'),
(4,'Neha');

INSERT INTO Orders VALUES
(101,1,5000),
(102,2,7000),
(103,3,4000),
(104,1,9000),
(105,4,6000);

INSERT INTO Payments VALUES
(1,101,5000),
(2,102,6500),
(3,103,4000),
(4,104,9500),
(5,105,6000);

SELECT c.CustomerID,
     c.CustomerName, 
     o.OrderID, 
     o.OrderAmount, 
     p.PaymentAmount
FROM Customers AS c JOIN
Orders AS o ON 
c.CustomerID = o.CustomerID
JOIN Payments AS p 
ON p.OrderID = o.OrderID
WHERE p.PaymentAmount >= o.OrderAmount
AND EXISTS(
 SELECT 1 FROM Orders o2
 WHERE o2.CustomerID = c.CustomerID
)
ORDER BY  o.OrderAmount DESC;


```

---

[View on CodeChef](https://www.codechef.com/problems/LOKPL01)