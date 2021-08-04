-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2020 at 06:51 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `systemfx`
--

-- --------------------------------------------------------

--
-- Table structure for table `courseinf`
--

CREATE TABLE `courseinf` (
  `semester` varchar(225) NOT NULL,
  `CourseID` varchar(225) NOT NULL,
  `CourseName` varchar(225) NOT NULL,
  `TeacherID` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `courseinf`
--

INSERT INTO `courseinf` (`semester`, `CourseID`, `CourseName`, `TeacherID`) VALUES
('108 2', '1001', '資訊安全', 'A10016002'),
('108 2', '1002', '自動機', 'A10016002'),
('108 2', '1004', '計算機組織', 'A10016003'),
('108 2', '1005', '資料庫程式', 'A10016006'),
('108 2', '1006', '網路', 'A10016001'),
('108 2', '1007', '電路實驗', 'A10016010'),
('108 2', '1008', '人工智慧', 'A10016005'),
('108 2', '1009', '編譯程式', 'A10016004'),
('108 2', '1010', '電子學', 'A10016007'),
('﻿108 2', '1011', '網頁程式設計', 'A10016003'),
('108 2', '1012', '動態網頁設計', 'A10016003'),
('108 2', '1013', '計算機概論', 'A10016004'),
('108 2', '1014', '離散數學', 'A10016001'),
('108 2', '1015', '線性代數', 'A10016005'),
('108 2', '1016', '資料結構', 'A10016003'),
('108 2', '1017', 'IOS 程式設計', 'A10016004'),
('108 2', '1018', 'PHP', 'A10016006');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
