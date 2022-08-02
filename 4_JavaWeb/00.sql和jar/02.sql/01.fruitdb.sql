CREATE DATABASE fruitdb charset utf8;
USE fruitdb ;
CREATE TABLE `t_fruit` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `fcount` int(11) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

insert  into `t_fruit`(`fid`,`fname`,`price`,`fcount`,`remark`) 
values 
(1,'红富士',5,16,'红富士也是苹果!'),
(2,'大瓜',5,100,'王校长的瓜真香'),
(3,'南瓜',4,456,'水果真好吃'),
(4,'苦瓜',5,55,'苦瓜很好吃'),
(5,'莲雾',9,99,'莲雾是一种神奇的水果'),
(6,'羊角蜜',4,30,'羊角蜜是一种神奇的瓜'),
(7,'啃大瓜',13,123,'孤瓜');