-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.21    Database: bachhoa
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NOT NULL,
  `roleID` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `employeeId` (`employeeId`),
  KEY `roleID` (`roleID`),
  CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employees` (`employeeID`),
  CONSTRAINT `authority_ibfk_2` FOREIGN KEY (`roleID`) REFERENCES `roles` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3013 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (3001,3,'qlch'),(3002,4,'bhoa'),(3003,1,'qlch'),(3004,2,'bhoa'),(3012,22,'qlch');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_details`
--

DROP TABLE IF EXISTS `bill_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_details` (
  `billID` varchar(18) NOT NULL,
  `productID` varchar(24) NOT NULL,
  `quantity` int DEFAULT NULL,
  `quantityGift` int DEFAULT '0',
  `totalAmount` float DEFAULT NULL,
  PRIMARY KEY (`billID`,`productID`),
  KEY `productID` (`productID`),
  CONSTRAINT `billdetail_ibfk_1` FOREIGN KEY (`billID`) REFERENCES `bills` (`billID`),
  CONSTRAINT `billdetail_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_details`
--

LOCK TABLES `bill_details` WRITE;
/*!40000 ALTER TABLE `bill_details` DISABLE KEYS */;
INSERT INTO `bill_details` VALUES 
('1700646318982','8934456220031',1,0,4189500),
('1700646594858','8934456220031',1,0,4189500),
('1700646688587','8934588063060',1,0,315000),
('1700646707946','8934456220031',1,0,4189500),
('1700646739258','8934456220031',1,0,4189500),
('1700658147234','8934456220031',1,0,4189500),
('1700658641561','8934456220031',1,0,4189500),
('1700664690937','8934456220031',4,2,16758000),
('1700664690937','8934807006679',1,0,157500),
('1700907381642','8938972063060',1,0,96600),
('1699465515708','8938972063060',1,0,15750.0),
('1699465515708','8935526000012',1,0,15750.0),
('1699465515708','8936500366375',1,0,30450.0),
('1699465535104','8935526000012',1,0,15750.0),
('1699465535104','8938972063060',5,0,162750.0),
('1699465535104','8936500366375',3,0,78750.0),
('1699465628120','8938972063060',3,0,47250.0),
('1699465628120','8936500366375',3,0,97650.0);
/*!40000 ALTER TABLE `bill_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `billID` varchar(18) NOT NULL,
  `storeID` int DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  `totalAmount` float DEFAULT NULL,
  `timeCreate` datetime DEFAULT NULL,
  `cash` float DEFAULT NULL,
  `reduced` float DEFAULT NULL,
  PRIMARY KEY (`billID`),
  KEY `employeeID` (`employeeID`),
  KEY `storeID` (`storeID`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`),
  CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES ('1700646318982',1,3,4189,'2023-11-22 16:49:55',5000000,0),('1700646594858',1,3,4189,'2023-11-22 16:51:06',5000000,0),('1700646688587',1,3,315000,'2023-11-22 16:51:39',500000,0),('1700646707946',1,3,4189500,'2023-11-22 16:52:16',5000000,0),('1700646739258',1,3,4189500,'2023-11-22 17:18:39',5000000,0),('1700658147234',1,3,4189,'2023-11-22 20:02:49',5000000,0),('1700658641561',1,3,4189500,'2023-11-22 20:10:48',5000000,0),('1700664690937',2,22,25294500,'2023-11-22 22:02:56',20000000,8379000),('1700907381642',1,3,96600,'2023-11-25 18:19:05',500000,0);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`nd`@`localhost`*/ /*!50003 TRIGGER `tg_delete_bill` BEFORE DELETE ON `bills` FOR EACH ROW delete from bill_details where billID = old.billID */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `categoriesID` int NOT NULL AUTO_INCREMENT,
  `categoriesName` varchar(255) NOT NULL,
  PRIMARY KEY (`categoriesID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Thực phẩm khô'),(2,'Nhà bếp'),(3,'Nước giải khát'),(4,'Bánh kẹo'),(5,'Giặc rửa'),(6,'Gia dụng');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_note`
--

DROP TABLE IF EXISTS `delivery_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_note` (
  `id` varchar(18) NOT NULL,
  `storeID` int NOT NULL,
  `employeeID` int NOT NULL,
  `timeCreate` datetime DEFAULT NULL,
  `timeCompleted` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `storeID` (`storeID`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `delivery_note_ibfk_1` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`),
  CONSTRAINT `delivery_note_ibfk_2` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note`
--

LOCK TABLES `delivery_note` WRITE;
/*!40000 ALTER TABLE `delivery_note` DISABLE KEYS */;
INSERT INTO bachhoa.delivery_note (id,storeID,employeeID,timeCreate,timeCompleted) VALUES
	 ('1',1,3,'2023-10-11 00:00:00','2023-10-12 00:00:00'),
	 ('10',1,2,'2023-11-12 00:00:00',NULL),
	 ('2',1,4,'2021-01-12 00:00:00','2021-01-14 00:00:00'),
	 ('20231112204732',1,3,'2023-11-12 20:47:32',NULL),
	 ('20231113202730',1,3,'2023-11-13 20:27:30',NULL),
	 ('20231113203728',1,3,'2023-11-13 20:37:28',NULL),
	 ('20231113204642',1,3,'2023-11-13 20:46:42',NULL),
	 ('20231113210344',1,3,'2023-11-13 21:03:45',NULL),
	 ('20231113210545',1,3,'2023-11-13 21:05:45',NULL),
	 ('20231113211144',1,3,'2023-11-13 21:11:45',NULL);
INSERT INTO bachhoa.delivery_note (id,storeID,employeeID,timeCreate,timeCompleted) VALUES
	 ('20231113211208',1,3,'2023-03-13 21:12:09',NULL),
	 ('20231113211748',1,3,'2023-09-13 21:17:48',NULL),
	 ('20231113212028',1,3,'2023-01-13 21:20:29',NULL),
	 ('20231113212222',1,3,'2023-12-13 21:22:23',NULL),
	 ('20231113213220',1,3,'2023-11-13 21:32:21',NULL),
	 ('20231113220156',1,3,'2023-06-15 22:01:57',NULL),
	 ('20231118132509',1,3,'2023-11-18 13:25:10',NULL),
	 ('20231120234520',1,3,'2023-11-20 23:45:21',NULL),
	 ('20231122150509',1,3,'2023-11-22 15:05:10',NULL),
	 ('3',1,3,'2023-10-24 00:00:00','2023-10-25 00:00:00');
INSERT INTO bachhoa.delivery_note (id,storeID,employeeID,timeCreate,timeCompleted) VALUES
	 ('4',1,2,'2021-06-30 00:00:00','2021-07-01 00:00:00'),
	 ('5',1,1,'2023-07-31 00:00:00','2023-08-01 00:00:00'),
	 ('6',1,4,'2023-01-17 00:00:00','2023-01-18 00:00:00'),
	 ('7',1,2,'2020-12-17 00:00:00','2020-12-18 00:00:00'),
	 ('8',1,1,'2021-01-13 00:00:00','2021-01-14 00:00:00'),
	 ('9',1,4,'2023-05-02 00:00:00','2023-05-03 00:00:00');
/*!40000 ALTER TABLE `delivery_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailed_delivery_note`
--

DROP TABLE IF EXISTS `detailed_delivery_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detailed_delivery_note` (
  `id` varchar(18) NOT NULL,
  `productID` varchar(25) NOT NULL,
  `quantity` int DEFAULT NULL,
  `index` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`,`productID`),
  KEY `productID` (`productID`),
  CONSTRAINT `detailed_delivery_note_ibfk_1` FOREIGN KEY (`id`) REFERENCES `delivery_note` (`id`),
  CONSTRAINT `detailed_delivery_note_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailed_delivery_note`
--

LOCK TABLES `detailed_delivery_note` WRITE;
/*!40000 ALTER TABLE `detailed_delivery_note` DISABLE KEYS */;
INSERT INTO bachhoa.detailed_delivery_note (id,productID,quantity,`index`,count) VALUES
	 ('10','8935149556083',160,2,150),
	 ('10','8934588063060',130,3,40),
	 ('10','8935500602357',50,1,0),
	 ('20231112204732','8934588063060',10,1,0),
	 ('20231112204732','8935500602357',10,1,0),
	 ('20231113202730','8935149556083',6,1,0),
	 ('20231113202730','8935526000012',100,2,10),
	 ('20231113202730','8936096670021',1,3,0),
	 ('20231113203728','8935526000012',1,5,0),
	 ('20231113203728','8936096670021',1,4,0);
INSERT INTO bachhoa.detailed_delivery_note (id,productID,quantity,`index`,count) VALUES
	 ('20231113203728','8934807006679',1,2,0),
	 ('20231113203728','8936500366375',1,1,0),
	 ('20231113204642','8935149556083',1,1,0),
	 ('20231113204642','8935526000012',1,2,0),
	 ('20231113204642','8936500366375',1,3,0),
	 ('20231113204642','8934807006679',1,4,0),
	 ('20231113210344','8934807006679',1,1,0),
	 ('20231113210344','8935512694520',1,2,0),
	 ('20231113210545','8934588063176',1,1,1);
INSERT INTO bachhoa.detailed_delivery_note (id,productID,quantity,`index`,count) VALUES
	 ('20231113210545','8935500602357',1,2,0),
	 ('20231113210545','6955807973986',1,3,0),
	 ('20231113213220','8935526000012',1,3,0),
	 ('20231113213220','8934588063176',1,1,0),
	 ('20231113213220','8936500366375',1,2,1);
INSERT INTO bachhoa.detailed_delivery_note (id,productID,quantity,`index`,count) VALUES
	 ('20231113213220','8935500602357',1,5,0),
	 ('20231113213220','8936050360974',1,4,0),
	 ('20231113220156','6955807973986',6,1,0),
	 ('20231113220156','8935149556083',100,2,90),
	 ('20231113220156','8938972063060',1,3,1),
	 ('20231118132509','8936050360974',500,3,0),
	 ('20231118132509','8934456220031',200,1,0),
	 ('20231118132509','6955807973986',200,2,0),
	 ('20231120234520','8934456220031',1,1,0),
	 ('20231120234520','6955807973986',1,2,0);
INSERT INTO bachhoa.detailed_delivery_note (id,productID,quantity,`index`,count) VALUES
	 ('20231120234520','8935526000012',1,3,0),
	 ('20231120234520','9314807006679',1,4,0),
	 ('20231122150509','6955807973986',1,1,0),
	 ('20231122150509','8935149556083',1,2,0),
	 ('20231122150509','9314807006679',1,3,0),
	 ('20231122150509','8938972063060',1,4,0);
/*!40000 ALTER TABLE `detailed_delivery_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_details`
--

DROP TABLE IF EXISTS `discount_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount_details` (
  `disID` varchar(12) NOT NULL,
  `storeID` int NOT NULL,
  `productID` varchar(24) NOT NULL,
  `activity` bit(1) DEFAULT NULL,
  `startTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  PRIMARY KEY (`disID`,`storeID`,`productID`),
  KEY `storeID` (`storeID`),
  KEY `productID` (`productID`),
  CONSTRAINT `discountdetails_ibfk_1` FOREIGN KEY (`disID`) REFERENCES `discounts` (`disID`),
  CONSTRAINT `discountdetails_ibfk_2` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`),
  CONSTRAINT `discountdetails_ibfk_3` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_details`
--

LOCK TABLES `discount_details` WRITE;
/*!40000 ALTER TABLE `discount_details` DISABLE KEYS */;
INSERT INTO `discount_details` VALUES ('2T1',1,'8934456220031',_binary '','2023-11-01','2023-12-01'),('2T1',1,'8936050360974',_binary '','2023-11-18','2023-11-12'),('S25',1,'8934807006679',_binary '','2023-11-04','2023-12-03'),('S25',1,'8935578640025',_binary '','2023-11-25','2023-11-30'),('S50',1,'6955807973986',_binary '','2023-11-01','2023-11-30'),('S50',1,'8935500602357',_binary '','2023-11-05','2023-12-08');
/*!40000 ALTER TABLE `discount_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `disID` varchar(12) NOT NULL,
  `discountType` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`disID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES ('2T1','Mua 2 tặng 1'),('S25','Giảm giá 25%'),('S50','Giảm giá 50%');
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `display_platters`
--

DROP TABLE IF EXISTS `display_platters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `display_platters` (
  `disPlaID` int NOT NULL,
  `rowName` varchar(10) DEFAULT NULL,
  `storeID` int NOT NULL,
  PRIMARY KEY (`storeID`,`disPlaID`),
  KEY `storeID` (`storeID`),
  CONSTRAINT `displayplatter_ibfk_1` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `display_platters`
--

LOCK TABLES `display_platters` WRITE;
/*!40000 ALTER TABLE `display_platters` DISABLE KEYS */;
INSERT INTO `display_platters` VALUES (1,'Mâm 1',1),(2,'Mâm 2',1),(3,'Mâm 3',1),(4,'Mâm 4',1),(5,'Mâm 5',1);
/*!40000 ALTER TABLE `display_platters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `display_shelves`
--

DROP TABLE IF EXISTS `display_shelves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `display_shelves` (
  `disSheID` int NOT NULL,
  `shelfName` varchar(12) DEFAULT NULL,
  `storeID` int NOT NULL,
  PRIMARY KEY (`disSheID`,`storeID`),
  KEY `storeID` (`storeID`),
  CONSTRAINT `displayshelves_ibfk_1` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `display_shelves`
--

LOCK TABLES `display_shelves` WRITE;
/*!40000 ALTER TABLE `display_shelves` DISABLE KEYS */;
INSERT INTO `display_shelves` VALUES (1,'Kệ 1',1),(2,'Kệ 2',1),(3,'Kệ 3',1),(4,'Kệ 4',1),(5,'Kệ 5',1),(6,'Kệ 6',1),(7,'Kệ 7',1),(8,'Kệ 8',1);
/*!40000 ALTER TABLE `display_shelves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employeeID` int NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(255) DEFAULT NULL,
  `age` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `pictureURL` varchar(255) DEFAULT NULL,
  `firstWork` date DEFAULT NULL,
  `storeID` int DEFAULT NULL,
  `roleID` varchar(15) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`employeeID`),
  KEY `roleID` (`roleID`),
  KEY `storeID` (`storeID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `roles` (`roleID`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Nguyễn Minh Thư','2003-11-02','BÌnh ĐỊnh',NULL,'2022-03-19',1,'qlch',_binary '',NULL,NULL),(2,'Nguyễn Minh Thư','2003-11-02','BÌnh ĐỊnh',NULL,'2022-03-19',1,'qlch',_binary '',NULL,NULL),(3,'Đồng Võ Nghiệp','2003-01-04','Ho Chi Minh','http://192.168.1.4:8081/bachhoaimg/avatar5-min.png','2022-03-19',1,'qlch',_binary '','dongnghiepit@gmail.com','$2a$12$t.mfGk8I1bVHoRfm0RZ0beFZX7fmNc2GPCldK8WBr6KMoZxf7.xk2'),(4,'NST Phúc','2003-04-21','Bình Dương','nstp.png','2023-10-10',1,'bhoa',_binary '','phucnstps20362@fpt.edu.vn','$2a$12$t.mfGk8I1bVHoRfm0RZ0beFZX7fmNc2GPCldK8WBr6KMoZxf7.xk2'),(22,'Thành công',NULL,NULL,NULL,'2023-11-22',10,'qlch',_binary '','thanhcong@gmail.com','$2a$10$83.n0W0C8.8DO64rCBMatuCsiwKQtFzFrY6JV1ZyRTh820R4hsf5C');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_histories`
--

DROP TABLE IF EXISTS `inventory_histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_histories` (
  `inHisID` varchar(18) NOT NULL,
  `storeID` int DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  `productID` varchar(25) DEFAULT NULL,
  `sysInventory` int DEFAULT NULL,
  `inventoryCount` int DEFAULT NULL,
  `countingTime` datetime DEFAULT NULL,
  PRIMARY KEY (`inHisID`),
  KEY `storeID` (`storeID`),
  KEY `employeeID` (`employeeID`),
  KEY `productID` (`productID`),
  CONSTRAINT `inventoryhistory_ibfk_1` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`),
  CONSTRAINT `inventoryhistory_ibfk_2` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`),
  CONSTRAINT `inventoryhistory_ibfk_3` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_histories`
--

LOCK TABLES `inventory_histories` WRITE;
/*!40000 ALTER TABLE `inventory_histories` DISABLE KEYS */;
INSERT INTO `inventory_histories` VALUES ('KT1',1,3,'6955807973986',100,95,'2023-10-30 18:24:30'),('KT2',1,3,'8936050360974',500,500,'2023-11-25 18:30:30');
/*!40000 ALTER TABLE `inventory_histories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `need_inventories`
--

DROP TABLE IF EXISTS `need_inventories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `need_inventories` (
  `productID` varchar(25) NOT NULL,
  `storeID` int NOT NULL,
  PRIMARY KEY (`productID`,`storeID`),
  KEY `storeID` (`storeID`),
  CONSTRAINT `needinventory_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`),
  CONSTRAINT `needinventory_ibfk_2` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need_inventories`
--

LOCK TABLES `need_inventories` WRITE;
/*!40000 ALTER TABLE `need_inventories` DISABLE KEYS */;
/*!40000 ALTER TABLE `need_inventories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_detail`
--

DROP TABLE IF EXISTS `payment_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `paymentId` int NOT NULL,
  `500k` int DEFAULT '0',
  `200k` int DEFAULT '0',
  `100k` int DEFAULT '0',
  `50k` int DEFAULT '0',
  `20k` int DEFAULT '0',
  `10k` int DEFAULT '0',
  `5k` int DEFAULT '0',
  `2k` int DEFAULT '0',
  `1k` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `paymentId` (`paymentId`),
  CONSTRAINT `payment_detail_ibfk_1` FOREIGN KEY (`paymentId`) REFERENCES `payment_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_detail`
--

LOCK TABLES `payment_detail` WRITE;
/*!40000 ALTER TABLE `payment_detail` DISABLE KEYS */;
INSERT INTO `payment_detail` VALUES (3005,3005,18,0,0,1,0,0,0,0,0),(3006,3006,26,1,0,0,2,0,0,2,0),(3007,3007,26,1,0,0,2,0,0,2,0),(3008,3008,0,0,0,1,2,0,1,1,0),(3009,3009,0,0,0,1,2,0,1,1,0);
/*!40000 ALTER TABLE `payment_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_history`
--

DROP TABLE IF EXISTS `payment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NOT NULL,
  `adminID` int DEFAULT NULL,
  `time_pay` datetime DEFAULT NULL,
  `time_received` datetime DEFAULT NULL,
  `totalAmount` float DEFAULT NULL,
  `totalReceived` float DEFAULT NULL,
  `paied` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `employeeId` (`employeeId`),
  KEY `adminID` (`employeeId`),
  KEY `payment_history_ibfk_2` (`adminID`),
  CONSTRAINT `payment_history_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employees` (`employeeID`),
  CONSTRAINT `payment_history_ibfk_2` FOREIGN KEY (`adminID`) REFERENCES `employees` (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_history`
--

LOCK TABLES `payment_history` WRITE;
/*!40000 ALTER TABLE `payment_history` DISABLE KEYS */;
INSERT INTO `payment_history` VALUES (3005,3,3,'2023-11-22 17:19:57','2023-11-22 22:13:58',9050000,9050000,2),(3006,3,3,'2023-11-22 22:11:12','2023-11-22 22:12:41',13244000,13244000,2),(3007,3,3,'2023-11-22 22:19:24','2023-11-25 10:52:37',13244000,13244000,2),(3008,3,3,'2023-11-25 18:31:11','2023-11-25 18:35:06',97000,97000,2),(3009,3,3,'2023-11-25 18:42:07','2023-11-25 18:54:49',97000,97000,2);
/*!40000 ALTER TABLE `payment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_positionings`
--

DROP TABLE IF EXISTS `product_positionings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_positionings` (
  `proPosID` int NOT NULL,
  `disPlaID` int DEFAULT NULL,
  `disSheID` int NOT NULL,
  `productID` varchar(24) NOT NULL,
  `displayQuantity` int DEFAULT NULL,
  `storeID` int NOT NULL,
  `form` int NOT NULL,
  PRIMARY KEY (`proPosID`,`productID`,`storeID`,`disSheID`),
  KEY `disSheID` (`disSheID`),
  KEY `productID` (`productID`),
  KEY `productpositioning_ibfk_1` (`disPlaID`),
  CONSTRAINT `productpositioning_ibfk_3` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_positionings`
--

LOCK TABLES `product_positionings` WRITE;
/*!40000 ALTER TABLE `product_positionings` DISABLE KEYS */;
INSERT INTO `product_positionings` VALUES (1,1,1,'8935149556083',10,1,1),(2,1,1,'6955807973986',10,1,1);
/*!40000 ALTER TABLE `product_positionings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `productID` varchar(25) NOT NULL,
  `categoriesID` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `vat` int DEFAULT NULL,
  `nearestExpDate` date DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `importPrice` float DEFAULT NULL,
  `storeID` int NOT NULL,
  `inventory` int DEFAULT NULL,
  PRIMARY KEY (`productID`,`storeID`),
  KEY `categoriesID` (`categoriesID`),
  KEY `storeID` (`storeID`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoriesID`) REFERENCES `categories` (`categoriesID`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('6955807973986',6,2,10,NULL,'Tăm bông',_binary '\0','http://192.168.1.5:8083/bachhoaimg//IMG_20231024_192503.jpg',1,1,10),('8934456220031',2,3990000,5,'2023-12-10','Bộ Dao ZWILLING Gourmet - 3 món',_binary '','ZWilling3mon.jpg',2000000,1,9),('8934588063060',1,300000,5,'2023-12-10','Tinh Bột Nghệ Vàng 500g',_binary '','tinhbotnghe.jpg',200000,1,48),('8934588063176',5,95000,5,'2023-12-10','Nước giặt xả Booster 3,2 lít',_binary '','booster32.jpg',80000,1,59),('8934807006679',1,150000,5,'2023-12-10','Hạt điều rang muối (500g)',_binary '','hatdieumuoi.jpg',90000,1,49),('8935149556083',1,1,10,NULL,'Đậu phộng tỏi ớt Tài Tài',_binary '\0','http://192.168.1.5:8083/bachhoaimg//IMG_20231024_194947.jpg',1,1,10),('8935500602357',2,550000,5,'2023-12-10','Chảo chống dính Five Star 24cm',_binary '','fiveStar24.jpg',305000,1,40),('8935512694520',4,29000,5,'2023-12-10','Playmore Thái Lan hũ (22g)',_binary '','playmoreThaiLan.jpg',11000,1,60),('8935526000012',6,209000,5,'2023-12-10','Nồi Đạt Tường 1.5 lít trắng',_binary '','dattuong15.jpg',129000,1,40),('8935578640025',6,18200,5,'2023-12-10','Găng tay rửa chén silicon',_binary '','gangtaysilicon.jpg',9000,1,199),('8936050360974',3,15000,5,'2023-12-10','String Dâu',_binary '','stringdau.jpg',5000,1,100),('8936096670020',1,550000,5,'2023-12-10','Hoa atiso khô (1kg)',_binary '','hoaAtiso.jpg',370000,1,29),('8936096670021',5,115000,5,'2023-12-10','Nước giặt Booster 3,7 kg',_binary '','booster37.jpg',95000,1,60),('8936500366375',4,31000,5,'2023-12-10','Playmore Dưa Hấu hũ (22g)',_binary '','playmoreDuaHau.jpg',12000,1,50),('8938500363689',3,15000,5,'2023-12-10','Coca cola',_binary '','cocacola.jpg',6000,1,90),('8938972063060',5,92000,5,'2023-12-10','Viên nén vệ sinh lồng giặt Omo Matic',_binary '','vienVSlongOmo.jpg',80000,1,45),('9314807006679',1,25000,5,'2023-12-10','Hạt sen khô (100gr)',_binary '','hatsenkho.jpg',8000,1,100);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_history`
--

DROP TABLE IF EXISTS `purchase_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_history` (
  `id` varchar(18) NOT NULL,
  `storeID` int NOT NULL,
  `productID` varchar(25) NOT NULL,
  `employeeID` int NOT NULL,
  `sysInventory` int DEFAULT NULL,
  `quantityReceived` int DEFAULT NULL,
  `confirmedQuantity` int DEFAULT NULL,
  `totalAmount` float DEFAULT NULL,
  PRIMARY KEY (`id`,`productID`),
  KEY `productID` (`productID`),
  CONSTRAINT `purchase_history_ibfk_1` FOREIGN KEY (`id`) REFERENCES `delivery_note` (`id`),
  CONSTRAINT `purchase_history_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_history`
--

LOCK TABLES `purchase_history` WRITE;
/*!40000 ALTER TABLE `purchase_history` DISABLE KEYS */;
INSERT INTO bachhoa.purchase_history (id,storeID,productID,employeeID,sysInventory, quantityReceived, confirmedQuantity, totalAmount) VALUES
	 ('20231113211144',1,'8935149556083',1,10,200,200,200),
	 ('20231113211144',1,'8934588063060',1,5,100,100,30000000),
	 ('20231113211144',1,'8934807006679',1,3,150,150,22500000),
	 ('20231113211208',1,'8935526000012',2,20,400,400,83600000),
	 ('20231113211208',1,'9314807006679',2,4,178,178,4450000),
	 ('20231113211748',1,'6955807973986',22,5,345,245,690),
	 ('20231113211748',1,'8935149556083',22,5,10000,10000,10000),
	 ('20231113212028',1,'8936500366375',3,5,234,234,7254000),
	 ('20231113212028',1,'9314807006679',3,6,999,1000,25000000),
	 ('20231113212028',1,'8938500363689',3,8,45,45,675000),
	 ('20231113212028',1,'8935578640025',3,10,45,40,728000),
	 ('20231113212222',1,'8936500366375',4,3,466,466,14446000),
	 ('20231113212222',1,'8935578640025',4,3,777,777,14141400),
	 ('20231113212222',1,'6955807973986',4,3,108,108,216),
	 ('20231113212222',1,'8934588063060',4,7,200,100,30000000);
/*!40000 ALTER TABLE `purchase_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `roleID` varchar(15) NOT NULL,
  `workRole` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleID`),
  KEY `idx_roleID` (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('bhoa','Chưa có cửa hàng'),('qlch','Quản lý cửa hàng');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment_batch_details`
--

DROP TABLE IF EXISTS `shipment_batch_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment_batch_details` (
  `shiBatID` varchar(18) NOT NULL,
  `productID` varchar(25) NOT NULL,
  `employeeID` int DEFAULT NULL,
  `situation` bit(1) DEFAULT NULL,
  `pictureURL` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `storeID` int DEFAULT NULL,
  PRIMARY KEY (`shiBatID`,`productID`),
  KEY `employeeID` (`employeeID`),
  KEY `shipmentbatchdetail_ibfk_3` (`productID`),
  CONSTRAINT `shipmentbatchdetail_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`),
  CONSTRAINT `shipmentbatchdetail_ibfk_2` FOREIGN KEY (`shiBatID`) REFERENCES `shipment_batchs` (`shiBatID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment_batch_details`
--

LOCK TABLES `shipment_batch_details` WRITE;
/*!40000 ALTER TABLE `shipment_batch_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipment_batch_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment_batchs`
--

DROP TABLE IF EXISTS `shipment_batchs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment_batchs` (
  `shiBatID` varchar(18) NOT NULL,
  `storeID` int DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `finishTime` datetime DEFAULT NULL,
  `situation` bit(1) DEFAULT b'0',
  PRIMARY KEY (`shiBatID`),
  KEY `storeID` (`storeID`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `shipmentbatch_ibfk_1` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`),
  CONSTRAINT `shipmentbatch_ibfk_2` FOREIGN KEY (`employeeID`) REFERENCES `employees` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment_batchs`
--

LOCK TABLES `shipment_batchs` WRITE;
/*!40000 ALTER TABLE `shipment_batchs` DISABLE KEYS */;
INSERT INTO `shipment_batchs` VALUES ('004',1,1,NULL,NULL,_binary '\0'),('033',1,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `shipment_batchs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `storeID` int NOT NULL AUTO_INCREMENT,
  `storeName` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `size` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`storeID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'BACHHOA','Quận 12, TP.HCM','5 tỷ'),(2,'ND Farm','Bình Định','500 tỷ');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bachhoa'
--

--
-- Dumping routines for database 'bachhoa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-25 21:42:06	
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('1699465515708',1,3,61950.0,'2023-11-09 00:45:35',70000.0,0.0),
	 ('1699465535104',1,3,257250.0,'2023-11-09 00:47:08',300000.0,0.0),
	 ('1699465628120',1,3,144900.0,'2023-11-09 00:47:52',150000.0,0.0),
	 ('1699465628121',1,1,100000.0,'2022-11-09 00:47:52',100000.0,0.0),
	 ('afc13ae1ae52f9ebe',1,1,1717330.0,'2021-10-15 11:04:09',1938440.0,0.0),
	 ('afc13ae1ae52f9ebf',1,4,1050700.0,'2021-10-11 08:04:54',1920220.0,0.0),
	 ('afc13ae1ae52f9ec0',1,2,50436.0,'2022-01-21 14:18:02',1785380.0,0.0),
	 ('afc13ae1ae52f9ec1',1,4,525225.0,'2023-10-04 00:45:04',1795610.0,0.0),
	 ('afc13ae1ae52f9ec2',1,4,1951560.0,'2022-12-26 13:59:33',1909230.0,0.0),
	 ('afc13ae1ae52f9ec3',1,2,1484080.0,'2022-01-06 08:49:40',1778280.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ec4',1,2,308008.0,'2020-11-25 21:46:33',1655340.0,0.0),
	 ('afc13ae1ae52f9ec5',1,1,913413.0,'2021-01-14 21:02:19',1549840.0,0.0),
	 ('afc13ae1ae52f9ec6',1,4,164191.0,'2021-11-04 11:22:55',1550080.0,0.0),
	 ('afc13ae1ae52f9ec7',1,3,562044.0,'2021-08-24 06:26:28',1921220.0,0.0),
	 ('afc13ae1ae52f9ec8',1,4,686771.0,'2022-04-23 21:51:19',1637910.0,0.0),
	 ('afc13ae1ae52f9ec9',1,1,395796.0,'2023-08-08 09:25:04',1737010.0,0.0),
	 ('afc13ae1ae52f9eca',1,1,680340.0,'2021-04-26 09:37:36',1931920.0,0.0),
	 ('afc13ae1ae52f9ecb',1,4,1457230.0,'2022-07-03 04:19:06',1781710.0,0.0),
	 ('afc13ae1ae52f9ecc',1,4,1571980.0,'2022-08-09 04:08:07',1662700.0,0.0),
	 ('afc13ae1ae52f9ecd',1,4,1921600.0,'2021-05-02 10:58:36',1759110.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ece',1,1,480865.0,'2022-02-19 08:51:57',1889320.0,0.0),
	 ('afc13ae1ae52f9ecf',1,2,1189920.0,'2023-07-29 07:42:14',1787000.0,0.0),
	 ('afc13ae1ae52f9ed0',1,4,1822180.0,'2022-04-22 07:56:21',1801110.0,0.0),
	 ('afc13ae1ae52f9ed1',1,1,1342740.0,'2021-05-01 03:45:40',1648220.0,0.0),
	 ('afc13ae1ae52f9ed2',1,1,1647810.0,'2023-02-23 19:25:21',1862460.0,0.0),
	 ('afc13ae1ae52f9ed3',1,2,1514580.0,'2022-03-19 13:38:04',1714210.0,0.0),
	 ('afc13ae1ae52f9ed4',1,4,1416450.0,'2021-08-12 11:50:10',1703320.0,0.0),
	 ('afc13ae1ae52f9ed5',1,2,1228480.0,'2021-12-09 22:59:18',1878840.0,0.0),
	 ('afc13ae1ae52f9ed6',1,4,1873510.0,'2023-04-10 17:43:40',1995610.0,0.0),
	 ('afc13ae1ae52f9ed7',1,4,1907110.0,'2023-07-05 03:25:32',1555090.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ed8',1,3,1917820.0,'2021-04-17 10:17:37',1613980.0,0.0),
	 ('afc13ae1ae52f9ed9',1,2,806702.0,'2020-11-20 20:40:56',1792930.0,0.0),
	 ('afc13ae1ae52f9eda',1,1,303748.0,'2021-11-30 00:39:55',1842670.0,0.0),
	 ('afc13ae1ae52f9edb',1,1,293028.0,'2022-10-24 09:58:52',1847390.0,0.0),
	 ('afc13ae1ae52f9edc',1,1,746234.0,'2021-05-04 08:23:17',1505900.0,0.0),
	 ('afc13ae1ae52f9edd',1,4,1454560.0,'2021-07-26 23:40:25',1955340.0,0.0),
	 ('afc13ae1ae52f9ede',1,4,1042370.0,'2022-02-27 17:27:21',1996430.0,0.0),
	 ('afc13ae1ae52f9edf',1,3,368773.0,'2022-02-09 15:38:51',1812500.0,0.0),
	 ('afc13ae1ae52f9ee0',1,1,538126.0,'2022-03-08 06:32:26',1679390.0,0.0),
	 ('afc13ae1ae52f9ee1',1,4,1255780.0,'2022-09-24 20:21:08',1950950.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ee2',1,2,1295930.0,'2021-07-03 02:51:45',1763780.0,0.0),
	 ('afc13ae1ae52f9ee3',1,2,1498160.0,'2022-09-11 01:00:46',1975300.0,0.0),
	 ('afc13ae1ae52f9ee4',1,3,799321.0,'2021-08-23 10:31:35',1873870.0,0.0),
	 ('afc13ae1ae52f9ee5',1,2,1118760.0,'2021-06-19 01:21:55',1991990.0,0.0),
	 ('afc13ae1ae52f9ee6',1,1,877771.0,'2021-05-13 21:15:26',1708360.0,0.0),
	 ('afc13ae1ae52f9ee7',1,2,72717.0,'2023-05-02 12:02:43',1965680.0,0.0),
	 ('afc13ae1ae52f9ee8',1,3,403271.0,'2022-04-10 08:19:33',1675640.0,0.0),
	 ('afc13ae1ae52f9ee9',1,4,1256480.0,'2021-06-27 04:44:25',1550920.0,0.0),
	 ('afc13ae1ae52f9eea',1,1,1301840.0,'2021-03-14 10:09:27',1888320.0,0.0),
	 ('afc13ae1ae52f9eeb',1,3,181111.0,'2023-05-29 06:04:57',1783050.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9eec',1,2,1107670.0,'2021-11-22 18:42:21',1808420.0,0.0),
	 ('afc13ae1ae52f9eed',1,2,1290600.0,'2021-02-03 01:23:09',1623600.0,0.0),
	 ('afc13ae1ae52f9eee',1,3,295912.0,'2023-07-28 00:07:55',1543600.0,0.0),
	 ('afc13ae1ae52f9eef',1,3,1036960.0,'2021-06-22 17:51:27',1572370.0,0.0),
	 ('afc13ae1ae52f9ef0',1,1,474942.0,'2022-12-28 14:57:02',1555750.0,0.0),
	 ('afc13ae1ae52f9ef1',1,4,484174.0,'2020-11-21 10:27:34',1591860.0,0.0),
	 ('afc13ae1ae52f9ef2',1,1,1099040.0,'2023-10-06 08:47:35',1674870.0,0.0),
	 ('afc13ae1ae52f9ef3',1,4,675112.0,'2021-05-09 03:28:02',1982760.0,0.0),
	 ('afc13ae1ae52f9ef4',1,2,1377960.0,'2021-08-30 20:54:41',1815730.0,0.0),
	 ('afc13ae1ae52f9ef5',1,2,69203.0,'2023-02-02 23:16:43',1622480.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ef6',1,4,1899980.0,'2020-12-15 16:41:31',1728110.0,0.0),
	 ('afc13ae1ae52f9ef7',1,2,26134.0,'2021-03-20 21:17:05',1542930.0,0.0),
	 ('afc13ae1ae52f9ef8',1,1,454369.0,'2021-12-25 07:02:38',1559190.0,0.0),
	 ('afc13ae1ae52f9ef9',1,4,1669860.0,'2023-07-04 15:14:09',1752580.0,0.0),
	 ('afc13ae1ae52f9efa',1,3,98770.0,'2021-09-07 17:55:55',1855790.0,0.0),
	 ('afc13ae1ae52f9efb',1,1,1894770.0,'2021-11-19 10:01:38',1766660.0,0.0),
	 ('afc13ae1ae52f9efc',1,3,1422140.0,'2022-10-20 20:35:16',1847060.0,0.0),
	 ('afc13ae1ae52f9efd',1,3,313769.0,'2021-08-27 02:44:33',1692250.0,0.0),
	 ('afc13ae1ae52f9efe',1,4,1893360.0,'2023-06-22 23:11:25',1931800.0,0.0),
	 ('afc13ae1ae52f9eff',1,2,1268420.0,'2021-10-13 10:12:53',1920210.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f00',1,3,343106.0,'2020-12-17 05:26:02',1772360.0,0.0),
	 ('afc13ae1ae52f9f01',1,3,747647.0,'2023-09-28 17:13:55',1713310.0,0.0),
	 ('afc13ae1ae52f9f02',1,3,672361.0,'2022-07-13 05:04:03',1827790.0,0.0),
	 ('afc13ae1ae52f9f03',1,3,1564960.0,'2022-11-03 22:20:18',1550150.0,0.0),
	 ('afc13ae1ae52f9f04',1,1,709655.0,'2021-09-30 17:23:14',1891330.0,0.0),
	 ('afc13ae1ae52f9f05',1,3,681253.0,'2022-01-23 01:26:58',1569530.0,0.0),
	 ('afc13ae1ae52f9f06',1,4,1883290.0,'2023-01-16 12:47:54',1585260.0,0.0),
	 ('afc13ae1ae52f9f07',1,2,1999980.0,'2023-04-24 12:17:41',1947050.0,0.0),
	 ('afc13ae1ae52f9f08',1,2,1438520.0,'2022-07-13 07:09:10',1881080.0,0.0),
	 ('afc13ae1ae52f9f09',1,1,746711.0,'2022-07-09 03:38:02',1824650.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f0a',1,4,296802.0,'2023-08-16 23:36:22',1797500.0,0.0),
	 ('afc13ae1ae52f9f0b',1,2,1941150.0,'2023-01-19 20:17:41',1540240.0,0.0),
	 ('afc13ae1ae52f9f0c',1,1,1650370.0,'2021-04-05 05:48:49',1544970.0,0.0),
	 ('afc13ae1ae52f9f0d',1,3,1793290.0,'2023-01-01 11:11:15',1719810.0,0.0),
	 ('afc13ae1ae52f9f0e',1,2,163060.0,'2021-12-10 00:08:38',1790980.0,0.0),
	 ('afc13ae1ae52f9f0f',1,1,1946420.0,'2022-08-20 00:49:30',1779270.0,0.0),
	 ('afc13ae1ae52f9f10',1,1,1665910.0,'2022-02-18 22:04:38',1890870.0,0.0),
	 ('afc13ae1ae52f9f11',1,4,1017770.0,'2023-06-02 08:32:05',1564810.0,0.0),
	 ('afc13ae1ae52f9f12',1,2,253593.0,'2022-04-26 19:12:02',1779610.0,0.0),
	 ('afc13ae1ae52f9f13',1,3,233168.0,'2022-07-12 22:42:44',1761040.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f14',1,3,1536600.0,'2020-12-18 08:46:31',1980950.0,0.0),
	 ('afc13ae1ae52f9f15',1,1,960161.0,'2023-04-20 15:45:12',1633870.0,0.0),
	 ('afc13ae1ae52f9f16',1,3,231847.0,'2022-06-21 05:59:19',1863460.0,0.0),
	 ('afc13ae1ae52f9f17',1,1,1358910.0,'2022-09-07 17:28:06',1748500.0,0.0),
	 ('afc13ae1ae52f9f18',1,4,1801170.0,'2023-03-07 02:57:40',1931180.0,0.0),
	 ('afc13ae1ae52f9f19',1,4,659593.0,'2023-10-27 15:58:36',1804240.0,0.0),
	 ('afc13ae1ae52f9f1a',1,4,1285700.0,'2022-05-04 22:40:26',1929540.0,0.0),
	 ('afc13ae1ae52f9f1b',1,2,1881080.0,'2021-09-08 09:48:41',1748970.0,0.0),
	 ('afc13ae1ae52f9f1c',1,4,659032.0,'2022-03-17 03:48:45',1746200.0,0.0),
	 ('afc13ae1ae52f9f1d',1,3,1624640.0,'2021-09-15 06:14:19',1762500.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f1e',1,4,1484050.0,'2021-03-01 03:55:31',1610690.0,0.0),
	 ('afc13ae1ae52f9f1f',1,4,1696000.0,'2022-06-25 14:58:22',1565850.0,0.0),
	 ('afc13ae1ae52f9f20',1,1,755477.0,'2022-08-14 17:25:54',1607780.0,0.0),
	 ('afc13ae1ae52f9f21',1,3,1251460.0,'2021-05-28 04:36:08',1614030.0,0.0),
	 ('afc13ae1ae52f9f22',1,4,1726960.0,'2023-08-20 09:16:09',1557700.0,0.0),
	 ('afc13ae1ae52f9f23',1,2,1212880.0,'2021-04-27 18:17:39',1519590.0,0.0),
	 ('afc13ae1ae52f9f24',1,1,1658080.0,'2023-01-06 14:59:57',1602520.0,0.0),
	 ('afc13ae1ae52f9f25',1,2,895948.0,'2022-04-18 23:43:18',1980140.0,0.0),
	 ('afc13ae1ae52f9f26',1,4,756978.0,'2022-01-17 10:31:54',1576330.0,0.0),
	 ('afc13ae1ae52f9f27',1,3,587755.0,'2021-10-21 08:20:45',1683420.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f28',1,3,365431.0,'2021-01-01 05:02:24',1615570.0,0.0),
	 ('afc13ae1ae52f9f29',1,4,1685020.0,'2022-12-16 21:53:03',1711790.0,0.0),
	 ('afc13ae1ae52f9f2a',1,1,1924680.0,'2022-08-01 05:53:04',1878430.0,0.0),
	 ('afc13ae1ae52f9f2b',1,3,432282.0,'2020-12-29 00:42:40',1708940.0,0.0),
	 ('afc13ae1ae52f9f2c',1,3,1279610.0,'2023-08-23 22:22:12',1697790.0,0.0),
	 ('afc13ae1ae52f9f2d',1,3,1140980.0,'2022-09-14 06:31:14',1993490.0,0.0),
	 ('afc13ae1ae52f9f2e',1,1,48323.0,'2022-10-25 21:20:52',1906300.0,0.0),
	 ('afc13ae1ae52f9f2f',1,3,1094080.0,'2023-08-23 00:13:41',1841080.0,0.0),
	 ('afc13ae1ae52f9f30',1,4,1116020.0,'2021-07-17 06:44:59',1606590.0,0.0),
	 ('afc13ae1ae52f9f31',1,1,599092.0,'2021-02-06 01:01:35',1782910.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f32',1,2,1102240.0,'2022-01-31 06:55:09',1902960.0,0.0),
	 ('afc13ae1ae52f9f33',1,2,439807.0,'2022-05-05 02:53:04',1550890.0,0.0),
	 ('afc13ae1ae52f9f34',1,1,851465.0,'2021-12-13 14:58:29',1609490.0,0.0),
	 ('afc13ae1ae52f9f35',1,3,831649.0,'2020-12-02 17:32:58',1771440.0,0.0),
	 ('afc13ae1ae52f9f36',1,3,1717180.0,'2020-11-23 21:11:01',1869520.0,0.0),
	 ('afc13ae1ae52f9f37',1,1,1421740.0,'2022-07-21 04:00:47',1810860.0,0.0),
	 ('afc13ae1ae52f9f38',1,4,1525900.0,'2021-08-31 08:25:24',1667310.0,0.0),
	 ('afc13ae1ae52f9f39',1,1,557478.0,'2020-12-30 20:23:52',1613210.0,0.0),
	 ('afc13ae1ae52f9f3a',1,3,1380770.0,'2022-02-09 02:34:54',1941760.0,0.0),
	 ('afc13ae1ae52f9f3b',1,4,632618.0,'2021-08-20 21:32:15',1721600.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f3c',1,2,87700.0,'2022-03-09 08:16:18',1598210.0,0.0),
	 ('afc13ae1ae52f9f3d',1,4,1581120.0,'2022-10-29 09:54:20',1779190.0,0.0),
	 ('afc13ae1ae52f9f3e',1,3,1131360.0,'2020-11-30 07:04:43',1940600.0,0.0),
	 ('afc13ae1ae52f9f3f',1,1,588022.0,'2022-03-18 10:32:38',1991310.0,0.0),
	 ('afc13ae1ae52f9f40',1,3,353045.0,'2023-08-05 17:43:26',1968140.0,0.0),
	 ('afc13ae1ae52f9f41',1,3,1023050.0,'2023-07-21 23:36:47',1568120.0,0.0),
	 ('afc13ae1ae52f9f42',1,3,1706260.0,'2022-09-26 16:01:33',1649420.0,0.0),
	 ('afc13ae1ae52f9f43',1,4,956363.0,'2022-04-06 12:11:12',1847030.0,0.0),
	 ('afc13ae1ae52f9f44',1,1,1863080.0,'2022-11-11 07:55:55',1643760.0,0.0),
	 ('afc13ae1ae52f9f45',1,2,1544850.0,'2023-03-12 08:22:03',1945270.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f46',1,4,1853650.0,'2022-07-12 05:18:28',1744110.0,0.0),
	 ('afc13ae1ae52f9f47',1,4,788711.0,'2023-09-09 15:06:29',1523600.0,0.0),
	 ('afc13ae1ae52f9f48',1,2,913888.0,'2022-11-16 18:35:24',1540160.0,0.0),
	 ('afc13ae1ae52f9f49',1,2,357376.0,'2022-09-12 11:20:05',1595200.0,0.0),
	 ('afc13ae1ae52f9f4a',1,2,1253000.0,'2022-05-04 09:16:29',1623150.0,0.0),
	 ('afc13ae1ae52f9f4b',1,4,576383.0,'2022-01-16 18:12:43',1652860.0,0.0),
	 ('afc13ae1ae52f9f4c',1,1,1636870.0,'2023-07-18 21:29:24',1836450.0,0.0),
	 ('afc13ae1ae52f9f4d',1,4,625213.0,'2022-06-03 09:50:27',1502490.0,0.0),
	 ('afc13ae1ae52f9f4e',1,1,715094.0,'2021-06-05 11:12:58',1793360.0,0.0),
	 ('afc13ae1ae52f9f4f',1,4,1614500.0,'2021-03-24 12:11:02',1869100.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f50',1,3,1527550.0,'2023-02-08 02:45:57',1616610.0,0.0),
	 ('afc13ae1ae52f9f51',1,4,184055.0,'2021-09-07 13:55:38',1513300.0,0.0),
	 ('afc13ae1ae52f9f52',1,4,346046.0,'2021-12-20 04:28:20',1776470.0,0.0),
	 ('afc13ae1ae52f9f53',1,2,1138020.0,'2023-01-08 02:47:51',1631940.0,0.0),
	 ('afc13ae1ae52f9f54',1,1,1088450.0,'2022-10-22 19:42:02',1585740.0,0.0),
	 ('afc13ae1ae52f9f55',1,3,1793980.0,'2023-03-22 03:27:09',1565690.0,0.0),
	 ('afc13ae1ae52f9f56',1,2,113608.0,'2022-06-20 21:33:57',1825540.0,0.0),
	 ('afc13ae1ae52f9f57',1,2,401344.0,'2022-04-11 17:57:41',1612120.0,0.0),
	 ('afc13ae1ae52f9f58',1,4,1284030.0,'2022-11-29 09:11:00',1918660.0,0.0),
	 ('afc13ae1ae52f9f59',1,4,1723950.0,'2022-06-20 15:56:24',1824680.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f5a',1,1,399524.0,'2023-10-06 13:45:00',1936470.0,0.0),
	 ('afc13ae1ae52f9f5b',1,3,917269.0,'2023-02-18 23:22:54',1659110.0,0.0),
	 ('afc13ae1ae52f9f5c',1,4,1090050.0,'2021-09-20 20:04:09',1654850.0,0.0),
	 ('afc13ae1ae52f9f5d',1,3,1198430.0,'2021-12-15 13:13:44',1738360.0,0.0),
	 ('afc13ae1ae52f9f5e',1,4,170915.0,'2021-12-09 04:52:54',1730880.0,0.0),
	 ('afc13ae1ae52f9f5f',1,2,224197.0,'2022-05-26 02:01:32',1655110.0,0.0),
	 ('afc13ae1ae52f9f60',1,3,519550.0,'2021-06-04 22:03:33',1974360.0,0.0),
	 ('afc13ae1ae52f9f61',1,1,1862280.0,'2023-02-25 14:20:18',1965260.0,0.0),
	 ('afc13ae1ae52f9f62',1,1,861915.0,'2022-09-16 02:56:14',1738250.0,0.0),
	 ('afc13ae1ae52f9f63',1,1,465326.0,'2021-12-07 10:50:21',1705560.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f64',1,2,1938740.0,'2022-05-30 16:10:23',1604720.0,0.0),
	 ('afc13ae1ae52f9f65',1,2,1458910.0,'2023-09-09 05:22:08',1849910.0,0.0),
	 ('afc13ae1ae52f9f66',1,1,1779790.0,'2021-04-16 23:17:58',1641510.0,0.0),
	 ('afc13ae1ae52f9f67',1,3,1506580.0,'2021-08-19 09:48:34',1808620.0,0.0),
	 ('afc13ae1ae52f9f68',1,2,587311.0,'2021-03-01 20:53:04',1885040.0,0.0),
	 ('afc13ae1ae52f9f69',1,2,1666020.0,'2023-05-14 15:50:51',1546470.0,0.0),
	 ('afc13ae1ae52f9f6a',1,4,1374030.0,'2023-10-29 14:39:23',1644640.0,0.0),
	 ('afc13ae1ae52f9f6b',1,2,1503170.0,'2023-01-31 04:50:48',1597640.0,0.0),
	 ('afc13ae1ae52f9f6c',1,2,1022860.0,'2021-04-21 20:16:34',1667290.0,0.0),
	 ('afc13ae1ae52f9f6d',1,4,621204.0,'2021-09-20 18:53:49',1645830.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f6e',1,2,480468.0,'2021-06-24 17:10:13',1539010.0,0.0),
	 ('afc13ae1ae52f9f6f',1,2,468250.0,'2022-08-26 19:06:57',1576460.0,0.0),
	 ('afc13ae1ae52f9f70',1,2,1572520.0,'2021-09-09 07:58:34',1896960.0,0.0),
	 ('afc13ae1ae52f9f71',1,3,1037660.0,'2022-01-13 03:58:13',1674580.0,0.0),
	 ('afc13ae1ae52f9f72',1,2,803473.0,'2021-07-05 18:54:56',1811430.0,0.0),
	 ('afc13ae1ae52f9f73',1,3,1612620.0,'2022-06-16 11:02:45',1934330.0,0.0),
	 ('afc13ae1ae52f9f74',1,3,1405840.0,'2021-07-19 04:27:37',1538800.0,0.0),
	 ('afc13ae1ae52f9f75',1,1,796218.0,'2022-05-08 01:19:48',1597650.0,0.0),
	 ('afc13ae1ae52f9f76',1,4,1194980.0,'2020-11-21 03:15:47',1506590.0,0.0),
	 ('afc13ae1ae52f9f77',1,4,261012.0,'2022-10-10 18:30:54',1996950.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f78',1,2,116394.0,'2022-07-26 15:31:53',1731100.0,0.0),
	 ('afc13ae1ae52f9f79',1,4,443178.0,'2022-07-07 19:10:43',1900380.0,0.0),
	 ('afc13ae1ae52f9f7a',1,2,1218100.0,'2023-08-28 06:09:18',1965660.0,0.0),
	 ('afc13ae1ae52f9f7b',1,3,428379.0,'2021-09-30 02:13:41',1992170.0,0.0),
	 ('afc13ae1ae52f9f7c',1,4,1143010.0,'2021-01-15 17:30:42',1932580.0,0.0),
	 ('afc13ae1ae52f9f7d',1,4,1094700.0,'2023-04-26 17:58:00',1929900.0,0.0),
	 ('afc13ae1ae52f9f7e',1,4,1023760.0,'2023-10-29 10:01:03',1686140.0,0.0),
	 ('afc13ae1ae52f9f7f',1,1,192482.0,'2023-10-07 05:38:25',1948940.0,0.0),
	 ('afc13ae1ae52f9f80',1,2,400979.0,'2022-04-11 15:40:49',1513000.0,0.0),
	 ('afc13ae1ae52f9f81',1,1,734677.0,'2020-12-29 15:50:07',1924550.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f82',1,1,1924700.0,'2021-11-25 17:12:52',1737180.0,0.0),
	 ('afc13ae1ae52f9f83',1,3,996937.0,'2022-04-11 08:50:11',1677990.0,0.0),
	 ('afc13ae1ae52f9f84',1,2,1014420.0,'2022-06-20 07:29:45',1869410.0,0.0),
	 ('afc13ae1ae52f9f85',1,1,316485.0,'2023-10-07 18:27:20',1966910.0,0.0),
	 ('afc13ae1ae52f9f86',1,3,336986.0,'2023-09-23 19:21:14',1922790.0,0.0),
	 ('afc13ae1ae52f9f87',1,3,1890980.0,'2022-03-31 09:34:21',1703320.0,0.0),
	 ('afc13ae1ae52f9f88',1,2,828121.0,'2021-07-02 20:15:27',1981630.0,0.0),
	 ('afc13ae1ae52f9f89',1,2,1252720.0,'2022-03-04 05:07:13',1936970.0,0.0),
	 ('afc13ae1ae52f9f8a',1,4,698072.0,'2021-08-09 16:45:19',1527460.0,0.0),
	 ('afc13ae1ae52f9f8b',1,1,1917190.0,'2022-09-15 02:17:35',1956670.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f8c',1,1,430032.0,'2022-01-05 23:31:23',1603230.0,0.0),
	 ('afc13ae1ae52f9f8d',1,1,1557280.0,'2021-07-30 14:06:19',1787920.0,0.0),
	 ('afc13ae1ae52f9f8e',1,2,1395970.0,'2022-07-24 20:07:04',1855640.0,0.0),
	 ('afc13ae1ae52f9f8f',1,1,1209650.0,'2021-05-20 18:28:03',1568770.0,0.0),
	 ('afc13ae1ae52f9f90',1,1,986144.0,'2022-07-11 03:24:01',1715740.0,0.0),
	 ('afc13ae1ae52f9f91',1,3,861241.0,'2022-04-14 10:41:18',1758430.0,0.0),
	 ('afc13ae1ae52f9f92',1,2,1894820.0,'2022-12-21 17:09:21',1847420.0,0.0),
	 ('afc13ae1ae52f9f93',1,4,1981080.0,'2021-08-23 01:25:01',1674460.0,0.0),
	 ('afc13ae1ae52f9f94',1,4,534017.0,'2021-05-23 18:28:56',1746230.0,0.0),
	 ('afc13ae1ae52f9f95',1,4,502402.0,'2022-04-21 20:32:27',1543160.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9f96',1,4,1081430.0,'2022-03-15 20:33:20',1990860.0,0.0),
	 ('afc13ae1ae52f9f97',1,1,108432.0,'2021-05-19 22:55:34',1753370.0,0.0),
	 ('afc13ae1ae52f9f98',1,4,1472470.0,'2021-05-02 19:51:07',1569300.0,0.0),
	 ('afc13ae1ae52f9f99',1,1,1881620.0,'2021-02-05 23:08:40',1942740.0,0.0),
	 ('afc13ae1ae52f9f9a',1,4,1853210.0,'2021-03-26 10:46:40',1748660.0,0.0),
	 ('afc13ae1ae52f9f9b',1,1,213858.0,'2021-06-14 15:10:33',1880410.0,0.0),
	 ('afc13ae1ae52f9f9c',1,2,256880.0,'2021-07-08 05:49:56',1837620.0,0.0),
	 ('afc13ae1ae52f9f9d',1,1,988915.0,'2022-09-05 15:31:52',1859860.0,0.0),
	 ('afc13ae1ae52f9f9e',1,4,1406370.0,'2023-06-25 10:37:53',1562060.0,0.0),
	 ('afc13ae1ae52f9f9f',1,2,940842.0,'2023-02-28 06:25:29',1891060.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fa0',1,3,576945.0,'2021-05-26 00:13:04',1861330.0,0.0),
	 ('afc13ae1ae52f9fa1',1,1,1663350.0,'2022-05-14 14:07:18',1637830.0,0.0),
	 ('afc13ae1ae52f9fa2',1,4,437808.0,'2023-02-11 22:46:27',1704520.0,0.0),
	 ('afc13ae1ae52f9fa3',1,1,1270330.0,'2021-03-04 07:24:37',1634630.0,0.0),
	 ('afc13ae1ae52f9fa4',1,2,1679510.0,'2021-07-29 12:12:58',1720610.0,0.0),
	 ('afc13ae1ae52f9fa5',1,4,583684.0,'2023-01-07 18:13:35',1740280.0,0.0),
	 ('afc13ae1ae52f9fa6',1,2,1187490.0,'2020-11-27 13:02:34',1724720.0,0.0),
	 ('afc13ae1ae52f9fa7',1,2,76855.0,'2022-11-07 09:32:40',1905630.0,0.0),
	 ('afc13ae1ae52f9fa8',1,2,1201990.0,'2021-02-08 12:45:54',1575240.0,0.0),
	 ('afc13ae1ae52f9fa9',1,1,1196250.0,'2022-08-28 07:51:14',1545150.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9faa',1,3,421432.0,'2022-05-18 13:46:04',1926950.0,0.0),
	 ('afc13ae1ae52f9fab',1,1,20623.0,'2021-01-09 21:54:26',1532600.0,0.0),
	 ('afc13ae1ae52f9fac',1,1,590829.0,'2022-10-19 22:52:18',1879460.0,0.0),
	 ('afc13ae1ae52f9fad',1,1,1647320.0,'2022-09-24 07:13:44',1975310.0,0.0),
	 ('afc13ae1ae52f9fae',1,4,425599.0,'2021-09-01 04:16:22',1708970.0,0.0),
	 ('afc13ae1ae52f9faf',1,1,1222140.0,'2023-04-25 18:52:56',1965340.0,0.0),
	 ('afc13ae1ae52f9fb0',1,3,1902060.0,'2023-05-03 21:42:36',1916190.0,0.0),
	 ('afc13ae1ae52f9fb1',1,2,94308.0,'2021-03-06 06:24:37',1717440.0,0.0),
	 ('afc13ae1ae52f9fb2',1,4,663997.0,'2020-12-05 11:15:16',1521360.0,0.0),
	 ('afc13ae1ae52f9fb3',1,3,184351.0,'2023-10-21 23:59:30',1937250.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fb4',1,1,1860830.0,'2021-12-05 18:52:27',1534920.0,0.0),
	 ('afc13ae1ae52f9fb5',1,3,1839100.0,'2023-04-21 19:08:16',1847330.0,0.0),
	 ('afc13ae1ae52f9fb6',1,3,1084010.0,'2023-07-24 01:21:38',1542150.0,0.0),
	 ('afc13ae1ae52f9fb7',1,2,863719.0,'2021-09-24 19:39:19',1891680.0,0.0),
	 ('afc13ae1ae52f9fb8',1,2,931503.0,'2022-03-22 20:40:01',1902520.0,0.0),
	 ('afc13ae1ae52f9fb9',1,1,220068.0,'2022-01-24 17:41:42',1808860.0,0.0),
	 ('afc13ae1ae52f9fba',1,1,843467.0,'2023-10-26 21:35:44',1670240.0,0.0),
	 ('afc13ae1ae52f9fbb',1,1,1537430.0,'2023-04-16 09:01:40',1898730.0,0.0),
	 ('afc13ae1ae52f9fbc',1,4,1562470.0,'2023-08-27 08:43:33',1859630.0,0.0),
	 ('afc13ae1ae52f9fbd',1,1,571981.0,'2021-07-23 20:27:59',1981730.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fbe',1,3,1257480.0,'2021-06-09 11:36:39',1842250.0,0.0),
	 ('afc13ae1ae52f9fbf',1,3,255509.0,'2020-11-22 14:34:23',1779550.0,0.0),
	 ('afc13ae1ae52f9fc0',1,3,565784.0,'2022-02-04 04:50:33',1814900.0,0.0),
	 ('afc13ae1ae52f9fc1',1,2,1146590.0,'2023-01-15 17:58:11',1681960.0,0.0),
	 ('afc13ae1ae52f9fc2',1,1,834899.0,'2021-01-02 16:22:13',1698060.0,0.0),
	 ('afc13ae1ae52f9fc3',1,4,674952.0,'2022-10-09 08:17:19',1509380.0,0.0),
	 ('afc13ae1ae52f9fc4',1,2,1499460.0,'2023-06-30 03:07:16',1743050.0,0.0),
	 ('afc13ae1ae52f9fc5',1,3,811241.0,'2022-12-06 02:20:18',1636980.0,0.0),
	 ('afc13ae1ae52f9fc6',1,1,1748060.0,'2023-01-30 19:03:05',1501040.0,0.0),
	 ('afc13ae1ae52f9fc7',1,3,337060.0,'2023-11-08 08:59:47',1559530.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fc8',1,4,166539.0,'2021-01-01 03:20:17',1588960.0,0.0),
	 ('afc13ae1ae52f9fc9',1,2,1021320.0,'2021-02-26 07:27:47',1534460.0,0.0),
	 ('afc13ae1ae52f9fca',1,4,437736.0,'2020-11-23 14:05:34',1769250.0,0.0),
	 ('afc13ae1ae52f9fcb',1,4,835513.0,'2023-08-16 01:29:26',1513950.0,0.0),
	 ('afc13ae1ae52f9fcc',1,1,299159.0,'2023-08-04 10:33:32',1562220.0,0.0),
	 ('afc13ae1ae52f9fcd',1,3,740348.0,'2022-09-02 18:15:36',1787450.0,0.0),
	 ('afc13ae1ae52f9fce',1,1,670508.0,'2022-10-05 16:05:23',1844490.0,0.0),
	 ('afc13ae1ae52f9fcf',1,4,1706950.0,'2022-09-29 11:50:43',1742710.0,0.0),
	 ('afc13ae1ae52f9fd0',1,1,1908560.0,'2021-11-28 20:57:01',1538270.0,0.0),
	 ('afc13ae1ae52f9fd1',1,1,673398.0,'2021-03-23 13:48:16',1746050.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fd2',1,1,1708040.0,'2022-09-11 15:07:03',1744820.0,0.0),
	 ('afc13ae1ae52f9fd3',1,3,11726.0,'2020-12-26 22:25:04',1779860.0,0.0),
	 ('afc13ae1ae52f9fd4',1,4,463861.0,'2020-12-27 15:10:13',1536010.0,0.0),
	 ('afc13ae1ae52f9fd5',1,2,1095130.0,'2021-03-21 12:30:56',1874550.0,0.0),
	 ('afc13ae1ae52f9fd6',1,4,154976.0,'2021-09-02 03:49:23',1755610.0,0.0),
	 ('afc13ae1ae52f9fd7',1,3,1401250.0,'2022-06-06 13:33:42',1897170.0,0.0),
	 ('afc13ae1ae52f9fd8',1,2,1336720.0,'2023-07-30 21:16:54',1858590.0,0.0),
	 ('afc13ae1ae52f9fd9',1,4,1292980.0,'2022-12-15 11:39:00',1980540.0,0.0),
	 ('afc13ae1ae52f9fda',1,4,1744860.0,'2023-09-25 14:37:59',1940320.0,0.0),
	 ('afc13ae1ae52f9fdb',1,3,587169.0,'2022-05-05 18:00:09',1974890.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fdc',1,1,1557270.0,'2021-01-11 05:47:03',1692550.0,0.0),
	 ('afc13ae1ae52f9fdd',1,1,742800.0,'2021-08-25 01:07:47',1613450.0,0.0),
	 ('afc13ae1ae52f9fde',1,4,1165680.0,'2023-04-05 10:24:58',1792210.0,0.0),
	 ('afc13ae1ae52f9fdf',1,1,1331140.0,'2022-09-14 21:55:25',1558150.0,0.0),
	 ('afc13ae1ae52f9fe0',1,1,1741760.0,'2021-08-07 10:15:11',1872230.0,0.0),
	 ('afc13ae1ae52f9fe1',1,1,902889.0,'2021-05-26 19:28:30',1582960.0,0.0),
	 ('afc13ae1ae52f9fe2',1,2,1362260.0,'2023-10-03 16:57:32',1676930.0,0.0),
	 ('afc13ae1ae52f9fe3',1,2,293817.0,'2021-06-18 20:30:19',1846360.0,0.0),
	 ('afc13ae1ae52f9fe4',1,3,278645.0,'2023-04-27 16:00:38',1752910.0,0.0),
	 ('afc13ae1ae52f9fe5',1,1,1802860.0,'2022-05-21 18:45:29',1552790.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9fe6',1,3,1684060.0,'2021-01-02 09:47:07',1644790.0,0.0),
	 ('afc13ae1ae52f9fe7',1,4,1992280.0,'2022-07-08 08:17:32',1801570.0,0.0),
	 ('afc13ae1ae52f9fe8',1,1,883056.0,'2023-02-13 04:52:39',1838470.0,0.0),
	 ('afc13ae1ae52f9fe9',1,4,1452340.0,'2022-07-08 11:56:15',1974660.0,0.0),
	 ('afc13ae1ae52f9fea',1,3,1258330.0,'2020-11-28 04:24:48',1980600.0,0.0),
	 ('afc13ae1ae52f9feb',1,2,372164.0,'2021-09-13 19:49:25',1664800.0,0.0),
	 ('afc13ae1ae52f9fec',1,2,1423950.0,'2022-11-02 10:21:38',1786210.0,0.0),
	 ('afc13ae1ae52f9fed',1,4,65973.0,'2021-08-28 09:11:56',1521140.0,0.0),
	 ('afc13ae1ae52f9fee',1,2,1063140.0,'2022-07-23 13:53:18',1700780.0,0.0),
	 ('afc13ae1ae52f9fef',1,2,1576190.0,'2023-03-26 19:10:16',1784890.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ff0',1,3,437260.0,'2023-07-04 23:43:05',1547860.0,0.0),
	 ('afc13ae1ae52f9ff1',1,3,147567.0,'2021-06-29 17:41:39',1771900.0,0.0),
	 ('afc13ae1ae52f9ff2',1,3,1140580.0,'2022-08-10 03:28:33',1683110.0,0.0),
	 ('afc13ae1ae52f9ff3',1,4,981843.0,'2022-12-17 12:12:07',1758490.0,0.0),
	 ('afc13ae1ae52f9ff4',1,2,1143260.0,'2023-06-16 08:47:23',1994950.0,0.0),
	 ('afc13ae1ae52f9ff5',1,2,1770880.0,'2021-06-07 19:41:40',1628990.0,0.0),
	 ('afc13ae1ae52f9ff6',1,4,1827870.0,'2022-05-09 14:34:01',1715590.0,0.0),
	 ('afc13ae1ae52f9ff7',1,1,121237.0,'2021-12-06 17:28:45',1681670.0,0.0),
	 ('afc13ae1ae52f9ff8',1,2,1273250.0,'2021-12-16 17:16:03',1755050.0,0.0),
	 ('afc13ae1ae52f9ff9',1,3,1410310.0,'2022-05-08 08:10:19',1928860.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52f9ffa',1,4,545638.0,'2022-04-15 11:21:48',1718930.0,0.0),
	 ('afc13ae1ae52f9ffb',1,4,451640.0,'2023-04-17 18:05:12',1656650.0,0.0),
	 ('afc13ae1ae52f9ffc',1,3,1465030.0,'2023-04-06 02:39:14',1856560.0,0.0),
	 ('afc13ae1ae52f9ffd',1,4,697899.0,'2023-05-07 11:02:22',1659670.0,0.0),
	 ('afc13ae1ae52f9ffe',1,4,26127.0,'2022-12-06 18:59:36',1797880.0,0.0),
	 ('afc13ae1ae52f9fff',1,1,908913.0,'2023-03-28 20:48:14',1547220.0,0.0),
	 ('afc13ae1ae52fa000',1,3,404605.0,'2023-03-22 21:56:02',1703070.0,0.0),
	 ('afc13ae1ae52fa001',1,3,1412690.0,'2022-11-25 06:35:19',1559160.0,0.0),
	 ('afc13ae1ae52fa002',1,2,1681900.0,'2020-11-15 19:14:59',1856540.0,0.0),
	 ('afc13ae1ae52fa003',1,1,1031560.0,'2022-02-03 20:02:40',1680790.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa004',1,1,855779.0,'2022-12-20 05:31:37',1835340.0,0.0),
	 ('afc13ae1ae52fa005',1,3,703698.0,'2022-04-02 00:26:15',1989160.0,0.0),
	 ('afc13ae1ae52fa006',1,1,164147.0,'2021-10-04 20:58:38',1633860.0,0.0),
	 ('afc13ae1ae52fa007',1,2,1769820.0,'2021-11-07 13:04:17',1714280.0,0.0),
	 ('afc13ae1ae52fa008',1,2,500394.0,'2021-08-09 12:51:45',1687780.0,0.0),
	 ('afc13ae1ae52fa009',1,2,1266490.0,'2023-06-23 16:48:24',1642860.0,0.0),
	 ('afc13ae1ae52fa00a',1,1,1484390.0,'2022-02-25 11:06:00',1968690.0,0.0),
	 ('afc13ae1ae52fa00b',1,1,1613180.0,'2023-01-30 22:31:38',1897740.0,0.0),
	 ('afc13ae1ae52fa00c',1,4,724707.0,'2023-05-21 20:15:46',1548220.0,0.0),
	 ('afc13ae1ae52fa00d',1,4,1187500.0,'2022-06-24 19:39:13',1968300.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa00e',1,1,1206460.0,'2023-07-02 12:12:13',1532270.0,0.0),
	 ('afc13ae1ae52fa00f',1,4,1223740.0,'2021-04-25 18:00:15',1547780.0,0.0),
	 ('afc13ae1ae52fa010',1,4,1351760.0,'2022-07-05 17:54:33',1903260.0,0.0),
	 ('afc13ae1ae52fa011',1,1,1042920.0,'2020-11-30 13:52:38',1907250.0,0.0),
	 ('afc13ae1ae52fa012',1,3,1825500.0,'2023-06-11 20:57:22',1792190.0,0.0),
	 ('afc13ae1ae52fa013',1,3,340813.0,'2022-12-24 11:48:34',1833680.0,0.0),
	 ('afc13ae1ae52fa014',1,4,1138480.0,'2022-10-01 02:28:51',1645560.0,0.0),
	 ('afc13ae1ae52fa015',1,3,130473.0,'2023-01-10 14:05:38',1529130.0,0.0),
	 ('afc13ae1ae52fa016',1,4,52918.0,'2022-08-03 06:39:32',1587650.0,0.0),
	 ('afc13ae1ae52fa017',1,2,1079840.0,'2020-11-20 22:22:27',1874350.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa018',1,4,991746.0,'2021-05-01 16:23:11',1915860.0,0.0),
	 ('afc13ae1ae52fa019',1,2,758320.0,'2021-09-15 23:01:53',1768110.0,0.0),
	 ('afc13ae1ae52fa01a',1,4,1696020.0,'2023-03-31 16:15:20',1673450.0,0.0),
	 ('afc13ae1ae52fa01b',1,2,722031.0,'2021-06-13 14:26:08',1869520.0,0.0),
	 ('afc13ae1ae52fa01c',1,1,656139.0,'2021-01-21 16:07:03',1634920.0,0.0),
	 ('afc13ae1ae52fa01d',1,1,1000370.0,'2022-04-08 19:42:14',1550410.0,0.0),
	 ('afc13ae1ae52fa01e',1,3,331482.0,'2023-07-30 13:58:06',1749260.0,0.0),
	 ('afc13ae1ae52fa01f',1,1,1408160.0,'2023-08-08 18:41:50',1893690.0,0.0),
	 ('afc13ae1ae52fa020',1,2,34934.0,'2023-03-28 09:26:51',1792060.0,0.0),
	 ('afc13ae1ae52fa021',1,2,751127.0,'2021-07-04 13:43:20',1626630.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa022',1,2,429815.0,'2022-01-29 21:22:02',1574000.0,0.0),
	 ('afc13ae1ae52fa023',1,3,63243.0,'2023-04-06 06:37:16',1597250.0,0.0),
	 ('afc13ae1ae52fa024',1,1,1121100.0,'2023-07-27 15:58:24',1797650.0,0.0),
	 ('afc13ae1ae52fa025',1,4,1321720.0,'2022-04-09 09:11:51',1664790.0,0.0),
	 ('afc13ae1ae52fa026',1,2,1634080.0,'2021-02-06 09:38:44',1911110.0,0.0),
	 ('afc13ae1ae52fa027',1,2,1083900.0,'2021-04-06 03:50:05',1741390.0,0.0),
	 ('afc13ae1ae52fa028',1,3,485068.0,'2021-06-20 02:06:57',1826820.0,0.0),
	 ('afc13ae1ae52fa029',1,4,513037.0,'2023-04-24 19:41:35',1687500.0,0.0),
	 ('afc13ae1ae52fa02a',1,2,800282.0,'2023-02-22 06:18:31',1838620.0,0.0),
	 ('afc13ae1ae52fa02b',1,3,103384.0,'2022-07-20 08:54:17',1517190.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa02c',1,3,849448.0,'2023-09-28 10:19:37',1509120.0,0.0),
	 ('afc13ae1ae52fa02d',1,2,241411.0,'2021-10-15 18:30:58',1728390.0,0.0),
	 ('afc13ae1ae52fa02e',1,2,1901940.0,'2022-05-05 11:41:41',1930230.0,0.0),
	 ('afc13ae1ae52fa02f',1,4,925249.0,'2021-03-06 12:12:34',1850720.0,0.0),
	 ('afc13ae1ae52fa030',1,4,216355.0,'2022-08-10 03:26:38',1662860.0,0.0),
	 ('afc13ae1ae52fa031',1,3,1461950.0,'2021-09-08 19:00:19',1904710.0,0.0),
	 ('afc13ae1ae52fa032',1,3,1284660.0,'2021-06-01 16:15:22',1682230.0,0.0),
	 ('afc13ae1ae52fa033',1,2,600604.0,'2023-05-14 15:27:34',1703900.0,0.0),
	 ('afc13ae1ae52fa034',1,1,1166440.0,'2021-01-21 03:36:17',1957000.0,0.0),
	 ('afc13ae1ae52fa035',1,1,1959840.0,'2022-03-04 12:25:20',1664500.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa036',1,2,496831.0,'2023-07-18 03:55:40',1527430.0,0.0),
	 ('afc13ae1ae52fa037',1,1,1785520.0,'2021-04-02 03:43:17',1755830.0,0.0),
	 ('afc13ae1ae52fa038',1,1,1730350.0,'2022-11-27 14:44:47',1938630.0,0.0),
	 ('afc13ae1ae52fa039',1,2,120209.0,'2023-01-30 22:01:29',1707790.0,0.0),
	 ('afc13ae1ae52fa03a',1,1,749288.0,'2021-02-09 06:59:33',1591860.0,0.0),
	 ('afc13ae1ae52fa03b',1,4,213190.0,'2022-03-03 09:48:48',1515720.0,0.0),
	 ('afc13ae1ae52fa03c',1,3,1573300.0,'2023-10-09 14:57:10',1931320.0,0.0),
	 ('afc13ae1ae52fa03d',1,3,977237.0,'2021-08-22 18:37:57',1831490.0,0.0),
	 ('afc13ae1ae52fa03e',1,1,1798710.0,'2023-06-29 18:08:03',1609120.0,0.0),
	 ('afc13ae1ae52fa03f',1,4,277729.0,'2020-11-19 02:58:05',1986380.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa040',1,2,1365440.0,'2021-07-31 09:33:11',1741030.0,0.0),
	 ('afc13ae1ae52fa041',1,4,520022.0,'2021-09-01 13:54:57',1886450.0,0.0),
	 ('afc13ae1ae52fa042',1,3,629043.0,'2021-03-30 18:50:02',1588040.0,0.0),
	 ('afc13ae1ae52fa043',1,3,485226.0,'2022-09-23 03:27:29',1538950.0,0.0),
	 ('afc13ae1ae52fa044',1,1,615086.0,'2022-02-10 15:31:21',1626530.0,0.0),
	 ('afc13ae1ae52fa045',1,1,1276220.0,'2022-08-03 14:05:20',1634050.0,0.0),
	 ('afc13ae1ae52fa046',1,4,890509.0,'2021-12-17 01:30:14',1897620.0,0.0),
	 ('afc13ae1ae52fa047',1,2,1041500.0,'2022-11-05 03:13:32',1800830.0,0.0),
	 ('afc13ae1ae52fa048',1,1,421873.0,'2023-07-03 03:43:45',1587170.0,0.0),
	 ('afc13ae1ae52fa049',1,2,1414990.0,'2022-03-11 18:25:26',1545290.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa04a',1,4,1667440.0,'2022-12-15 20:23:50',1630550.0,0.0),
	 ('afc13ae1ae52fa04b',1,2,1952220.0,'2021-10-27 22:02:16',1701240.0,0.0),
	 ('afc13ae1ae52fa04c',1,1,1492680.0,'2023-08-12 15:33:17',1860440.0,0.0),
	 ('afc13ae1ae52fa04d',1,1,1643670.0,'2022-07-17 00:04:49',1563140.0,0.0),
	 ('afc13ae1ae52fa04e',1,1,1725880.0,'2022-12-06 04:51:06',1745520.0,0.0),
	 ('afc13ae1ae52fa04f',1,4,1423380.0,'2021-06-22 03:18:27',1792690.0,0.0),
	 ('afc13ae1ae52fa050',1,2,1108220.0,'2022-08-15 19:16:44',1999300.0,0.0),
	 ('afc13ae1ae52fa051',1,2,1558960.0,'2023-03-29 14:15:22',1799550.0,0.0),
	 ('afc13ae1ae52fa052',1,2,24740.0,'2023-03-13 11:11:05',1581170.0,0.0),
	 ('afc13ae1ae52fa053',1,4,1535320.0,'2023-09-02 23:45:14',1616800.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa054',1,3,853871.0,'2021-02-14 13:21:16',1936940.0,0.0),
	 ('afc13ae1ae52fa055',1,2,1071000.0,'2023-09-16 13:28:09',1830740.0,0.0),
	 ('afc13ae1ae52fa056',1,2,1991180.0,'2022-01-18 00:40:15',1901880.0,0.0),
	 ('afc13ae1ae52fa057',1,3,121928.0,'2021-05-18 09:01:28',1933370.0,0.0),
	 ('afc13ae1ae52fa058',1,3,1935380.0,'2021-05-08 11:36:46',1866720.0,0.0),
	 ('afc13ae1ae52fa059',1,2,1688760.0,'2021-02-11 13:51:35',1799570.0,0.0),
	 ('afc13ae1ae52fa05a',1,4,474443.0,'2021-02-22 02:55:20',1673970.0,0.0),
	 ('afc13ae1ae52fa05b',1,3,551462.0,'2023-04-01 15:47:35',1823350.0,0.0),
	 ('afc13ae1ae52fa05c',1,2,1849630.0,'2021-04-30 13:47:45',1595580.0,0.0),
	 ('afc13ae1ae52fa05d',1,1,143933.0,'2021-05-06 01:37:00',1632260.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa05e',1,4,1038790.0,'2022-01-02 11:07:47',1684770.0,0.0),
	 ('afc13ae1ae52fa05f',1,1,1762790.0,'2021-04-02 13:39:05',1767790.0,0.0),
	 ('afc13ae1ae52fa060',1,3,1503050.0,'2021-06-27 01:52:19',1867890.0,0.0),
	 ('afc13ae1ae52fa061',1,2,141330.0,'2022-05-13 08:24:34',1660000.0,0.0),
	 ('afc13ae1ae52fa062',1,4,948040.0,'2021-07-08 03:00:01',1846990.0,0.0),
	 ('afc13ae1ae52fa063',1,3,721061.0,'2023-08-11 14:19:10',1911760.0,0.0),
	 ('afc13ae1ae52fa064',1,3,347807.0,'2023-10-16 10:45:05',1802380.0,0.0),
	 ('afc13ae1ae52fa065',1,4,681494.0,'2023-05-18 07:49:11',1854210.0,0.0),
	 ('afc13ae1ae52fa066',1,3,1517360.0,'2021-05-15 22:20:10',1968700.0,0.0),
	 ('afc13ae1ae52fa067',1,2,679461.0,'2021-03-29 17:43:46',1598550.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa068',1,1,472881.0,'2022-02-12 11:43:21',1972020.0,0.0),
	 ('afc13ae1ae52fa069',1,1,314366.0,'2022-03-14 10:49:01',1861890.0,0.0),
	 ('afc13ae1ae52fa06a',1,4,1412700.0,'2023-11-04 17:15:30',1840850.0,0.0),
	 ('afc13ae1ae52fa06b',1,3,1913790.0,'2021-10-07 07:47:46',1514530.0,0.0),
	 ('afc13ae1ae52fa06c',1,1,178895.0,'2022-06-22 07:25:26',1547840.0,0.0),
	 ('afc13ae1ae52fa06d',1,2,724247.0,'2023-10-26 08:37:22',1629360.0,0.0),
	 ('afc13ae1ae52fa06e',1,1,586608.0,'2021-03-01 07:04:25',1761150.0,0.0),
	 ('afc13ae1ae52fa06f',1,4,1316470.0,'2021-06-30 00:41:08',1728500.0,0.0),
	 ('afc13ae1ae52fa070',1,1,81123.0,'2022-11-22 23:13:22',1551110.0,0.0),
	 ('afc13ae1ae52fa071',1,2,154697.0,'2023-08-05 21:22:45',1594160.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('afc13ae1ae52fa072',1,4,357966.0,'2022-03-20 09:57:37',1876380.0,0.0),
	 ('bfc13ae1ae52fa073',1,2,300398.0,'2023-06-15 14:05:32',1673970.0,0.0),
	 ('bfc13ae1ae52fa074',1,4,312237.0,'2021-11-05 22:49:52',1949400.0,0.0),
	 ('bfc13ae1ae52fa075',1,4,734847.0,'2022-07-24 00:10:03',1998140.0,0.0),
	 ('bfc13ae1ae52fa076',1,1,25194.0,'2022-01-14 03:55:56',1667920.0,0.0),
	 ('bfc13ae1ae52fa077',1,3,1000880.0,'2022-07-15 10:46:31',1506940.0,0.0),
	 ('bfc13ae1ae52fa078',1,4,335066.0,'2020-11-18 19:23:57',1873960.0,0.0),
	 ('bfc13ae1ae52fa079',1,1,554851.0,'2022-07-10 15:11:36',1508530.0,0.0),
	 ('bfc13ae1ae52fa07a',1,3,614585.0,'2021-12-23 13:14:47',1946030.0,0.0),
	 ('bfc13ae1ae52fa07b',1,4,1295600.0,'2021-01-14 20:59:15',1841040.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa07c',1,2,980459.0,'2023-02-03 00:27:44',1716220.0,0.0),
	 ('bfc13ae1ae52fa07d',1,3,690273.0,'2022-03-06 09:16:15',1607930.0,0.0),
	 ('bfc13ae1ae52fa07e',1,4,656232.0,'2021-08-13 22:11:59',1584790.0,0.0),
	 ('bfc13ae1ae52fa07f',1,4,633834.0,'2022-07-20 23:28:26',1506210.0,0.0),
	 ('bfc13ae1ae52fa080',1,4,489518.0,'2021-11-04 12:08:16',1522870.0,0.0),
	 ('bfc13ae1ae52fa081',1,3,1847330.0,'2021-10-27 07:19:08',1541970.0,0.0),
	 ('bfc13ae1ae52fa082',1,2,802369.0,'2022-12-01 01:18:43',1898560.0,0.0),
	 ('bfc13ae1ae52fa083',1,1,460543.0,'2022-10-25 03:09:08',1922650.0,0.0),
	 ('bfc13ae1ae52fa084',1,2,758629.0,'2021-06-08 08:03:00',1822250.0,0.0),
	 ('bfc13ae1ae52fa085',1,3,33211.0,'2021-02-22 11:44:42',1672320.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa086',1,1,1516190.0,'2021-01-09 05:39:01',1789240.0,0.0),
	 ('bfc13ae1ae52fa087',1,3,977307.0,'2023-01-20 08:11:04',1783470.0,0.0),
	 ('bfc13ae1ae52fa088',1,2,1320840.0,'2022-11-07 20:32:54',1961170.0,0.0),
	 ('bfc13ae1ae52fa089',1,1,1254000.0,'2022-12-07 04:54:34',1731230.0,0.0),
	 ('bfc13ae1ae52fa08a',1,2,998917.0,'2021-10-15 14:57:14',1749250.0,0.0),
	 ('bfc13ae1ae52fa08b',1,3,1119050.0,'2023-05-13 19:36:07',1936710.0,0.0),
	 ('bfc13ae1ae52fa08c',1,3,1099580.0,'2021-12-09 17:58:01',1712060.0,0.0),
	 ('bfc13ae1ae52fa08d',1,1,1814170.0,'2022-07-21 23:22:12',1852690.0,0.0),
	 ('bfc13ae1ae52fa08e',1,1,294665.0,'2021-09-14 04:09:43',1707760.0,0.0),
	 ('bfc13ae1ae52fa08f',1,1,1383240.0,'2023-02-14 09:29:27',1567720.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa090',1,2,602821.0,'2021-02-19 07:26:09',1624210.0,0.0),
	 ('bfc13ae1ae52fa091',1,3,160673.0,'2022-02-15 16:29:02',1614590.0,0.0),
	 ('bfc13ae1ae52fa092',1,4,517340.0,'2023-06-17 16:14:54',1566090.0,0.0),
	 ('bfc13ae1ae52fa093',1,4,1697920.0,'2023-04-26 12:43:19',1604080.0,0.0),
	 ('bfc13ae1ae52fa094',1,4,1705620.0,'2023-05-29 23:44:30',1850230.0,0.0),
	 ('bfc13ae1ae52fa095',1,1,431622.0,'2022-07-18 09:37:57',1653600.0,0.0),
	 ('bfc13ae1ae52fa096',1,1,954960.0,'2021-11-11 14:36:07',1570000.0,0.0),
	 ('bfc13ae1ae52fa097',1,1,744102.0,'2023-07-09 03:05:46',1876480.0,0.0),
	 ('bfc13ae1ae52fa098',1,3,1702560.0,'2021-04-01 14:34:19',1521340.0,0.0),
	 ('bfc13ae1ae52fa099',1,3,578245.0,'2022-02-04 14:22:01',1910570.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa09a',1,1,1445770.0,'2023-10-07 18:41:52',1735200.0,0.0),
	 ('bfc13ae1ae52fa09b',1,1,1563430.0,'2021-01-16 23:19:08',1893380.0,0.0),
	 ('bfc13ae1ae52fa09c',1,2,894484.0,'2023-10-10 23:15:15',1859790.0,0.0),
	 ('bfc13ae1ae52fa09d',1,4,424375.0,'2021-04-16 16:49:04',1703880.0,0.0),
	 ('bfc13ae1ae52fa09e',1,2,338537.0,'2021-10-23 14:16:18',1793480.0,0.0),
	 ('bfc13ae1ae52fa09f',1,2,213839.0,'2021-02-08 13:41:13',1765560.0,0.0),
	 ('bfc13ae1ae52fa0a0',1,2,209785.0,'2022-10-11 18:47:07',1692490.0,0.0),
	 ('bfc13ae1ae52fa0a1',1,1,393654.0,'2020-12-04 11:30:16',1768380.0,0.0),
	 ('bfc13ae1ae52fa0a2',1,1,1351870.0,'2023-05-04 21:30:04',1882880.0,0.0),
	 ('bfc13ae1ae52fa0a3',1,1,231878.0,'2022-12-12 11:30:15',1609390.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0a4',1,1,138051.0,'2021-03-05 18:58:31',1835640.0,0.0),
	 ('bfc13ae1ae52fa0a5',1,1,991577.0,'2023-09-08 05:12:34',1568920.0,0.0),
	 ('bfc13ae1ae52fa0a6',1,4,198593.0,'2021-11-19 18:54:49',1546690.0,0.0),
	 ('bfc13ae1ae52fa0a7',1,3,54999.0,'2021-12-06 22:01:57',1578500.0,0.0),
	 ('bfc13ae1ae52fa0a8',1,3,1932870.0,'2022-07-11 23:46:49',1551550.0,0.0),
	 ('bfc13ae1ae52fa0a9',1,1,77324.0,'2021-08-20 16:11:55',1702640.0,0.0),
	 ('bfc13ae1ae52fa0aa',1,3,1541060.0,'2021-06-30 13:32:32',1633140.0,0.0),
	 ('bfc13ae1ae52fa0ab',1,4,1034150.0,'2021-03-10 05:44:48',1593060.0,0.0),
	 ('bfc13ae1ae52fa0ac',1,4,587184.0,'2022-06-07 23:50:25',1520930.0,0.0),
	 ('bfc13ae1ae52fa0ad',1,4,733415.0,'2023-01-12 15:42:12',1915160.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0ae',1,3,1369420.0,'2022-03-09 19:24:58',1906200.0,0.0),
	 ('bfc13ae1ae52fa0af',1,4,751639.0,'2023-10-21 21:15:00',1808200.0,0.0),
	 ('bfc13ae1ae52fa0b0',1,4,1628660.0,'2023-03-06 06:04:50',1940420.0,0.0),
	 ('bfc13ae1ae52fa0b1',1,3,1981680.0,'2021-11-23 02:40:18',1758300.0,0.0),
	 ('bfc13ae1ae52fa0b2',1,3,1699560.0,'2021-10-06 03:58:00',1725070.0,0.0),
	 ('bfc13ae1ae52fa0b3',1,1,610565.0,'2022-06-01 05:23:49',1854920.0,0.0),
	 ('bfc13ae1ae52fa0b4',1,2,1555450.0,'2022-06-09 02:00:04',1912130.0,0.0),
	 ('bfc13ae1ae52fa0b5',1,1,20231.0,'2023-06-27 15:59:58',1744810.0,0.0),
	 ('bfc13ae1ae52fa0b6',1,1,7252.0,'2023-09-17 10:19:52',1630810.0,0.0),
	 ('bfc13ae1ae52fa0b7',1,1,1935910.0,'2021-01-19 08:45:02',1732600.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0b8',1,1,87080.0,'2020-12-06 01:46:27',1660110.0,0.0),
	 ('bfc13ae1ae52fa0b9',1,1,179713.0,'2023-01-13 02:05:52',1655980.0,0.0),
	 ('bfc13ae1ae52fa0ba',1,3,263714.0,'2021-10-20 09:31:22',1722890.0,0.0),
	 ('bfc13ae1ae52fa0bb',1,4,799353.0,'2023-07-20 16:27:11',1767510.0,0.0),
	 ('bfc13ae1ae52fa0bc',1,2,712782.0,'2022-04-03 15:37:27',1943400.0,0.0),
	 ('bfc13ae1ae52fa0bd',1,3,1508920.0,'2021-05-25 12:55:55',1529980.0,0.0),
	 ('bfc13ae1ae52fa0be',1,3,685361.0,'2021-08-14 08:50:56',1985680.0,0.0),
	 ('bfc13ae1ae52fa0bf',1,2,482432.0,'2022-09-07 05:26:58',1668590.0,0.0),
	 ('bfc13ae1ae52fa0c0',1,2,889335.0,'2023-04-13 15:54:08',1873320.0,0.0),
	 ('bfc13ae1ae52fa0c1',1,1,1177620.0,'2022-03-04 13:12:30',1528500.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0c2',1,4,1336630.0,'2023-08-03 20:17:01',1513230.0,0.0),
	 ('bfc13ae1ae52fa0c3',1,2,1465970.0,'2021-09-24 00:43:34',1647740.0,0.0),
	 ('bfc13ae1ae52fa0c4',1,4,1967650.0,'2021-05-08 05:39:02',1507260.0,0.0),
	 ('bfc13ae1ae52fa0c5',1,1,489389.0,'2021-12-27 17:00:54',1704710.0,0.0),
	 ('bfc13ae1ae52fa0c6',1,3,567536.0,'2021-02-12 05:41:21',1976910.0,0.0),
	 ('bfc13ae1ae52fa0c7',1,3,1277160.0,'2023-06-13 15:10:35',1759350.0,0.0),
	 ('bfc13ae1ae52fa0c8',1,4,1053350.0,'2023-09-26 02:23:13',1586070.0,0.0),
	 ('bfc13ae1ae52fa0c9',1,4,1259720.0,'2023-09-23 04:03:49',1867130.0,0.0),
	 ('bfc13ae1ae52fa0ca',1,2,1372000.0,'2021-10-31 10:39:35',1831300.0,0.0),
	 ('bfc13ae1ae52fa0cb',1,4,1625370.0,'2021-07-19 09:09:21',1676190.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0cc',1,2,1960320.0,'2022-10-06 16:48:00',1593670.0,0.0),
	 ('bfc13ae1ae52fa0cd',1,2,834275.0,'2022-09-14 10:12:25',1717720.0,0.0),
	 ('bfc13ae1ae52fa0ce',1,2,1682880.0,'2021-03-01 13:05:46',1560680.0,0.0),
	 ('bfc13ae1ae52fa0cf',1,3,1094820.0,'2021-02-03 12:51:15',1959380.0,0.0),
	 ('bfc13ae1ae52fa0d0',1,3,1887990.0,'2021-01-21 12:53:46',1611340.0,0.0),
	 ('bfc13ae1ae52fa0d1',1,2,1050770.0,'2022-05-06 01:36:03',1629560.0,0.0),
	 ('bfc13ae1ae52fa0d2',1,4,1303610.0,'2022-06-02 06:33:15',1588240.0,0.0),
	 ('bfc13ae1ae52fa0d3',1,3,1979420.0,'2023-03-09 13:35:34',1748120.0,0.0),
	 ('bfc13ae1ae52fa0d4',1,2,46542.0,'2023-06-17 13:14:08',1638130.0,0.0),
	 ('bfc13ae1ae52fa0d5',1,4,667624.0,'2021-04-25 17:13:31',1845410.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0d6',1,3,1863100.0,'2023-07-09 05:13:30',1641980.0,0.0),
	 ('bfc13ae1ae52fa0d7',1,1,748400.0,'2023-11-03 20:34:36',1628860.0,0.0),
	 ('bfc13ae1ae52fa0d8',1,4,384525.0,'2021-10-10 08:50:25',1997470.0,0.0),
	 ('bfc13ae1ae52fa0d9',1,4,748602.0,'2023-08-30 15:01:46',1716700.0,0.0),
	 ('bfc13ae1ae52fa0da',1,1,1417920.0,'2022-08-31 21:05:35',1748030.0,0.0),
	 ('bfc13ae1ae52fa0db',1,1,274787.0,'2022-08-09 20:08:56',1834100.0,0.0),
	 ('bfc13ae1ae52fa0dc',1,4,1222560.0,'2023-05-11 11:21:16',1757480.0,0.0),
	 ('bfc13ae1ae52fa0dd',1,4,1413300.0,'2022-10-28 03:06:53',1614880.0,0.0),
	 ('bfc13ae1ae52fa0de',1,4,162686.0,'2021-08-16 17:25:00',1511570.0,0.0),
	 ('bfc13ae1ae52fa0df',1,2,946567.0,'2022-06-24 16:07:02',1979750.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0e0',1,1,567348.0,'2022-11-03 04:28:46',1729480.0,0.0),
	 ('bfc13ae1ae52fa0e1',1,4,504849.0,'2021-05-10 04:24:15',1660490.0,0.0),
	 ('bfc13ae1ae52fa0e2',1,3,1761020.0,'2023-10-21 14:15:27',1797350.0,0.0),
	 ('bfc13ae1ae52fa0e3',1,1,935758.0,'2021-06-18 00:36:23',1891720.0,0.0),
	 ('bfc13ae1ae52fa0e4',1,3,1216020.0,'2022-01-13 18:38:09',1669880.0,0.0),
	 ('bfc13ae1ae52fa0e5',1,3,440530.0,'2021-12-14 23:38:37',1870480.0,0.0),
	 ('bfc13ae1ae52fa0e6',1,2,1738620.0,'2022-10-08 17:32:18',1631500.0,0.0),
	 ('bfc13ae1ae52fa0e7',1,4,1984410.0,'2022-12-29 04:59:44',1831300.0,0.0),
	 ('bfc13ae1ae52fa0e8',1,1,1794250.0,'2021-06-02 03:23:18',1722300.0,0.0),
	 ('bfc13ae1ae52fa0e9',1,3,65974.0,'2023-06-08 11:49:53',1981700.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0ea',1,3,1581020.0,'2022-03-14 01:21:47',1763710.0,0.0),
	 ('bfc13ae1ae52fa0eb',1,2,1019540.0,'2021-07-01 06:32:28',1500890.0,0.0),
	 ('bfc13ae1ae52fa0ec',1,4,1727150.0,'2022-09-26 04:42:55',1659240.0,0.0),
	 ('bfc13ae1ae52fa0ed',1,4,429942.0,'2021-12-05 04:07:55',1609870.0,0.0),
	 ('bfc13ae1ae52fa0ee',1,3,1204310.0,'2022-03-07 21:27:40',1839760.0,0.0),
	 ('bfc13ae1ae52fa0ef',1,1,1679810.0,'2023-03-07 19:00:41',1697130.0,0.0),
	 ('bfc13ae1ae52fa0f0',1,3,927576.0,'2022-01-01 18:59:14',1936810.0,0.0),
	 ('bfc13ae1ae52fa0f1',1,4,1377230.0,'2022-06-03 13:57:09',1626870.0,0.0),
	 ('bfc13ae1ae52fa0f2',1,4,1685770.0,'2022-02-27 15:39:07',1784280.0,0.0),
	 ('bfc13ae1ae52fa0f3',1,4,1364600.0,'2023-05-23 22:02:00',1713400.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0f4',1,3,1520140.0,'2022-09-22 02:31:33',1602190.0,0.0),
	 ('bfc13ae1ae52fa0f5',1,1,251772.0,'2021-04-04 00:10:00',1729130.0,0.0),
	 ('bfc13ae1ae52fa0f6',1,2,537312.0,'2021-05-11 08:31:39',1708630.0,0.0),
	 ('bfc13ae1ae52fa0f7',1,3,1477370.0,'2023-07-03 11:05:49',1790560.0,0.0),
	 ('bfc13ae1ae52fa0f8',1,2,239915.0,'2022-05-03 16:23:51',1926660.0,0.0),
	 ('bfc13ae1ae52fa0f9',1,1,979395.0,'2021-07-19 14:44:51',1826430.0,0.0),
	 ('bfc13ae1ae52fa0fa',1,2,1800370.0,'2022-11-05 18:59:14',1871210.0,0.0),
	 ('bfc13ae1ae52fa0fb',1,4,1325780.0,'2022-06-30 19:24:05',1864230.0,0.0),
	 ('bfc13ae1ae52fa0fc',1,3,472630.0,'2022-04-06 10:16:17',1787370.0,0.0),
	 ('bfc13ae1ae52fa0fd',1,3,84449.0,'2020-12-04 05:48:04',1910610.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa0fe',1,4,12536.0,'2023-01-08 22:28:51',1692120.0,0.0),
	 ('bfc13ae1ae52fa0ff',1,2,1856520.0,'2022-07-17 16:13:08',1809940.0,0.0),
	 ('bfc13ae1ae52fa100',1,2,1199630.0,'2022-12-22 07:12:30',1553710.0,0.0),
	 ('bfc13ae1ae52fa101',1,3,1855980.0,'2021-04-02 00:51:25',1530040.0,0.0),
	 ('bfc13ae1ae52fa102',1,1,1088710.0,'2021-06-15 21:50:52',1628800.0,0.0),
	 ('bfc13ae1ae52fa103',1,1,1750280.0,'2023-03-25 03:49:30',1643190.0,0.0),
	 ('bfc13ae1ae52fa104',1,4,53405.0,'2022-08-15 05:41:31',1722850.0,0.0),
	 ('bfc13ae1ae52fa105',1,2,175836.0,'2022-12-15 08:04:37',1665100.0,0.0),
	 ('bfc13ae1ae52fa106',1,2,1765370.0,'2022-07-04 06:36:23',1746280.0,0.0),
	 ('bfc13ae1ae52fa107',1,4,643901.0,'2022-03-02 12:33:49',1959740.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa108',1,4,1184160.0,'2021-11-12 02:02:30',1586910.0,0.0),
	 ('bfc13ae1ae52fa109',1,3,1785330.0,'2020-12-13 00:17:24',1763640.0,0.0),
	 ('bfc13ae1ae52fa10a',1,1,140409.0,'2023-07-25 02:56:31',1996840.0,0.0),
	 ('bfc13ae1ae52fa10b',1,4,1324760.0,'2023-10-14 02:05:58',1919300.0,0.0),
	 ('bfc13ae1ae52fa10c',1,2,1612020.0,'2022-10-15 18:45:02',1558070.0,0.0),
	 ('bfc13ae1ae52fa10d',1,3,1268720.0,'2022-05-23 21:58:33',1719180.0,0.0),
	 ('bfc13ae1ae52fa10e',1,1,846029.0,'2022-04-12 05:21:39',1657050.0,0.0),
	 ('bfc13ae1ae52fa10f',1,4,1110930.0,'2023-08-07 15:10:39',1518390.0,0.0),
	 ('bfc13ae1ae52fa110',1,4,1081010.0,'2023-07-08 07:15:06',1512150.0,0.0),
	 ('bfc13ae1ae52fa111',1,4,1486130.0,'2022-11-02 17:03:45',1740040.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa112',1,3,1705570.0,'2023-07-23 14:30:07',1657260.0,0.0),
	 ('bfc13ae1ae52fa113',1,4,1699660.0,'2021-01-25 06:34:52',1914630.0,0.0),
	 ('bfc13ae1ae52fa114',1,4,951593.0,'2020-11-09 20:24:49',1941760.0,0.0),
	 ('bfc13ae1ae52fa115',1,4,748188.0,'2021-10-13 00:53:41',1801500.0,0.0),
	 ('bfc13ae1ae52fa116',1,3,76366.0,'2022-07-25 07:52:24',1910710.0,0.0),
	 ('bfc13ae1ae52fa117',1,4,1684480.0,'2021-06-06 05:29:14',1604190.0,0.0),
	 ('bfc13ae1ae52fa118',1,1,1929190.0,'2023-02-04 04:10:11',1949770.0,0.0),
	 ('bfc13ae1ae52fa119',1,1,1577180.0,'2023-06-25 11:14:14',1791100.0,0.0),
	 ('bfc13ae1ae52fa11a',1,2,1785810.0,'2022-07-16 09:28:48',1975900.0,0.0),
	 ('bfc13ae1ae52fa11b',1,2,969661.0,'2023-10-31 03:11:21',1799720.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa11c',1,4,1990450.0,'2021-01-27 01:37:12',1860840.0,0.0),
	 ('bfc13ae1ae52fa11d',1,1,1259560.0,'2023-01-12 12:47:30',1970250.0,0.0),
	 ('bfc13ae1ae52fa11e',1,4,366381.0,'2023-05-19 22:39:21',1941970.0,0.0),
	 ('bfc13ae1ae52fa11f',1,2,976972.0,'2022-12-17 08:19:27',1540110.0,0.0),
	 ('bfc13ae1ae52fa120',1,2,1715820.0,'2023-07-14 19:51:05',1623260.0,0.0),
	 ('bfc13ae1ae52fa121',1,1,1070900.0,'2023-06-07 20:16:51',1994210.0,0.0),
	 ('bfc13ae1ae52fa122',1,4,1649440.0,'2020-12-29 09:37:54',1597350.0,0.0),
	 ('bfc13ae1ae52fa123',1,1,1491420.0,'2023-06-13 15:27:15',1896310.0,0.0),
	 ('bfc13ae1ae52fa124',1,1,90111.0,'2023-08-08 02:21:00',1617930.0,0.0),
	 ('bfc13ae1ae52fa125',1,2,305612.0,'2023-10-17 17:23:15',1783370.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa126',1,1,1021700.0,'2021-07-12 23:31:58',1593870.0,0.0),
	 ('bfc13ae1ae52fa127',1,4,1242360.0,'2023-04-14 04:14:33',1616820.0,0.0),
	 ('bfc13ae1ae52fa128',1,3,1332350.0,'2022-01-17 13:24:53',1578670.0,0.0),
	 ('bfc13ae1ae52fa129',1,4,237746.0,'2022-01-11 05:44:35',1599910.0,0.0),
	 ('bfc13ae1ae52fa12a',1,1,983316.0,'2023-04-01 16:35:21',1764270.0,0.0),
	 ('bfc13ae1ae52fa12b',1,3,1469030.0,'2022-02-28 16:51:57',1577000.0,0.0),
	 ('bfc13ae1ae52fa12c',1,2,1245050.0,'2022-04-16 04:20:11',1890570.0,0.0),
	 ('bfc13ae1ae52fa12d',1,3,1042860.0,'2022-09-15 22:02:49',1748880.0,0.0),
	 ('bfc13ae1ae52fa12e',1,2,596440.0,'2022-06-29 17:03:02',1562270.0,0.0),
	 ('bfc13ae1ae52fa12f',1,1,967218.0,'2021-11-12 11:33:47',1674520.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa130',1,3,1053790.0,'2021-04-15 06:26:15',1988570.0,0.0),
	 ('bfc13ae1ae52fa131',1,2,1699810.0,'2022-09-01 04:52:03',1802800.0,0.0),
	 ('bfc13ae1ae52fa132',1,4,1926300.0,'2021-08-24 13:30:22',1637870.0,0.0),
	 ('bfc13ae1ae52fa133',1,2,1321770.0,'2021-11-28 09:45:31',1699990.0,0.0),
	 ('bfc13ae1ae52fa134',1,2,433532.0,'2022-03-17 23:02:51',1546890.0,0.0),
	 ('bfc13ae1ae52fa135',1,3,341245.0,'2023-09-10 13:46:52',1817930.0,0.0),
	 ('bfc13ae1ae52fa136',1,4,1087100.0,'2022-09-21 22:14:15',1984240.0,0.0),
	 ('bfc13ae1ae52fa137',1,1,654518.0,'2022-10-22 11:53:31',1541550.0,0.0),
	 ('bfc13ae1ae52fa138',1,1,973098.0,'2023-01-10 10:44:17',1704660.0,0.0),
	 ('bfc13ae1ae52fa139',1,2,1315860.0,'2022-05-29 13:10:38',1944890.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa13a',1,2,1869060.0,'2021-10-02 12:52:11',1795300.0,0.0),
	 ('bfc13ae1ae52fa13b',1,2,75066.0,'2022-01-06 19:41:59',1830700.0,0.0),
	 ('bfc13ae1ae52fa13c',1,2,673974.0,'2023-03-30 07:16:48',1738240.0,0.0),
	 ('bfc13ae1ae52fa13d',1,4,429024.0,'2020-11-16 14:56:42',1939660.0,0.0),
	 ('bfc13ae1ae52fa13e',1,3,623243.0,'2021-01-13 14:39:18',1983620.0,0.0),
	 ('bfc13ae1ae52fa13f',1,1,1004570.0,'2023-02-01 02:26:50',1557530.0,0.0),
	 ('bfc13ae1ae52fa140',1,3,425123.0,'2023-09-01 02:17:07',1880770.0,0.0),
	 ('bfc13ae1ae52fa141',1,3,193943.0,'2023-08-30 09:09:38',1797160.0,0.0),
	 ('bfc13ae1ae52fa142',1,3,1268940.0,'2023-09-18 05:11:43',1754970.0,0.0),
	 ('bfc13ae1ae52fa143',1,3,1069300.0,'2022-08-26 23:16:09',1622110.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa144',1,2,1056320.0,'2023-01-09 22:29:58',1837990.0,0.0),
	 ('bfc13ae1ae52fa145',1,1,1301030.0,'2023-01-12 11:25:13',1851670.0,0.0),
	 ('bfc13ae1ae52fa146',1,3,1900490.0,'2023-04-21 10:35:32',1678550.0,0.0),
	 ('bfc13ae1ae52fa147',1,4,1163990.0,'2022-09-13 19:09:07',1829900.0,0.0),
	 ('bfc13ae1ae52fa148',1,3,1844610.0,'2022-05-01 07:44:42',1849670.0,0.0),
	 ('bfc13ae1ae52fa149',1,3,260609.0,'2022-03-07 09:14:57',1647370.0,0.0),
	 ('bfc13ae1ae52fa14a',1,4,1064210.0,'2023-06-30 03:32:30',1779970.0,0.0),
	 ('bfc13ae1ae52fa14b',1,4,1018580.0,'2021-05-13 14:45:27',1867020.0,0.0),
	 ('bfc13ae1ae52fa14c',1,3,876488.0,'2021-05-24 15:32:48',1693420.0,0.0),
	 ('bfc13ae1ae52fa14d',1,4,1014170.0,'2021-09-11 01:46:13',1604890.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa14e',1,2,528629.0,'2023-03-24 21:41:50',1564630.0,0.0),
	 ('bfc13ae1ae52fa14f',1,4,1884520.0,'2023-10-13 13:30:51',1734970.0,0.0),
	 ('bfc13ae1ae52fa150',1,2,169275.0,'2021-01-02 13:08:55',1545750.0,0.0),
	 ('bfc13ae1ae52fa151',1,1,308919.0,'2022-12-25 11:15:44',1743740.0,0.0),
	 ('bfc13ae1ae52fa152',1,1,1655290.0,'2022-04-25 18:58:24',1610990.0,0.0),
	 ('bfc13ae1ae52fa153',1,2,263249.0,'2023-09-17 17:04:37',1778970.0,0.0),
	 ('bfc13ae1ae52fa154',1,4,238607.0,'2022-01-03 09:37:25',1500190.0,0.0),
	 ('bfc13ae1ae52fa155',1,3,1668270.0,'2023-06-04 03:39:03',1633690.0,0.0),
	 ('bfc13ae1ae52fa156',1,3,1400850.0,'2023-02-15 16:26:36',1892000.0,0.0),
	 ('bfc13ae1ae52fa157',1,4,1249230.0,'2022-12-05 15:31:50',1661600.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa158',1,3,472020.0,'2022-03-06 21:40:55',1914870.0,0.0),
	 ('bfc13ae1ae52fa159',1,3,394042.0,'2021-05-04 12:07:24',1863550.0,0.0),
	 ('bfc13ae1ae52fa15a',1,2,379198.0,'2023-08-09 23:13:28',1937920.0,0.0),
	 ('bfc13ae1ae52fa15b',1,1,1908170.0,'2023-10-31 17:03:39',1914610.0,0.0),
	 ('bfc13ae1ae52fa15c',1,2,1429240.0,'2023-06-01 03:27:51',1793560.0,0.0),
	 ('bfc13ae1ae52fa15d',1,4,368517.0,'2022-10-28 15:09:07',1920650.0,0.0),
	 ('bfc13ae1ae52fa15e',1,2,487102.0,'2021-05-23 12:13:07',1529490.0,0.0),
	 ('bfc13ae1ae52fa15f',1,4,439985.0,'2023-01-24 05:35:57',1886140.0,0.0),
	 ('bfc13ae1ae52fa160',1,3,1466260.0,'2022-02-01 07:01:32',1652360.0,0.0),
	 ('bfc13ae1ae52fa161',1,1,742149.0,'2023-06-16 03:06:53',1591250.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa162',1,2,1986240.0,'2022-06-02 01:34:22',1770950.0,0.0),
	 ('bfc13ae1ae52fa163',1,4,711312.0,'2022-05-16 16:11:28',1893710.0,0.0),
	 ('bfc13ae1ae52fa164',1,1,1526870.0,'2021-10-10 04:53:31',1643360.0,0.0),
	 ('bfc13ae1ae52fa165',1,2,1094370.0,'2023-01-01 05:38:49',1645240.0,0.0),
	 ('bfc13ae1ae52fa166',1,4,772020.0,'2022-02-21 21:48:50',1823380.0,0.0),
	 ('bfc13ae1ae52fa167',1,2,962375.0,'2022-07-15 23:03:37',1701920.0,0.0),
	 ('bfc13ae1ae52fa168',1,1,923264.0,'2023-10-08 04:21:39',1962660.0,0.0),
	 ('bfc13ae1ae52fa169',1,1,953102.0,'2021-03-27 21:22:58',1852440.0,0.0),
	 ('bfc13ae1ae52fa16a',1,1,424792.0,'2022-05-10 14:20:24',1745330.0,0.0),
	 ('bfc13ae1ae52fa16b',1,2,1744900.0,'2022-03-01 06:13:26',1544560.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa16c',1,4,1492220.0,'2021-04-28 17:30:18',1712710.0,0.0),
	 ('bfc13ae1ae52fa16d',1,4,310343.0,'2023-08-26 23:44:33',1735410.0,0.0),
	 ('bfc13ae1ae52fa16e',1,2,1004050.0,'2022-11-04 13:18:37',1742890.0,0.0),
	 ('bfc13ae1ae52fa16f',1,3,1500430.0,'2023-04-25 09:40:13',1822350.0,0.0),
	 ('bfc13ae1ae52fa170',1,1,244731.0,'2023-08-18 00:40:00',1670920.0,0.0),
	 ('bfc13ae1ae52fa171',1,4,53857.0,'2022-07-31 03:40:40',1513140.0,0.0),
	 ('bfc13ae1ae52fa172',1,3,1809260.0,'2021-09-28 04:14:22',1765400.0,0.0),
	 ('bfc13ae1ae52fa173',1,1,1254770.0,'2023-06-27 19:29:04',1654470.0,0.0),
	 ('bfc13ae1ae52fa174',1,1,1867130.0,'2021-07-30 20:01:20',1823270.0,0.0),
	 ('bfc13ae1ae52fa175',1,4,1432860.0,'2022-02-15 01:08:16',1751650.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa176',1,3,95903.0,'2020-12-23 04:53:24',1578310.0,0.0),
	 ('bfc13ae1ae52fa177',1,2,35488.0,'2022-02-20 11:12:09',1550270.0,0.0),
	 ('bfc13ae1ae52fa178',1,1,1041400.0,'2023-06-19 08:24:02',1976170.0,0.0),
	 ('bfc13ae1ae52fa179',1,4,1505860.0,'2023-07-25 11:43:43',1948610.0,0.0),
	 ('bfc13ae1ae52fa17a',1,4,877066.0,'2021-06-14 08:17:56',1915580.0,0.0),
	 ('bfc13ae1ae52fa17b',1,3,882686.0,'2020-12-04 15:16:20',1918220.0,0.0),
	 ('bfc13ae1ae52fa17c',1,2,535476.0,'2021-07-22 13:08:14',1518050.0,0.0),
	 ('bfc13ae1ae52fa17d',1,1,1927030.0,'2022-09-02 16:42:22',1691920.0,0.0),
	 ('bfc13ae1ae52fa17e',1,3,652296.0,'2022-06-04 15:39:16',1515210.0,0.0),
	 ('bfc13ae1ae52fa17f',1,4,1201110.0,'2023-09-01 17:44:42',1757770.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa180',1,1,1044270.0,'2022-09-28 01:01:01',1655180.0,0.0),
	 ('bfc13ae1ae52fa181',1,2,147777.0,'2022-12-28 13:42:13',1843920.0,0.0),
	 ('bfc13ae1ae52fa182',1,2,1238590.0,'2023-10-10 05:18:55',1684920.0,0.0),
	 ('bfc13ae1ae52fa183',1,1,1792800.0,'2023-06-30 17:50:21',1740420.0,0.0),
	 ('bfc13ae1ae52fa184',1,2,898795.0,'2021-05-16 16:28:22',1750400.0,0.0),
	 ('bfc13ae1ae52fa185',1,3,1418540.0,'2021-01-30 08:20:20',1981510.0,0.0),
	 ('bfc13ae1ae52fa186',1,3,721334.0,'2021-01-15 04:51:21',1502590.0,0.0),
	 ('bfc13ae1ae52fa187',1,1,788146.0,'2023-08-24 14:39:14',1503110.0,0.0),
	 ('bfc13ae1ae52fa188',1,2,549921.0,'2023-08-26 07:37:55',1821360.0,0.0),
	 ('bfc13ae1ae52fa189',1,3,1351260.0,'2021-11-07 11:34:27',1582810.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa18a',1,4,302032.0,'2022-01-02 22:29:47',1529590.0,0.0),
	 ('bfc13ae1ae52fa18b',1,4,1375860.0,'2023-05-21 13:01:43',1688120.0,0.0),
	 ('bfc13ae1ae52fa18c',1,4,1849590.0,'2023-09-22 19:31:32',1767580.0,0.0),
	 ('bfc13ae1ae52fa18d',1,3,178676.0,'2021-10-26 17:42:42',1725560.0,0.0),
	 ('bfc13ae1ae52fa18e',1,4,1073440.0,'2022-07-29 06:58:18',1602730.0,0.0),
	 ('bfc13ae1ae52fa18f',1,1,492779.0,'2022-05-06 11:03:21',1503060.0,0.0),
	 ('bfc13ae1ae52fa190',1,4,571571.0,'2021-04-18 13:52:11',1929750.0,0.0),
	 ('bfc13ae1ae52fa191',1,2,371359.0,'2022-02-04 05:29:01',1997920.0,0.0),
	 ('bfc13ae1ae52fa192',1,3,1356090.0,'2022-01-15 11:47:10',1581970.0,0.0),
	 ('bfc13ae1ae52fa193',1,2,1811930.0,'2021-07-17 05:31:57',1717840.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa194',1,1,765514.0,'2021-01-05 05:52:17',1767560.0,0.0),
	 ('bfc13ae1ae52fa195',1,4,1069910.0,'2021-01-21 19:28:55',1933930.0,0.0),
	 ('bfc13ae1ae52fa196',1,4,1837120.0,'2022-07-19 18:33:07',1798510.0,0.0),
	 ('bfc13ae1ae52fa197',1,4,1672430.0,'2023-09-18 16:10:58',1603380.0,0.0),
	 ('bfc13ae1ae52fa198',1,3,831970.0,'2022-04-25 03:37:10',1586500.0,0.0),
	 ('bfc13ae1ae52fa199',1,4,1301890.0,'2022-07-10 09:45:10',1670660.0,0.0),
	 ('bfc13ae1ae52fa19a',1,2,879947.0,'2022-01-03 22:04:21',1741040.0,0.0),
	 ('bfc13ae1ae52fa19b',1,1,544743.0,'2023-08-15 23:38:26',1545100.0,0.0),
	 ('bfc13ae1ae52fa19c',1,1,480172.0,'2021-04-16 23:35:53',1718740.0,0.0),
	 ('bfc13ae1ae52fa19d',1,2,932815.0,'2020-11-29 19:49:48',1596560.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa19e',1,3,1442300.0,'2021-05-08 16:57:40',1566190.0,0.0),
	 ('bfc13ae1ae52fa19f',1,3,1714530.0,'2022-05-15 09:34:34',1532440.0,0.0),
	 ('bfc13ae1ae52fa1a0',1,1,835450.0,'2023-01-27 11:22:12',1842390.0,0.0),
	 ('bfc13ae1ae52fa1a1',1,3,1941890.0,'2021-02-25 02:57:39',1686670.0,0.0),
	 ('bfc13ae1ae52fa1a2',1,3,1232190.0,'2023-09-24 11:05:14',1702040.0,0.0),
	 ('bfc13ae1ae52fa1a3',1,4,222820.0,'2023-03-08 12:32:58',1586780.0,0.0),
	 ('bfc13ae1ae52fa1a4',1,3,1680120.0,'2022-09-20 15:16:16',1505470.0,0.0),
	 ('bfc13ae1ae52fa1a5',1,3,27188.0,'2021-12-05 04:37:24',1911260.0,0.0),
	 ('bfc13ae1ae52fa1a6',1,1,1560030.0,'2021-03-23 01:27:17',1817950.0,0.0),
	 ('bfc13ae1ae52fa1a7',1,1,55786.0,'2023-07-01 07:22:31',1558610.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1a8',1,2,1420450.0,'2022-05-07 08:32:33',1595120.0,0.0),
	 ('bfc13ae1ae52fa1a9',1,1,1274930.0,'2022-10-10 15:18:16',1508520.0,0.0),
	 ('bfc13ae1ae52fa1aa',1,2,625058.0,'2022-11-04 15:17:06',1793150.0,0.0),
	 ('bfc13ae1ae52fa1ab',1,1,1061280.0,'2023-07-23 08:54:31',1730420.0,0.0),
	 ('bfc13ae1ae52fa1ac',1,3,1099650.0,'2022-12-10 04:39:05',1654580.0,0.0),
	 ('bfc13ae1ae52fa1ad',1,2,84947.0,'2022-09-01 01:25:14',1816920.0,0.0),
	 ('bfc13ae1ae52fa1ae',1,3,1854720.0,'2020-12-29 07:04:47',1578510.0,0.0),
	 ('bfc13ae1ae52fa1af',1,1,128795.0,'2022-12-27 23:39:11',1718890.0,0.0),
	 ('bfc13ae1ae52fa1b0',1,4,1825260.0,'2021-07-09 06:37:34',1568450.0,0.0),
	 ('bfc13ae1ae52fa1b1',1,2,1906180.0,'2022-01-14 21:05:15',1756520.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1b2',1,2,1244830.0,'2023-01-26 21:24:23',1698460.0,0.0),
	 ('bfc13ae1ae52fa1b3',1,2,14846.0,'2023-01-22 09:38:37',1583110.0,0.0),
	 ('bfc13ae1ae52fa1b4',1,2,43453.0,'2022-01-25 17:24:11',1839150.0,0.0),
	 ('bfc13ae1ae52fa1b5',1,1,1819330.0,'2023-02-17 23:28:24',1576740.0,0.0),
	 ('bfc13ae1ae52fa1b6',1,2,229859.0,'2021-03-01 16:42:31',1901920.0,0.0),
	 ('bfc13ae1ae52fa1b7',1,3,1871120.0,'2021-10-20 10:57:48',1537480.0,0.0),
	 ('bfc13ae1ae52fa1b8',1,1,1436120.0,'2023-05-21 23:54:14',1748400.0,0.0),
	 ('bfc13ae1ae52fa1b9',1,2,1636340.0,'2023-09-14 14:06:38',1623390.0,0.0),
	 ('bfc13ae1ae52fa1ba',1,1,1166050.0,'2023-08-16 22:28:55',1643610.0,0.0),
	 ('bfc13ae1ae52fa1bb',1,3,1200930.0,'2021-10-29 00:04:46',1777500.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1bc',1,3,1992860.0,'2023-10-23 09:43:57',1898740.0,0.0),
	 ('bfc13ae1ae52fa1bd',1,3,478748.0,'2023-05-21 22:04:35',1730690.0,0.0),
	 ('bfc13ae1ae52fa1be',1,4,254303.0,'2022-09-30 01:07:23',1705340.0,0.0),
	 ('bfc13ae1ae52fa1bf',1,4,237250.0,'2022-10-02 18:53:29',1560780.0,0.0),
	 ('bfc13ae1ae52fa1c0',1,4,948443.0,'2022-07-23 21:14:16',1698300.0,0.0),
	 ('bfc13ae1ae52fa1c1',1,4,1082730.0,'2021-08-27 04:16:41',1682570.0,0.0),
	 ('bfc13ae1ae52fa1c2',1,3,1290350.0,'2021-01-17 00:29:26',1765640.0,0.0),
	 ('bfc13ae1ae52fa1c3',1,2,265024.0,'2020-12-06 17:52:07',1654290.0,0.0),
	 ('bfc13ae1ae52fa1c4',1,4,600969.0,'2023-09-16 03:55:39',1757250.0,0.0),
	 ('bfc13ae1ae52fa1c5',1,3,973435.0,'2022-08-14 15:14:45',1552180.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1c6',1,1,1172980.0,'2021-09-05 09:16:16',1966050.0,0.0),
	 ('bfc13ae1ae52fa1c7',1,2,847165.0,'2022-06-04 08:09:04',1650430.0,0.0),
	 ('bfc13ae1ae52fa1c8',1,1,759918.0,'2022-01-29 13:03:37',1559350.0,0.0),
	 ('bfc13ae1ae52fa1c9',1,4,1410160.0,'2021-07-02 06:00:59',1709160.0,0.0),
	 ('bfc13ae1ae52fa1ca',1,3,1104080.0,'2021-01-27 14:56:48',1560720.0,0.0),
	 ('bfc13ae1ae52fa1cb',1,4,1414820.0,'2020-11-22 16:36:08',1641960.0,0.0),
	 ('bfc13ae1ae52fa1cc',1,2,952062.0,'2023-08-09 11:21:30',1591680.0,0.0),
	 ('bfc13ae1ae52fa1cd',1,1,818627.0,'2021-11-02 05:05:20',1521670.0,0.0),
	 ('bfc13ae1ae52fa1ce',1,4,1471780.0,'2020-12-11 07:45:52',1996160.0,0.0),
	 ('bfc13ae1ae52fa1cf',1,1,1228200.0,'2022-08-24 14:55:01',1823000.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1d0',1,1,1142130.0,'2023-06-18 15:50:59',1812860.0,0.0),
	 ('bfc13ae1ae52fa1d1',1,4,668367.0,'2023-09-16 17:55:45',1992000.0,0.0),
	 ('bfc13ae1ae52fa1d2',1,4,1490740.0,'2022-03-23 13:37:04',1530960.0,0.0),
	 ('bfc13ae1ae52fa1d3',1,1,1316380.0,'2023-09-04 17:56:57',1579920.0,0.0),
	 ('bfc13ae1ae52fa1d4',1,2,980962.0,'2021-10-19 15:27:15',1531390.0,0.0),
	 ('bfc13ae1ae52fa1d5',1,3,123149.0,'2023-08-13 06:11:20',1761560.0,0.0),
	 ('bfc13ae1ae52fa1d6',1,2,1937890.0,'2021-08-30 07:55:39',1886050.0,0.0),
	 ('bfc13ae1ae52fa1d7',1,4,612315.0,'2021-09-27 20:35:22',1734640.0,0.0),
	 ('bfc13ae1ae52fa1d8',1,2,1276460.0,'2023-06-02 14:50:25',1697940.0,0.0),
	 ('bfc13ae1ae52fa1d9',1,4,1861880.0,'2021-07-28 06:44:56',1634530.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1da',1,4,1240350.0,'2020-12-26 05:20:30',1863260.0,0.0),
	 ('bfc13ae1ae52fa1db',1,4,874503.0,'2022-08-24 14:31:00',1509850.0,0.0),
	 ('bfc13ae1ae52fa1dc',1,3,328702.0,'2023-04-02 08:03:28',1582270.0,0.0),
	 ('bfc13ae1ae52fa1dd',1,4,394619.0,'2022-11-05 22:57:28',1579120.0,0.0),
	 ('bfc13ae1ae52fa1de',1,4,1167030.0,'2021-04-11 01:34:13',1638810.0,0.0),
	 ('bfc13ae1ae52fa1df',1,3,1395190.0,'2022-02-01 03:47:23',1806990.0,0.0),
	 ('bfc13ae1ae52fa1e0',1,1,425855.0,'2023-05-24 02:35:42',1871780.0,0.0),
	 ('bfc13ae1ae52fa1e1',1,2,237857.0,'2022-05-25 11:25:19',1781950.0,0.0),
	 ('bfc13ae1ae52fa1e2',1,4,1055540.0,'2021-12-05 18:35:27',1886680.0,0.0),
	 ('bfc13ae1ae52fa1e3',1,1,1375850.0,'2023-04-26 00:02:32',1832040.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1e4',1,1,320325.0,'2022-02-04 12:54:23',1803610.0,0.0),
	 ('bfc13ae1ae52fa1e5',1,4,1888440.0,'2021-10-19 07:31:05',1709880.0,0.0),
	 ('bfc13ae1ae52fa1e6',1,2,1014690.0,'2023-10-09 07:36:23',1552310.0,0.0),
	 ('bfc13ae1ae52fa1e7',1,1,545606.0,'2023-07-11 08:10:49',1722440.0,0.0),
	 ('bfc13ae1ae52fa1e8',1,2,1857960.0,'2023-05-03 00:41:20',1940260.0,0.0),
	 ('bfc13ae1ae52fa1e9',1,1,1330350.0,'2021-08-27 01:14:52',1781360.0,0.0),
	 ('bfc13ae1ae52fa1ea',1,4,328357.0,'2022-09-21 13:23:54',1878930.0,0.0),
	 ('bfc13ae1ae52fa1eb',1,4,1232790.0,'2022-12-20 07:01:53',1958380.0,0.0),
	 ('bfc13ae1ae52fa1ec',1,2,1530530.0,'2023-09-24 20:36:34',1638470.0,0.0),
	 ('bfc13ae1ae52fa1ed',1,1,545380.0,'2022-05-07 03:34:20',1952960.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1ee',1,3,518244.0,'2023-09-18 17:21:36',1973290.0,0.0),
	 ('bfc13ae1ae52fa1ef',1,1,485846.0,'2021-06-15 21:04:59',1939220.0,0.0),
	 ('bfc13ae1ae52fa1f0',1,2,332736.0,'2022-10-28 01:26:41',1683340.0,0.0),
	 ('bfc13ae1ae52fa1f1',1,1,1948320.0,'2022-04-25 03:08:31',1908920.0,0.0),
	 ('bfc13ae1ae52fa1f2',1,1,599618.0,'2022-05-04 22:39:46',1539940.0,0.0),
	 ('bfc13ae1ae52fa1f3',1,3,269909.0,'2020-12-23 23:53:32',1914230.0,0.0),
	 ('bfc13ae1ae52fa1f4',1,4,1853330.0,'2022-04-07 11:03:06',1506970.0,0.0),
	 ('bfc13ae1ae52fa1f5',1,2,1763590.0,'2022-04-07 04:00:57',1876630.0,0.0),
	 ('bfc13ae1ae52fa1f6',1,2,491554.0,'2022-12-20 07:07:43',1719030.0,0.0),
	 ('bfc13ae1ae52fa1f7',1,4,673586.0,'2023-02-16 11:05:20',1983150.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa1f8',1,1,1720800.0,'2022-06-24 02:56:31',1857340.0,0.0),
	 ('bfc13ae1ae52fa1f9',1,4,1945800.0,'2021-01-20 03:34:01',1740860.0,0.0),
	 ('bfc13ae1ae52fa1fa',1,3,1278660.0,'2021-11-18 18:11:47',1559260.0,0.0),
	 ('bfc13ae1ae52fa1fb',1,1,1965210.0,'2021-06-10 01:48:30',1731520.0,0.0),
	 ('bfc13ae1ae52fa1fc',1,3,1611790.0,'2023-01-27 11:36:05',1792060.0,0.0),
	 ('bfc13ae1ae52fa1fd',1,4,599860.0,'2023-10-24 02:42:11',1568750.0,0.0),
	 ('bfc13ae1ae52fa1fe',1,3,1723040.0,'2021-10-25 02:43:25',1817740.0,0.0),
	 ('bfc13ae1ae52fa1ff',1,4,879399.0,'2021-09-03 18:08:33',1551430.0,0.0),
	 ('bfc13ae1ae52fa200',1,2,1312280.0,'2021-12-29 22:44:48',1975530.0,0.0),
	 ('bfc13ae1ae52fa201',1,3,1602700.0,'2021-02-23 03:44:22',1700990.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa202',1,3,450815.0,'2021-03-13 06:26:45',1946920.0,0.0),
	 ('bfc13ae1ae52fa203',1,4,817916.0,'2023-09-27 17:05:17',1766860.0,0.0),
	 ('bfc13ae1ae52fa204',1,3,86880.0,'2022-08-28 07:31:42',1719550.0,0.0),
	 ('bfc13ae1ae52fa205',1,2,761714.0,'2022-07-17 10:07:08',1893640.0,0.0),
	 ('bfc13ae1ae52fa206',1,1,1281540.0,'2023-01-15 03:22:40',1710040.0,0.0),
	 ('bfc13ae1ae52fa207',1,2,1415860.0,'2021-04-21 07:48:24',1881740.0,0.0),
	 ('bfc13ae1ae52fa208',1,3,1369020.0,'2022-11-05 15:12:06',1599430.0,0.0),
	 ('bfc13ae1ae52fa209',1,3,865899.0,'2023-06-15 09:51:14',1870570.0,0.0),
	 ('bfc13ae1ae52fa20a',1,4,1727730.0,'2023-10-06 05:24:35',1642460.0,0.0),
	 ('bfc13ae1ae52fa20b',1,3,556792.0,'2021-10-09 06:05:14',1564740.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa20c',1,4,673257.0,'2021-06-23 03:58:28',1751860.0,0.0),
	 ('bfc13ae1ae52fa20d',1,1,508291.0,'2022-10-19 22:11:30',1808430.0,0.0),
	 ('bfc13ae1ae52fa20e',1,1,1063500.0,'2021-03-23 19:15:16',1961500.0,0.0),
	 ('bfc13ae1ae52fa20f',1,1,726162.0,'2021-11-02 08:46:21',1558580.0,0.0),
	 ('bfc13ae1ae52fa210',1,4,134334.0,'2021-08-03 16:52:26',1627040.0,0.0),
	 ('bfc13ae1ae52fa211',1,4,610647.0,'2021-10-10 07:40:41',1722580.0,0.0),
	 ('bfc13ae1ae52fa212',1,2,1780520.0,'2023-06-28 06:04:33',1999060.0,0.0),
	 ('bfc13ae1ae52fa213',1,1,1850340.0,'2021-02-10 02:09:55',1855870.0,0.0),
	 ('bfc13ae1ae52fa214',1,4,621893.0,'2023-07-04 00:00:05',1743630.0,0.0),
	 ('bfc13ae1ae52fa215',1,2,500222.0,'2020-12-13 10:07:58',1890270.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa216',1,4,1931330.0,'2022-07-09 02:49:24',1980860.0,0.0),
	 ('bfc13ae1ae52fa217',1,1,1542190.0,'2021-01-20 22:58:13',1597470.0,0.0),
	 ('bfc13ae1ae52fa218',1,1,626048.0,'2023-02-28 21:50:54',1991270.0,0.0),
	 ('bfc13ae1ae52fa219',1,3,604641.0,'2021-01-04 23:24:14',1996750.0,0.0),
	 ('bfc13ae1ae52fa21a',1,2,575661.0,'2023-04-19 14:56:02',1799420.0,0.0),
	 ('bfc13ae1ae52fa21b',1,4,889254.0,'2021-11-18 09:50:56',1715850.0,0.0),
	 ('bfc13ae1ae52fa21c',1,2,874943.0,'2022-09-23 13:39:48',1883370.0,0.0),
	 ('bfc13ae1ae52fa21d',1,2,335951.0,'2023-09-11 22:56:36',1660970.0,0.0),
	 ('bfc13ae1ae52fa21e',1,4,1766870.0,'2021-10-03 05:16:11',1912320.0,0.0),
	 ('bfc13ae1ae52fa21f',1,4,1741610.0,'2021-01-06 09:48:10',1792990.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa220',1,4,911324.0,'2022-04-16 02:52:19',1696690.0,0.0),
	 ('bfc13ae1ae52fa221',1,1,864665.0,'2021-07-27 22:18:36',1551960.0,0.0),
	 ('bfc13ae1ae52fa222',1,3,1210610.0,'2022-08-24 15:55:33',1616340.0,0.0),
	 ('bfc13ae1ae52fa223',1,1,1922720.0,'2021-04-19 15:58:00',1779890.0,0.0),
	 ('bfc13ae1ae52fa224',1,4,1250710.0,'2023-08-17 10:31:18',1845540.0,0.0),
	 ('bfc13ae1ae52fa225',1,3,1873900.0,'2023-04-01 15:17:28',1898820.0,0.0),
	 ('bfc13ae1ae52fa226',1,2,434106.0,'2023-09-18 06:16:47',1814700.0,0.0),
	 ('bfc13ae1ae52fa227',1,3,1037150.0,'2022-03-05 10:22:37',1667330.0,0.0),
	 ('bfc13ae1ae52fa228',1,4,695825.0,'2022-10-17 19:45:36',1866880.0,0.0),
	 ('bfc13ae1ae52fa229',1,1,75457.0,'2023-08-20 05:39:37',1898610.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa22a',1,4,410708.0,'2023-01-23 08:48:09',1985170.0,0.0),
	 ('bfc13ae1ae52fa22b',1,2,957792.0,'2021-03-19 03:59:49',1792300.0,0.0),
	 ('bfc13ae1ae52fa22c',1,3,669002.0,'2021-03-26 12:26:53',1515300.0,0.0),
	 ('bfc13ae1ae52fa22d',1,3,1524110.0,'2023-05-06 01:25:46',1824800.0,0.0),
	 ('bfc13ae1ae52fa22e',1,2,876100.0,'2022-07-26 02:35:57',1752700.0,0.0),
	 ('bfc13ae1ae52fa22f',1,3,1545830.0,'2022-01-14 13:13:35',1675640.0,0.0),
	 ('bfc13ae1ae52fa230',1,1,1265780.0,'2023-07-09 22:36:07',1960010.0,0.0),
	 ('bfc13ae1ae52fa231',1,2,1578480.0,'2021-01-23 10:50:02',1687710.0,0.0),
	 ('bfc13ae1ae52fa232',1,2,166731.0,'2023-10-26 13:26:51',1684530.0,0.0),
	 ('bfc13ae1ae52fa233',1,1,288750.0,'2023-10-02 09:39:47',1683500.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa234',1,2,933163.0,'2021-07-07 19:49:57',1607100.0,0.0),
	 ('bfc13ae1ae52fa235',1,3,909063.0,'2023-06-24 06:30:16',1646310.0,0.0),
	 ('bfc13ae1ae52fa236',1,4,688293.0,'2023-10-07 17:05:05',1687500.0,0.0),
	 ('bfc13ae1ae52fa237',1,2,314242.0,'2021-12-31 18:10:22',1689180.0,0.0),
	 ('bfc13ae1ae52fa238',1,2,1733760.0,'2021-02-10 13:20:05',1720550.0,0.0),
	 ('bfc13ae1ae52fa239',1,4,636761.0,'2022-04-10 05:21:31',1993260.0,0.0),
	 ('bfc13ae1ae52fa23a',1,2,229962.0,'2023-07-03 11:27:31',1760250.0,0.0),
	 ('bfc13ae1ae52fa23b',1,2,386556.0,'2023-10-12 06:26:04',1872010.0,0.0),
	 ('bfc13ae1ae52fa23c',1,1,1528010.0,'2021-12-06 05:41:58',1857900.0,0.0),
	 ('bfc13ae1ae52fa23d',1,1,524381.0,'2021-05-16 11:33:21',1852260.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa23e',1,2,1181540.0,'2023-04-10 16:07:11',1925470.0,0.0),
	 ('bfc13ae1ae52fa23f',1,1,1949330.0,'2022-03-21 07:40:56',1543620.0,0.0),
	 ('bfc13ae1ae52fa240',1,1,1377400.0,'2023-02-04 07:01:23',1961560.0,0.0),
	 ('bfc13ae1ae52fa241',1,2,781735.0,'2022-05-03 03:52:13',1955090.0,0.0),
	 ('bfc13ae1ae52fa242',1,1,182533.0,'2021-03-19 13:33:59',1878300.0,0.0),
	 ('bfc13ae1ae52fa243',1,2,1573360.0,'2023-04-01 01:22:51',1688340.0,0.0),
	 ('bfc13ae1ae52fa244',1,4,1216970.0,'2023-05-24 09:32:51',1864120.0,0.0),
	 ('bfc13ae1ae52fa245',1,3,1817920.0,'2022-11-27 22:44:26',1996810.0,0.0),
	 ('bfc13ae1ae52fa246',1,2,31733.0,'2023-02-27 06:31:50',1711880.0,0.0),
	 ('bfc13ae1ae52fa247',1,2,216327.0,'2022-07-27 13:39:42',1886150.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa248',1,2,1650330.0,'2023-07-08 05:20:09',1540160.0,0.0),
	 ('bfc13ae1ae52fa249',1,1,1618890.0,'2023-11-03 20:53:20',1895410.0,0.0),
	 ('bfc13ae1ae52fa24a',1,1,202878.0,'2021-10-30 16:08:08',1743750.0,0.0),
	 ('bfc13ae1ae52fa24b',1,4,553750.0,'2021-11-08 03:43:51',1846100.0,0.0),
	 ('bfc13ae1ae52fa24c',1,3,262938.0,'2021-05-26 14:15:14',1733980.0,0.0),
	 ('bfc13ae1ae52fa24d',1,2,1193110.0,'2022-08-11 08:02:04',1983250.0,0.0),
	 ('bfc13ae1ae52fa24e',1,2,1240670.0,'2021-09-22 18:30:37',1779640.0,0.0),
	 ('bfc13ae1ae52fa24f',1,3,89257.0,'2020-12-01 21:45:45',1714420.0,0.0),
	 ('bfc13ae1ae52fa250',1,3,129812.0,'2022-08-24 09:28:20',1516380.0,0.0),
	 ('bfc13ae1ae52fa251',1,3,492974.0,'2023-07-25 13:43:01',1568690.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa252',1,2,952422.0,'2023-07-16 00:10:14',1630690.0,0.0),
	 ('bfc13ae1ae52fa253',1,1,195166.0,'2022-05-31 19:02:42',1821530.0,0.0),
	 ('bfc13ae1ae52fa254',1,1,862903.0,'2021-03-16 06:35:15',1513180.0,0.0),
	 ('bfc13ae1ae52fa255',1,4,1920500.0,'2023-08-09 04:33:04',1585680.0,0.0),
	 ('bfc13ae1ae52fa256',1,2,1880850.0,'2021-04-15 00:29:52',1884120.0,0.0),
	 ('bfc13ae1ae52fa257',1,1,1735560.0,'2023-09-13 01:37:24',1768860.0,0.0),
	 ('bfc13ae1ae52fa258',1,1,1016550.0,'2022-01-26 13:35:18',1793760.0,0.0),
	 ('bfc13ae1ae52fa259',1,1,842072.0,'2023-07-27 13:10:36',1582300.0,0.0),
	 ('bfc13ae1ae52fa25a',1,4,1596030.0,'2023-04-06 13:59:14',1772220.0,0.0),
	 ('bfc13ae1ae52fa25b',1,3,633445.0,'2021-07-29 12:27:56',1710080.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa25c',1,2,1208150.0,'2021-05-15 16:24:13',1863420.0,0.0),
	 ('bfc13ae1ae52fa25d',1,2,932250.0,'2021-12-28 06:10:19',1987970.0,0.0),
	 ('bfc13ae1ae52fa25e',1,1,1078900.0,'2022-06-17 03:03:09',1804350.0,0.0),
	 ('bfc13ae1ae52fa25f',1,2,1470070.0,'2021-03-09 08:51:04',1778720.0,0.0),
	 ('bfc13ae1ae52fa260',1,3,27796.0,'2020-11-22 02:32:10',1622650.0,0.0),
	 ('bfc13ae1ae52fa261',1,3,1780390.0,'2022-01-26 04:30:29',1856540.0,0.0),
	 ('bfc13ae1ae52fa262',1,3,648593.0,'2021-12-20 11:34:14',1767250.0,0.0),
	 ('bfc13ae1ae52fa263',1,4,152454.0,'2023-02-17 03:01:08',1786620.0,0.0),
	 ('bfc13ae1ae52fa264',1,4,1818600.0,'2023-11-05 03:32:34',1710770.0,0.0),
	 ('bfc13ae1ae52fa265',1,2,1762840.0,'2022-01-01 23:08:47',1715130.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa266',1,2,151214.0,'2023-10-30 18:37:43',1756540.0,0.0),
	 ('bfc13ae1ae52fa267',1,3,19442.0,'2022-08-05 23:33:06',1682120.0,0.0),
	 ('bfc13ae1ae52fa268',1,2,464452.0,'2022-09-15 21:28:38',1905300.0,0.0),
	 ('bfc13ae1ae52fa269',1,2,964919.0,'2023-09-19 12:35:34',1809850.0,0.0),
	 ('bfc13ae1ae52fa26a',1,4,1316940.0,'2023-02-18 03:33:24',1616020.0,0.0),
	 ('bfc13ae1ae52fa26b',1,3,359348.0,'2022-09-26 05:00:06',1945740.0,0.0),
	 ('bfc13ae1ae52fa26c',1,1,743922.0,'2021-05-12 13:34:08',1805330.0,0.0),
	 ('bfc13ae1ae52fa26d',1,2,1381350.0,'2023-02-08 18:05:06',1538930.0,0.0),
	 ('bfc13ae1ae52fa26e',1,4,1773320.0,'2022-09-04 13:31:01',1844880.0,0.0),
	 ('bfc13ae1ae52fa26f',1,4,1029110.0,'2021-03-28 18:04:20',1653820.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa270',1,3,1568020.0,'2021-05-07 20:08:03',1714400.0,0.0),
	 ('bfc13ae1ae52fa271',1,1,192846.0,'2022-04-02 20:55:45',1624210.0,0.0),
	 ('bfc13ae1ae52fa272',1,2,194922.0,'2021-10-25 00:15:38',1504810.0,0.0),
	 ('bfc13ae1ae52fa273',1,1,510695.0,'2023-02-08 00:35:28',1933110.0,0.0),
	 ('bfc13ae1ae52fa274',1,2,230975.0,'2022-08-23 02:54:47',1962810.0,0.0),
	 ('bfc13ae1ae52fa275',1,3,1859180.0,'2022-10-01 01:39:26',1684720.0,0.0),
	 ('bfc13ae1ae52fa276',1,2,1884550.0,'2022-07-15 22:31:39',1911360.0,0.0),
	 ('bfc13ae1ae52fa277',1,2,1438660.0,'2021-06-07 05:03:05',1942080.0,0.0),
	 ('bfc13ae1ae52fa278',1,3,370930.0,'2022-07-14 04:43:32',1605030.0,0.0),
	 ('bfc13ae1ae52fa279',1,2,783938.0,'2023-09-09 06:58:32',1895610.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa27a',1,4,643052.0,'2022-09-11 23:23:32',1667880.0,0.0),
	 ('bfc13ae1ae52fa27b',1,2,1235870.0,'2021-01-25 22:23:11',1866750.0,0.0),
	 ('bfc13ae1ae52fa27c',1,4,1137940.0,'2021-01-05 15:38:27',1843080.0,0.0),
	 ('bfc13ae1ae52fa27d',1,1,1541970.0,'2022-03-15 05:38:40',1575630.0,0.0),
	 ('bfc13ae1ae52fa27e',1,4,130172.0,'2021-10-12 17:05:21',1966590.0,0.0),
	 ('bfc13ae1ae52fa27f',1,4,1311580.0,'2021-02-13 18:57:59',1777260.0,0.0),
	 ('bfc13ae1ae52fa280',1,3,130107.0,'2021-09-04 13:25:22',1735200.0,0.0),
	 ('bfc13ae1ae52fa281',1,3,1139250.0,'2022-05-20 04:35:22',1957820.0,0.0),
	 ('bfc13ae1ae52fa282',1,4,1321430.0,'2023-06-13 14:19:21',1949410.0,0.0),
	 ('bfc13ae1ae52fa283',1,1,318249.0,'2023-05-15 21:26:06',1599490.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa284',1,4,48171.0,'2020-11-17 03:50:05',1885070.0,0.0),
	 ('bfc13ae1ae52fa285',1,4,1973090.0,'2021-07-08 02:32:23',1632710.0,0.0),
	 ('bfc13ae1ae52fa286',1,2,1955300.0,'2021-10-14 03:50:54',1507330.0,0.0),
	 ('bfc13ae1ae52fa287',1,2,42321.0,'2023-01-11 01:39:46',1519450.0,0.0),
	 ('bfc13ae1ae52fa288',1,3,591559.0,'2023-01-21 21:56:45',1969470.0,0.0),
	 ('bfc13ae1ae52fa289',1,2,409493.0,'2022-09-11 01:12:34',1894680.0,0.0),
	 ('bfc13ae1ae52fa28a',1,2,1047230.0,'2021-12-23 08:39:12',1711370.0,0.0),
	 ('bfc13ae1ae52fa28b',1,1,1709070.0,'2023-06-07 03:28:25',1912520.0,0.0),
	 ('bfc13ae1ae52fa28c',1,2,817651.0,'2021-05-31 17:43:02',1814380.0,0.0),
	 ('bfc13ae1ae52fa28d',1,1,1616680.0,'2022-01-10 14:50:53',1654710.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa28e',1,2,1818980.0,'2021-07-10 09:05:56',1782490.0,0.0),
	 ('bfc13ae1ae52fa28f',1,3,1563080.0,'2021-03-29 03:21:25',1936270.0,0.0),
	 ('bfc13ae1ae52fa290',1,3,422264.0,'2022-11-25 14:12:21',1682220.0,0.0),
	 ('bfc13ae1ae52fa291',1,4,946668.0,'2022-07-26 08:48:07',1701400.0,0.0),
	 ('bfc13ae1ae52fa292',1,3,392040.0,'2022-02-09 23:46:09',1676780.0,0.0),
	 ('bfc13ae1ae52fa293',1,3,1525080.0,'2023-11-01 16:17:27',1586310.0,0.0),
	 ('bfc13ae1ae52fa294',1,1,1042270.0,'2022-11-09 14:54:39',1548730.0,0.0),
	 ('bfc13ae1ae52fa295',1,3,834169.0,'2022-10-12 16:49:00',1677690.0,0.0),
	 ('bfc13ae1ae52fa296',1,3,1215700.0,'2021-11-24 10:11:29',1589380.0,0.0),
	 ('bfc13ae1ae52fa297',1,1,257843.0,'2023-09-09 02:55:34',1899440.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa298',1,2,1175950.0,'2022-02-28 14:45:13',1983610.0,0.0),
	 ('bfc13ae1ae52fa299',1,1,1605190.0,'2022-04-09 00:37:30',1508580.0,0.0),
	 ('bfc13ae1ae52fa29a',1,4,1932740.0,'2023-10-10 11:12:55',1639160.0,0.0),
	 ('bfc13ae1ae52fa29b',1,1,1338970.0,'2021-11-21 17:56:49',1940440.0,0.0),
	 ('bfc13ae1ae52fa29c',1,1,1494430.0,'2021-09-19 18:10:00',1703820.0,0.0),
	 ('bfc13ae1ae52fa29d',1,1,1134290.0,'2022-12-31 11:11:40',1767380.0,0.0),
	 ('bfc13ae1ae52fa29e',1,4,771349.0,'2022-11-19 23:08:24',1755820.0,0.0),
	 ('bfc13ae1ae52fa29f',1,1,791635.0,'2022-04-26 02:19:40',1975820.0,0.0),
	 ('bfc13ae1ae52fa2a0',1,2,593797.0,'2023-11-02 03:32:55',1953880.0,0.0),
	 ('bfc13ae1ae52fa2a1',1,4,1115810.0,'2023-06-30 03:45:15',1638360.0,0.0);
INSERT INTO bachhoa.bills (billID,storeID,employeeID,totalAmount,timeCreate,cash,reduced) VALUES
	 ('bfc13ae1ae52fa2a2',1,2,964696.0,'2022-09-25 00:09:04',1707120.0,0.0),
	 ('bfc13ae1ae52fa2a3',1,3,301794.0,'2021-07-10 17:04:18',1713830.0,0.0),
	 ('bfc13ae1ae52fa2a4',1,1,979963.0,'2021-04-13 23:01:39',1996530.0,0.0),
	 ('bfc13ae1ae52fa2a5',1,4,950519.0,'2021-01-19 03:21:23',1942340.0,0.0);