-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 09, 2024 lúc 05:04 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `b2test`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `idcart` varchar(10) NOT NULL,
  `iduser` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`idcart`, `iduser`) VALUES
('cart2', 'easylove');

--
-- Bẫy `cart`
--
DELIMITER $$
CREATE TRIGGER `trigger_cart` BEFORE INSERT ON `cart` FOR EACH ROW set new.idcart = CONCAT('cart', CAST(next value for seq_cart as CHAR))
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cartdetail`
--

CREATE TABLE `cartdetail` (
  `idcart` varchar(10) NOT NULL,
  `idproduct` varchar(10) NOT NULL,
  `idtopping` varchar(10) NOT NULL,
  `idsize` varchar(10) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `log`
--

CREATE TABLE `log` (
  `idlog` varchar(10) NOT NULL,
  `level` varchar(10) NOT NULL,
  `address` varchar(10) NOT NULL,
  `action` varchar(50) NOT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp(),
  `user` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `log`
--

INSERT INTO `log` (`idlog`, `level`, `address`, `action`, `time`, `user`, `status`) VALUES
('log2', 'INFO', 'Account', 'Login', '2024-05-04 16:51:24', 'admin', 'Success'),
('log3', 'INFO', 'Account', 'Login', '2024-05-04 16:59:59', 'admin', 'Success'),
('log4', 'INFO', 'Account', 'Login', '2024-05-04 17:03:06', 'admin', 'Success'),
('log5', 'INFO', 'Account', 'Login', '2024-05-04 17:28:30', 'user1', 'Success');

--
-- Bẫy `log`
--
DELIMITER $$
CREATE TRIGGER `tr_log` BEFORE INSERT ON `log` FOR EACH ROW SET NEW.idlog = CONCAT('log',CAST(NEXT VALUE for seq_log as char))
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `idproduct` varchar(10) NOT NULL,
  `idtype` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  `describe` varchar(255) NOT NULL,
  `srcIMG` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`idproduct`, `idtype`, `name`, `price`, `describe`, `srcIMG`) VALUES
('product1', 'type1', 'Ô lông sữa trân châu ngũ cốc', 15000, 'Trà sữa khói thanh nhiệt thơm béo, topping trân châu ngũ cốc dẻo bùi được làm từ khoai lang Đà Lạt', 'OLongSuaTranChauNguCoc.img');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seq_cart`
--

CREATE TABLE `seq_cart` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- Đang đổ dữ liệu cho bảng `seq_cart`
--

INSERT INTO `seq_cart` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(1001, 1, 9223372036854775806, 1, 1, 1000, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seq_cart2`
--

CREATE TABLE `seq_cart2` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- Đang đổ dữ liệu cho bảng `seq_cart2`
--

INSERT INTO `seq_cart2` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(1, 1, 9223372036854775806, 1, 1, 1000, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seq_log`
--

CREATE TABLE `seq_log` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- Đang đổ dữ liệu cho bảng `seq_log`
--

INSERT INTO `seq_log` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(1001, 1, 9223372036854775806, 1, 1, 1000, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `size`
--

CREATE TABLE `size` (
  `idsize` varchar(10) NOT NULL,
  `namesize` varchar(20) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `size`
--

INSERT INTO `size` (`idsize`, `namesize`, `price`) VALUES
('size1', 'Size L', 10000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `topping`
--

CREATE TABLE `topping` (
  `idtopping` varchar(10) NOT NULL,
  `nametopping` varchar(50) NOT NULL,
  `pricetopping` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `topping`
--

INSERT INTO `topping` (`idtopping`, `nametopping`, `pricetopping`) VALUES
('topping1', 'Trân châu sương mai', 6000),
('topping2', 'Thạch Konjac', 5000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type`
--

CREATE TABLE `type` (
  `idtype` varchar(10) NOT NULL,
  `nametype` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `type`
--

INSERT INTO `type` (`idtype`, `nametype`) VALUES
('type1', 'Milk tea');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `username` varchar(10) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`username`, `email`, `password`) VALUES
('admin1', 'admin', 'admin'),
('easylove', 'easylove@gmail.com', 'Easylove1!');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`idcart`),
  ADD KEY `iduser` (`iduser`);

--
-- Chỉ mục cho bảng `cartdetail`
--
ALTER TABLE `cartdetail`
  ADD KEY `idcart` (`idcart`),
  ADD KEY `idproduct` (`idproduct`),
  ADD KEY `idsize` (`idsize`),
  ADD KEY `idtopping` (`idtopping`);

--
-- Chỉ mục cho bảng `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`idlog`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idproduct`),
  ADD KEY `idtype` (`idtype`);

--
-- Chỉ mục cho bảng `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`idsize`);

--
-- Chỉ mục cho bảng `topping`
--
ALTER TABLE `topping`
  ADD PRIMARY KEY (`idtopping`);

--
-- Chỉ mục cho bảng `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`idtype`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`username`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `cartdetail`
--
ALTER TABLE `cartdetail`
  ADD CONSTRAINT `cartdetail_ibfk_1` FOREIGN KEY (`idcart`) REFERENCES `cart` (`idcart`),
  ADD CONSTRAINT `cartdetail_ibfk_2` FOREIGN KEY (`idproduct`) REFERENCES `product` (`idproduct`),
  ADD CONSTRAINT `cartdetail_ibfk_3` FOREIGN KEY (`idsize`) REFERENCES `size` (`idsize`),
  ADD CONSTRAINT `cartdetail_ibfk_4` FOREIGN KEY (`idtopping`) REFERENCES `topping` (`idtopping`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`idtype`) REFERENCES `type` (`idtype`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
