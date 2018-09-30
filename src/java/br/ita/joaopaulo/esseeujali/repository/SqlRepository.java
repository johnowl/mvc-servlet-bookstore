package br.ita.joaopaulo.esseeujali.repository;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public abstract class SqlRepository {

    protected static final String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    protected static final String DATABASE = "jdbc:postgresql://localhost/livros";
    protected static final String USER = "postgres";
    protected static final String PASSWORD = "changeme";

    static {
        try {
            Class.forName(DB_DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Não foi possível carregar o driver do banco de dados", e);
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, USER, PASSWORD);
    }
    
    protected Date getDataHoraAtual() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }
    
}