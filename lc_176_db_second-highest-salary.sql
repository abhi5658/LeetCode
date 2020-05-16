# Write your MySQL query statement below
select 
Salary as SecondHighestSalary 
from Employee 
order by Salary DESC 
limit 1
offset 1;