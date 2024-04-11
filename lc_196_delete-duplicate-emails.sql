-- Write your PostgreSQL query statement below

-- mysql
-- delete p1 from person p1, person p2 where p1.id > p2.id and p1.email = p2.email

-- postgresql
delete from
person where id not in (select min(id) from person group by email);
