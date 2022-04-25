-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 03:12 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogriddb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billID` int(11) NOT NULL,
  `bName` varchar(50) NOT NULL,
  `bDate` varchar(50) NOT NULL,
  `accNo` varchar(50) NOT NULL,
  `preReading` double NOT NULL,
  `currentReading` double NOT NULL,
  `bUnits` double NOT NULL,
  `bTotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`billID`, `bName`, `bDate`, `accNo`, `preReading`, `currentReading`, `bUnits`, `bTotal`) VALUES
(6, 'Kamal', '20/02/2022', '4523224878', 110, 150, 40, 850),
(7, 'Nimal', '25/03/2022', '4523224879', 320, 450, 130, 2400),
(8, 'Nimal', '25/03/2022', '4523224880', 1000, 1250, 250, 4200);

-- --------------------------------------------------------

--
-- Table structure for table `interrupt`
--

CREATE TABLE `interrupt` (
  `interruptID` int(11) NOT NULL,
  `interruptDate` varchar(50) NOT NULL,
  `interruptSubject` varchar(200) NOT NULL,
  `interruptDesc` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interrupt`
--

INSERT INTO `interrupt` (`interruptID`, `interruptDate`, `interruptSubject`, `interruptDesc`) VALUES
(6, '01/01/2022', 'Technical Maintenance', 'There will be a maintenance at Kadugannawa from 3 p.m. until 5 p.m.'),
(7, '02/02/2022', 'Line Dead', 'Due to a landslide, Kandy town will experience a power breakdown from 15:00 p.m. until 18:00 p.m.'),
(8, '10/07/2022', 'New Connection Establishment', 'New Electrical Line to be established through Kadawatha to Kiribathgoda. Power supply will be defualted from 13:00 to 16:00.');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payID` int(11) NOT NULL,
  `fullName` varchar(30) NOT NULL,
  `NIC` varchar(10) NOT NULL,
  `amount` varchar(20) NOT NULL,
  `date` varchar(30) NOT NULL,
  `bankName` varchar(30) NOT NULL,
  `debitCard` varchar(19) NOT NULL,
  `otpNumber` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payID`, `fullName`, `NIC`, `amount`, `date`, `bankName`, `debitCard`, `otpNumber`) VALUES
(6, 'Gihan', '893453453V', '2000.00', '2022-02-03', 'NDB ', '1111-5435-2311-5433', '1005');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(10) NOT NULL,
  `userNumber` varchar(50) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `interrupt`
--
ALTER TABLE `interrupt`
  ADD PRIMARY KEY (`interruptID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `billID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `interrupt`
--
ALTER TABLE `interrupt`
  MODIFY `interruptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
