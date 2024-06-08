package Trabalho;

import javax.swing.*;
import java.awt.*;

public class cadastroPet {

    public static void main(String[] args) {
        // Cria a frame principal
        JFrame frame = new JFrame("Cadastro de Animais");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Cria o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Adiciona o título
        JLabel titleLabel = new JLabel(" Cadastro do Pet");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adiciona os componentes ao painel
        gbc.anchor = GridBagConstraints.WEST;

        // Linha Animal
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome do Animal"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);

        // Linha Proprietario
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CPF Tutor"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);

        // Linha Registro
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Registro"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);

        // Linha especie
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Espécie"), gbc);
        gbc.gridx = 1;
        panel.add(new JComboBox<>(new String[] { "Gato", "Cachorro" }), gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Raça"), gbc);
        gbc.gridx = 3;
        panel.add(new JTextField(10), gbc);

        // Linha pelagem
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Cor/Pelagem"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("SEXO"), gbc);
        gbc.gridx = 3;
        panel.add(new JComboBox<>(new String[] { "Macho", "Fêmea" }), gbc);

        // Linha peso
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Peso (Kg)"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Porte"), gbc);
        gbc.gridx = 3;
        panel.add(new JComboBox<>(new String[] { "Pequeno", "Médio", "Grande" }), gbc);

        // Linha nascimento
        gbc.gridx = 0;
        gbc.gridy = 15;
        panel.add(new JLabel("Idade"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(10), gbc);

        // Linha 11 - Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("CADASTRAR"));

        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Adiciona o painel à frame
        frame.add(panel);

        // Torna a frame visível
        frame.setVisible(true);
    }
}
