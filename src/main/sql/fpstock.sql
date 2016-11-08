-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 08 Lis 2016, 03:14
-- Wersja serwera: 10.1.13-MariaDB
-- Wersja PHP: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `fpstock`
--
CREATE DATABASE IF NOT EXISTS `fpstock` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `fpstock`;
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stocks`
--

CREATE TABLE `stocks` (
  `stock_id` int(2) NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `company_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `stocks`
--

INSERT INTO `stocks` (`stock_id`, `company_name`, `company_code`) VALUES
(1, 'Future Processing', 'FP'),
(2, 'FP Lab', 'FPL'),
(3, 'FP Coin', 'FPC'),
(4, 'ProgressBar', 'PGB'),
(5, 'FP Adventure', 'FPA'),
(6, 'Deadline 24', 'DL24');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stock_initial`
--

CREATE TABLE `stock_initial` (
  `stock_name` varchar(100) NOT NULL,
  `stock_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `stock_initial`
--

INSERT INTO `stock_initial` (`stock_name`, `stock_amount`) VALUES
('Future Processing', 1000),
('FP Lab', 800),
('FP Coin', 700),
('FP Adventure', 1000),
('Deadline 24', 700),
('Progress Bar', 600);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `userwallet`
--

CREATE TABLE `userwallet` (
  `wallet_id` varchar(100) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `wallet_resource` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `userwallet_d`
--

CREATE TABLE `userwallet_d` (
  `wallet_item_id` int(11) NOT NULL,
  `wallet_id` varchar(100) NOT NULL,
  `stock_name` varchar(50) DEFAULT NULL,
  `stock_amount` int(11) NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_roles`
--

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `userwallet_d`
--
ALTER TABLE `userwallet_d`
  ADD PRIMARY KEY (`wallet_item_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;
--
-- AUTO_INCREMENT dla tabeli `userwallet_d`
--
ALTER TABLE `userwallet_d`
  MODIFY `wallet_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;
--
-- AUTO_INCREMENT dla tabeli `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
