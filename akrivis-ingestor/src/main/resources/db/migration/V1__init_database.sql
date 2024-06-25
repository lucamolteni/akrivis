create table raw_data (
    id serial primary key,
    data jsonb not null,
    created_at timestamp not null default now()
);


insert into raw_data (id, data) values (1, '{"name": "John Akrivis", "age": 30}');