INSERT INTO customer (name, address, email, telephone_number)
VALUES ('John Doe', '1111 Budapest Valami utca 34.', 'john.doe@email.hk', '+36-15-1122334'),
       ('Jane Doe', '9000 Győr Virág utca 3.', 'jane.doe@gmail.kom', '+36-11-1234567'),
       ('Joe Doe', '5000 Szolnok Valami utca 12.', 'john.doe@email.it', '+36229999999'),
       ('Jessica Doe', '2000 Szentendre Otthon utca 4.', 'jessica.doe@citromail.hun', '+36-22-8888888'),
       ('Tim Doe', '3000 Hatvan Puszta tér 5.', 'tim.doe@email.fr', '+36-33-7689130');

INSERT INTO one_order (customer_id, order_time)
VALUES (1, '2022-06-06 10:30'),
       (1, '2022-06-10 11:25'),
       (2, '2022-07-01 03:45'),
       (3, '2020-05-30 18:45'),
       (4, '2021-04-09 12:20'),
       (4, '2022-05-10 10:40');

INSERT INTO product (name, description, order_id)
VALUES ('USB cable 5m', 'Cable with 5m length', 1),
       ('USB cable 2m', 'Cable with 2m length', 2),
       ('Energy Drink', 'Sugar and caffeine', 3),
       ('Bacon', 'Meat', 4),
       ('Mouse', 'Not the animal', 5),
       ('Paper sheet', 'A plain sheet to write your thoughts', 6),
       ('Air 5l', 'Priceless', 2),
       ('Air 5l', 'Priceless', 1),
       ('USB cable 2m', 'Cable with 2m length', 3),
       ('Energy Drink', 'Sugar and caffeine', 4),
       ('Paper sheet', 'A plain sheet to write your thoughts', 5);