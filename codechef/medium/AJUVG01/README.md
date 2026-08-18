# AJUVG01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an Employees table. Write an SQL query to display the details of employees whose salary is greater than the average salary of all employees.

Display the following columns:

EmpID | Name | Department | Salary

Sort the output Sort the result by Salary in descending order.

## Input Table
EmpID	Name	Department	Salary
101	Alice	HR	50000
102	Bob	IT	70000
103	Charlie	Sales	60000
104	David	IT	80000
105	Eva	HR	45000
106	Frank	Sales	90000
## Output
EmpID	Name	Department	Salary
106	Frank	Sales	90000
104	David	IT	80000
102	Bob	IT	70000

Average Salary = 65833.33

## Solution

**Language:** SQL  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-18T17:43:04.052Z  

```sql
-- your code goes here
SELECT EmpID, Name,
Department, Salary

FROM Employees
WHERE Salary > (SELECT AVG(SALARY) FROM Employees)
ORDER BY Salary DESC;
```

---

[View on CodeChef](https://www.codechef.com/problems/AJUVG01)