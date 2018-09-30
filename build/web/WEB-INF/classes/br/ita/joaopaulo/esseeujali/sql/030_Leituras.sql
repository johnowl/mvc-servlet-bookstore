CREATE TABLE Leituras(
    codigoLivro INT NOT NULL,
    codigoUsuario INT NOT NULL,
    dataLeitura TIMESTAMP NOT NULL,
    estilo VARCHAR(100) NOT NULL,
    paginas INT NOT NULL,
    CONSTRAINT FK_LIVRO FOREIGN KEY(codigoLivro) REFERENCES Livros(codigo) ON DELETE CASCADE,
    CONSTRAINT FK_USUARIO FOREIGN KEY(codigoUsuario) REFERENCES Usuarios(codigo) ON DELETE CASCADE
);