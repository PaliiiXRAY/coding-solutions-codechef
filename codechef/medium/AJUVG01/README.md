# AJUVG01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** SQL  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-18T17:56:26.998Z  

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

[View on CodeChef](https://www.codechef.com/problems/AJUVG01)