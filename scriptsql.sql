CREATE DATABASE Proy3TE6;

USE Proy3TE6;

CREATE TABLE DONANTES(
	DNI CHAR (9),
	Nombre VARCHAR (50),
	Direccion VARCHAR (50),
	CodPostal CHAR (5),
	Localidad VARCHAR (80),
	FechaNac DATE,
	Correo VARCHAR (50),
	Telefono CHAR (9),
	GrupoSang CHAR (2),
	FactorRH CHAR (1),
	PRIMARY KEY (DNI)
);

CREATE OR REPLACE TABLE sanitariosbanco(
	ID INT (3) AUTO_INCREMENT,
	CodSanitario INT,
	NombreSanitario VARCHAR (50),
	FechaDonacion DATE,
	Cantidad DECIMAL (8,2),
	Incidencia CHAR (2),
	DniDonante CHAR (9),
	PRIMARY KEY (ID),
	FOREIGN KEY (DniDonante) REFERENCES DONANTES (DNI)
);

CREATE TABLE COMPATIBILDAD(
	IdCompatibilidad INT AUTO_INCREMENT,
	GrupoSang CHAR (2),
	FactorRH CHAR (1),
	DonarA VARCHAR (50),
	RecibirDe VARCHAR (50)
);

INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('12345678A', 'Nombre1', 'Direccion1', '41930', 'Sevilla', '2000-01-01', 'mail1@gmail.com', 123456780, 'A', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('23456781B', 'Nombre2', 'Direccion2', '41930', 'Sevilla', '2000-01-02', 'mail2@gmail.com', 123456781, 'A', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('12345679C', 'Nombre3', 'Direccion3', '41930', 'Sevilla', '2000-01-03', 'mail3@gmail.com', 123456782, 'B', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('23456789D', 'Nombre4', 'Direccion4', '41930', 'Sevilla', '2000-01-04', 'mail4@gmail.com', 123456783, 'B', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('34567891E', 'Nombre5', 'Direccion5', '41930', 'Sevilla', '2000-01-05', 'mail5@gmail.com', 123456784, 'AB', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('45678912F', 'Nombre6', 'Direccion6', '41930', 'Sevilla', '2000-01-06', 'mail6@gmail.com', 123456785, 'AB', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('56789123G', 'Nombre7', 'Direccion7', '41930', 'Sevilla', '2000-01-07', 'mail7@gmail.com', 123456786, '0', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('67891234H', 'Nombre8', 'Direccion8', '41930', 'Sevilla', '2000-01-08', 'imail8@gmail.com', 123456787, '0', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('78912345I', 'Nombre9', 'Direccion9', '41930', 'Sevilla', '2000-01-09', 'mail9@gmail.com', 123456788, 'A', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('89123456J', 'Nombre10', 'Direccion10', '41930', 'Sevilla', '2000-01-10', 'mail10@gmail.com', 123456789, 'A', '-');


