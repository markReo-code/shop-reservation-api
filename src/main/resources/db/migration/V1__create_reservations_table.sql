create table reservations (
    id bigserial primary key,
    customer_name varchar(100) not null,
    customer_email varchar(255) not null,
    shop_name varchar(100) not null,
    reserved_at timestamp not null,
    status varchar(30) not null
);