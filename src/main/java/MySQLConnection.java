package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_veterinaria";
        String username = "teste"; // Substitua pelo seu nome de usuário
        String password = "usjt";  // Substitua pela sua senha

        try {
            // Estabelecer a conexão
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer conexão!");
            }

            // Fechar a conexão
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão: " + e.getMessage());
        }
    }
}
