CREATE DATABASE IF NOT EXISTS fincontrol;

USE fincontrol;

CREATE TABLE IF NOT EXISTS movimentacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    categoria VARCHAR(100),
    data_movimentacao DATE NOT NULL
);