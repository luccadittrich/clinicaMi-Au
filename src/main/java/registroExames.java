// package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class registroExames extends JFrame {
    private JTextField CPFField;
    private JTextArea resultadosArea;

    public registroExames() {
        // Configurações da janela
        setTitle("Tela de Exames");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // Painel de entrada de dados
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new GridLayout(2, 2));

        painelDados.add(new JLabel("CPF:"));
        CPFField = new JTextField();
        painelDados.add(CPFField);

        painel.add(painelDados, BorderLayout.NORTH);

        // Resultados
        resultadosArea = new JTextArea();
        resultadosArea.setEditable(false);
        painel.add(new JScrollPane(resultadosArea), BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(2, 1));

        // Botão de submissão
        JButton botaoSubmeter = new JButton("Submeter");
        botaoSubmeter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String cpf = CPFField.getText();
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "SELECT \r\n" + //
                                                "    TBL_FICHA.ID,\r\n" + //
                                                "    TBL_FICHA.NOME,\r\n" + //
                                                "    TBL_EXAMES.EXAME\r\n" + //
                                                "FROM \r\n" + //
                                                "    TBL_FICHA\r\n" + //
                                                "INNER JOIN \r\n" + //
                                                "    TBL_EXAMES ON TBL_FICHA.ID = TBL_EXAMES.ANIMAL_ID\r\n" + //
                                                "WHERE \r\n" + //
                                                "    TBL_FICHA.CPF_DONO = ?";
                    try (PreparedStatement statement = conn.prepareStatement(sql)) {
                        statement.setString(1, cpf);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                String resultMessage = "ID: " + resultSet.getString("ID") + "\n" +
                                                       "Nome: " + resultSet.getString("NOME") + "\n" +
                                                       "Exame: " + resultSet.getString("EXAME");
                                JOptionPane.showMessageDialog(registroExames.this, resultMessage, "Resultados", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(registroExames.this, "CPF não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Botão Voltar
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new TelaPrincipal().setVisible(true);
                dispose();
            }
        });

        painelBotoes.add(botaoSubmeter);
        painelBotoes.add(botaoVoltar);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        // Adiciona o painel à janela
        add(painel);

        // Torna a janela visível
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new registroExames().setVisible(true);
            }
        });
    }
}
