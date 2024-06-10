package main.java;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ConsultaExamesFrame extends JFrame {
    private JTextField cpfField;

    public ConsultaExamesFrame(String cpf) {
        setTitle("Consulta de Exames");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        cpfField = new JTextField(11);
        cpfField.setText(cpf);
        JButton searchButton = new JButton("Buscar Exames");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarExames();
            }
        });

        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(searchButton);

        add(panel);
    }

    private void buscarExames() {
        String cpf = cpfField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica_miau", "root", "123456")) {
            String sql = "SELECT * FROM TBL_EXAMES WHERE cpf_dono = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Exames encontrados
                StringBuilder resultados = new StringBuilder();
                do {
                    resultados.append("ID: ").append(resultSet.getInt("id"))
                              .append(", Resultado: ").append(resultSet.getString("exame")).append("\n");
                } while (resultSet.next());

                JOptionPane.showMessageDialog(this, resultados.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum exame encontrado para o CPF informado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        
    }
}
