-- your code goes here
SELECT EmpID, Name,
Department, Salary

FROM Employees
WHERE Salary > (SELECT AVG(SALARY) FROM Employees)
ORDER BY Salary DESC;