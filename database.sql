DROP TABLE IF EXISTS odontologos;
CREATE TABLE odontologos (ID INT AUTO_INCREMENT PRIMARY KEY,
NUMERO_MATRICULA INT,
NOMBRE varchar(100),
APELLIDO varchar(100));

INSERT INTO odontologos(numero_matricula, nombre, apellido) VALUES (497,'Nicolás','Alonso');
INSERT INTO odontologos(numero_matricula, nombre, apellido) VALUES (1246,'Veronica','Bellomio');