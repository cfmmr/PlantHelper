-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 01, 2024 at 10:58 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `plantasdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `agua`
--

CREATE TABLE `agua` (
  `ID_agua` int(11) NOT NULL,
  `Humidade` tinyint(3) NOT NULL,
  `Solo` set('1','2','3') NOT NULL,
  `Quantidade` enum('Pouca','Normal','Muita') NOT NULL,
  `Frequencia` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `agua`
--

INSERT INTO `agua` (`ID_agua`, `Humidade`, `Solo`, `Quantidade`, `Frequencia`) VALUES
(1, 50, '2', 'Normal', 7),
(2, 55, '1', 'Pouca', 21),
(3, 60, '2', 'Normal', 7),
(4, 60, '3', 'Normal', 5),
(5, 55, '1', 'Normal', 10),
(6, 70, '3', 'Muita', 5),
(7, 50, '1', 'Normal', 10),
(8, 70, '3', 'Muita', 7),
(9, 45, '1', 'Pouca', 7),
(10, 50, '2', 'Muita', 7),
(11, 50, '1', 'Normal', 10),
(12, 50, '2', 'Normal', 14),
(13, 50, '2', 'Normal', 15),
(14, 70, '2', 'Muita', 10),
(15, 55, '1', 'Normal', 7),
(16, 50, '1', 'Pouca', 21),
(17, 70, '3', 'Normal', 5),
(18, 60, '3', 'Normal', 5),
(19, 70, '3', 'Normal', 7),
(20, 55, '1', 'Normal', 7),
(21, 75, '3', 'Pouca', 5),
(22, 70, '2', 'Muita', 7),
(23, 50, '1', 'Normal', 10),
(24, 70, '1', 'Normal', 10),
(25, 65, '1', 'Normal', 10),
(26, 65, '1', 'Normal', 10),
(27, 50, '2', 'Muita', 10),
(28, 65, '2', 'Normal', 7),
(29, 45, '1', 'Muita', 15),
(30, 55, '2', 'Normal', 7),
(31, 45, '1', 'Muita', 15),
(32, 35, '1', 'Muita', 7),
(33, 65, '2', 'Muita', 10),
(34, 55, '2', 'Normal', 7),
(35, 65, '2', 'Muita', 10);

-- --------------------------------------------------------

--
-- Table structure for table `luminosidade`
--

CREATE TABLE `luminosidade` (
  `ID_lumin` int(11) NOT NULL,
  `Luz_solar` enum('Direta','Indireta') NOT NULL,
  `Densidade` enum('Alta','Média','Baixa') NOT NULL,
  `Ambiente` enum('Interior','Exterior','Interior e Exterior') NOT NULL,
  `Temp_min` tinyint(2) NOT NULL,
  `Temp_max` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `luminosidade`
--

INSERT INTO `luminosidade` (`ID_lumin`, `Luz_solar`, `Densidade`, `Ambiente`, `Temp_min`, `Temp_max`) VALUES
(1, 'Direta', 'Alta', 'Interior e Exterior', 0, 18),
(2, 'Direta', 'Alta', 'Interior e Exterior', 13, 27),
(3, 'Indireta', 'Média', 'Interior', 16, 29),
(4, 'Indireta', 'Alta', 'Interior', 10, 30),
(5, 'Indireta', 'Média', 'Interior', 20, 27),
(6, 'Indireta', 'Média', 'Interior', 13, 25),
(7, 'Indireta', 'Média', 'Interior', 13, 25),
(8, 'Indireta', 'Média', 'Interior', 13, 25),
(9, 'Indireta', 'Baixa', 'Interior', 13, 26),
(10, 'Indireta', 'Alta', 'Interior e Exterior', 7, 30),
(11, 'Indireta', 'Alta', 'Interior', 15, 29),
(12, 'Indireta', 'Média', 'Interior', 15, 30),
(13, 'Indireta', 'Alta', 'Interior', 18, 24),
(14, 'Indireta', 'Média', 'Interior', 15, 30),
(15, 'Indireta', 'Alta', 'Interior', 10, 30),
(16, 'Direta', 'Alta', 'Interior', 10, 30),
(17, 'Direta', 'Alta', 'Exterior', 10, 28),
(18, 'Indireta', 'Alta', 'Interior', 10, 25),
(19, 'Indireta', 'Alta', 'Interior', 10, 28),
(20, 'Indireta', 'Média', 'Interior', 10, 30),
(21, 'Indireta', 'Média', 'Interior', 7, 25),
(22, 'Indireta', 'Média', 'Interior', 15, 30),
(23, 'Indireta', 'Alta', 'Interior e Exterior', 15, 29),
(24, 'Indireta', 'Alta', 'Interior', 18, 30),
(25, 'Indireta', 'Média', 'Interior', 13, 25),
(26, 'Indireta', 'Média', 'Interior', 13, 30),
(27, 'Indireta', 'Alta', 'Interior e Exterior', 7, 30),
(28, 'Indireta', 'Alta', 'Interior', 17, 27),
(29, 'Indireta', 'Alta', 'Exterior', 10, 26),
(30, 'Direta', 'Alta', 'Exterior', 10, 25),
(31, 'Indireta', 'Alta', 'Interior e Exterior', 15, 30),
(32, 'Direta', 'Alta', 'Interior e Exterior', 18, 30),
(33, 'Indireta', 'Média', 'Interior e Exterior', 13, 24),
(34, 'Direta', 'Alta', 'Exterior', 15, 30),
(35, 'Indireta', 'Média', 'Interior e Exterior', 16, 24);

-- --------------------------------------------------------

--
-- Table structure for table `plantas`
--

CREATE TABLE `plantas` (
  `ID_especie` int(11) NOT NULL,
  `Nome` char(25) NOT NULL,
  `Tipo` enum('Ornamental','Arbusto','Feto','Erva','Cacto','Suculenta') NOT NULL,
  `Toxicidade` enum('Sim','Não') NOT NULL,
  `Fertelizacao` enum('Semanal','Quinzenal','Mensal','Trimestral','Semestral','Anual') NOT NULL,
  `Propagacao` enum('Divisão da Raíz','Corte da Folha','Corte do Caule') NOT NULL,
  `Manutencao` set('1','2','3','4','5') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `plantas`
--

INSERT INTO `plantas` (`ID_especie`, `Nome`, `Tipo`, `Toxicidade`, `Fertelizacao`, `Propagacao`, `Manutencao`) VALUES
(1, 'Hedera Helix', 'Arbusto', 'Sim', 'Mensal', 'Corte do Caule', '2'),
(2, 'Aloe Vera', 'Suculenta', 'Sim', 'Anual', 'Divisão da Raíz', '1'),
(3, 'Aglaonema Zinkon', 'Ornamental', 'Sim', 'Mensal', 'Divisão da Raíz', '2'),
(4, 'Asparagus Plumosus', 'Arbusto', 'Sim', 'Mensal', 'Divisão da Raíz', '2'),
(5, 'Chamaedorea Elegans', 'Ornamental', 'Não', 'Mensal', 'Divisão da Raíz', '2'),
(6, 'Calathea Makoyana', 'Ornamental', 'Não', 'Quinzenal', 'Divisão da Raíz', '3'),
(7, 'Philodendron Wizard', 'Ornamental', 'Sim', 'Semanal', 'Corte do Caule', '1'),
(8, 'Calathea Triostar', 'Ornamental', 'Não', 'Quinzenal', 'Divisão da Raíz', '3'),
(9, 'Zamioculcas Raven', 'Ornamental', 'Sim', 'Anual', 'Divisão da Raíz', '1'),
(10, 'Tradescantia Fluminesis', 'Ornamental', 'Sim', 'Quinzenal', 'Corte do Caule', '1'),
(11, 'Dracaena Fragans', 'Ornamental', 'Sim', 'Quinzenal', 'Corte do Caule', '2'),
(12, 'Epipremnum Aureum', 'Ornamental', 'Sim', 'Mensal', 'Corte do Caule', '1'),
(13, 'Peperomia Prostata', 'Suculenta', 'Não', 'Mensal', 'Corte da Folha', '1'),
(14, 'Alocasia Cuprea', 'Ornamental', 'Sim', 'Quinzenal', 'Divisão da Raíz', '3'),
(15, 'Monstera Adansonii', 'Ornamental', 'Sim', 'Anual', 'Corte do Caule', '2'),
(16, 'Peperomia Caperata', 'Suculenta', 'Não', 'Quinzenal', 'Corte da Folha', '1'),
(17, 'Eucalyptus Gunni', 'Ornamental', 'Sim', 'Anual', 'Corte do Caule', '3'),
(18, 'Begonia Rex', 'Ornamental', 'Sim', 'Quinzenal', 'Corte da Folha', '4'),
(19, 'Begonia Maculata', 'Ornamental', 'Sim', 'Quinzenal', 'Corte da Folha', '4'),
(20, 'Monstera Deliciosa', 'Ornamental', 'Sim', 'Quinzenal', 'Corte do Caule', '2'),
(21, 'Caladium Bicolor', 'Ornamental', 'Sim', 'Mensal', 'Divisão da Raíz', '4'),
(22, 'Alocasia x Amazonica', 'Ornamental', 'Sim', 'Quinzenal', 'Divisão da Raíz', '4'),
(23, 'Dracaena Reflexa', 'Ornamental', 'Sim', 'Quinzenal', 'Corte do Caule', '2'),
(24, 'Fittonia Albivenis', 'Ornamental', 'Não', 'Mensal', 'Corte da Folha', '3'),
(25, 'Philodendron Cardinal', 'Ornamental', 'Sim', 'Semanal', 'Corte do Caule', '2'),
(26, 'Philodendron Hederaceum', 'Ornamental', 'Não', 'Semanal', 'Corte do Caule', '2'),
(27, 'Tradescantia Bubblegum', 'Ornamental', 'Sim', 'Quinzenal', 'Corte do Caule', '2'),
(28, 'Syngonium Podophylum', 'Ornamental', 'Sim', 'Anual', 'Corte do Caule', '3'),
(29, 'Senecio Rowleyanus', 'Suculenta', 'Sim', 'Anual', 'Corte do Caule', '3'),
(30, 'Rosa x odorata', 'Ornamental', 'Não', 'Quinzenal', 'Corte do Caule', '4'),
(31, 'Ceropegia Woddi', 'Suculenta', 'Não', 'Quinzenal', 'Corte do Caule', '2'),
(32, 'Lavandula Augustifolia', 'Ornamental', 'Sim', 'Anual', 'Corte do Caule', '3'),
(33, 'Ficus Pumila', 'Feto', 'Sim', 'Mensal', 'Corte da Folha', '3'),
(34, 'Dryopteris Flix-mas', 'Feto', 'Sim', 'Anual', 'Divisão da Raíz', '3'),
(35, 'Adiantum Capillus-Veneris', 'Feto', 'Sim', 'Anual', 'Divisão da Raíz', '4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agua`
--
ALTER TABLE `agua`
  ADD PRIMARY KEY (`ID_agua`),
  ADD KEY `ID` (`ID_agua`) USING BTREE;

--
-- Indexes for table `luminosidade`
--
ALTER TABLE `luminosidade`
  ADD PRIMARY KEY (`ID_lumin`) USING BTREE,
  ADD KEY `ID` (`ID_lumin`) USING BTREE;

--
-- Indexes for table `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`ID_especie`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agua`
--
ALTER TABLE `agua`
  MODIFY `ID_agua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT for table `luminosidade`
--
ALTER TABLE `luminosidade`
  MODIFY `ID_lumin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT for table `plantas`
--
ALTER TABLE `plantas`
  MODIFY `ID_especie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agua`
--
ALTER TABLE `agua`
  ADD CONSTRAINT `agua_ibfk_1` FOREIGN KEY (`ID_agua`) REFERENCES `plantas` (`ID_especie`);

--
-- Constraints for table `luminosidade`
--
ALTER TABLE `luminosidade`
  ADD CONSTRAINT `luminosidade_ibfk_1` FOREIGN KEY (`ID_lumin`) REFERENCES `plantas` (`ID_especie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
