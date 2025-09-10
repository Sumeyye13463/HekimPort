-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 27 Ara 2024, 22:47:56
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `SaglikYonetimi`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Doktorlar`
--

CREATE TABLE `Doktorlar` (
  `doktorNo` int(11) NOT NULL,
  `Ad` varchar(50) NOT NULL,
  `Soyad` varchar(50) NOT NULL,
  `Brans` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Doktorlar`
--

INSERT INTO `Doktorlar` (`doktorNo`, `Ad`, `Soyad`, `Brans`) VALUES
(300, 'serra', 'sarı', 'nef');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Hastalar`
--

CREATE TABLE `Hastalar` (
  `tcNo` varchar(11) NOT NULL,
  `Ad` varchar(50) NOT NULL,
  `Soyad` varchar(50) NOT NULL,
  `Brans` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Hastalar`
--

INSERT INTO `Hastalar` (`tcNo`, `Ad`, `Soyad`, `Brans`) VALUES
('11111111111', 'atilla', 'esmer', 'kardi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Hastalıklar`
--

CREATE TABLE `Hastalıklar` (
  `tcNo` varchar(11) NOT NULL,
  `Hastalik` varchar(100) NOT NULL,
  `Tedavi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Hastalıklar`
--

INSERT INTO `Hastalıklar` (`tcNo`, `Hastalik`, `Tedavi`) VALUES
('11122233344', 'böbrek', 'diyaliz');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Randevular`
--

CREATE TABLE `Randevular` (
  `tcNo` varchar(11) NOT NULL,
  `doktorNo` text NOT NULL,
  `Tarih` date NOT NULL,
  `Saat` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Randevular`
--

INSERT INTO `Randevular` (`tcNo`, `doktorNo`, `Tarih`, `Saat`) VALUES
('11122233344', '300', '2024-12-31', '13:30:00');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `Doktorlar`
--
ALTER TABLE `Doktorlar`
  ADD PRIMARY KEY (`doktorNo`);

--
-- Tablo için indeksler `Hastalar`
--
ALTER TABLE `Hastalar`
  ADD PRIMARY KEY (`tcNo`);

--
-- Tablo için indeksler `Hastalıklar`
--
ALTER TABLE `Hastalıklar`
  ADD PRIMARY KEY (`tcNo`);

--
-- Tablo için indeksler `Randevular`
--
ALTER TABLE `Randevular`
  ADD PRIMARY KEY (`tcNo`,`doktorNo`(10));
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
