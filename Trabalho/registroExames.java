package Trabalho;

import javax.swing.*;
import java.awt.*;

public class registroExames {
    public static void main(String[] args) {
        // frame principal
        JFrame frame = new JFrame("Resultados de Exames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        // Painel superior com o botão de voltar, título e botão de sair
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        panel.add(topPanel, BorderLayout.NORTH);

        JButton backButton = new JButton("Voltar à página principal");
        topPanel.add(backButton, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Resultados de Exames", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titlePanel.add(titleLabel);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Sair");
        topPanel.add(exitButton, BorderLayout.EAST);

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(inputPanel, BorderLayout.CENTER);

        inputPanel.add(new JLabel("CPF"));
        JTextField cpfField = new JTextField(10);
        inputPanel.add(cpfField);

        inputPanel.add(new JLabel("Período"));
        JComboBox<String> periodoCombo = new JComboBox<>(
                new String[] { "Todos os períodos", "Últimos 7 dias", "Último mês", "Último ano" });
        inputPanel.add(periodoCombo);

        JButton searchButton = new JButton("Pesquisar");
        inputPanel.add(searchButton);

        // Painel inferior com a lista de fichas
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(createFichaPanel("19403629"));
        bottomPanel.add(createFichaPanel("31329481"));
        bottomPanel.add(createFichaPanel("13194810"));
        bottomPanel.add(createFichaPanel("24615875"));

        // Exibindo a janela
        frame.setVisible(true);
    }

    private static JPanel createFichaPanel(String fichaNumber) {
        JPanel fichaPanel = new JPanel();
        fichaPanel.setLayout(new BorderLayout());
        fichaPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        fichaPanel.setPreferredSize(new Dimension(600, 90));

        JButton expandButton = new JButton("▼");
        fichaPanel.add(expandButton, BorderLayout.WEST);

        JLabel fichaLabel = new JLabel("Ficha " + fichaNumber);
        fichaPanel.add(fichaLabel, BorderLayout.CENTER);

        JButton examsButton = new JButton("Todos os exames");
        fichaPanel.add(examsButton, BorderLayout.EAST);

        return fichaPanel;
    }
}
