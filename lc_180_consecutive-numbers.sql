# Write your MySQL query statement below
select 
    * 
from 
    Logs l1,
    Logs l2,
    Logs l3
where
    l1.Id = l2.Id - 1
    and l2.Id = l3.Id - 1
# result => filter rows matching consecutive Ids from the joined tables e.g. Ids: [1,2,3], [2,3,4]