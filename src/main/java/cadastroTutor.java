package main.java;

import java.awt.*;
import javax.swing.*;

public class cadastroTutor {

    public static void main(String[] args) {
        // Cria a frame principal
        JFrame frame = new JFrame("Cadastro de Tutor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Cria o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Adiciona o título
        JLabel titleLabel = new JLabel(" Cadastro do Tutor");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adiciona os componentes ao painel
        gbc.anchor = GridBagConstraints.WEST;

        // Linha Nome completo
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel("Sobrenome"), gbc);
        gbc.gridx = 3;
        panel.add(new JTextField(10), gbc);

        // Linha Documentos
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CPF"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(new JLabel("RG"), gbc);
        gbc.gridx = 3;
        panel.add(new JTextField(10), gbc);

        // Linha Registro
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("E-mail"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);

        // Linha especie
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Contato"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);

        // Linha pelagem
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Idade"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("SEXO"), gbc);
        gbc.gridx = 3;
        panel.add(new JComboBox<>(new String[] { "Homem", "Mulher", "Campo vazio" }), gbc);

        // Linha peso
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Endereço"), gbc);
        gbc.gridx = 3;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("CEP"), gbc);
        gbc.gridx = 3;
        panel.add(new JTextField(10), gbc);

        // Linha 11 - Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("CADASTRAR"));

        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Adiciona o painel à frame
        frame.add(panel);

        // Torna a frame visível
        frame.setVisible(true);
    }
}
