DROP DATABASE BOOKSTORE;
CREATE DATABASE bookstore;
USE bookstore;

CREATE TABLE Users (
  userID VARCHAR(50),
  pwd VARCHAR(150),
  salt VARCHAR(150),
  email VARCHAR(50),
  firstName VARCHAR(50),
  lastName VARCHAR(50),
  usercategory VARCHAR(1),
  USER_ADDR_LN1 VARCHAR(100),
  USER_ADDR_LN2 VARCHAR(100),
  ZIP VARCHAR(10),
  CITY VARCHAR(50),
  State VARCHAR(50),
  country varchar(50),
  cctype varchar(50),
  ccnumber varchar(50),
  Phonenumber varchar(15),
  ccexpiration varchar(50),
  
  PRIMARY KEY(userID) 
);

INSERT INTO USERS (USERID, PWD, EMAIL, FIRSTNAME, LASTNAME, USERCATEGORY, USER_ADDR_LN1, USER_ADDR_LN2, ZIP, PHONENUMBER)
VALUES
('aakashpanjwani', 'newpass123', 'aakash@gmail.com', 'Aakash Suresh', 'Panjwani', 'A', '1190 ALFROTH LN', 'CHARLOTTE', '28273-4906', '980-167-1990');  

INSERT INTO USERS (USERID, PWD, EMAIL, FIRSTNAME, LASTNAME, USERCATEGORY, USER_ADDR_LN1, USER_ADDR_LN2, ZIP, PHONENUMBER)
VALUES
('sandraduncan', 'newpass123', 'sandra@gmail.com', 'Sandra', 'Duncan', 'C', '1191 ALFROTH LN', 'CHARLOTTE', '28273-4907', '980-167-1991');  

INSERT INTO USERS (USERID, PWD, EMAIL, FIRSTNAME, LASTNAME, USERCATEGORY, USER_ADDR_LN1, USER_ADDR_LN2, ZIP, PHONENUMBER)
VALUES
('michellescott', 'newpass123', 'pinkplane@gmail.com', 'Michelle', 'Scott', 'C', '1192 ALFROTH LN', 'CHARLOTTE', '28273-4908', '980-167-1992');  

INSERT INTO USERS (USERID, PWD, EMAIL, FIRSTNAME, LASTNAME, USERCATEGORY, USER_ADDR_LN1, USER_ADDR_LN2, ZIP, PHONENUMBER)
VALUES
('stevejim11', 'newpass123', 'footballfan@gmail.com', 'Steve James', 'Jim', 'C', '1193 ALFROTH LN', 'CHARLOTTE', '28273-4909', '980-167-1993');  

Create table Authors (
AuthorID INT auto_increment,
AuthorName varchar(100),
AuthorInfo VARCHAR(100),
Primary key (AuthorID)
);

insert into Authors (authorname, authorinfo) values ('James Lewonski','Author is relatively new and has published only 1 book');
insert into Authors (authorname, authorinfo) values ('Sandra Duncan','Author is relatively new and has published only 1 book');

Create Table Books (
bookID INT NOT NULL auto_increment,
authorID INT,
title VARCHAR(100),
ISBN VARCHAR(100),
genre VARCHAR(50),
type VARCHAR(50),
publicationYear INT,
price Decimal(10,2),
bookCondition VARCHAR(8),
PRIMARY KEY(bookID),
Foreign key (AuthorID) References Authors(AuthorID)
);

insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (1, "Norwegian Wood","9788020711939","Fiction","Paperback",1987,5.82,"New");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Alice in Icicycle","9788020711940","Science","Paperback",1990,10.14,"Used");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Technological Advancements","9788020711941","Techonology","Paperback",1991,8.92,"New");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Bhoot in the Yard","9788020711942","Horror","Paperback",1992,12.67,"New");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Watch the Jupiter","9788020711943","Astronomy","Paperback",1993,7.9,"Used");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Doing it Yourself - Tables","9788020711944","Furniture","Paperback",1994,45.89,"Used");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Make your dream house","9788020711945","House","Paperback",1995,12.67,"Used");	
insert into Books (authorID, title, isbn, genre, type, publicationyear, price, bookcondition) values (2, "Organizing the Drawers","9788020711946","Kitchen","Paperback",1996,12.9,"New");	

CREATE TABLE ORDERS
(
ORDERID INT NOT NULL AUTO_INCREMENT,
USERID VARCHAR(50),
ORDERDATE DATE,
ORDERTOTAL DECIMAL(5,2),
PRIMARY KEY (ORDERID),
FOREIGN KEY (USERID) REFERENCES USERS(USERID)
);

INSERT INTO ORDERS (USERID, ORDERDATE, ORDERTOTAL) VALUES ('aakashpanjwani', STR_TO_DATE('11/06/2016', '%m/%d/%Y'), 10.26);
INSERT INTO ORDERS (USERID, ORDERDATE, ORDERTOTAL) VALUES ('sandraduncan', STR_TO_DATE('11/07/2016', '%m/%d/%Y'), 5.19);
INSERT INTO ORDERS (USERID, ORDERDATE, ORDERTOTAL) VALUES ('aakashpanjwani', STR_TO_DATE('11/08/2016', '%m/%d/%Y'), 9.62);
INSERT INTO ORDERS (USERID, ORDERDATE, ORDERTOTAL) VALUES ('sandraduncan', STR_TO_DATE('11/10/2016', '%m/%d/%Y'), 11.90);



CREATE TABLE CART
(
CARTID INT NOT NULL auto_increment,
USERID VARCHAR(50),
BOOKID INT,
QUantity INT,
PRIMARY KEY (CARTID)
);

CREATE TABLE ORDERDETAILS
(
OrderID	int(11),
USERID	varchar(50),
Quantity	int(11),
BookID	int(11),

Foreign key (orderID) references Orders(OrderID)
);
COMMIT;