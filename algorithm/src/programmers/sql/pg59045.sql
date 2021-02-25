select i.animal_id, i.animal_type, i.name
from animal_ins as i
    join animal_outs as o
    on i.animal_id = o.animal_id
where i.sex_upon_intake like 'I%'
    and (o.sex_upon_outcome like 'S%'
        or o.sex_upon_outcome like 'N%')
order by i.animal_id;


select i.animal_id, i.animal_type, i.name
from animal_ins as i
    join animal_outs as o
    on i.animal_id = o.animal_id
    and i.sex_upon_intake <> o.sex_upon_outcome
order by i.animal_id;


select i.animal_id, i.animal_type, i.name
from animal_ins as i
    join animal_outs as o
    on i.animal_id = o.animal_id
where i.sex_upon_intake != o.sex_upon_outcome
    and i.sex_upon_intake like 'I%'
order by i.animal_id;