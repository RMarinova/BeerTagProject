INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (1, 'Glarus English Ale', 4.6, 3, 3);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (2, 'Rhombus Porter', 5.0, 2, 2);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (3, 'Opasen char', 6.6, 1, 1);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (4, 'Ailyak', 6.6, 1, 2);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (5, 'Basi Kefa', 6.7, 2, 3);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (6, 'Vitoshko lale', 5.5, 3, 2);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (7, 'Divo Pivo', 5.5, 2, 1);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (8, 'Bloody Muddy', 5.0, 1, 3);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (9, 'Black Head', 5.0, 3, 1);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (10, 'Pilsner Urquell', 4.4, 2, 3);


INSERT INTO beers.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (1, 'ivan', 'pass1234', 'Ivan', 'Ivanov', 'ivan@email.com', true);

INSERT INTO beers.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (2, 'petya', 'pass1234', 'Petya', 'Peteva', 'petya@email.com', false);

INSERT INTO beers.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (3, 'pesho', 'pass1234', 'Pesho', 'Peshev', 'pesho@email.com', false);


INSERT INTO beers.styles (style_id, name)
VALUES (1, 'Special Ale');

INSERT INTO beers.styles (style_id, name)
VALUES (2, 'English Porter');

INSERT INTO beers.styles (style_id, name)
VALUES (3, 'Indian Pale Ale');
