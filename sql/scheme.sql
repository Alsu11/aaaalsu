-- создание таблицы преподователя
create table teacher
(
    id  serial primary key,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    experience integer
);

-- создание таблицы курса
create table course
(
    id  serial primary key,
    name varchar(20) not null,
    start  varchar(20) not null,
    ending varchar(20) not null,
    teacher_id integer,
    foreign key (teacher_id) references teacher (id)
);

-- создание таблицы урока
create table lesson
(
    lesson_id  serial primary key,
    lesson_name varchar(20) not null,
    day varchar(20) not null,
    time  varchar(20) not null,
    course_id integer,
    foreign key (course_id) references course (id)
);

-- создание таблицы студента
create table student
(
    student_id  serial primary key,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    group_number integer not null,
    course_id integer,
    foreign key (course_id) references course (id)
);
