
CREATE DATABASE IF NOT EXISTS tcb_bio;
USE tcb_bio;


CREATE TABLE IF NOT EXISTS cadastro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomePessoa VARCHAR(100) NOT NULL,
    nomeUsuario VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100),
    senha VARCHAR(50) NOT NULL,
    tipoUsuario VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS perfil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCadastro INT NOT NULL,
    totalAcertos INT DEFAULT 0,
    FOREIGN KEY (idCadastro) REFERENCES cadastro(id) ON DELETE CASCADE
);

-- Tabela reino
CREATE TABLE IF NOT EXISTS reino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS filo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idReino INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    FOREIGN KEY (idReino) REFERENCES reino(id) ON DELETE CASCADE
);

-- Tabela classe
CREATE TABLE IF NOT EXISTS classe (
    id_classe INT AUTO_INCREMENT PRIMARY KEY,
    id_filo INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_filo) REFERENCES filo(id) ON DELETE CASCADE
);

-- Tabela organismo
CREATE TABLE IF NOT EXISTS organismo (
    id_organismo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    id_classe INT NOT NULL,
    FOREIGN KEY (id_classe) REFERENCES classe(id_classe) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS questao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idReino INT NOT NULL,
    enunciado TEXT NOT NULL,
    FOREIGN KEY (idReino) REFERENCES reino(id) ON DELETE CASCADE
);

-- Tabela alternativa
CREATE TABLE IF NOT EXISTS alternativa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idQuestao INT NOT NULL,
    texto TEXT NOT NULL,
    correta BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (idQuestao) REFERENCES questao(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS questao_alternativa (
    id_questao INT NOT NULL,
    id_alternativa INT NOT NULL,
    PRIMARY KEY (id_questao, id_alternativa),
    FOREIGN KEY (id_questao) REFERENCES questao(id) ON DELETE CASCADE,
    FOREIGN KEY (id_alternativa) REFERENCES alternativa(id) ON DELETE CASCADE
);
