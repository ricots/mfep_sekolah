-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 02 Bulan Mei 2018 pada 14.29
-- Versi server: 10.0.34-MariaDB-cll-lve
-- Versi PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb1675_sekolah`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_guru`
--

CREATE TABLE `tbl_guru` (
  `nip_guru` varchar(25) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `nuptk` varchar(25) NOT NULL,
  `nrg` varchar(25) NOT NULL,
  `tempat_lahir` varchar(25) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `masa_kerja` varchar(25) NOT NULL,
  `tgl_mulai_bekerja` varchar(25) NOT NULL,
  `pendidikan_terakhir` varchar(25) NOT NULL,
  `spesialisasi` varchar(25) NOT NULL,
  `jenis_kelamin` varchar(25) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `tahun_penilaian` varchar(25) NOT NULL,
  `password_guru` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_guru`
--

INSERT INTO `tbl_guru` (`nip_guru`, `nama`, `nuptk`, `nrg`, `tempat_lahir`, `tanggal_lahir`, `masa_kerja`, `tgl_mulai_bekerja`, `pendidikan_terakhir`, `spesialisasi`, `jenis_kelamin`, `alamat`, `tahun_penilaian`, `password_guru`) VALUES
('1', 'tes', '1', '1', 'tes', '1995-01-24', '2', '2010', 'SMA', 'BAHASA', 'LAKI LAKI', 'tes', '2010', '1'),
('130403020096', 'Candra Kurniawan', '18263820181', '1100110', 'Blitar', '1994-11-28', '19', '', 'SMA', '', 'LAKI LAKI', 'Blitar', '1990', 'sapiireng'),
('22', 'atep', '', '', '', '0000-00-00', '', '', '', '', '', '', '', ''),
('233', 'Rini Agustina', '15277336', '8464738', 'malang', '2017-01-25', '19', '', 'S1', '', 'PEREMPUAN', 'malang', '2017', '1234'),
('33', 'pakde', '', '', '', '0000-00-00', '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_kategori`
--

CREATE TABLE `tbl_kategori` (
  `id_kategori` varchar(10) NOT NULL,
  `kategori` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_kategori`
--

INSERT INTO `tbl_kategori` (`id_kategori`, `kategori`) VALUES
('KTO0001', 'Pedagogik'),
('KTO0002', 'Kepribadian'),
('KTO0003', 'Sosial'),
('KTO0004', 'Profesional');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_kepsek`
--

CREATE TABLE `tbl_kepsek` (
  `nip_kepsek` int(25) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `tlpn` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_kepsek`
--

INSERT INTO `tbl_kepsek` (`nip_kepsek`, `nama`, `alamat`, `tlpn`, `password`) VALUES
(112, 'rico ts', 'banyuwangi', '1', '1234');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_penilaian`
--

CREATE TABLE `tbl_penilaian` (
  `id_penilaian` varchar(10) NOT NULL,
  `id_pertanyaan` varchar(10) NOT NULL,
  `nip_kepsek` varchar(25) NOT NULL,
  `nip_guru` varchar(25) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `keterangan` varchar(25) NOT NULL,
  `skor` int(11) NOT NULL,
  `skor_konf` double NOT NULL,
  `total_skor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_penilaian`
--

INSERT INTO `tbl_penilaian` (`id_penilaian`, `id_pertanyaan`, `nip_kepsek`, `nip_guru`, `semester`, `tanggal`, `keterangan`, `skor`, `skor_konf`, `total_skor`) VALUES
('PNO0002', 'PTO0002', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.16),
('PNO0003', 'PTO0001', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.18),
('PNO0004', 'PTO0003', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.18),
('PNO0005', 'PTO0004', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.18),
('PNO0006', 'PTO0005', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.14),
('PNO0007', 'PTO0006', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.14),
('PNO0008', 'PTO0007', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.12),
('PNO0009', 'PTO0008', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.12),
('PNO0010', 'PTO0009', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.16),
('PNO0011', 'PTO0010', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.16),
('PNO0012', 'PTO0011', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.12),
('PNO0013', 'PTO0012', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.12),
('PNO0014', 'PTO0013', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.1),
('PNO0015', 'PTO0014', '', '1', 'GANJIL', '2018-02-04', 'sangat buruk', 2, 0.2, 0.12),
('PNO0028', 'PTO0001', '', '130403020096', 'GENAP', '2018-02-09', 'cukup', 6, 0.6, 0.54),
('PNO0029', 'PTO0001', '', '130403020096', 'GENAP', '2018-02-09', 'cukup', 6, 0.6, 0.54),
('PNO0030', 'PTO0002', '', '130403020096', 'GENAP', '2018-02-09', 'cukup', 6, 0.6, 0.48),
('PNO0031', 'PTO0003', '', '130403020096', 'GENAP', '2018-02-09', 'cukup', 6, 0.6, 0.54),
('PNO0032', 'PTO0004', '', '130403020096', 'GENAP', '2018-02-09', 'cukup', 6, 0.6, 0.54),
('PNO0033', 'PTO0006', '', '130403020096', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.7000000000000001),
('PNO0034', 'PTO0007', '', '130403020096', 'GENAP', '2018-02-09', 'cukup', 6, 0.6, 0.36),
('PNO0035', 'PTO0008', '', '130403020096', 'GENAP', '2018-02-09', 'baik', 8, 0.8, 0.48),
('PNO0036', 'PTO0010', '', '130403020096', 'GENAP', '2018-02-09', 'baik', 8, 0.8, 0.64),
('PNO0037', 'PTO0011', '', '130403020096', 'GENAP', '2018-02-09', 'buruk', 4, 0.4, 0.24),
('PNO0038', 'PTO0012', '', '130403020096', 'GENAP', '2018-02-09', 'buruk', 4, 0.4, 0.24),
('PNO0039', 'PTO0013', '', '130403020096', 'GENAP', '2018-02-09', 'baik', 8, 0.8, 0.4),
('PNO0040', 'PTO0014', '', '130403020096', 'GENAP', '2018-02-09', 'buruk', 4, 0.4, 0.24),
('PNO0041', 'PTO0001', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.8999999999999999),
('PNO0042', 'PTO0002', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.8),
('PNO0043', 'PTO0003', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.8999999999999999),
('PNO0044', 'PTO0004', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.8999999999999999),
('PNO0045', 'PTO0005', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.7000000000000001),
('PNO0046', 'PTO0006', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.7000000000000001),
('PNO0047', 'PTO0007', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.6),
('PNO0048', 'PTO0008', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.6),
('PNO0049', 'PTO0009', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.8),
('PNO0050', 'PTO0010', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.8),
('PNO0051', 'PTO0011', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.6),
('PNO0052', 'PTO0012', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.6),
('PNO0053', 'PTO0013', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.5),
('PNO0054', 'PTO0014', '', '233', 'GENAP', '2018-02-09', 'sangat baik', 10, 1, 0.6);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_pertanyaan`
--

CREATE TABLE `tbl_pertanyaan` (
  `id_kategori` varchar(10) NOT NULL,
  `id_pertanyaan` varchar(10) NOT NULL,
  `pertanyaan` varchar(300) NOT NULL,
  `bobot` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_pertanyaan`
--

INSERT INTO `tbl_pertanyaan` (`id_kategori`, `id_pertanyaan`, `pertanyaan`, `bobot`) VALUES
('KTO0001', 'PTO0001', 'Mengenal karakteristik peserta didik', 0.07),
('KTO0001', 'PTO0002', 'Menguasai Teori belajar dan prinsip-prinsip pembelajaran yang mendidik', 0.08),
('KTO0001', 'PTO0003', 'Pengembangan kurikulum', 0.08),
('KTO0001', 'PTO0004', 'Kegiatan pembelajaran yang mendidik', 0.12),
('KTO0001', 'PTO0005', 'Memahami dan mengembangkan potensi', 0.07),
('KTO0001', 'PTO0006', 'Komunikasi dengan peserta didik', 0.07),
('KTO0001', 'PTO0007', 'Penilaian dan evaluasi', 0.06),
('KTO0002', 'PTO0008', 'Bertindak sesuai dengan norma, agama, hukum, sosial dan kebudayaan nasional Indonesia', 0.06),
('KTO0002', 'PTO0009', 'Mewujudkan pribadi yang dewasa dan teladan', 0.08),
('KTO0002', 'PTO0010', 'Etos kerja, tanggung jawab yang tinggi, rasa bangga menjadi guru', 0.08),
('KTO0003', 'PTO0011', 'Bersikap inklusif, bertindak obyektif, serta tidak diskriminatif', 0.06),
('KTO0003', 'PTO0012', 'Komunikasi dengan sesama guru, tenaga kependidikan, orang tua peserta didik, dan masyarakat', 0.06),
('KTO0004', 'PTO0013', 'Penguasaan materi struktur konsep dan pola pikir keilmuan yang mendukung mata pelajaran yang diampu', 0.05),
('KTO0004', 'PTO0014', 'Mengembangkan keprofesian melalui tindakan reflektif', 0.06);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_guru`
--
ALTER TABLE `tbl_guru`
  ADD PRIMARY KEY (`nip_guru`);

--
-- Indeks untuk tabel `tbl_kategori`
--
ALTER TABLE `tbl_kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `tbl_kepsek`
--
ALTER TABLE `tbl_kepsek`
  ADD PRIMARY KEY (`nip_kepsek`);

--
-- Indeks untuk tabel `tbl_penilaian`
--
ALTER TABLE `tbl_penilaian`
  ADD PRIMARY KEY (`id_penilaian`);

--
-- Indeks untuk tabel `tbl_pertanyaan`
--
ALTER TABLE `tbl_pertanyaan`
  ADD PRIMARY KEY (`id_pertanyaan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
