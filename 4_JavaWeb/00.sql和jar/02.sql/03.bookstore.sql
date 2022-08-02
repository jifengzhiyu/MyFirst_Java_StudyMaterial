
CREATE DATABASE bookstoredb CHAR SET utf8;
USE bookstoredb;
CREATE TABLE t_user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  uname VARCHAR(20) NOT NULL UNIQUE,
  pwd VARCHAR(32) NOT NULL,
  email VARCHAR(50) DEFAULT NULL,
  role TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (id)
) CHARSET=utf8;

INSERT  INTO t_user(id,uname,pwd,email,role) 
VALUES (1,'lina','ok','lina@126.com',0),(2,'rose','ok','rose@sina.com.cn',1);


CREATE TABLE t_book (
  id INT(11) NOT NULL AUTO_INCREMENT,
  bookName VARCHAR(20) NOT NULL UNIQUE,
  author VARCHAR(20) NOT NULL,
  price DOUBLE(5,2) DEFAULT NULL,
  salCount INT(11) DEFAULT NULL,
  bookCount INT(11) DEFAULT NULL,
  bookImg VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
) CHARSET=utf8;
INSERT  INTO t_book(id,bookName,author,price,salCount,bookCount,bookImg) 
VALUES (1,'皮囊','蔡崇达',99.00,15,100,'pinang.jpg'),
(2,'看见','柴静',79.00,10,50,'kanjian.jpg'),
(3,'为奴十二年','鸠摩智',99.00,15,100,'weinushiernian.jpg'),
(4,'悟空传','唐僧',99.00,15,100,'wukongzhuan.jpg'),
(5,'小王子','周星驰',99.00,15,100,'xiaowangzi.jpg'),
(6,'硬派健身','阿朱',99.00,15,100,'yingpaijianshen.jpg'),
(7,'中国哲学史','冯友兰',99.00,15,100,'zhongguozhexueshi.jpg');

CREATE TABLE t_cart (
  id INT(11) NOT NULL AUTO_INCREMENT,
  book INT(11) NOT NULL,
  bookName VARCHAR(20) NOT NULL,
  price DOUBLE NOT NULL,
  buyCount INT(11) DEFAULT NULL,
  USER INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY PK_cart_book (book),
  KEY PK_cart_user (USER),
  CONSTRAINT PK_cart_book FOREIGN KEY (book) REFERENCES t_book (id),
  CONSTRAINT PK_cart_user FOREIGN KEY (USER) REFERENCES t_user (id)
) CHARSET=utf8;


CREATE TABLE t_order (
  id INT(11) NOT NULL AUTO_INCREMENT,
  orderNo VARCHAR(100) NOT NULL,
  orderDate DATETIME NOT NULL,
  orderMoney DOUBLE DEFAULT NULL,
  orderCount INT(11) DEFAULT NULL,
  orderStatus TINYINT(4) DEFAULT NULL,
  USER INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY orderNo (orderNo),
  KEY PK_order_user (USER),
  CONSTRAINT PK_order_user FOREIGN KEY (USER) REFERENCES t_user (id)
) CHARSET=utf8;


CREATE TABLE t_order_detail (
  id INT(11) NOT NULL AUTO_INCREMENT,
  book INT(11) DEFAULT NULL,
  bookName VARCHAR(20) NOT NULL,
  price DOUBLE NOT NULL,
  buyCount INT(11) DEFAULT NULL,
  orderBean INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT PK_order_detail_book FOREIGN KEY (book) REFERENCES t_book (id),
  CONSTRAINT PK_order_detail_order FOREIGN KEY (orderBean) REFERENCES t_order (id)
) CHARSET=utf8;