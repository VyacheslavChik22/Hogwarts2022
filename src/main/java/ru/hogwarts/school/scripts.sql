select *
from student;

select *
from student
where age >10
  and age < 20; /*Получить всех студентов, возраст которых находится между 10 и 20*/

select name
from student; /*Получить всех студентов, но отобразить только список их имен*/

select *
from student
where name like '%rr%'; /*Получить всех студентов, у которых в имени присутствует буква «О» (или любая другая)*/

select name
from student
where age < id; /*Получить всех студентов, у которых возраст меньше их идентификатора*/

select name
from student
order By age; /*Получить всех студентов упорядоченных по возрасту*/



