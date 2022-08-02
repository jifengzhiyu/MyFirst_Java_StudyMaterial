
CREATE DATABASE bookdb CHAR SET utf8;
USE bookdb ;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookImg` varchar(200) NOT NULL,
  `bookName` varchar(20) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `saleCount` int(11) DEFAULT NULL,
  `bookCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */

insert  into `t_book`(`id`,`bookImg`,`bookName`,`price`,`author`,`saleCount`,`bookCount`) values (1,'cyuyanrumenjingdian.jpg','C语言入门经典',99.00,'亚历山大',8,197),(2,'santi.jpg','三体',48.95,'周杰伦',18,892),(3,'ailuntulingzhuan.jpg','艾伦图灵传',50.00,'刘若英',12,143),(4,'bainiangudu.jpg','百年孤独',40.00,'王力宏',3,98),(5,'biancheng.jpg','边城',30.00,'刘德华',2,99),(6,'jieyouzahuodian.jpg','解忧杂货店',27.00,'东野圭吾',5,100),(7,'zhongguozhexueshi.jpg','中国哲学史',45.00,'冯友兰',3,100),(8,'huranqiri.jpg','忽然七日',19.00,'劳伦',50,200),(9,'sudongpozhuan.jpg','苏东坡传',20.00,'林语堂',50,300),(10,'fusang.jpg','扶桑',20.00,'严歌岑',10,89),(11,'geihaizideshi.jpg','给孩子的诗',23.00,'北岛',5,99);


CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`uname`,`pwd`,`email`,`role`) values (1,'lina','ok','lina@sina.com.cn',0),(2,'kate','ok','hello_kate@126.com',1),(3,'鸠摩智','ok','jiujiu@126.com',0),(4,'宝2021','ok','bao2021@sohu.com.cn',0),(5,'宝2022','123','bao2022@sohu.com.cn',0);



/*Table structure for table `t_cart_item` */

CREATE TABLE `t_cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `userBean` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cart_book` (`book`),
  KEY `FK_cart_user` (`userBean`),
  CONSTRAINT `FK_cart_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
  CONSTRAINT `FK_cart_user` FOREIGN KEY (`userBean`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_cart_item` */

insert  into `t_cart_item`(`id`,`book`,`buyCount`,`userBean`) values (9,1,1,2),(10,5,1,1),(11,1,2,1),(12,2,13,1),(13,3,2,1),(14,4,1,1),(15,6,1,1),(16,7,1,1),(17,8,1,1),(18,9,1,1),(19,10,1,1),(20,11,4,1);

/*Table structure for table `t_order` */

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(128) NOT NULL,
  `orderDate` datetime DEFAULT NULL,
  `orderUser` int(11) DEFAULT NULL,
  `orderMoney` double(10,2) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `FK_order_user` (`orderUser`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`orderUser`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`orderNo`,`orderDate`,`orderUser`,`orderMoney`,`orderStatus`) values (4,'5eaab6146dc54e0482fdb8b6120c229b_20211025112519','2021-10-25 11:25:20',1,506.90,0),(5,'f5a22aac925d42eabc6b49c45a3eb74f_20211025113004','2021-10-25 11:30:04',1,48.95,0),(6,'8a245df4359e4224b531cf121c4acab3_20211025113019','2021-10-25 11:30:20',1,0.00,0),(7,'b521cd49ab2943f0bbc0630c95978f1c_20211025113039','2021-10-25 11:30:40',1,48.95,0),(8,'d4f366a82cd4491c9899b181753804b4_20211025113151','2021-10-25 11:31:52',1,48.95,0),(9,'8f5869a839f4483e947bd2c3163f3c23_20211025113159','2021-10-25 11:31:59',1,48.95,0),(10,'c5fcd95dbe7f49669f96b4ad6444ae6b_20211025120531','2021-10-25 12:05:32',1,147.95,0),(11,'6240ec3e5ac04e3583e1beb75a9e94ec_20211025120542','2021-10-25 12:05:42',1,147.95,0);

/*Table structure for table `t_order_item` */

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `orderBean` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_detail_book` (`book`),
  KEY `FK_detail_order` (`orderBean`),
  CONSTRAINT `FK_detail_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
  CONSTRAINT `FK_detail_order` FOREIGN KEY (`orderBean`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_item` */

insert  into `t_order_item`(`id`,`book`,`buyCount`,`orderBean`) values (6,1,1,4),(7,2,2,4),(8,10,1,4),(9,3,5,4),(10,4,1,4),(11,2,1,5),(12,2,1,7),(13,2,1,8),(14,2,1,9),(15,1,1,10),(16,2,1,10),(17,1,1,11),(18,2,1,11);

