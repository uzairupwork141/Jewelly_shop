-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: yasir_db
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'YASIR DAI'),(4,'SALEEM'),(5,'SHAHID GOLD');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descriptions`
--

DROP TABLE IF EXISTS `descriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descriptions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descriptions`
--

LOCK TABLES `descriptions` WRITE;
/*!40000 ALTER TABLE `descriptions` DISABLE KEYS */;
INSERT INTO `descriptions` VALUES (1,'MOBILE DESIGN'),(2,'DESIGN PENDING'),(3,'RENDOM DESIGN'),(4,'(نمونہ) Design '),(5,'MOBILE DESIGN #');
/*!40000 ALTER TABLE `descriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TYPE` varchar(10) NOT NULL DEFAULT 'SILVER',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (8,'انگھوٹی','SILVER'),(9,'پانزیب','GOLD'),(10,'ITL لاکٹ','SILVER'),(11,'ITL پانزیب','SILVER'),(12,'ITL انگھوٹی','SILVER'),(13,'ITL مالا سیٹ','SILVER'),(14,'انگھوٹی','GOLD'),(15,'لاکٹ','GOLD'),(16,'کانٹے','GOLD');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `APP_PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,'ADMIN','ADMIN','admin','224700','hskemails1@gmail.com','mjleprkiypsdckvw');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moramat`
--

DROP TABLE IF EXISTS `moramat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moramat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TOTAL_WEIGHT` double NOT NULL,
  `RDATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moramat`
--

LOCK TABLES `moramat` WRITE;
/*!40000 ALTER TABLE `moramat` DISABLE KEYS */;
INSERT INTO `moramat` VALUES (1,'test','023456567',17.4,'29/09/2024','31 / 8 / 2024','PENDING'),(2,'KHAN','43652437865723',8,'16/10/2024','14 / 10 / 2024','DONE'),(3,'sdfsdf','443543',2,'25/10/2024','24 / 10 / 2024','PENDING');
/*!40000 ALTER TABLE `moramat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moramat_details`
--

DROP TABLE IF EXISTS `moramat_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moramat_details` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `MID` int(10) NOT NULL,
  `ITEM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `WEIGHT` double NOT NULL,
  `DISCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `moramat_details_ibfk_1` (`MID`),
  CONSTRAINT `moramat_details_ibfk_1` FOREIGN KEY (`MID`) REFERENCES `moramat` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moramat_details`
--

