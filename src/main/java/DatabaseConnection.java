package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configurações do banco de dados
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_veterinaria";
    private static final String DB_USER = "lucca";
    private static final String DB_PASSWORD = "Mbt2019@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
