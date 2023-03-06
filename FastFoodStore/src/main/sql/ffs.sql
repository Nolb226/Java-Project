-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2023 at 06:57 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qltan`
--

-- --------------------------------------------------------

--
-- Table structure for table `billdetail`
--

CREATE TABLE `billdetail` (
  `amount` int(11) NOT NULL,
  `productStatus` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `productCode` varchar(10) NOT NULL,
  `billCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `billdetail2`
--

CREATE TABLE `billdetail2` (
  `productStatus` varchar(100) NOT NULL,
  `amout` int(11) NOT NULL,
  `price` float NOT NULL,
  `billCode` varchar(10) NOT NULL,
  `comboCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billCode` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `totalPrice` float NOT NULL,
  `orderNumber` int(11) NOT NULL,
  `excess` float NOT NULL,
  `cash` float NOT NULL,
  `billStatus` varchar(100) NOT NULL,
  `promoCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `combo`
--

CREATE TABLE `combo` (
  `comboCode` varchar(10) NOT NULL,
  `comboName` varchar(50) NOT NULL,
  `comboPrice` float NOT NULL,
  `numberOfProduct` int(11) NOT NULL,
  `groupCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `combodetail`
--

CREATE TABLE `combodetail` (
  `productCode` varchar(10) NOT NULL,
  `comboCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `duty`
--

CREATE TABLE `duty` (
  `dutyCode` varchar(10) NOT NULL,
  `dutyName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `functions`
--

CREATE TABLE `functions` (
  `functionCode` varchar(10) NOT NULL,
  `functionName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `groupCode` varchar(10) NOT NULL,
  `groupName` varchar(50) NOT NULL,
  `groupIcon` varchar(100) NOT NULL,
  `IN_groupCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `has`
--

CREATE TABLE `has` (
  `dutyCode` varchar(10) NOT NULL,
  `functionCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ingredient`
--

CREATE TABLE `ingredient` (
  `ingredientName` varchar(50) NOT NULL,
  `ingredientCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `productdetail`
--

CREATE TABLE `productdetail` (
  `amount` int(11) NOT NULL,
  `productCode` varchar(10) NOT NULL,
  `ingredientCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productCode` varchar(10) NOT NULL,
  `productName` varchar(50) NOT NULL,
  `productPrice` float NOT NULL,
  `productGenre` varchar(50) NOT NULL,
  `productSize` char(1) NOT NULL,
  `productImage` varchar(100) NOT NULL,
  `groupCode` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotions`
--

CREATE TABLE `promotions` (
  `promoCode` varchar(10) NOT NULL,
  `promoGenre` varchar(100) DEFAULT NULL,
  `productCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receiptdetail`
--

CREATE TABLE `receiptdetail` (
  `amout` float NOT NULL,
  `ingredientCode` varchar(10) NOT NULL,
  `receiptCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receipts`
--

CREATE TABLE `receipts` (
  `date` date NOT NULL,
  `totalPrice` float NOT NULL,
  `receiptCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shifts`
--

CREATE TABLE `shifts` (
  `date` date NOT NULL,
  `shiftsCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `ID` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `dutyCode` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE `work` (
  `checkIn` date NOT NULL,
  `checkOut` date NOT NULL,
  `session` varchar(50) NOT NULL,
  `time` time NOT NULL,
  `ID` varchar(10) NOT NULL,
  `shiftsCode` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`productCode`,`billCode`),
  ADD KEY `billCode` (`billCode`);

--
-- Indexes for table `billdetail2`
--
ALTER TABLE `billdetail2`
  ADD PRIMARY KEY (`billCode`,`comboCode`),
  ADD KEY `comboCode` (`comboCode`);

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billCode`),
  ADD KEY `promoCode` (`promoCode`);

--
-- Indexes for table `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`comboCode`),
  ADD KEY `groupCode` (`groupCode`);

--
-- Indexes for table `combodetail`
--
ALTER TABLE `combodetail`
  ADD PRIMARY KEY (`productCode`,`comboCode`),
  ADD KEY `comboCode` (`comboCode`);

--
-- Indexes for table `duty`
--
ALTER TABLE `duty`
  ADD PRIMARY KEY (`dutyCode`);

--
-- Indexes for table `functions`
--
ALTER TABLE `functions`
  ADD PRIMARY KEY (`functionCode`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`groupCode`),
  ADD KEY `IN_groupCode` (`IN_groupCode`);

--
-- Indexes for table `has`
--
ALTER TABLE `has`
  ADD PRIMARY KEY (`dutyCode`,`functionCode`),
  ADD KEY `functionCode` (`functionCode`);

--
-- Indexes for table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`ingredientCode`);

--
-- Indexes for table `productdetail`
--
ALTER TABLE `productdetail`
  ADD PRIMARY KEY (`productCode`,`ingredientCode`),
  ADD KEY `ingredientCode` (`ingredientCode`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productCode`),
  ADD KEY `groupCode` (`groupCode`);

--
-- Indexes for table `promotions`
--
ALTER TABLE `promotions`
  ADD PRIMARY KEY (`promoCode`),
  ADD KEY `productCode` (`productCode`);

--
-- Indexes for table `receiptdetail`
--
ALTER TABLE `receiptdetail`
  ADD PRIMARY KEY (`ingredientCode`,`receiptCode`),
  ADD KEY `receiptCode` (`receiptCode`);

--
-- Indexes for table `receipts`
--
ALTER TABLE `receipts`
  ADD PRIMARY KEY (`receiptCode`);

--
-- Indexes for table `shifts`
--
ALTER TABLE `shifts`
  ADD PRIMARY KEY (`shiftsCode`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `dutyCode` (`dutyCode`);

--
-- Indexes for table `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`ID`,`shiftsCode`),
  ADD KEY `shiftsCode` (`shiftsCode`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD CONSTRAINT `billdetail_ibfk_1` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`),
  ADD CONSTRAINT `billdetail_ibfk_2` FOREIGN KEY (`billCode`) REFERENCES `bills` (`billCode`);

--
-- Constraints for table `billdetail2`
--
ALTER TABLE `billdetail2`
  ADD CONSTRAINT `billdetail2_ibfk_1` FOREIGN KEY (`billCode`) REFERENCES `bills` (`billCode`),
  ADD CONSTRAINT `billdetail2_ibfk_2` FOREIGN KEY (`comboCode`) REFERENCES `combo` (`comboCode`);

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`promoCode`) REFERENCES `promotions` (`promoCode`);

--
-- Constraints for table `combo`
--
ALTER TABLE `combo`
  ADD CONSTRAINT `combo_ibfk_1` FOREIGN KEY (`groupCode`) REFERENCES `groups` (`groupCode`);

--
-- Constraints for table `combodetail`
--
ALTER TABLE `combodetail`
  ADD CONSTRAINT `combodetail_ibfk_1` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`),
  ADD CONSTRAINT `combodetail_ibfk_2` FOREIGN KEY (`comboCode`) REFERENCES `combo` (`comboCode`);

--
-- Constraints for table `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`IN_groupCode`) REFERENCES `groups` (`groupCode`);

--
-- Constraints for table `has`
--
ALTER TABLE `has`
  ADD CONSTRAINT `has_ibfk_1` FOREIGN KEY (`dutyCode`) REFERENCES `duty` (`dutyCode`),
  ADD CONSTRAINT `has_ibfk_2` FOREIGN KEY (`functionCode`) REFERENCES `functions` (`functionCode`);

--
-- Constraints for table `productdetail`
--
ALTER TABLE `productdetail`
  ADD CONSTRAINT `productdetail_ibfk_1` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`),
  ADD CONSTRAINT `productdetail_ibfk_2` FOREIGN KEY (`ingredientCode`) REFERENCES `ingredient` (`ingredientCode`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`groupCode`) REFERENCES `groups` (`groupCode`);

--
-- Constraints for table `promotions`
--
ALTER TABLE `promotions`
  ADD CONSTRAINT `promotions_ibfk_1` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`);

--
-- Constraints for table `receiptdetail`
--
ALTER TABLE `receiptdetail`
  ADD CONSTRAINT `receiptdetail_ibfk_1` FOREIGN KEY (`ingredientCode`) REFERENCES `ingredient` (`ingredientCode`),
  ADD CONSTRAINT `receiptdetail_ibfk_2` FOREIGN KEY (`receiptCode`) REFERENCES `receipts` (`receiptCode`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`dutyCode`) REFERENCES `duty` (`dutyCode`);

--
-- Constraints for table `work`
--
ALTER TABLE `work`
  ADD CONSTRAINT `work_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `staff` (`ID`),
  ADD CONSTRAINT `work_ibfk_2` FOREIGN KEY (`shiftsCode`) REFERENCES `shifts` (`shiftsCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
