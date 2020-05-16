# Write your MySQL query statement below
select 
    Score,
    DENSE_RANK() OVER (order by Score DESC) `Rank`
from 
    Scores;