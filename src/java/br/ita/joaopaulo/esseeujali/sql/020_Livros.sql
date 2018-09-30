CREATE TABLE Livros(
    codigo SERIAL NOT NULL,
    titulo VARCHAR(100) NOT NULL,    
    autor VARCHAR(100) NOT NULL,
    foto VARCHAR(100) NOT NULL,
    estilo VARCHAR(100) NOT NULL,
    paginas INT NOT NULL,
    CONSTRAINT PK_LIVROS PRIMARY KEY(codigo)
);






