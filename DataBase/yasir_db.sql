-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 08, 2024 at 11:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yasir_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `ID` int(11) NOT NULL,
  `NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ID`, `NAME`) VALUES
(1, 'YASIR DAI'),
(3, 'SHAHID DAI');

-- --------------------------------------------------------

--
-- Table structure for table `descriptions`
--

CREATE TABLE `descriptions` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `descriptions`
--

INSERT INTO `descriptions` (`ID`, `DESCRIPTION`) VALUES
(1, 'MOBILE DESIGN'),
(2, 'DESIGN PENDING'),
(3, 'RENDOM DESIGN'),
(4, '(نمونہ) Design ');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `ID` int(10) NOT NULL,
  `ITEM` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TYPE` varchar(10) NOT NULL DEFAULT 'SILVER'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`ID`, `ITEM`, `TYPE`) VALUES
(8, 'انگھوٹی', 'SILVER'),
(9, 'پانزیب', 'GOLD'),
(10, 'ITL لاکٹ', 'SILVER'),
(11, 'ITL پانزیب', 'SILVER'),
(12, 'ITL انگھوٹی', 'SILVER'),
(13, 'ITL مالا سیٹ', 'SILVER'),
(14, 'انگھوٹی', 'GOLD'),
(15, 'لاکٹ', 'GOLD'),
(16, 'کانٹے', 'GOLD');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `CODE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`ID`, `NAME`, `USERNAME`, `PASSWORD`, `CODE`) VALUES
(1, 'ADMIN', 'ADMIN', 'admin', '224700');

-- --------------------------------------------------------

--
-- Table structure for table `moramat`
--

CREATE TABLE `moramat` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TOTAL_WEIGHT` double NOT NULL,
  `RDATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `moramat`
--

INSERT INTO `moramat` (`ID`, `NAME`, `PHONE`, `TOTAL_WEIGHT`, `RDATE`, `DATE`, `STATUS`) VALUES
(1, 'test', '023456567', 17.4, '29/09/2024', '31 / 8 / 2024', 'PENDING');

-- --------------------------------------------------------

--
-- Table structure for table `moramat_details`
--

