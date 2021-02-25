select i.name, i.datetime
from animal_ins as i
    left join animal_outs as o
    on i.animal_id = o.animal_id
where o.animal_id is null
    and i.name is not null
order by i.datetime
limit 3;

select name, datetime
from animal_ins
where animal_id not in (select animal_id
                        from animal_outs)
    and name is not null
order by datetime
limit 3;