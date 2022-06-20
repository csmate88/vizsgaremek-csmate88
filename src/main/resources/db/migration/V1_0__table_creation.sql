DROP TABLE IF NOT EXISTS customer;
CREATE TABLE customer ( id identity NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, address varchar(255));