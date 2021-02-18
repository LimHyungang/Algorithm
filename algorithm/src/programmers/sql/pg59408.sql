select count(distinct name)
from animal_ins;

select count(distinct name)
from animal_ins
where name is not null;