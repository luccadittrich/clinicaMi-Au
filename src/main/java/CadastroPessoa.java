package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class CadastroPessoa extends JFrame {

    private JTextField campoNomePessoa;
    private JTextField campoCpf;
    private JTextField campoRaca;
    private JTextField campoCorPele;
    private JTextField campoPeso;
    private JTextField campoSexo;
    private JTextField campoIdade;

    public CadastroPessoa() {
        setTitle("Cadastro de Pessoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Criar o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Adicionar o título
        JLabel titleLabel = new JLabel("Cadastro de Pessoa");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adicionar os componentes ao painel
        gbc.anchor = GridBagConstraints.WEST;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome Completo"), gbc);
        campoNomePessoa = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoNomePessoa, gbc);

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CPF"), gbc);
        campoCpf = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoCpf, gbc);

        // nacionalidade
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Nacionalidade"), gbc);
        campoRaca = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoRaca, gbc);

        // idade
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Idade"), gbc);
        campoCorPele = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoCorPele, gbc);

        // Peso
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Peso (Kg)"), gbc);
        campoPeso = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoPeso, gbc);

        // Sexo
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Sexo"), gbc);
        campoSexo = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoSexo, gbc);

        // Idade
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Idade"), gbc);
        campoIdade = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoIdade, gbc);

        // Botões
        JPanel buttonPanel = new JPanel();
        JButton btnCadastrar = new JButton("CADASTRAR");
        buttonPanel.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar à Tela Principal");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaPrincipal().setVisible(true);
                dispose();
            }
        });

        buttonPanel.add(btnVoltar);

        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Adiciona o painel à frame
        add(panel);

        // Evento de clique no botão Cadastrar
        btnCadastrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String nomePessoa = campoNomePessoa.getText().trim();
                String cpf = campoCpf.getText().trim();
                String raca = campoRaca.getText().trim();
                String corPele = campoCorPele.getText().trim();
                String peso = campoPeso.getText().trim();
                char sexo = campoSexo.getText().trim().charAt(0);
                int idade = Integer.parseInt(campoIdade.getText().trim());

                salvarNovaPessoa(nomePessoa, cpf, raca, corPele, peso, sexo, idade);
            }
        });
    }

    private void salvarNovaPessoa(String nome, String cpf, String raca, String corPele, String peso, char sexo, int idade) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO TBL_FICHA (NOME, CPF_DONO, ESPECIE, RACA, COR_PELAGEM, PESO, SEXO, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, cpf);
                stmt.setString(3, "Humano"); // Simula a coluna "ESPECIE" para sempre ser "Humano"
                stmt.setString(4, raca);
                stmt.setString(5, corPele);
                stmt.setString(6, peso);
                stmt.setString(7, String.valueOf(sexo));
                java.sql.Date dataNascimento = new java.sql.Date(System.currentTimeMillis() - (long)idade * 365 * 24 * 60 * 60 * 1000);
                stmt.setDate(8, dataNascimento);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar nova pessoa.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroPessoa().setVisible(true);
            }
        });
    }
}
