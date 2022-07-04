DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS order_item CASCADE;
DROP TABLE IF EXISTS one_order CASCADE;
DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE customer
(
    id               bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name             varchar(255) NOT NULL,
    address          varchar(255),
    email            varchar(255) UNIQUE,
    telephone_number varchar(255)
);

CREATE TABLE one_order
(
    id          bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    customer_id bigint,
    order_time  TIMESTAMP
);

CREATE TABLE product
(
    id          bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    name        varchar(255),
    description varchar(511),
    order_id bigint
);

ALTER TABLE one_order
    ADD CONSTRAINT fk_customer
        FOREIGN KEY (customer_id) REFERENCES customer;
ALTER TABLE product
    ADD CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES one_order;
