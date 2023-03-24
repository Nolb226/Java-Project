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
  `comboImage` varchar(100) NOT NULL,
  `groupCode` varchar(10) NOT NULL,
  `inMenu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `combodetail`
--

CREATE TABLE `combodetail` (
  `comboCode` varchar(10) NOT NULL,
  `productCode` varchar(10) NOT NULL
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
  `recipe` varchar(30) NOT NULL,
  `toChange` tinyint(1) NOT NULL
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


-- ===============================================================================================================
-- Insert data for table `groups`
-- ===============================================================================================================

-- Burger 
INSERT INTO `groups` (`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`) VALUES 
('01','Burger','./img-group/Burger.png',NULL),
('111','Burger Bò','./img-group/BeefBurger.png','01'),
('112','Burger Gà & Cá','./img-group/ChickenFishBurger.png','01'),
('2111','Burger Big Mac','./img-group/bigmac.png','111'),
('2112','Burger Bò phô mai đặc biệt','./img-group/cheese-burger-deluxe.png','111'),
('2113','Burger Bò phô mai 2 lớp','./img-group/double-cheese-burger.png','111'),
('2114','Burger Bò Hoàng Gia Đặc Biệt','./img-group/mcroyal-deluxe.png','111'),
('2121','Burger Gà phô mai đặc biệt','./img-group/mcchicken-deluxe.png','112'),
('2122','Burger Gà thượng hạng giòn cay ','./img-group/mcspicy-deluxe.png','112'),
('2123','Burger Gà sốt Mayo đặc biệt','./img-group/mcchicken-mayo.png','112'),
('2124','Burger Phile cá Tuyết','./img-group/filet-o-fish.png','112'),
-- Chicken
('02','Gà rán da giòn','./img-group/FriedChicken.png',NULL),
('121','Phần lẻ','./img-group/OnlyChicken.png','02'),
('122','Phần ăn 1 người','./img-group/EVM.png','02'),
('123','Gà Wings','./img-group/wings.png','02'),
('124','Gà Nuggets','./img-group/chicken-nuggets.png','02'),
-- Side Dishes 
('03','Món ăn nhẹ','./img-group/FrenchFries.png',NULL),
('131','Khoai tây chiên','./img-group/french-fries.png','03'),
-- Drink & Dessert
('04','Thức uống & tráng miệng','./img-group/Soda.png',NULL),
('141','Thức uống','./img-group/drink.png','04'),
('142','Tráng miệng','./img-group/dessert.png','04'),
('2411','Coca-Cola','./img-group/Coca-cola.png','141'),
('2412','Fanta','./img-group/Fanta.png','141'),
('2413','Sprite','./img-group/Sprite.png','141');



-- ===============================================================================================================
-- Insert data for table `products`
-- ===============================================================================================================

-- Burger
INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) VALUES 
('B01R','Burger Big Mac (Bánh lẻ)',74000,1,'R','./img-product/bigmac.png','2111',1),
('B02R','Burger Bò phô mai đặc biệt (Bánh lẻ)',49000,1,'R','./img-product/cheese-burger-deluxe.png','2112',1),
('B03R','Burger Bò phô mai 2 lớp (Bánh lẻ)',65000,1,'R','./img-product/double-cheese-burger.png','2113',1),
('B04R','Burger Bò hoàng gia đặc biệt (Bánh lẻ)',89000,1,'R','./img-product/mcroyal-deluxe.png','2114',1),
('B05R','Burger Gà phô mai đặc biệt (Bánh lẻ)',69000,1,'R','./img-product/mcchicken-deluxe.png','2121',1),
('B06R','Burger Gà thượng hạng giòn cay (Bánh lẻ)',89000,1,'R','./img-product/mcspicy-deluxe.png','2122',1),
('B07R','Burger Gà sốt Mayo đặc biệt (Bánh lẻ)',59000,1,'R','./img-product/mcchicken-mayo.png','2123',1),
('B08R','Burger Phile cá Tuyết (Bánh lẻ)',56000,1,'R','./img-product/filet-o-fish.png','2124',1),
-- Fried Chicken
('C01E','1 miếng gà rán',37000,1,'E','./img-product/1-chicken.png','121',1),
('C02R','2 miếng gà rán',74000,1,'E','./img-product/1-chicken.png','121',0),
('C03E','3 miếng gà rán',101000,1,'E','./img-product/3-chicken.png','121',1),
('C04E','4 miếng gà rán',141000,1,'E','./img-product/1-chicken.png','121',0),
('C05E','5 miếng gà rán',176000,1,'E','./img-product/5-chicken.png','121',1),
('C09E','9 miếng gà rán',389000,1,'E','./img-product/9-chicken.png','121',1),
('W03R','3 miếng gà Wings',69000,1,'R','./img-product/3-wings.png','123',1),
('W06E','6 miếng gà Wings',125000,1,'E','./img-product/6-wings.png','123',1),
('W10E','10 miếng gà Wings',176000,1,'E','./img-product/10-wings.png','123',1),
('N06R','6 gà Nuggets',49000,1,'R','./img-product/6-nuggets.png','124',1),
('N09E','9 gà Nuggets',69000,1,'E','./img-product/9-nuggets.png','124',1),
('N20E','20 gà Nuggets',121000,1,'E','./img-product/20-nuggets.png','124',1),
('R01E','Cơm gà rán 1 miếng',29000,1,'E','./img-combo/1pc-rice.png','122',0),
('R02E','Cơm gà rán 2 miếng',69000,1,'E','./img-product/2pc-rice.png','122',1),
('R03E','Cơm gà rán mắm tỏi',39000,1,'E','./img-product/1pc-gfs-rice.png','122',0),
-- Side Dishes
('S01E','Salad lắc',35000,2,'E','./img-product/salad.png','03',1),
('S02E','Bắp ngọt',22000,2,'E','./img-product/sweet-corn.png','03',1),
('F01R','Khoai tây chiên cỡ nhỏ',19000,2,'R','./img-product/regular-fries.png','131',1),
('F01M','Khoai tây chiên cỡ vừa',29000,2,'M','./img-product/medium-fries.png','131',1),
('F01L','Khoai tây chiên cỡ lớn',39000,2,'L','./img-product/large-fries.png','131',1),
-- Drink & Dessert
('D01R','Coca-cola cỡ nhỏ',17000,3,'R','./img-product/coca-cola.png','2411',1),
('D01M','Coca-cola cỡ vừa',22000,3,'M','./img-product/coca-cola.png','2411',1),
('D01L','Coca-cola cỡ lớn',29000,3,'L','./img-product/coca-cola.png','2411',1),
('D02R','Fanta cỡ nhỏ',17000,3,'R','./img-product/fanta.png','2412',1),
('D02M','Fanta cỡ vừa',22000,3,'M','./img-product/fanta.png','2412',1),
('D02L','Fanta cỡ lớn',29000,3,'L','./img-product/fanta.png','2412',1),
('D03R','Sprite cỡ nhỏ',17000,3,'R','./img-product/sprite.png','2413',1),
('D03M','Sprite cỡ vừa',22000,3,'M','./img-product/sprite.png','2413',1),
('D03L','Sprite cỡ lớn',29000,3,'L','./img-product/sprite.png','2413',1),
('D04E','Milo',20000,3,'E','./img-product/milo.png','141',1),
('D05E','Sữa tươi',20000,3,'E','./img-product/milk.png','141',1),
('D06E','Nước suối',22000,3,'E','./img-product/both-water.png','141',1),
('I01E','Kem ốc quế',10000,4,'E','./img-product/cone.png','142',1),
('I02E','Kem Chocolate Sundae',29000,4,'E','./img-product/choc-sun.png','142',1),
('I03E','Kem Dâu Sundae',29000,4,'E','./img-product/straw-sun.png','142',1),
('I04E','Kem Xay Oreo',39000,4,'E','./img-product/oreo-mcflurry.png','142',1);



--===============================================================================================================
-- Insert data for table `combo` and `combodetail`
--===============================================================================================================

-- EVM Burger
INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B01M','Combo Big Mac cỡ vừa ',99000,3,'./img-combo/EVM-bigmac.png','2111',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B01M','B01R'),
('B01M','F01M'),
('B01M','S01E'),
('B01M','S02E'),
('B01M','D01M'),
('B01M','D02M'),
('B01M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B01L','Combo Big Mac cỡ lớn ',114000,3,'./img-combo/EVM-bigmac.png','2111',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B01L','B01R'),
('B01L','F01L'),
('B01L','S01E'),
('B01L','D01L'),
('B01L','D02L'),
('B01L','D03L');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B02M','Combo Bò phô mai đặc biệt cỡ vừa ',69000,3,'./img-combo/EVM-cheeseburger-deluxe.png','2112',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES ('B02M','B02R')
('B02M','F01M'),
('B02M','S01E'),
('B02M','S02E'),
('B02M','D01M'),
('B02M','D02M'),
('B02M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B02L','Combo Bò phô mai đặc biệt cỡ lớn ',84000,3,'./img-combo/EVM-cheeseburger-deluxe.png','2112',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B02L','B02R'),
('B02L','F01M'),
('B02L','S01E'),
('B02L','S02E'),
('B02L','D01M'),
('B02L','D02M'),
('B02L','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B03M','Combo Bò phô mai 2 lớp cỡ vừa ',79000,3,'./img-combo/EVM-double-cheeseburger.png','2113',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B03M','B03R'),
('B03M','F01M'),
('B03M','S01E'),
('B03M','S02E'),
('B03M','D01M'),
('B03M','D02M'),
('B03M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B03L','Combo Bò phô mai 2 lớp cỡ lớn ',94000,3,'./img-combo/EVM-double-cheeseburger.png','2113',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B03L','B03R'),
('B03L','F01M'),
('B03L','S01E'),
('B03L','S02E'),
('B03L','D01M'),
('B03L','D02M'),
('B03L','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B04M','Combo Bò hoàng gia đặc biệt cỡ vừa ',99000,3,'./img-combo/EVM-mcroyal-deluxe.png','2114',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B04M','B04R'),
('B04M','F01M'),
('B04M','S01E'),
('B04M','S02E'),
('B04M','D01M'),
('B04M','D02M'),
('B04M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B04L','Combo Bò hoàng gia đặc biệt cỡ lớn ',114000,3,'./img-combo/EVM-mcroyal-deluxe.png','2114',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B04L','B04R'),
('B04L','F01M'),
('B04L','S01E'),
('B04L','S02E'),
('B04L','D01M'),
('B04L','D02M'),
('B04L','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B05M','Combo Gà phô mai đặc biệt cỡ vừa ',89000,3,'./img-combo/EVM-mcchicken-deluxe.png','2115',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B05M','B05R'),
('B05M','F01M'),
('B05M','S01E'),
('B05M','S02E'),
('B05M','D01M'),
('B05M','D02M'),
('B05M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B05L','Combo Gà phô mai đặc biệt cỡ lớn ',104000,3,'./img-combo/EVM-mcchicken-deluxe.png','2115',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B05L','B05R'),
('B05L','F01M'),
('B05L','S01E'),
('B05L','S02E'),
('B05L','D01M'),
('B05L','D02M'),
('B05L','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B06M','Combo Gà thượng hạng giòn cay cỡ vừa ',99000,3,'./img-combo/EVM-mcspicy-deluxe.png','2116',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B06M','B06R'),
('B06M','F01M'),
('B06M','S01E'),
('B06M','S02E'),
('B06M','D01M'),
('B06M','D02M'),
('B06M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B06L','Combo Gà thượng hạng giòn cay cỡ lớn ',114000,3,'./img-combo/EVM-mcspicy-deluxe.png','2116',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B06L','B06R'),
('B06L','F01M'),
('B06L','S01E'),
('B06L','S02E'),
('B06L','D01M'),
('B06L','D02M'),
('B06L','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B07M','Combo Gà sốt Mayo cỡ vừa ',79000,3,'./img-combo/EVM-mcchicken-mayo.png','2117',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B07M','B07R'),
('B07M','F01M'),
('B07M','S01E'),
('B07M','S02E'),
('B07M','D01M'),
('B07M','D02M'),
('B07M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B07L','Combo Gà sốt Mayo cỡ lớn ',94000,3,'./img-combo/EVM-mcchicken-mayo.png','2117',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B07L','B07R'),
('B07L','F01M'),
('B07L','S01E'),
('B07L','S02E'),
('B07L','D01M'),
('B07L','D02M'),
('B07L','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B08M','Combo Phi lê cá tuyết cỡ vừa ',69000,3,'./img-combo/EVM-filet-o-fish.png','2118',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B08M','B08R'),
('B08M','F01M'),
('B08M','S01E'),
('B08M','S02E'),
('B08M','D01M'),
('B08M','D02M'),
('B08M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`) 
VALUES ('B08L','Combo Phi lê cá tuyết cỡ lớn ',84000,3,'./img-combo/EVM-filet-o-fish.png','2118',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('B08L','B08R'),
('B08L','F01M'),
('B08L','S01E'),
('B08L','S02E'),
('B08L','D01M'),
('B08L','D02M'),
('B08L','D03M');

--EVM Fried Chicken
INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`)  
VALUES ('C02M','Combo 2 miếng gà rán cỡ vừa',92000,3,'./img-combo/EVM-2chicken.png','122',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('C02M','C02R'),
('C02M','F01M'),
('C02M','S01E'),
('C02M','S02E'),
('C02M','D01M'),
('C02M','D02M'),
('C02M','D03M');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`)  
VALUES ('C02L','Combo 2 miếng gà rán cỡ lớn',107000,3,'./img-combo/EVM-2chicken.png','122',1);

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('C02L','C02R'),
('C02L','F01L'),
('C02L','S01E'),
('C02L','S02E'),
('C02L','D01L'),
('C02L','D02L'),
('C02L','D03L');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`)  
VALUES ('R01R','Cơm gà rán 1 miếng kèm nước',39000,2,'./img-combo/1pc-rice.png','122',1)

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('R01R','R01E'),
('R01R','D01R'),
('R01R','D02R'),
('R01R','D03R');

INSERT INTO `combo`(`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage` , `groupCode`, `inMenu`)  
VALUES ('R03R','Cơm gà rán mắm tỏi kèm nước',49000,2,'./img-combo/1pc-gfs-rice.png','122',1)

INSERT INTO `combodetail` (`comboCode`, `productCode`) VALUES 
('R03R','R01E'),
('R03R','D01R'),
('R03R','D02R'),
('R03R','D03R');



-- ===============================================================================================================
-- Insert data for table `ingredient`
-- ===============================================================================================================

INSERT INTO `ingredient` (`ingredientCode`, `ingredientName`) VALUES 
('IN001','Vỏ bánh lớn'),
('IN002','Vỏ bánh nhỏ'),
('IN003','Thịt bò'),
('IN004','Cơm')
('IN005','Thịt gà'),
('IN006','Thịt gà cay'),
('IN007','Phi lê cá Tuyết'),
('IN008','Rau xà lách'),
('IN009','Cà chua'),
('IN010','Dưa chua'),
('IN011','Hành tây'),
('IN012','Ớt chuông')
('IN013','Phô mai lát'),
('IN014','Tương cà'),
('IN015','Sốt Mustard'),
('IN016','Sốt Mayonnaise'),
('IN017','Gà miếng'),
('IN018','Gà viên'),
('IN019','Cánh gà cắt khúc'),
('IN020','Kem'),
('IN021','Sốt chocolate'),
('IN022','Sốt dâu'),
('IN023','Vụn Oreo'),
('IN024','Khoai tây'),
('IN025','Muối'),
('IN026','Coca Cola'),
('IN027','Fanta'),
('IN028','Sprite'),
('IN029','Đá'),
('IN030','Sốt mắm tỏi'),
('IN031','Bắp'),
('IN032','Milo hộp'),
('IN033','Sữa tươi hộp'),
('IN034','Nước suối đóng chai'),
('IN035','Bánh ốc quế');



-- ===============================================================================================================
-- Insert data for table `productdetails`
-- ===============================================================================================================

INSERT INTO `productdetail` (`productCode`,`ingredientCode`) VALUES 
('B01R','IN001','3 miếng',0),
('B01R','IN003','2 miếng',0),
('B01R','IN008','10 gram',1),
('B01R','IN011','10 gram',1),
('B01R','IN010','3 lát',1),
('B01R','IN013','1 lát',1),
('B01R','IN015','10gram',1),

('B02R','IN001','2 miếng',0),
('B02R','IN003','1 miếng',0),
('B02R','IN008','10 gram',1),
('B02R','IN009','2 lát',1),
('B02R','IN011','10 gram',1),
('B02R','IN013','1 lát',1),
('B02R','IN014','10 gram',1),
('B02R','IN015','10 gram',1),

('B03R','IN001','2 miếng',0),
('B03R','IN003','2 miếng',0),
('B03R','IN013','2 lát',1),
('B03R','IN014','10 gram',1),
('B03R','IN015','10 gram',1),
('B03R','IN010','3 lát',1),
('B03R','IN011','10 gram',1),

('B04R','IN001','2 miếng',0),
('B04R','IN003','1 miếng',0),
('B04R','IN008','10 gram',1),
('B04R','IN009','2 lát',1),
('B04R','IN010','3 lát',1),
('B04R','IN011','10 gram',1),
('B04R','IN013','1 lát',1),
('B04R','IN014','10 gram',1),
('B04R','IN015','10 gram',1),

('B05R','IN001','2 miếng',0),
('B05R','IN005','1 miếng',0),
('B05R','IN008','10 gram',1),
('B05R','IN009','2 lát',1),
('B05R','IN013','1 lát',1),
('B05R','IN016','10 gram',1),

('B06R','IN001','2 miếng',0),
('B06R','IN006','1 miếng',0),
('B06R','IN008','10 gram',1),
('B06R','IN009','2 lát',1),
('B06R','IN016','10 gram',1),

('B07R','IN002','2 miếng',0),
('B07R','IN005','1 miếng',0),
('B07R','IN008','10 gram',1),
('B07R','IN016','10 gram',1),

('B08R','IN002','2 miếng',0),
('B08R','IN007','1 miếng',0),
('B08R','IN008','10 gram',1),
('B08R','IN013','1 lát',1),
('B08R','IN016','10 gram',1),

('C01E','IN017','1 miếng',0),
('C02R','IN017','2 miếng',0),
('C03E','IN017','3 miếng',0),
('C04E','IN017','4 miếng',0),
('C05E','IN017','5 miếng',0),
('C09E','IN017','9 miếng',0),

('W03R','IN019','3 miếng',0),
('W06E','IN019','6 miếng',0),
('W10E','IN019','10 miếng',0),

('N06R','IN018','6 viên',0),
('N09E','IN018','9 viên',0),
('N20E','IN018','20 viên',0),

('R01E','IN004','1 chén',0),
('R01E','IN017','1 miếng',0),

('R02E','IN004','1 chén',0),
('R02E','IN017','2 miếng',0),

('R03E','IN004','1 chén',0),
('R03E','IN017','1 miếng',0),
('R03E','IN030','10 gram',1),

('S01E','IN008','100 gram',0),
('S01E','IN009','4 lát',0),
('S01E','IN031','50 gram',0),
('S01E','IN016','10 gram',1),

('S02E','IN031','200 gram',0),

('F01R','IN024','75 gram',0),
('F01R','IN025','2 gram',1),

('F01M','IN024','95 gram',0),
('F01M','IN025','3 gram',1),

('F01L','IN024','150 gram',0),
('F01L','IN025','4 gram',1),

('D01R','IN026','300 ml',0),
('D01R','IN029','10 gram',1),

('D01M','IN026','400 ml',0),
('D01M','IN029','10 gram',1),

('D01L','IN026','600 ml',0),
('D01L','IN029','10 gram',1),

('D02R','IN027','300 ml',0),
('D02R','IN029','10 gram',1),

('D02M','IN027','400 ml',0),
('D02M','IN029','10 gram',1),

('D02L','IN027','600 ml',0),
('D02L','IN029','10 gram',1),

('D03R','IN028','300 ml',0),
('D03R','IN029','10 gram',1),

('D03M','IN028','400 ml',0),
('D03M','IN029','10 gram',1),

('D03L','IN028','600 ml',0),
('D03L','IN029','10 gram',1),

('D04E','IN032','1 hộp',0),
('D05E','IN033','1 hộp',0),
('D06E','IN034','1 chai',0),

('I01E','IN020','2.5 vòng',0),
('I01E','IN035','1 cái',0),

('I02E','IN020','4.5 vòng',0),
('I02E','IN021','30 ml',1),

('I03E','IN020','4.5 vòng',0),
('I03E','IN022','30 ml',1),

('I04E','IN020','4.5 vòng',0)
('I04E','IN023','30 gram',1)