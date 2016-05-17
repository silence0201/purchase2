# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.5.42)
# Database: purchase
# Generation Time: 2016-05-17 13:00:54 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table export
# ------------------------------------------------------------

DROP TABLE IF EXISTS `export`;

CREATE TABLE `export` (
  `exportID` int(11) NOT NULL AUTO_INCREMENT,
  `requestID` int(11) NOT NULL,
  `stockManID` varchar(255) NOT NULL,
  `exportTime` date NOT NULL,
  PRIMARY KEY (`exportID`),
  KEY `requestIDE` (`requestID`),
  KEY `stockManIDE` (`stockManID`),
  CONSTRAINT `requestIDE` FOREIGN KEY (`requestID`) REFERENCES `request` (`requestID`),
  CONSTRAINT `stockManIDE` FOREIGN KEY (`stockManID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `export` WRITE;
/*!40000 ALTER TABLE `export` DISABLE KEYS */;

INSERT INTO `export` (`exportID`, `requestID`, `stockManID`, `exportTime`)
VALUES
	(1001,1001,'S00001','2016-05-02'),
	(1002,1003,'S00001','2016-04-30'),
	(1003,1012,'S00002','2016-04-30'),
	(1004,1014,'S00001','2016-05-01'),
	(1005,1015,'S00002','2016-04-30'),
	(1006,1016,'S00002','2016-04-30');

/*!40000 ALTER TABLE `export` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table import
# ------------------------------------------------------------

DROP TABLE IF EXISTS `import`;

CREATE TABLE `import` (
  `importID` int(11) NOT NULL AUTO_INCREMENT,
  `orderID` int(11) NOT NULL,
  `stockManID` varchar(255) NOT NULL,
  `ImportTime` date NOT NULL,
  PRIMARY KEY (`importID`),
  KEY `stockManID` (`stockManID`),
  KEY `orderIDI` (`orderID`),
  CONSTRAINT `orderIDI` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`),
  CONSTRAINT `stockManIDI` FOREIGN KEY (`stockManID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `import` WRITE;
/*!40000 ALTER TABLE `import` DISABLE KEYS */;

INSERT INTO `import` (`importID`, `orderID`, `stockManID`, `ImportTime`)
VALUES
	(1001,1001,'S00002','2016-04-30'),
	(1002,1004,'S00001','2016-04-30'),
	(1003,1005,'S00001','2016-04-30'),
	(1004,1006,'S00002','2016-04-30');

/*!40000 ALTER TABLE `import` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(255) NOT NULL,
  `avePrice` double NOT NULL,
  `inventory` int(11) NOT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`itemID`, `itemName`, `avePrice`, `inventory`)
VALUES
	(1001,'锡膏',56,150),
	(1002,'红胶',12,200),
	(1003,'印刷电路版',33,200),
	(1004,'润滑油脂',89,100),
	(1005,'高温油',66,300),
	(1006,'焊接笔',50,29),
	(1007,'打印机',800,0),
	(1008,'锡线',160,13),
	(1009,'洗板水',230,32),
	(1010,'工业酒精',90,45),
	(1011,'硒鼓',270,73),
	(1012,'回形针',5,56),
	(1013,'票据夹',12,13),
	(1014,'固体胶',1.5,0),
	(1015,'印泥',10,56);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `planID` int(11) NOT NULL,
  `orderManID` varchar(255) NOT NULL,
  `orderTime` date NOT NULL,
  `orderStatus` varchar(255) NOT NULL DEFAULT '需采购',
  PRIMARY KEY (`orderID`),
  KEY `planIDO` (`planID`),
  KEY `orderManIDO` (`orderManID`),
  CONSTRAINT `orderManIDO` FOREIGN KEY (`orderManID`) REFERENCES `user` (`userID`),
  CONSTRAINT `planIDO` FOREIGN KEY (`planID`) REFERENCES `plan` (`planID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;

INSERT INTO `orders` (`orderID`, `planID`, `orderManID`, `orderTime`, `orderStatus`)
VALUES
	(1001,1001,'P00001','2016-05-03','采购完成'),
	(1002,1002,'P00001','2016-05-02','采购中'),
	(1003,1003,'P00002','2016-04-30','采购中'),
	(1004,1009,'P00001','2016-04-30','采购完成'),
	(1005,1011,'P00002','2016-04-30','采购完成'),
	(1006,1012,'P00001','2016-04-30','采购完成');

/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table plan
# ------------------------------------------------------------

DROP TABLE IF EXISTS `plan`;

CREATE TABLE `plan` (
  `planID` int(11) NOT NULL AUTO_INCREMENT,
  `itemID` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `totalCost` double(11,2) NOT NULL,
  `planStauts` varchar(255) NOT NULL DEFAULT '需受理',
  `planTime` date NOT NULL,
  PRIMARY KEY (`planID`),
  KEY `itemIDP` (`itemID`),
  CONSTRAINT `itemIDP` FOREIGN KEY (`itemID`) REFERENCES `item` (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;

INSERT INTO `plan` (`planID`, `itemID`, `number`, `totalCost`, `planStauts`, `planTime`)
VALUES
	(1001,1001,5,280.00,'已受理','2016-04-30'),
	(1002,1003,3,99.00,'已受理','2016-04-30'),
	(1003,1004,5,445.00,'已受理','2016-04-30'),
	(1004,1007,3,2400.00,'需受理','2016-04-30'),
	(1005,1006,20,1000.00,'有货','2016-04-30'),
	(1006,1014,50,60.00,'需受理','2016-04-30'),
	(1007,1009,30,6900.00,'需受理','2016-04-30'),
	(1008,1008,20,3200.00,'需受理','2016-04-30'),
	(1009,1013,10,120.00,'已受理','2016-04-30'),
	(1010,1011,20,5400.00,'有货','2016-04-30'),
	(1011,1008,4,640.00,'已受理','2016-04-30'),
	(1012,1015,20,200.00,'已受理','2016-04-30'),
	(1013,1001,42,7572.00,'需采购','2016-05-14'),
	(1014,1001,42,7572.00,'有货','2016-05-14'),
	(1015,1006,15,750.00,'有货','2016-05-14'),
	(1016,1004,30,6300.00,'有货','2016-05-14'),
	(1017,1014,20,350.00,'需受理','2016-05-14');

/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table provider
# ------------------------------------------------------------

DROP TABLE IF EXISTS `provider`;

CREATE TABLE `provider` (
  `providerID` int(11) NOT NULL,
  `providerName` varchar(255) NOT NULL,
  `contant` varchar(255) NOT NULL,
  `tele` varchar(255) NOT NULL,
  `provinces` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`providerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;

INSERT INTO `provider` (`providerID`, `providerName`, `contant`, `tele`, `provinces`, `address`)
VALUES
	(1001,'东莞永安科技有限公司','倪正文','13756234455','东莞','12号大街36号路口'),
	(1002,'博蓝电子有限公司','汉斐斐','13556224433','上海','12号大街36号路口'),
	(1003,'邦士电子有限公司','席秀美','13755336699','广州','12号大街36号路口'),
	(1004,'上海凝睿有限公司','丁高歌','13711556633','上海','12号大街36号路口'),
	(1005,'捷科电子有限公司','力高明','13777556633','北京','12号大街36号路口'),
	(1006,'东安文具有限公司','从芳洁','13777556622','杭州','12号大街36号路口'),
	(1007,'苏光文具有限公司','荣和泽','13711223333','北京','12号大街36号路口'),
	(1008,'光谷文具有限公司','钟楼乐','18566345269','深圳','12号大街36号路口');

/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table provideritem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `provideritem`;

CREATE TABLE `provideritem` (
  `providerItemID` int(11) NOT NULL AUTO_INCREMENT,
  `providerID` int(11) NOT NULL,
  `itemID` int(11) NOT NULL,
  `quality` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`providerItemID`),
  KEY `providerIDPI` (`providerID`),
  KEY `itemIDPI` (`itemID`),
  CONSTRAINT `itemIDPI` FOREIGN KEY (`itemID`) REFERENCES `item` (`itemID`),
  CONSTRAINT `providerIDPI` FOREIGN KEY (`providerID`) REFERENCES `provider` (`providerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `provideritem` WRITE;
/*!40000 ALTER TABLE `provideritem` DISABLE KEYS */;

INSERT INTO `provideritem` (`providerItemID`, `providerID`, `itemID`, `quality`, `price`)
VALUES
	(1001,1001,1001,'A',56),
	(1002,1001,1006,'A',50),
	(1003,1001,1007,'A',800),
	(1004,1002,1003,'B',30),
	(1005,1002,1004,'A',89),
	(1006,1003,1003,'A',33),
	(1007,1003,1008,'A',160),
	(1008,1004,1002,'B',10),
	(1009,1004,1005,'B',60),
	(1010,1005,1002,'A',12),
	(1011,1005,1005,'A',66),
	(1012,1006,1009,'A',230),
	(1013,1006,1010,'A',90),
	(1014,1006,1011,'B',250),
	(1015,1007,1011,'A',270),
	(1016,1007,1012,'A',5),
	(1017,1007,1013,'A',12),
	(1018,1007,1014,'A',1.5),
	(1019,1008,1015,'A',56);

/*!40000 ALTER TABLE `provideritem` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `requestID` int(11) NOT NULL AUTO_INCREMENT,
  `itemID` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `totalCost` double(11,2) NOT NULL,
  `requestManID` varchar(255) NOT NULL,
  `requestTime` date NOT NULL,
  `requestStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '未审核',
  `auditorID` varchar(255) DEFAULT NULL,
  `auditTime` date DEFAULT NULL,
  `planID` int(11) DEFAULT NULL,
  `reason` varchar(255) NOT NULL,
  PRIMARY KEY (`requestID`),
  KEY `itemIDR` (`itemID`),
  KEY `planIDR` (`planID`),
  KEY `requestManIDR` (`requestManID`),
  KEY `auditorIDR` (`auditorID`),
  CONSTRAINT `auditorIDR` FOREIGN KEY (`auditorID`) REFERENCES `user` (`userID`),
  CONSTRAINT `itemIDR` FOREIGN KEY (`itemID`) REFERENCES `item` (`itemID`),
  CONSTRAINT `planIDR` FOREIGN KEY (`planID`) REFERENCES `plan` (`planID`),
  CONSTRAINT `requestManIDR` FOREIGN KEY (`requestManID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;

INSERT INTO `request` (`requestID`, `itemID`, `number`, `totalCost`, `requestManID`, `requestTime`, `requestStatus`, `auditorID`, `auditTime`, `planID`, `reason`)
VALUES
	(1001,1001,3,168.00,'R00011','2016-05-02',X'E68B92E7BB9D','P00001','2016-05-30',NULL,'采购合理'),
	(1002,1003,3,99.00,'R00011','2016-05-01',X'E588B0E8B4A7','P00001','2016-04-30',1002,'采购合理'),
	(1003,1001,2,112.00,'R00011','2016-05-02',X'E8AEA1E58892','P00002','2016-04-30',1001,'采购合理'),
	(1004,1004,5,455.00,'R00021','2016-05-05',X'E8AEA1E58892','P00001','2016-04-30',1003,'采购合理'),
	(1005,1007,3,2400.00,'R00022','2016-05-06',X'E68B92E7BB9D','D00002','2016-05-16',1004,'太贵啦'),
	(1006,1006,15,750.00,'R00012','2016-04-30',X'E8AEA1E58892','P00002','2016-05-11',1015,'正在审核请耐心等待'),
	(1008,1014,50,60.00,'R00021','2016-04-30',X'E8AEA1E58892','P00001','2016-05-11',1006,'正在审核请耐心等待'),
	(1009,1007,30,6900.00,'R00032','2016-04-30',X'E8AEA1E58892','M00001','2016-05-11',1007,'审核通过'),
	(1010,1008,15,2400.00,'R00032','2016-04-30',X'E8AEA1E58892','P00002','2016-05-11',1008,'正在审核请耐心等待'),
	(1011,1008,5,800.00,'R00031','2016-04-30',X'E8AEA1E58892','P00001','2016-05-11',1008,'正在审核请耐心等待'),
	(1012,1013,10,120.00,'R00041','2016-04-30',X'E5AE8CE68890','P00001','2016-04-30',1009,'采购合理'),
	(1013,1011,20,5400.00,'R00042','2016-04-30',X'E8AEA1E58892','P00002','2016-04-30',1010,'正在审核请耐心等待'),
	(1014,1008,4,460.00,'R00051','2016-04-30',X'E5AE8CE68890','P00002','2016-04-30',1011,'采购合理'),
	(1015,1015,15,150.00,'R00051','2016-04-30',X'E5AE8CE68890','P00001','2016-04-30',1012,'采购合理'),
	(1016,1015,5,50.00,'R00052','2016-04-30',X'E5AE8CE68890','P00001','2016-04-30',1012,'采购合理'),
	(1017,1001,12,672.00,'R00012','2016-05-06',X'E8AEA1E58892','P00001','2016-05-01',1014,'没有理由'),
	(1018,1001,30,6900.00,'R00021','2016-05-06',X'E8AEA1E58892','M00001','2016-05-02',1014,''),
	(1022,1004,30,6300.00,'R00021','2016-05-07',X'E8AEA1E58892','M00001','2016-05-09',1016,''),
	(1023,1014,20,350.00,'R00011','2016-05-05',X'E8AEA1E58892','P00001','2016-05-09',1017,''),
	(1024,1001,11,616.00,'R00011','2016-05-15',X'E9809AE8BF87','P00001','2015-05-15',NULL,'需要'),
	(1031,1001,15,840.00,'R00011','2016-05-15',X'E69CAAE5AEA1E6A0B8',NULL,NULL,NULL,'需哦也\r\n'),
	(1034,1004,12,1068.00,'R00011','2016-05-16',X'E69C89E8B4A7',NULL,NULL,NULL,'需要润滑油'),
	(1035,1007,12,9600.00,'R00011','2016-05-17',X'E9809AE8BF87','M00001','2016-05-17',NULL,'允许购买打印机');

/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(255) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `tele` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`userID`, `passWord`, `userName`, `position`, `tele`, `email`)
VALUES
	('D00001','123456789','李浩鹏',X'E5B882E59CBAE983A8E997A8E7BB8FE79086','13712365633','yhp@pecr.com'),
	('D00002','123456789','李铁刚',X'E8B4A2E58AA1E983A8E997A8E7BB8FE79086','13756633284','ytg@pecr.com'),
	('D00003','123456789','李文轩',X'E7949FE4BAA7E983A8E997A8E7BB8FE79086','13566325698','ywx@pecr.com'),
	('D00004','123456789','李慧',X'E7A094E58F91E983A8E997A8E7BB8FE79086','18956332289','yh@pecr.com'),
	('D00005','123456789','李子豪',X'E99480E594AEE983A8E997A8E7BB8FE79086','13914476959','lzh@pecr.com'),
	('M00001','123456789','赵彦龙',X'E680BBE7BB8FE79086','1375632489','zyl@pecr.com'),
	('P00001','123456789','无子安',X'E98787E8B4ADE59198','13214955374','wza@pecr.com'),
	('P00002','123456789','将玉山',X'E98787E8B4ADE59198','13606451724','jys@pecr.com'),
	('R00011','123456789','王长旭',X'E5B882E59CBAE983A8E997A8E794B3E8AFB7E59198','13371653414','wcx@pecr.com'),
	('R00012','123456789','王鸿朗',X'E5B882E59CBAE983A8E997A8E794B3E8AFB7E59198','13091099159','whl@pecr.com'),
	('R00021','123456789','宋运骏',X'E8B4A2E58AA1E983A8E997A8E794B3E8AFB7E59198','13131494168','syy@pecr.com'),
	('R00022','123456789','宋志专',X'E8B4A2E58AA1E983A8E997A8E794B3E8AFB7E59198','13869891844','szc@pecr.com'),
	('R00031','123456789','宫正祥',X'E7949FE4BAA7E983A8E997A8E794B3E8AFB7E59198','13482859232','gzx@pecr.com'),
	('R00032','123456789','红子平',X'E7949FE4BAA7E983A8E997A8E794B3E8AFB7E59198','13704095804','hzp@pecr.com'),
	('R00041','123456789','肖晋鹏',X'E7A094E58F91E983A8E997A8E794B3E8AFB7E59198','13849971987','xjp@pecr.com'),
	('R00042','123456789','傅和志',X'E7A094E58F91E983A8E997A8E794B3E8AFB7E59198','13471835919','phz@pecr.com'),
	('R00051','123456789','田绍元',X'E99480E594AEE983A8E997A8E794B3E8AFB7E59198','13858579863','tsy@pecr.com'),
	('R00052','123456789','白心水',X'E99480E594AEE983A8E997A8E794B3E8AFB7E59198','13396305510','bxs@pecr.com'),
	('S00001','123456789','丹修然',X'E5BA93E7AEA1E59198','13575230336','dxr@pecr.com'),
	('S00002','123456789','业运凯',X'E5BA93E7AEA1E59198','13050824494','yyh@pecr.com');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
