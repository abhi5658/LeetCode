# Write your MySQL query statement below
select(
    select 
        Salary 
    from 
        Employee 
    order by 
        Salary DESC 
    limit 1 
    offset 1
) as SecondHighestSalary;