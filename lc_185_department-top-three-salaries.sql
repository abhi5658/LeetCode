# Write your MySQL query statement below
select 
    d.Name as Department,
    e.Name as Employee,
    e.Salary
from
    Employee e,
    Department d,
    (
        SELECT 
            GROUP_CONCAT(top_saleries_per_group) AS top_salaries
        FROM
            (
                SELECT 
                    SUBSTRING_INDEX(GROUP_CONCAT(distinct Salary ORDER BY Salary DESC),',',3)
                        AS top_saleries_per_group        
                FROM
                    Employee
                GROUP BY
                    DepartmentId
            ) s_top_salaries_per_group
    ) s_top_salaries
WHERE
    FIND_IN_SET(e.Salary, top_salaries)
    and e.DepartmentId = d.Id
ORDER BY
    e.DepartmentId,
    e.Salary DESC;
# Works at https://dbfiddle.uk/