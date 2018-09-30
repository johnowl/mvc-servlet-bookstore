CREATE TABLE Trofeus(
    codigo SERIAL NOT NULL,
    codigoUsuario INT NOT NULL,
    estilo VARCHAR(100) NOT NULL,
    CONSTRAINT PK_TROFEUS PRIMARY KEY(codigo),
    CONSTRAINT FK_TROFEUS_USUARIOS FOREIGN KEY(codigoUsuario) REFERENCES Usuarios(codigo) ON DELETE CASCADE
);