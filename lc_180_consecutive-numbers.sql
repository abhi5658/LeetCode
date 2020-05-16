# Write your MySQL query statement below
select distinct
    l1.Num AS ConsecutiveNums
from 
    Logs l1,
    Logs l2,
    Logs l3
where
    l1.Id = l2.Id - 1
    and l2.Id = l3.Id - 1
    and l1.Num = l2.Num
    and l2.Num = l3.Num
# result => filter rows matching consecutive Id and same Num as well. Now selecting one of the Num row w/ columnName. Selecting only unique(distinct) selected numbers(a number can have more than once 3 times occuring series)