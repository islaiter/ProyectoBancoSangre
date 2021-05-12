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

INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29540094K', 'Luis Pérez', 'Niña de la Alfalfa', '41009', 'Sevilla', '2000-04-04', 'ljperezperez@safareyes.es', 638337341, 'AB', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29540224G', 'Jose Jaramago', 'Calle Primavera', '41009', 'Sevilla', '2003-07-04', 'jjaramago@gmail.es', 677837342, 'A', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29640284L', 'Juan Soto', 'Calle Otoño', '41009', 'Sevilla', '1997-07-01', 'jsoto@gmail.es', 677837343, '0', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29540334F', 'Juan Aguilar', 'Calle Invierno', '41009', 'Sevilla', '1994-09-14', 'jaguilar@gmail.es', 677837344, 'A', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29540794D', 'David Dominguez', 'Calle Verano', '41009', 'Sevilla', '1988-07-18', 'ddc@gmail.es', 677837345, 'A', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29340964A', 'Andrea Colinas', 'Calle Diamante', '41009', 'Sevilla', '2000-01-15', 'acol@gmail.es', 677837346, 'B', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29240444H', 'Manuel Nogues', 'Calle Bellavista', '41009', 'Sevilla', '1968-08-24', 'sulfatoshino@gmail.es', 677837347, '0', '-');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29540714O', 'Inés Rosales', 'Calle Fedria', '41009', 'Sevilla', '1977-06-04', 'iro@gmail.es', 677837348, 'B', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('28822789V', 'Salvador Vázquez', 'Calle Dr.Royo', '41009', 'Sevilla', '1986-08-11', 'espegom@gmail.es', 677837349, '0', '+');
INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('29140654D', 'Marina Tarín', 'Calle Agata', '41009', 'Sevilla', '1992-06-20', 'maritari@gmail.es', 677837340, 'B', '+');


