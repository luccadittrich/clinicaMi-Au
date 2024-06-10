package main.java;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField cpfField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        cpfField = new JTextField(11);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Senha:"));
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }

    private void login() {
        String cpf = cpfField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_veterinaria", "lucca", "Mbt2019@")) {
            String sql = "SELECT * FROM TBL_RECEPCIONISTA WHERE cpf = ? AND senha = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Login bem-sucedido
                new TelaPrincipal().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "CPF ou senha inválidos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
