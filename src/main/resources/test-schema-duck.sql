drop table if exists duck CASCADE;
create table duck (
    id integer generated by default as identity,
    age integer not null check (
        age <= 36
        AND age >= 0
    ),
    colour varchar(255) not null,
    habitat varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);
alter table duck
add constraint UK_8qrhl5wlohdjoko9nrorkdjkp unique (name);