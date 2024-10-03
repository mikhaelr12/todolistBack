create table todos(
    id bigint not null,
    title varchar(255) not null,
    description varchar(255),
    user_id bigint,
    constraint  FK_USER_TODO  foreign key (user_id) references users (id)
);

alter table todos owner to root;

create sequence todo_id_seq;

alter sequence todo_id_seq owner to root;