create schema module_3 default character set utf8;

use module_3;

create table users
(
    id bigint auto_increment primary key,
    name varchar(45)
);

create table accounts
(
    id bigint auto_increment primary key,
    user_id bigint not null,
    amount bigint not null,
    foreign key (user_id) references users(id)
);

create table categories
(
    id bigint auto_increment primary key,
    name varchar(45)
);

create table operations
(
    id bigint auto_increment primary key,
    date_time varchar(45),
    from_account_id bigint,
    to_account_id bigint ,
    amount bigint not null,
    foreign key (from_account_id) references accounts(id) on delete set null,
    foreign key (to_account_id) references accounts(id) on delete set null
);

create table histories
(
    id bigint auto_increment primary key,
    account_id bigint,
    category_id bigint,
    operation_id bigint,
    foreign key (account_id) references accounts(id) on delete set null,
    foreign key (category_id) references categories(id) on delete set null,
    foreign key (operation_id) references operations(id) on delete set null
);