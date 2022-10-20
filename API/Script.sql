DROP DATABASE IF EXISTS  onlinemedicinesystem;
CREATE DATABASE IF NOT EXISTS  onlinemedicinesystem;

USE onlinemedicinesystem;

DROP TABLE  IF EXISTS Patient;
CREATE TABLE IF NOT EXISTS Patient(
        patientId VARCHAR (6),
        firstName VARCHAR (255),
        userName VARCHAR (255),
        secondName VARCHAR (255),
        idNumber VARCHAR (45),
        passWord VARCHAR (45),
        email VARCHAR (45),
        address VARCHAR (255),
        birthday DATE,
        CONSTRAINT PRIMARY KEY (patientId)

);


INSERT INTO Patient VALUES('p001','nimal','nimal123','kalupahana','94556237v','#1234$:','nimal@gmail.com','colombo','1998-05-13');

DROP TABLE  IF EXISTS  Item;
CREATE  TABLE IF NOT EXISTS Item (
        itemCode VARCHAR (6),
        itemName VARCHAR (45),
        brandName VARCHAR (45),
        description VARCHAR(45),
        price DOUBLE,
        drugType VARCHAR (45),
        quantity DOUBLE,
        expireDate DATE,
        CONSTRAINT PRIMARY KEY (itemCode)

);


DESC Patient;
DESC Item;


DROP TABLE  IF EXISTS  Orders;
CREATE TABLE  IF NOT EXISTS orders(

        orderId VARCHAR(6),
        orderDate DATE,
        orderTotal DOUBLE,
        patientId VARCHAR (6),
        CONSTRAINT  PRIMARY KEY(orderId),
        CONSTRAINT FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE  CASCADE  ON UPDATE  CASCADE

);



DROP TABLE  IF  EXISTS OrderDetail;
CREATE  TABLE  IF NOT EXISTS  OrderDetail(

        orderDetailId VARCHAR (6),
        orderId VARCHAR(6),
        itemCode VARCHAR (6),
        quantity DOUBLE ,
        CONSTRAINT  PRIMARY  KEY (orderDetailId),
        CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orders(orderId) ON DELETE  CASCADE  ON UPDATE  CASCADE,
        CONSTRAINT FOREIGN KEY (itemCode) REFERENCES  Item(itemCode) ON DELETE  CASCADE  ON UPDATE  CASCADE






);

DESC Patient;
DESC Item;
DESC orders;
DESC OrderDetail;