CREATE TABLE `moramat_details` (
  `ID` int(10) NOT NULL,
  `MID` int(10) NOT NULL,
  `ITEM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `WEIGHT` double NOT NULL,
  `DISCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `moramat_details`
--

INSERT INTO `moramat_details` (`ID`, `MID`, `ITEM`, `WEIGHT`, `DISCRIPTION`) VALUES
(140, 1, 'انگھوٹی', 2, 'ڈانڈی مرمت+صفائی'),
(141, 1, 'پانزیب', 3.6, 'براے صفائی '),
(142, 1, 'ITL مالا سیٹ', 11.8, 'براے صفائی ');

-- --------------------------------------------------------

--
-- Table structure for table `order_advance_gold`
--

CREATE TABLE `order_advance_gold` (
  `ID` int(10) NOT NULL,
  `OID` int(10) NOT NULL,
  `GOLD_WEIGHT` double DEFAULT 0,
  `KARAT` double DEFAULT 0,
  `KAAT` double DEFAULT 0,
  `PASA` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `order_advance_gold`
--

INSERT INTO `order_advance_gold` (`ID`, `OID`, `GOLD_WEIGHT`, `KARAT`, `KAAT`, `PASA`) VALUES
(3, 3, 3.4, 21, 0.425, 2.975),
(5, 6, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_advance_money`
--

CREATE TABLE `order_advance_money` (
  `ID` int(11) NOT NULL,
  `OID` int(11) NOT NULL,
  `ADVANCE` int(11) NOT NULL,
  `PAYMENT_DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `order_advance_money`
--

INSERT INTO `order_advance_money` (`ID`, `OID`, `ADVANCE`, `PAYMENT_DATE`) VALUES
(151, 3, 10000, '6/9/2024'),
(152, 3, 200000, '6/9/2024'),
(153, 3, 10000, '7/9/2024'),
(157, 6, 70000, '9/9/2024');

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `ID` int(11) NOT NULL,
  `OID` int(11) NOT NULL,
  `ITEM` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QTY` int(5) NOT NULL,
  `WEIGHT` double NOT NULL,
  `SIZE` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOOK` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `VOLUME` int(10) DEFAULT NULL,
  `PAGE` int(10) DEFAULT NULL,
  `ITEM_NO` int(10) DEFAULT NULL,
  `DESCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`ID`, `OID`, `ITEM`, `QTY`, `WEIGHT`, `SIZE`, `BOOK`, `VOLUME`, `PAGE`, `ITEM_NO`, `DESCRIPTION`) VALUES
(235, 3, 'لاکٹ', 2, 4, '', 'NONE', 0, 0, 0, 'MOBILE DESIGN'),
(236, 3, 'پانزیب', 1, 5, '', 'YASIR DAI', 2, 13, 342, 'NONE'),
(237, 3, 'کانٹے', 1, 4, '', 'NONE', 0, 0, 0, 'MOBILE DESIGN'),
(252, 6, 'لاکٹ', 1, 2, '', 'NONE', 0, 0, 0, 'MOBILE DESIGN'),
(253, 6, 'کانٹے', 1, 5, '', 'NONE', 0, 0, 0, '(نمونہ) Design ');

-- --------------------------------------------------------

--
-- Table structure for table `order_table`
--

CREATE TABLE `order_table` (
  `OID` int(11) NOT NULL,
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
  `STATUS` varchar(20) NOT NULL DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `order_table`
--

INSERT INTO `order_table` (`OID`, `NAME`, `PHONE`, `EMAIL`, `RATE`, `T_WEIGHT`, `T_ADVANCE`, `T_ADVANCE_GOLD`, `T_ADVANCE_PASA`, `DATE`, `RETURN_DATE`, `STATUS`) VALUES
(3, 'Yahya', '00000000', 'mu5667733@gmail.com', 275000, 13, 220000, 3.4, 2.975, '6/9/2024', '10/10/2024', 'DONE'),
(6, 'testing', '0000000', 'mu5667733@gmail.com', 0, 7, 70000, 0, 0, '9/9/2024', '20/09/2024', 'PENDING');

-- --------------------------------------------------------

--
-- Table structure for table `perchases`
--

CREATE TABLE `perchases` (
  `ID` int(11) NOT NULL,
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
  `DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `perchases`
--

INSERT INTO `perchases` (`ID`, `NAME`, `PHONE`, `CNIC`, `RATE`, `PGRAM`, `WAZAN`, `CHANDI`, `NAG`, `SAFIWAZAN`, `KARAT`, `KAAT`, `PASA`, `RAKAM`, `DATE`) VALUES
(1, 'uzair', '033333', '33333-3', 231000, 19012.346, 12.11, 0, 0, 12.11, 24, 0, 12.11, 230239, '15 / 3 / 2024'),
(2, 'Khan', '000', '00000-0000000-0', 122222, 10059.424, 12.15, 0, 0, 12.15, 24, 0, 12.15, 122221, '15 / 3 / 2024'),
(3, 'uzair', '3335323758', '00000-0000000-0', 255000, 20987.654, 3, 0, 0, 3, 19.2, 0.5999999, 2.4, 50370, '21 / 4 / 2024'),
(4, '', '', '', 22222, 1828.9712, 12.15, 0, 0, 12.15, 24, 0, 12.15, 22222, '3 / 9 / 2024');

-- --------------------------------------------------------

--
-- Table structure for table `sell_gold`
--

CREATE TABLE `sell_gold` (
  `ID` int(10) NOT NULL,
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
  `DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `sell_gold`
--

INSERT INTO `sell_gold` (`ID`, `SALESMANID`, `NAME`, `PHONE`, `CNIC`, `RATE`, `PGRAM_RATE`, `KARAT`, `TOTAL_WAZAN`, `TOTAL_NAG`, `SAFIWAZAN`, `GOLD_PRICE`, `MAZDORI`, `TOTAL_PRICE`, `TOTAL_RECIVED`, `RGHAYT`, `TOTAL_REMAINING`, `DATE`) VALUES
(2, 1, 'khan', '033323432', '234234234', 275000, 22633.745, 24, 31.25, 2, 29.25, 662036, 4000, 666036, 665000, 1036, 0, '7/9/2024'),
(3, 1, 'anas', '056653636', '9476463', 255000, 20987.654, 24, 9, 1, 8, 167901, 5000, 172901, 170000, 2901, 0, '7/9/2024'),
(4, 1, 'uzair', '0000000000', '00000000000', 275000, 22633.745, 24, 3.54, 0, 3.54, 80123, 2000, 82123, 81000, 1123, 0, '2/9/2024');

-- --------------------------------------------------------

--
-- Table structure for table `sell_gold_details`
--

CREATE TABLE `sell_gold_details` (
  `ID` int(11) NOT NULL,
  `SID` int(11) NOT NULL,
  `ITEM` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `WEIGHT` double NOT NULL,
  `NAG` double NOT NULL,
  `SAFI_WAZAN` double NOT NULL,
  `PASA` double NOT NULL,
  `PRICE` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `sell_gold_details`
--

INSERT INTO `sell_gold_details` (`ID`, `SID`, `ITEM`, `WEIGHT`, `NAG`, `SAFI_WAZAN`, `PASA`, `PRICE`) VALUES
(44, 4, 'انگھوٹی', 3.54, 0, 3.54, 3.54, 80123),
(59, 3, 'انگھوٹی', 4, 1, 3, 3, 62963),
(60, 3, 'کانٹے', 5, 0, 5, 5, 104938),
(61, 2, 'پانزیب', 12.15, 0, 12.15, 12.15, 275000),
(62, 2, 'پانزیب', 11, 1, 10, 10, 226337),
(63, 2, 'کانٹے', 2.4, 0, 2.4, 2.4, 54321),
(64, 2, 'لاکٹ', 2.7, 0, 2.7, 2.7, 61111),
(65, 2, 'انگھوٹی', 3, 1, 2, 2, 45267);

-- --------------------------------------------------------

--
-- Table structure for table `sell_invoice_details`
--

CREATE TABLE `sell_invoice_details` (
  `ID` int(10) NOT NULL,
  `SID` int(10) NOT NULL,
  `ITEM` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `WEIGHT` double NOT NULL,
  `RATE` double NOT NULL,
  `PGRAM_RATE` double NOT NULL,
  `KARAT` double NOT NULL,
  `KAAT` double NOT NULL,
  `PASA` double NOT NULL,
  `PRICE` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `sell_invoice_details`
--

INSERT INTO `sell_invoice_details` (`ID`, `SID`, `ITEM`, `WEIGHT`, `RATE`, `PGRAM_RATE`, `KARAT`, `KAAT`, `PASA`, `PRICE`) VALUES
(252, 4, 'ITL پانزیب', 14.3, 5468, 450, 24, 0, 14.3, 6435),
(253, 4, 'ITL انگھوٹی', 7, 5468, 450.041, 24, 0, 7, 3150),
(254, 1, 'ITL لاکٹ', 5, 4374, 360, 24, 0, 5, 1800),
(255, 1, 'ITL انگھوٹی', 12.15, 4374, 360, 22, 1.013, 11.138, 4009),
(262, 3, 'پانزیب', 12.15, 5468, 450, 24, 0, 12.15, 5468),
(263, 3, 'ITL لاکٹ', 7, 4131, 340, 24, 0, 7, 2380);

-- --------------------------------------------------------

--
-- Table structure for table `sell_silver`
--

CREATE TABLE `sell_silver` (
  `SID` int(10) NOT NULL,
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
  `DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `sell_silver`
--

INSERT INTO `sell_silver` (`SID`, `SALESMANID`, `NAME`, `PHONE`, `CNIC`, `TOTAL_WEIGHT`, `PURE_WEIGHT`, `SUB_PRICE`, `MZDORI`, `TOTAL_PRICE`, `RECIVED`, `REMANING`, `DATE`) VALUES
(1, 1, 'test', '03335000000', '162024444444', 17.15, 16.138, 5809, 500, 6309, 6309, 0, '10/3/2024'),
(3, 1, 'test', '0347345343', '1620208629569', 19.15, 19.15, 7848, 500, 8348, 5000, 3348, '15/3/2024'),
(4, 1, 'test', '03333434345', '1620222222222', 21.3, 21.3, 9585, 1000, 10585, 10135, 450, '20/3/2024');

-- --------------------------------------------------------

--
-- Table structure for table `shop_details`
--

CREATE TABLE `shop_details` (
  `SHOP_ID` int(11) NOT NULL,
  `SHOP_NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SHOP_PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SHOP_ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `shop_details`
--

INSERT INTO `shop_details` (`SHOP_ID`, `SHOP_NAME`, `SHOP_PHONE`, `SHOP_ADDRESS`) VALUES
(1, 'Haji Shamsheer Khan ( HSK )', '03335323758', '( مدار خان پلازہ ) لنک روڈ صوابی');

-- --------------------------------------------------------

--
-- Table structure for table `silver`
--

CREATE TABLE `silver` (
  `ID` int(11) NOT NULL,
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
  `DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `silver`
--

INSERT INTO `silver` (`ID`, `NAME`, `PHONE`, `CNIC`, `RATE`, `PGRAM`, `WAZAN`, `NAG`, `SAFIWAZAN`, `KARAT`, `KAAT`, `PASA`, `RAKAM`, `DATE`) VALUES
(1, 'uzair', '00000', '00000-0000000-0', 228000, 18765.432, 12.15, 0, 12.15, 24, 0, 12.15, 227999, '11 / 3 / 2024'),
(2, 'muhammad uzair', '03335323758', '16202-0862956-9', 1000, 82.30453, 12.15, 0, 12.15, 24, 0, 12.15, 1000, '18 / 4 / 2024'),
(3, 'Muhammad khan', '033333333', '00000-0000000-0', 20000, 1646.0906, 12.15, 0, 12.15, 22, 1.0124998, 11.1375, 18333, '29 / 4 / 2024'),
(4, 'khan', '03333', '23423-4324444-4', 271000, 22304.527, 12.15, 0, 12.15, 19, 2.53125, 9.61875, 214541, '21 / 5 / 2024'),
(5, 'sundas', '03219891084', '00000-0000000-0', 271000, 22304.527, 12.15, 0, 12.15, 19, 2.53125, 9.61875, 214541, '23 / 8 / 2024');

-- --------------------------------------------------------

--
-- Table structure for table `software_status`
--

CREATE TABLE `software_status` (
  `ID` int(10) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'deactivated'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `software_status`
--

INSERT INTO `software_status` (`ID`, `status`) VALUES
(1, 'deactivated');

-- --------------------------------------------------------

--
-- Table structure for table `trial`
--

CREATE TABLE `trial` (
  `ID` int(10) NOT NULL,
  `day` int(10) NOT NULL,
  `month` int(10) NOT NULL,
  `year` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `trial`
--

INSERT INTO `trial` (`ID`, `day`, `month`, `year`) VALUES
(1, 11, 9, 2024);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NAME` (`NAME`) USING HASH;

--
-- Indexes for table `descriptions`
--
ALTER TABLE `descriptions`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`);

--
-- Indexes for table `moramat`
--
ALTER TABLE `moramat`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `moramat_details`
--
ALTER TABLE `moramat_details`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `moramat_details_ibfk_1` (`MID`);

--
-- Indexes for table `order_advance_gold`
--
ALTER TABLE `order_advance_gold`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `order_advance_money`
--
ALTER TABLE `order_advance_money`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `order_table`
--
ALTER TABLE `order_table`
  ADD PRIMARY KEY (`OID`);

--
-- Indexes for table `perchases`
--
ALTER TABLE `perchases`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sell_gold`
--
ALTER TABLE `sell_gold`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sell_gold_details`
--
ALTER TABLE `sell_gold_details`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SID` (`SID`);

--
-- Indexes for table `sell_invoice_details`
--
ALTER TABLE `sell_invoice_details`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SID` (`SID`);

--
-- Indexes for table `sell_silver`
--
ALTER TABLE `sell_silver`
  ADD PRIMARY KEY (`SID`);

--
-- Indexes for table `shop_details`
--
ALTER TABLE `shop_details`
  ADD PRIMARY KEY (`SHOP_ID`);

--
-- Indexes for table `silver`
--
ALTER TABLE `silver`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `software_status`
--
ALTER TABLE `software_status`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `trial`
--
ALTER TABLE `trial`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `descriptions`
--
ALTER TABLE `descriptions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `moramat`
--
ALTER TABLE `moramat`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `moramat_details`
--
ALTER TABLE `moramat_details`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT for table `order_advance_gold`
--
ALTER TABLE `order_advance_gold`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `order_advance_money`
--
ALTER TABLE `order_advance_money`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=158;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=254;

--
-- AUTO_INCREMENT for table `order_table`
--
ALTER TABLE `order_table`
  MODIFY `OID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `perchases`
--
ALTER TABLE `perchases`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sell_gold`
--
ALTER TABLE `sell_gold`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sell_gold_details`
--
ALTER TABLE `sell_gold_details`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `sell_invoice_details`
--
ALTER TABLE `sell_invoice_details`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=264;

--
-- AUTO_INCREMENT for table `sell_silver`
--
ALTER TABLE `sell_silver`
  MODIFY `SID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `silver`
--
ALTER TABLE `silver`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `moramat_details`
--
ALTER TABLE `moramat_details`
  ADD CONSTRAINT `moramat_details_ibfk_1` FOREIGN KEY (`MID`) REFERENCES `moramat` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sell_gold_details`
--
ALTER TABLE `sell_gold_details`
  ADD CONSTRAINT `sell_gold_details_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `sell_gold` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sell_invoice_details`
--
ALTER TABLE `sell_invoice_details`
  ADD CONSTRAINT `sell_invoice_details_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `sell_silver` (`SID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
