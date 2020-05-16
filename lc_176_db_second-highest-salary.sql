# Write your MySQL query statement below
select 
Salary as SecondHighestSalary 
from Employee 
where Salary < 
(select MAX(Salary) from Employee)
order by Salary DESC 
limit 1;