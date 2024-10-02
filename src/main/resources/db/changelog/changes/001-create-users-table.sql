create table users
(
    id       bigint primary key not null,
    username varchar(255)       not null,
    password varchar(255)       not null,
    email    varchar(255)       not null
);

