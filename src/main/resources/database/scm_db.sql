-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2025 at 12:01 PM
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
-- Database: `scm_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tm_detail`
--

CREATE TABLE `tm_detail` (
  `id` int(11) NOT NULL,
  `po_number` varchar(10) DEFAULT NULL,
  `part_no` varchar(20) DEFAULT NULL,
  `part_name` varchar(100) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tm_header`
--

CREATE TABLE `tm_header` (
  `po_number` varchar(10) NOT NULL,
  `po_date` date DEFAULT NULL,
  `buyer_name` varchar(100) DEFAULT NULL,
  `buyer_addr` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tm_detail`
--
ALTER TABLE `tm_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tm_header`
--
ALTER TABLE `tm_header`
  ADD PRIMARY KEY (`po_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tm_detail`
--
ALTER TABLE `tm_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
