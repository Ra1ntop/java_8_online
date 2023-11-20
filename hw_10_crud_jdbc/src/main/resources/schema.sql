create schema java_hw_9 default character set utf8;

use java_hw_9;
create table students
(
    id bigint auto_increment primary key,
    first_name varchar(45),
    last_name varchar(45),
    age int
);

create table groupsOfStudents
(
    id bigint auto_increment primary key,
    name varchar(45)
);

create table gro_stu
(
    dep_id bigint not null,
    emp_id bigint not null,
    primary key (dep_id, emp_id),
    foreign key (dep_id) references groupsOfStudents(id),
    foreign key (emp_id) references students(id)
);
