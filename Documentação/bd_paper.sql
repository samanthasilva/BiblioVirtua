-- Criação da base de dados 
-- SGBD: Mysql

-- Criação do owner paper
CREATE DATABASE IF NOT EXISTS paper;


-- Utilizando o owner paper para fazer a carga incial de dados
USE paper;


-- Criação da tabela usuário `tb_usuario`
CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Para criação de usuário padrão defaul : Usuário: 123456 Senha: 123456
INSERT INTO `tb_usuario` (`matricula`, `nome`, `perfil`, `senha`, `email`)
VALUES (123456, 'BiblioVirtua Adm', '2', '$2a$10$MgXLgxj8JDMD9/HL15Wltu53Csvkr6k0lIVYLoYZ2qyTRtKVap4BK', 'samanthinhasilva@gmail.com');