LOCK TABLES `moramat_details` WRITE;
/*!40000 ALTER TABLE `moramat_details` DISABLE KEYS */;
INSERT INTO `moramat_details` VALUES (165,1,'انگھوٹی',2,'ڈانڈی مرمت+صفائی'),(166,1,'پانزیب',3.6,'براے صفائی '),(167,1,'ITL مالا سیٹ',11.8,'براے صفائی '),(170,2,'کانٹے',3,'ڈانڈی کنڈے مرمت '),(171,2,'انگھوٹی',5,'ڈانڈی مرمت+صفائی'),(176,3,'انگھوٹی',2,'صفائی+ مرمت');
/*!40000 ALTER TABLE `moramat_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_advance_gold`
--

DROP TABLE IF EXISTS `order_advance_gold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_advance_gold` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `OID` int(10) NOT NULL,
  `GOLD_WEIGHT` double DEFAULT 0,
  `KARAT` double DEFAULT 0,
  `KAAT` double DEFAULT 0,
  `PASA` double DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_advance_gold`
--

LOCK TABLES `order_advance_gold` WRITE;
/*!40000 ALTER TABLE `order_advance_gold` DISABLE KEYS */;
INSERT INTO `order_advance_gold` VALUES (3,3,3.4,21,0.425,2.975),(5,6,5,20,0.833,4.167),(6,7,5,20.5,0.729,4.271);
/*!40000 ALTER TABLE `order_advance_gold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_advance_money`
--

DROP TABLE IF EXISTS `order_advance_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_advance_money` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OID` int(11) NOT NULL,
  `ADVANCE` int(11) NOT NULL,
  `PAYMENT_DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_advance_money`
--

LOCK TABLES `order_advance_money` WRITE;
/*!40000 ALTER TABLE `order_advance_money` DISABLE KEYS */;
INSERT INTO `order_advance_money` VALUES (167,3,10000,'6/9/2024'),(168,3,200000,'6/9/2024'),(169,3,10000,'7/9/2024'),(170,6,70000,'9/9/2024'),(171,7,500000,'22/3/2025');
/*!40000 ALTER TABLE `order_advance_money` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OID` int(11) NOT NULL,
  `ITEM` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QTY` int(5) NOT NULL,
  `WEIGHT` double NOT NULL,
  `SIZE` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOOK` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `VOLUME` int(10) DEFAULT NULL,
  `PAGE` int(10) DEFAULT NULL,
  `ITEM_NO` int(10) DEFAULT NULL,
  `DESCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (272,3,'لاکٹ',2,4,'','NONE',0,0,0,'MOBILE DESIGN'),(273,3,'پانزیب',1,5,'','YASIR DAI',2,13,342,'NONE'),(274,3,'کانٹے',1,4,'','NONE',0,0,0,'MOBILE DESIGN'),(275,6,'لاکٹ',1,2,'','NONE',0,0,0,'MOBILE DESIGN'),(276,6,'کانٹے',1,5,'','NONE',0,0,0,'(نمونہ) Design '),(277,6,'پانزیب',1,1,'','NONE',0,0,0,'MOBILE DESIGN'),(278,7,'کانٹے',1,10,'','NONE',0,0,0,'MOBILE DESIGN'),(279,7,'لاکٹ',1,7,'','NONE',0,0,0,'MOBILE DESIGN #');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `OID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` text CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `EMAIL` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `RATE` int(20) DEFAULT NULL,
  `T_WEIGHT` double NOT NULL,
  `T_ADVANCE` int(11) NOT NULL,
  `T_ADVANCE_GOLD` double NOT NULL,
  `T_ADVANCE_PASA` double NOT NULL,
  `DATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RETURN_DATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATUS` varchar(20) NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (3,'Yahya','00000000','mu5667733@gmail.com',0,13,220000,3.4,2.975,'6/9/2024','10/10/2024','DONE'),(6,'testing','0000000','mu5667733@gmail.com',0,8,70000,5,4.167,'9/9/2024','20/09/2024','PENDING'),(7,'anas','02623475642','legendcharsi93@gmail.com',0,17,500000,5,4.271,'14/10/2024','05/11/24','PENDING');
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perchases`
--

DROP TABLE IF EXISTS `perchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perchases` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `CNIC` varchar(20) NOT NULL,
  `RATE` int(11) NOT NULL,
  `PGRAM` double NOT NULL,
  `WAZAN` double NOT NULL,
  `CHANDI` double NOT NULL,
  `NAG` double NOT NULL,
  `SAFIWAZAN` double NOT NULL,
  `KARAT` double NOT NULL,
  `KAAT` double NOT NULL,
  `PASA` double NOT NULL,
  `RAKAM` double NOT NULL,
  `DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perchases`
--

LOCK TABLES `perchases` WRITE;
/*!40000 ALTER TABLE `perchases` DISABLE KEYS */;
INSERT INTO `perchases` VALUES (1,'uzair','033333','33333-3',231000,19012.346,12.11,0,0,12.11,24,0,12.11,230239,'15 / 3 / 2024'),(2,'Khan','000','00000-0000000-0',122222,10059.424,12.15,0,0,12.15,24,0,12.15,122221,'15 / 3 / 2024'),(3,'uzair','3335323758','00000-0000000-0',255000,20987.654,3,0,0,3,19.2,0.5999999,2.4,50370,'21 / 4 / 2024'),(4,'','','',22222,1828.9712,12.15,0,0,12.15,24,0,12.15,22222,'3 / 9 / 2024');
/*!40000 ALTER TABLE `perchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scale_config`
--

DROP TABLE IF EXISTS `scale_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COM` varchar(5) NOT NULL,
  `BAUD_RATE` int(6) NOT NULL DEFAULT 9600,
  `BITS` int(2) NOT NULL DEFAULT 8,
  `STOP_BITS` int(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scale_config`
--

LOCK TABLES `scale_config` WRITE;
/*!40000 ALTER TABLE `scale_config` DISABLE KEYS */;
INSERT INTO `scale_config` VALUES (1,'COM4',9600,8,1);
/*!40000 ALTER TABLE `scale_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_gold`
--

DROP TABLE IF EXISTS `sell_gold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sell_gold` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `SALESMANID` int(11) NOT NULL,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CNIC` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RATE` double NOT NULL,
  `PGRAM_RATE` double NOT NULL,
  `KARAT` double NOT NULL,
  `TOTAL_WAZAN` double NOT NULL,
  `TOTAL_NAG` double NOT NULL,
  `SAFIWAZAN` double NOT NULL,
  `GOLD_PRICE` double NOT NULL,
  `MAZDORI` double NOT NULL,
  `TOTAL_PRICE` double NOT NULL,
  `TOTAL_RECIVED` double NOT NULL,
  `RGHAYT` int(10) NOT NULL,
  `TOTAL_REMAINING` double NOT NULL,
  `DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_gold`
--

LOCK TABLES `sell_gold` WRITE;
/*!40000 ALTER TABLE `sell_gold` DISABLE KEYS */;
INSERT INTO `sell_gold` VALUES (2,1,'khan','033323432','234234234',275000,22633.745,24,31.25,2,29.25,662036,4000,666036,665000,1036,0,'18/12/2024'),(3,1,'anas','056653636','9476463',255000,20987.654,24,9,1,8,167901,5000,172901,170000,2901,0,'26/2/2025'),(4,1,'uzair','0000000000','00000000000',275000,22633.745,24,3.54,0,3.54,80123,2000,82123,81000,1123,0,'2/9/2024');
/*!40000 ALTER TABLE `sell_gold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_gold_details`
--

DROP TABLE IF EXISTS `sell_gold_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sell_gold_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SID` int(11) NOT NULL,
  `ITEM` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `WEIGHT` double NOT NULL,
  `NAG` double NOT NULL,
  `SAFI_WAZAN` double NOT NULL,
  `PASA` double NOT NULL,
  `PRICE` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `SID` (`SID`),
  CONSTRAINT `sell_gold_details_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `sell_gold` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_gold_details`
--

LOCK TABLES `sell_gold_details` WRITE;
/*!40000 ALTER TABLE `sell_gold_details` DISABLE KEYS */;
INSERT INTO `sell_gold_details` VALUES (44,4,'انگھوٹی',3.54,0,3.54,3.54,80123),(76,2,'پانزیب',12.15,0,12.15,12.15,275000),(77,2,'پانزیب',11,1,10,10,226337),(78,2,'کانٹے',2.4,0,2.4,2.4,54321),(79,2,'لاکٹ',2.7,0,2.7,2.7,61111),(80,2,'انگھوٹی',3,1,2,2,45267),(89,3,'انگھوٹی',4,1,3,3,62963),(90,3,'کانٹے',5,0,5,5,104938);
/*!40000 ALTER TABLE `sell_gold_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_invoice_details`
--

DROP TABLE IF EXISTS `sell_invoice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sell_invoice_details` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `SID` int(10) NOT NULL,
  `ITEM` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `WEIGHT` double NOT NULL,
  `RATE` double NOT NULL,
  `PGRAM_RATE` double NOT NULL,
  `KARAT` double NOT NULL,
  `KAAT` double NOT NULL,
  `PASA` double NOT NULL,
  `PRICE` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `SID` (`SID`),
  CONSTRAINT `sell_invoice_details_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `sell_silver` (`SID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_invoice_details`
--

LOCK TABLES `sell_invoice_details` WRITE;
/*!40000 ALTER TABLE `sell_invoice_details` DISABLE KEYS */;
INSERT INTO `sell_invoice_details` VALUES (270,1,'ITL لاکٹ',5,4374,360,24,0,5,1800),(271,1,'ITL انگھوٹی',12.15,4374,360,22,1.013,11.138,4009),(274,4,'ITL پانزیب',14.3,5468,450,24,0,14.3,6435),(275,4,'ITL انگھوٹی',7,5468,450.041,24,0,7,3150),(285,3,'پانزیب',12.15,5468,450,24,0,12.15,5468),(286,3,'ITL لاکٹ',7,4131,340,24,0,7,2380),(287,3,'انگھوٹی',12,3000,246.914,24,0,12,2963),(291,6,'پانزیب',12,3402,280,24,0,12,3360),(292,5,'ITL انگھوٹی',6,6050,497.942,22,0.5,5.5,2739),(293,5,'انگھوٹی',5,3100,255.144,24,0,5,1276),(298,7,'انگھوٹی',191.78,4253,350,23,7.991,183.789,64326),(299,7,'ITL مالا سیٹ',191.8,3997,329,22,15.983,175.817,57844);
/*!40000 ALTER TABLE `sell_invoice_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_silver`
--

DROP TABLE IF EXISTS `sell_silver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sell_silver` (
  `SID` int(10) NOT NULL AUTO_INCREMENT,
  `SALESMANID` int(10) NOT NULL,
  `NAME` text NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `CNIC` varchar(15) NOT NULL,
  `TOTAL_WEIGHT` double NOT NULL,
  `PURE_WEIGHT` double NOT NULL,
  `SUB_PRICE` double NOT NULL,
  `MZDORI` double NOT NULL,
  `TOTAL_PRICE` double NOT NULL,
  `RECIVED` double NOT NULL,
  `REMANING` double NOT NULL,
  `DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_silver`
--

LOCK TABLES `sell_silver` WRITE;
/*!40000 ALTER TABLE `sell_silver` DISABLE KEYS */;
INSERT INTO `sell_silver` VALUES (1,1,'test','03335000000','162024444444',17.15,16.138,5809,500,6309,6309,0,'10/3/2024'),(3,1,'test','0347345343','1620208629569',31.15,31.15,10811,500,11311,11311,0,'15/3/2024'),(4,1,'test','03333434345','1620222222222',21.3,21.3,9585,1000,10585,10135,450,'20/3/2024'),(5,1,'4poitrkej','45345634563','36633466',11,10.5,4015,1200,5215,4000,1215,'14/10/2024'),(6,1,'KHAN','00000000','0000000000',12,12,3360,150,3510,3510,0,'7/3/2025'),(7,1,'khan','00000','00000',383.58,359.606,122170,2000,124170,66326,57844,'30/3/2025');
/*!40000 ALTER TABLE `sell_silver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_details`
--

DROP TABLE IF EXISTS `shop_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_details` (
  `SHOP_ID` int(11) NOT NULL,
  `SHOP_NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SHOP_PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SHOP_ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SHOP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_details`
--

LOCK TABLES `shop_details` WRITE;
/*!40000 ALTER TABLE `shop_details` DISABLE KEYS */;
INSERT INTO `shop_details` VALUES (1,'Haji Shamsheer Khan ( HSK )','03335323758','( مدار خان پلازہ ) لنک روڈ صوابی');
/*!40000 ALTER TABLE `shop_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silver`
--

DROP TABLE IF EXISTS `silver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silver` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `CNIC` varchar(20) NOT NULL,
  `RATE` int(11) NOT NULL,
  `PGRAM` double NOT NULL,
  `WAZAN` double NOT NULL,
  `NAG` double NOT NULL,
  `SAFIWAZAN` double NOT NULL,
  `KARAT` double NOT NULL,
  `KAAT` double NOT NULL,
  `PASA` double NOT NULL,
  `RAKAM` double NOT NULL,
  `DATE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silver`
--

LOCK TABLES `silver` WRITE;
/*!40000 ALTER TABLE `silver` DISABLE KEYS */;
INSERT INTO `silver` VALUES (1,'uzair','00000','00000-0000000-0',228000,18765.432,12.15,0,12.15,24,0,12.15,227999,'11 / 3 / 2024'),(2,'muhammad uzair','03335323758','16202-0862956-9',1000,82.30453,12.15,0,12.15,24,0,12.15,1000,'18 / 4 / 2024'),(3,'Muhammad khan','033333333','00000-0000000-0',20000,1646.0906,12.15,0,12.15,22,1.0124998,11.1375,18333,'29 / 4 / 2024'),(4,'khan','03333','23423-4324444-4',271000,22304.527,12.15,0,12.15,19,2.53125,9.61875,214541,'21 / 5 / 2024');
/*!40000 ALTER TABLE `silver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `software_status`
--

DROP TABLE IF EXISTS `software_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `software_status` (
  `ID` int(10) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'deactivated',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `software_status`
--

LOCK TABLES `software_status` WRITE;
/*!40000 ALTER TABLE `software_status` DISABLE KEYS */;
INSERT INTO `software_status` VALUES (1,'activated');
/*!40000 ALTER TABLE `software_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trial`
--

DROP TABLE IF EXISTS `trial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trial` (
  `ID` int(10) NOT NULL,
  `day` int(10) NOT NULL,
  `month` int(10) NOT NULL,
  `year` int(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trial`
--

LOCK TABLES `trial` WRITE;
/*!40000 ALTER TABLE `trial` DISABLE KEYS */;
INSERT INTO `trial` VALUES (1,12,10,2024);
/*!40000 ALTER TABLE `trial` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-30 12:03:53
