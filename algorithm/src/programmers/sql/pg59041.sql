select name, count(name)
from animal_ins
where name is not null
group by name
having count(name) > 1
order by name;