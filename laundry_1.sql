-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Jun 2023 pada 19.04
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laundry`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `no_transaksi` varchar(30) NOT NULL,
  `id_jenis_jasa` int(11) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `qty` int(8) DEFAULT NULL,
  `harga` int(30) DEFAULT NULL,
  `total_harga` int(30) DEFAULT NULL,
  `tgl_transaksi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `no_transaksi`, `id_jenis_jasa`, `nama_barang`, `qty`, `harga`, `total_harga`, `tgl_transaksi`) VALUES
(1, '', 1, '', 1, 12000, 12000, ''),
(2, '', 1, '', 1, 12000, 12000, ''),
(3, '', 22, '', 2, 10000, 20000, ''),
(4, '200002', 122, '', 2, 15000, 30000, '25-06-2023'),
(5, '200002', 2, '', 2, 12000, 24000, '25-06-2023'),
(6, '200002', 1, 'baju', 1, 12000, 12000, '25-06-2023'),
(7, '200002', 2, 'sarung', 2, 10000, 20000, '25-06-2023'),
(8, '200002', 1, 'baju', 12, 20000, 240000, '25-06-2023');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_jasa`
--

CREATE TABLE `jenis_jasa` (
  `id_jenis_jasa` int(20) NOT NULL,
  `nama_jenis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `item` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `berat` int(30) NOT NULL,
  `harga` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jenis_jasa`
--

INSERT INTO `jenis_jasa` (`id_jenis_jasa`, `nama_jenis`, `item`, `berat`, `harga`) VALUES
(1, 'setrika baju', 'baju', 200, 20000),
(2, 'setrika', 'sarung', 10, 10000),
(3, 'y', 'y', 0, 0),
(4, 'y', 'y', 0, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `member`
--

CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `no_hp` int(11) NOT NULL,
  `alamat` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `member`
--

INSERT INTO `member` (`member_id`, `first_name`, `last_name`, `no_hp`, `alamat`) VALUES
(1, 'yehezkiel', 'sutiono', 81328499, ''),
(6, 'tes2', 'tes2', 88822, 'tes2'),
(8, 'yehezkiel', 'sutiono', 123, 'ssss');

-- --------------------------------------------------------

--
-- Struktur dari tabel `staff`
--

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `handphone_number` int(11) NOT NULL,
  `tgl_masukKerja` date NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `staff`
--

INSERT INTO `staff` (`staff_id`, `first_name`, `last_name`, `address`, `handphone_number`, `tgl_masukKerja`, `username`, `password`) VALUES
(2, 'Yudi', 'Latif haha', '', 1721821, '2023-05-09', 'admin', 'admin123'),
(1222, 'yehezkiel', 'hhh', 'sds', 333, '2023-06-07', 'admin123', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `jenis_jasa`
--
ALTER TABLE `jenis_jasa`
  ADD PRIMARY KEY (`id_jenis_jasa`);

--
-- Indeks untuk tabel `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indeks untuk tabel `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`),
  ADD UNIQUE KEY `handphone_number` (`handphone_number`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `jenis_jasa`
--
ALTER TABLE `jenis_jasa`
  MODIFY `id_jenis_jasa` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1223;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
