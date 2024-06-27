create table fetch_job
(
    id         serial primary key,
    endpoint   varchar(255) not null,
    cron       varchar(255) not null,
    status     varchar(255) not null,
    created_at timestamp    not null default now(),
    last_run   timestamp null
);

insert into fetch_job (id, endpoint, cron, status)
values (1, '/issues', '0/5 * * * * ?', 'SCHEDULED');


create table raw_data
(
    id           serial primary key,
    data         jsonb     not null,
    created_at   timestamp not null default now(),
    fk_fetch_job serial    not null,
    foreign key (fk_fetch_job) references fetch_job (id)
);


insert into raw_data (id, data, fk_fetch_job)
values (1,
        '{
          "name": "John Akrivis",
          "age": 30
        }', 1);