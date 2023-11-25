-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bachhoa
-- ------------------------------------------------------
-- Server version	8.0.33

create database bachhoa;
use bachhoa;

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
-- Table structure for table `payment_history`
--

DROP TABLE IF EXISTS `payment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NOT NULL,
  `adminID` int NULL,
  `time_pay` datetime NULL,
  `time_received` datetime NULL,
  `totalAmount` float NULL,
  `totalReceived` float NULL,
  `paied` int DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `employeeId` (`employeeId`),
  KEY `adminID` (`employeeId`),
  CONSTRAINT `payment_history_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employees` (`employeeID`),
  CONSTRAINT `payment_history_ibfk_2` FOREIGN KEY (`adminId`) REFERENCES `employees` (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `payment_detail`
--

DROP TABLE IF EXISTS `payment_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `paymentId` int NOT NULL,
  `500k` int DEFAULT 0,
  `200k` int DEFAULT 0,
  `100k` int DEFAULT 0,
  `50k` int DEFAULT 0,
  `20k` int DEFAULT 0,
  `10k` int DEFAULT 0,
  `5k` int DEFAULT 0,
  `2k` int DEFAULT 0,
  `1k` int DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `paymentId` (`paymentId`),
  CONSTRAINT `payment_detail_ibfk_1` FOREIGN KEY (`paymentId`) REFERENCES `payment_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (3001,3,'qlch'),(3002,4,'bhoa'),(3003,1,'qlch'),(3004,2,'bhoa');
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
  `quantityGift` int DEFAULT 0,
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
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

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
  `index` int NULL,
  `count` int NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Nguyễn Minh Thư','2003-11-02','BÌnh ĐỊnh',NULL,'2022-03-19',1,'qlch',_binary '',NULL,NULL),(2,'Nguyễn Minh Thư','2003-11-02','BÌnh ĐỊnh',NULL,'2022-03-19',1,'qlch',_binary '',NULL,NULL),(3,'Đồng Võ Nghiệp','2003-01-04','Vietnam','dvn.png','2022-03-19',1,'qlch',_binary '','dongnghiepit@gmail.com','$2a$12$t.mfGk8I1bVHoRfm0RZ0beFZX7fmNc2GPCldK8WBr6KMoZxf7.xk2'),(4,'NST Phúc','2003-04-21','Bình Dương','nstp.png','2023-10-10',1,'bhoa',_binary '','phucnstps20362@fpt.edu.vn','$2a$12$t.mfGk8I1bVHoRfm0RZ0beFZX7fmNc2GPCldK8WBr6KMoZxf7.xk2');
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
INSERT INTO `products` VALUES ('6955807973986',6,2,10,NULL,'Tăm bông',_binary '\0','http://192.168.1.5:8083/bachhoaimg//IMG_20231024_192503.jpg',1,1,10),('8935149556083',1,1,10,NULL,'Đậu phộng tỏi ớt Tài Tài',_binary '\0','http://192.168.1.5:8083/bachhoaimg//IMG_20231024_194947.jpg',1,1,10),('8936050360974',3,15000,5,'2023-12-10','String Dâu',_binary '','stringdau.jpg',5000,1,100),('8938500363689',3,15000,5,'2023-12-10','Coca cola',_binary '','cocacola.jpg',6000,1,90),('8936500366375',4,31000,5,'2023-12-10','Playmore Dưa Hấu hũ (22g)',_binary '','playmoreDuaHau.jpg',12000,1,50),('8935512694520',4,29000,5,'2023-12-10','Playmore Thái Lan hũ (22g)',_binary '','playmoreThaiLan.jpg',11000,1,60),('9314807006679',1,25000,5,'2023-12-10','Hạt sen khô (100gr)',_binary '','hatsenkho.jpg',8000,1,100),('8934807006679',1,150000,5,'2023-12-10','Hạt điều rang muối (500g)',_binary '','hatdieumuoi.jpg',90000,1,50),('8936096670020',1,550000,5,'2023-12-10','Hoa atiso khô (1kg)',_binary '','hoaAtiso.jpg',370000,1,30),('8934588063060',1,300000,5,'2023-12-10','Tinh Bột Nghệ Vàng 500g',_binary '','tinhbotnghe.jpg',200000,1,50),('8934588063176',5,95000,5,'2023-12-10','Nước giặt xả Booster 3,2 lít',_binary '','booster32.jpg',80000,1,60),('8936096670021',5,115000,5,'2023-12-10','Nước giặt Booster 3,7 kg',_binary '','booster37.jpg',95000,1,60),('8938972063060',5,92000,5,'2023-12-10','Viên nén vệ sinh lồng giặt Omo Matic',_binary '','vienVSlongOmo.jpg',80000,1,50),('8935526000012',6,209000,5,'2023-12-10','Nồi Đạt Tường 1.5 lít trắng',_binary '','dattuong15.jpg',129000,1,40),('8935578640025',6,18200,5,'2023-12-10','Găng tay rửa chén silicon',_binary '','gangtaysilicon.jpg',9000,1,200),('8935500602357',2,550000,5,'2023-12-10','Chảo chống dính Five Star 24cm',_binary '','fiveStar24.jpg',305000,1,40),('8934456220031',2,3990000,5,'2023-12-10','Bộ Dao ZWILLING Gourmet - 3 món',_binary '','ZWilling3mon.jpg',2000000,1,30);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1, 'BACHHOA','Quận 12, TP.HCM','5 tỷ');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
use bachhoa
create trigger tg_delete_bill
before delete on bills
for each row
delete from bill_details where billID = old.billID;

-- Dump completed on 2023-11-05 21:36:54
