-- Architecture
create table greeting.greet_type
(
    id               bigint       not null primary key,
    name             varchar(255) not null
);

create sequence greeting.seq_greet_type_id
    start with 1
    increment by 1;