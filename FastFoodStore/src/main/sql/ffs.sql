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
  `billCode` varchar(10) NOT NULL,
  `productCode` varchar(10) NOT NULL,
  `productNote` varchar(100),
  `amountProduct` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `billdetail2`
--

CREATE TABLE `billdetail2` (
  `billCode` varchar(10) NOT NULL,
  `comboCode` varchar(10) NOT NULL,
  `productCode` varchar(10) NOT NULL,
  `productNote` varchar(100), 
  `amoutCombo` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billCode` varchar(10) NOT NULL,
  `orderNumber` int(11) NOT NULL,
  `date` date NOT NULL,
  `totalNumber` int(11) NOT NULL,
  `totalPrice` float NOT NULL,
  `cash` float NOT NULL,
  `excess` float NOT NULL,
  `billStatus` varchar(100) NOT NULL,
  `promoCode` varchar(10)
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
  `IN_groupCode` varchar(10)
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
  `ingredientCode` varchar(10) NOT NULL,
  `ingredientName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `productdetail`
--

CREATE TABLE `productdetail` (
  `productCode` varchar(10) NOT NULL,
  `ingredientCode` varchar(10) NOT NULL,
  `recipe` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productCode` varchar(10) NOT NULL,
  `productName` varchar(50) NOT NULL,
  `productPrice` float NOT NULL,
  `productGenre` int(11) NOT NULL,
  `productSize` char(1),
  `productImage` varchar(100) NOT NULL,
  `groupCode` varchar(10),
  `inMenu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotions`
--

CREATE TABLE `promotions` (
  `promoCode` varchar(10) NOT NULL,
  `promoGenre` varchar(100) DEFAULT NULL,
  `productCode` varchar(10) NOT NULL,
  `dueDate` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receiptdetail`
--

CREATE TABLE `receiptdetail` (
  `receiptCode` varchar(10) NOT NULL,
  `ingredientCode` varchar(10) NOT NULL,
  `amoutInReceipt` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receipts`
--

CREATE TABLE `receipts` (
  `receiptCode` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `totalPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shifts`
--

CREATE TABLE `shifts` (
  `shiftsCode` varchar(10) NOT NULL,
  `date` date NOT NULL
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
  `ID` varchar(10) NOT NULL,
  `shiftsCode` varchar(10) NOT NULL,
  `checkIn` date NOT NULL,
  `checkOut` date NOT NULL,
  `session` varchar(50) NOT NULL,
  `time` time NOT NULL
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


--===============================================================================================================
-- Insert data for table `groups`
--===============================================================================================================

--Burger 
INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('01','Burger','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\Burger.png',NULL)

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('111','Burger Bò','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\BeefBurger.png','01')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('112','Burger Gà & Cá','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\ChickenFishBurger.png','01')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2111','Burger Big Mac','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\bigmac.png','111')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2112','Burger Bò phô mai đặc biệt','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\cheese-burger-deluxe.png','111')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2113','Burger Bò phô mai 2 lớp','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\double-cheese-burger.png','111')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2114','Burger Bò Hoàng Gia Đặc Biệt','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\mcroyal-deluxe.png','111')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2121','Burger Gà phô mai đặc biệt','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\mcchicken-deluxe.png','112')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2122','Burger Gà thượng hạng giòn cay ','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\mcspicy-deluxe.png','112')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2123','Burger Gà sốt Mayo đặc biệt','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\mcchicken-mayo.png','112')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2124','Burger Phile cá Tuyết','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\filet-o-fish.png','112')

--Chicken 
INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('02','Gà rán da giòn','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\FriedChicken.png',NULL)

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`)
VALUES ('121','Phần lẻ','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\OnlyChicken.png','02')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`)
VALUES ('122','Phần ăn 1 người','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\EVM.png','02')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`)
VALUES ('123','Gà Wings','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\wings.png','02')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`)
VALUES ('124','Gà Nuggets','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\chicken-nuggets.png','02')

--Side Dishes 
INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('03','Món ăn nhẹ','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\FrenchFries.png',NULL)

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('131','Khoai tây chiên','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\french-fries.png','03')


--Drink & Dessert
INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('04','Thức uống & tráng miệng','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\Soda.png',NULL)

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('141','Thức uống','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\drink.png','04')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('142','Tráng miệng','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\dessert.png','04')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2411','Coca-Cola','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\Coca-cola.png','141')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2412','Fanta','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\Fanta.png','141')

INSERT INTO `groups`(`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) 
VALUES ('2413','Sprite','D:\Java\DOAN\Java-Project\FastFoodStore\img-group\Sprite.png','141')



--===============================================================================================================
-- Insert data for table `products`
--===============================================================================================================

-- Burger
INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B01R','Burger Big Mac (Bánh lẻ)',74000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\bigmac.png','2111',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B02R','Burger Bò phô mai đặc biệt (Bánh lẻ)',49000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\cheese-burger-deluxe.png','2112',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B03R','Burger Bò phô mai 2 lớp (Bánh lẻ)',65000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\double-cheese-burger.png','2113',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B04R','Burger Bò hoàng gia đặc biệt (Bánh lẻ)',89000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\mcroyal-deluxe.png','2114',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B05R','Burger Gà phô mai đặc biệt (Bánh lẻ)',69000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\mcchicken-deluxe.png','2121',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B06R','Burger Gà thượng hạng giòn cay (Bánh lẻ)',89000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\mcspicy-deluxe.png','2122',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B07R','Burger Gà sốt Mayo đặc biệt (Bánh lẻ)',59000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\mcchicken-mayo.png','2123',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('B08R','Burger Phile cá Tuyết (Bánh lẻ)',59000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\filet-o-fish.png','2124',1)


--Fried Chicken
INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('C01E','1 miếng gà rán',37000,1,'E','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\1-chicken.png','121',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('C03E','3 miếng gà rán',101000,1,'E','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\3-chicken.png','121',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('C05E','5 miếng gà rán',176000,1,'E','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\5-chicken.png','121',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('C09E','9 miếng gà rán',389000,1,'E','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\9-chicken.png','121',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('W03R','3 miếng gà Wings',69000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\3-wings.png','123',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('W06R','6 miếng gà Wings',125000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\6-wings.png','123',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('W09R','10 miếng gà Wings',176000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\10-wings.png','123',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('N06R','6 gà Nuggets',49000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\6-nuggets.png','124',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('N09R','9 gà Nuggets',69000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\9-nuggets.png','124',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('N20R','20 gà Nuggets',121000,1,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\20-nuggets.png','124',1)


--Side Dishes
INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('S01E','Salad lắc',35000,2,'E','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\salad.png','03',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('F01R','Khoai tây chiên cỡ nhỏ',19000,2,'R','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\regular-fries.png','131',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('F01M','Khoai tây chiên cỡ vừa',29000,2,'M','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\medium-fries.png','131',1)

INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) 
VALUES ('F01L','Khoai tây chiên cỡ lớn',39000,2,'L','D:\Java\DOAN\Java-Project\FastFoodStore\img-product\large-fries.png','131',1)
