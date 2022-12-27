select film_id from films where films.episode_id in (2,3,5);

select f.title,sf.starship_id,s.name,pl.people_id, p.name
from films f
inner join starshipfilm sf on sf.film_id=f.film_id
left join pilots pl on pl.starship_id=sf.starship_id	
inner join people p on p.people_id=pl.people_id
inner join starships s on s.starship_id=sf.starship_id
 where f.episode_id in (2,3,5);
 
 select count(sf.starship_id) as apariciones,sf.starship_id as starship_id from starshipfilm sf where sf.film_id in (select film_id from films where films.episode_id in (2,3,5)) group by sf.starship_id;
 
 
select * from 
(select count(sf.starship_id) as apariciones,sf.starship_id as starship_id from starshipfilm sf where sf.film_id in (select film_id from films where films.episode_id in (2,3,5)) group by sf.starship_id) as ships;


select starship_id from 
(
select max(ships.apariciones) as apariciones, ships.starship_id as starship_id from 
(select count(sf.starship_id) as apariciones,sf.starship_id as starship_id from starshipfilm sf where sf.film_id in (select film_id from films where films.episode_id in (2,3,5)) group by sf.starship_id) as ships
) as mostAppearances
;

select starship_id from pilots group by ;

select count(sf.starship_id) as apariciones,sf.starship_id ship_id
from films f
inner join starshipfilm sf on sf.film_id=f.film_id
right join pilots p on p.starship_id=sf.starship_id
where f.episode_id in (2,3,5) 
and p.people_id in (
select people_id from characters where )
group by sf.starship_id
order by apariciones desc, sf.starship_id asc

 ;
 
 select f.title as title, p.name as pilotName, s.name as shipName
 from people p
 inner join characters c on p.people_id=c.people_id
 inner join films f on f.film_id=c.film_id
 inner join pilots pl on p.people_id=pl.people_id 
 right join 
 (
select count(sf.starship_id) as apariciones,sf.starship_id ship_id
from films f
inner join starshipfilm sf on sf.film_id=f.film_id
where sf.starship_id in (select distinct(starship_id)
from pilots) and f.episode_id in (1,2,3,4,5,6,7) 
group by sf.starship_id
order by apariciones desc, sf.starship_id asc
limit 1) as selected_ship
on pl.starship_id=selected_ship.ship_id
inner join starships s on s.starship_id=selected_ship.ship_id
where f.episode_id in (1,2,3,4,5,6,7) 
 ;
 
 
select count(sf.starship_id) as apariciones,sf.starship_id ship_id
from films f
right  join starshipfilm sf on sf.film_id=f.film_id
left  join pilots pl on sf.starship_id=pl.starship_id
where f.episode_id in (1,2,3) 
group by sf.starship_id
order by apariciones desc, sf.starship_id asc
limit 1;

select *
from starshipfilm sf 
where film_id in (select film_id from films where episode_id in (1,2,3));

select *
from characters sf 
where film_id in (select film_id from films where episode_id in (1,2,3));

select distinct(starship_id)
from pilots
