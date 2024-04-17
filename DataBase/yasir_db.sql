-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2024 at 09:59 PM
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
(13, 'ITL مالا سیٹ', 'SILVER');

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
  `RDATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `moramat`
--

INSERT INTO `moramat` (`ID`, `NAME`, `PHONE`, `RDATE`, `DATE`, `STATUS`) VALUES
(1, 'uzair(swabi)', '0333454534', '14/03/2024', '10 / 3 / 2024', 'PENDING');

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
(1, 1, 'ITL لاکٹ', 12, 'ڈانڈي مرمت + صفاي'),
(2, 1, 'ITL پانزیب', 22, 'براے صفائ ');

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
  `PRICE` double NOT NULL,
  `MAZDORI` double NOT NULL,
  `TOTAL_PRICE` double NOT NULL,
  `TOTAL_RECIVED` double NOT NULL,
  `TOTAL_REMAINING` double NOT NULL,
  `DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `sell_gold`
--

INSERT INTO `sell_gold` (`ID`, `SALESMANID`, `NAME`, `PHONE`, `CNIC`, `RATE`, `PGRAM_RATE`, `KARAT`, `TOTAL_WAZAN`, `TOTAL_NAG`, `SAFIWAZAN`, `PRICE`, `MAZDORI`, `TOTAL_PRICE`, `TOTAL_RECIVED`, `TOTAL_REMAINING`, `DATE`) VALUES
(1, 1, 'YHAYA', '033333333', '162020000000', 230000, 18930.041, 24, 23.15, 2, 21.15, 400370, 5000, 405370, 400000, 5370, '11/3/2024');

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
(5, 1, 'پانزیب', 11, 1, 10, 10, 189300),
(6, 1, 'پانزیب', 12.15, 1, 11.15, 11.15, 211070);

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
(9, 1, 'ITL مالا سیٹ', 16, 4374, 360, 24, 0, 16, 5760),
(10, 1, 'ITL انگھوٹی', 5, 4374, 360, 24, 0, 5, 1800);

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
  `WEIGHT` double NOT NULL,
  `PURE_WEIGHT` double NOT NULL,
  `PRICE` double NOT NULL,
  `MZDORI` double NOT NULL,
  `TOTAL_PRICE` double NOT NULL,
  `RECIVED` double NOT NULL,
  `REMANING` double NOT NULL,
  `DATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `sell_silver`
--

INSERT INTO `sell_silver` (`SID`, `SALESMANID`, `NAME`, `PHONE`, `CNIC`, `WEIGHT`, `PURE_WEIGHT`, `PRICE`, `MZDORI`, `TOTAL_PRICE`, `RECIVED`, `REMANING`, `DATE`) VALUES
(1, 1, 'uzair', '03335-------', '16202-------', 21, 21, 7560, 500, 8060, 8060, 0, '10/3/2024');

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
(1, 'uzair', '00000', '00000-0000000-0', 228000, 18765.432, 12.15, 0, 12.15, 24, 0, 12.15, 227999, '11 / 3 / 2024');

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
(1, 22, 3, 2024);

--
-- Indexes for dumped tables
--

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
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

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
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `perchases`
--
ALTER TABLE `perchases`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sell_gold`
--
ALTER TABLE `sell_gold`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sell_gold_details`
--
ALTER TABLE `sell_gold_details`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `sell_invoice_details`
--
ALTER TABLE `sell_invoice_details`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sell_silver`
--
ALTER TABLE `sell_silver`
  MODIFY `SID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `silver`
--
ALTER TABLE `silver`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
