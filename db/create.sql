create table beers
(
    beer_id    int auto_increment primary key,
    name       varchar(50)    not null,
    abv        decimal(10, 1) not null,
    style_id   int            not null,
    created_by int            not null,
    constraint beers_styles_style_id_fk
        foreign key (style_id) references styles (style_id),
    constraint beers_users_user_id_fk
        foreign key (created_by) references users (user_id)

);

create table users
(
    user_id   int auto_increment primary key,
    username  varchar(50) not null,
    password  varchar(50) not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    email     varchar(50) not null,
    is_admin  boolean     not null
);

create table styles
(
    style_id int auto_increment primary key,
    name     varchar(50) not null
);

