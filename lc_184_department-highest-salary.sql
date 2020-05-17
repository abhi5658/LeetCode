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
        DepartmentId,
        max(e.Salary) as max
    from
        Employee e
    group by
    DepartmentId
    )  m
on 
    e.DepartmentId = m.DepartmentId
    and e.Salary = m.max


# Alternate
# Write your MySQL query statement below
select 
    d.Name as Department,
    e.Name as Employee, 
    e.Salary
from 
    Employee e 
right join 
    Department d
on 
    d.Id = e.DepartmentId
where 
    (e.DepartmentId, e.Salary) 
in 
    (select
        DepartmentId, max(Salary)
    from 
        Employee 
    group by 
        DepartmentId)