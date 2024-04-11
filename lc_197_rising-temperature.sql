-- Write your PostgreSQL query statement below
-- select id from
-- (
--   select w1.id as id, 
--   extract(day from w1.recordDate::timestamp - w2.recordDate::timestamp  ) as diff
--   from weather w1, weather w2 
--   where w1.temperature > w2.temperature
-- ) 
-- where diff = 1;

-- select w1.id as id
-- from weather w1, weather w2 
-- where 
--   w1.recordDate = w2.recordDate + 1
--   AND w1.temperature > w2.temperature

select w1.id as id
from 
  weather w1 left join weather w2 
on w1.recordDate = w2.recordDate + 1 
where 
  w1.temperature > w2.temperature;

