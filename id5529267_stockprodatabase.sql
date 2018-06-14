-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 25-Abr-2018 às 18:48
-- Versão do servidor: 10.2.12-MariaDB
-- PHP Version: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id5529267_stockprodatabase`
--
create table usuario(
    id int primary key AUTO_INCREMENT
    ,nome varchar(101)
    ,senha int
    ,dtcadastro timestamp
)

insert into usuario (nome,senha,dtcadastro)values("Teste 22",222,now())
-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE `estoque` (
  `id` int(11) NOT NULL,
  `posicao` int(11) NOT NULL,
  `fkproduto` int(11) NOT NULL,
  `dtcadastro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `historicomovimento`
--

CREATE TABLE `historicomovimento` (
  `id` int(11) NOT NULL,
  `tipomovimento` int(11) NOT NULL,
  `obs` varchar(101) COLLATE utf8_unicode_ci NOT NULL,
  `fkproduto` int(11) NOT NULL,
  `fkestoque` int(11) NOT NULL,
  `dtcadastro` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(101) COLLATE utf8_unicode_ci NOT NULL,
  `dtcadastro` datetime NOT NULL DEFAULT current_timestamp(),
  `ativo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estoque`
--
ALTER TABLE `estoque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `fkproduto` (`fkproduto`);

--
-- Indexes for table `historicomovimento`
--
ALTER TABLE `historicomovimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkproduto` (`fkproduto`),
  ADD KEY `fkestoque` (`fkestoque`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `estoque`
--
ALTER TABLE `estoque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `historicomovimento`
--
ALTER TABLE `historicomovimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`fkproduto`) REFERENCES `produto` (`id`);

--
-- Limitadores para a tabela `historicomovimento`
--
ALTER TABLE `historicomovimento`
  ADD CONSTRAINT `historicomovimento_ibfk_1` FOREIGN KEY (`fkproduto`) REFERENCES `produto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `historicomovimento_ibfk_2` FOREIGN KEY (`fkestoque`) REFERENCES `estoque` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
