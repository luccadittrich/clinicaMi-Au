package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_veterinaria";
        String username = "teste"; // Substitua pelo seu nome de usuário
        String password = "usjt";  // Substitua pela sua senha

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelecer a conexão
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // if (connection != null) {
            //     System.out.println("Conexão estabelecida com sucesso!");
                
                statement = connection.createStatement();

                String show_all = "select * from tbl_pessoa";

                resultSet = statement.executeQuery(show_all);

                while (resultSet.next()){

                    int cpf = resultSet.getInt("CPF");
                    String nome = resultSet.getString("NOME");
                    String email = resultSet.getString("EMAIL");
                    String dataNascimento = resultSet.getDate("DATA_NASCIMENTO").toString();
                    int telefone = resultSet.getInt("TELEFONE");
                    String endereco = resultSet.getString("ENDERECO");

                    System.out.println("CPF: " + cpf + ", Nome: " + nome + ", Email: " + email + ", Data de Nascimento: " + dataNascimento + ", Telefone: " + telefone + ", Endereço: " + endereco);
                }



            // } else {
            //     System.out.println("Falha ao estabelecer conexão!");
            // }

            // Fechar a conexão
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão: " + e.getMessage());
        }  finally {
            // Fechar os recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }

        }
    }
}
