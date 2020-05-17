# Write your MySQL query statement below
select 
    d.Name as Department,
    e.Name as Employee,
    e.Salary
from
    Employee e
inner join
    Department d
on
    e.DepartmentId = d.Id
inner join
    (select
        d.Id as dId,
        max(e.Salary) as max
    from
        Department d,
        Employee e
    where
        d.Id = e.DepartmentId
    group by
        d.Id
    ) as m
where 
    e.DepartmentId = m.dId
    and e.Salary = m.max