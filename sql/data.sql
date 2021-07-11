insert into teacher(first_name, last_name, experience)
values ('Михаил','Абрамский',10);
insert into teacher(first_name, last_name, experience)
values ('Мансур','Зиятдинов',5);
insert into teacher(first_name, last_name, experience)
values ('Джастин','Тимберлейк',1);
insert into teacher(first_name, last_name, experience)
values ('Хейли','Бибер',2);
insert into teacher(first_name, last_name, experience)
values ('Гарри','Стайлс',15);
insert into teacher(first_name, last_name, experience)
values ('Наруто','Узумаки',27);

insert into course(name, start, ending, teacher_id)
values ('Java','september','october',2);
insert into course(name, start, ending, teacher_id)
values ('SQL','september','october',5);
insert into course(name, start, ending, teacher_id)
values ('C#','september','october',4);
insert into course(name, start, ending, teacher_id)
values ('C++','september','october',2);
insert into course(name, start, ending, teacher_id)
values ('Python','september','october',1);
insert into course(name, start, ending, teacher_id)
values ('Нарутотерапия','beginning of the fight','ending of the fight',6);

insert into lesson(lesson_name, day, time, course_id) values ('java','10:10','17:17',1);
insert into lesson(lesson_name, day, time, course_id) values ('aisd','10:10','17:17',2);
insert into lesson(lesson_name, day, time, course_id) values ('python','10:10','17:17',3);
insert into lesson(lesson_name, day, time, course_id) values ('aisd','10:10','17:17',3);
insert into lesson(lesson_name, day, time, course_id) values ('c++','10:10','17:17',1);

insert into student(first_name, last_name, group_number, course_id)
values ('Юмадилова','Алсу',002, 1);
insert into student(first_name, last_name, group_number, course_id)
values ('Миронова','Адель',008, 2);
insert into student(first_name, last_name, group_number, course_id)
values ('Кенделл','Дженнер',010, 3);
insert into student(first_name, last_name, group_number, course_id)
values ('Ким','Кардшьян',010, 1);
insert into student(first_name, last_name, group_number, course_id)
values ('Саске','Учиха', 7, 6);
