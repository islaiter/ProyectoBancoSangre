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




