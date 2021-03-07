select animal_id, name,
    if(sex_upon_intake like 'N%' or sex_upon_intake like 'S%', 'O', 'X') as 중성화
from animal_ins
order by animal_id;

select animal_id, name,
    case when sex_upon_intake like 'N%' then 'O'
         when sex_upon_intake like 'S%' then 'O'
         else 'X'
    end as 중성화
from animal_ins
order by animal_id;