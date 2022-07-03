INSERT INTO customer (name, address, email, telephone_number)
VALUES ('John Doe', '1111 Budapest Valami utca 34.', 'john.doe@email.hk', '+36151122334'),
       ('Jane Doe', '9000 Győr Virág utca 3.', 'jane.doe@gmail.kom', '+36111234567'),
       ('Joe Doe', '5000 Szolnok Valami utca 12.', 'john.doe@email.it', '+36229999999'),
       ('Jessica Doe', '2000 Szentendre Otthon utca 4.', 'jessica.doe@citromail.hun', '+36228888888'),
       ('Tim Doe', '3000 Hatvan Puszta tér 5.', 'tim.doe@email.fr', '+36337689130');

INSERT INTO product (name,description,inventory)
VALUES ('USB cable 5m','Cable with 5m length',5),
       ('USB cable 2m','Cable with 2m length',10),
       ('Energy Drink','Sugar and caffeine',15),
       ('Bacon','Meat',20),
       ('Mouse','Not the animal',25),
       ('Paper sheet','A plain sheet to write your thoughts',30),
       ('Air 5l','Priceless',35);

INSERT INTO one_order (customer_id, order_time)
VALUES (1,'2022-06-06 10:30'),
       (1,'2022-06-10 11:25'),
       (2,'2022-07-01 03:45'),
       (3,'2020-05-30 18:45'),
       (4,'2021-04-09 12:20'),
       (4,'2022-05-10 10:40');

INSERT INTO order_item (product_id, order_id, quantity)
VALUES (1,1,3),
       (2,2,4),
       (3,3,3),
       (4,4,5),
       (5,5,4),
       (6,6,1),
       (7,2,2),
       (7,1,77),
       (2,3,3),
       (3,4,5),
       (6,5,10);