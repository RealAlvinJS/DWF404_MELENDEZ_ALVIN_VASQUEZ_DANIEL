CREATE DATABASE desafio_1_teorico;

use desafio_1_teorico;

CREATE TABLE medicamento (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);
