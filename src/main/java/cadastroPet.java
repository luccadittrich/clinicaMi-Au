package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class cadastroPet extends JFrame {

    private JTextField campoNomeAnimal;
    private JTextField campoCpfTutor;
    private JTextField campoEspecie;
    private JTextField campoRaca;
    private JTextField campoCorPelagem;
    private JTextField campoPeso;
    private JTextField campoSexo;
    private JTextField campoIdade;

    public cadastroPet() {
        setTitle("Cadastro de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Criar o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Adicionar o título
        JLabel titleLabel = new JLabel("Cadastro do Paciente");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adicionar os componentes ao painel
        gbc.anchor = GridBagConstraints.WEST;

        //  Animal
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome"), gbc);
        campoNomeAnimal = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoNomeAnimal, gbc);

        //  Proprietario
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CPF Tutor"), gbc);
        campoCpfTutor = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoCpfTutor, gbc);

        //  especie
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("E-mail"), gbc);
        campoEspecie = new JTextField(30);
        gbc.gridx = 1;
        panel.add(campoEspecie, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Nacionalidade"), gbc);
        campoRaca = new JTextField(10);
        gbc.gridx = 3;
        panel.add(campoRaca, gbc);

        //  pelagem
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Estado civil"), gbc);
        campoCorPelagem = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoCorPelagem, gbc);

        //  peso
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Peso (Kg)"), gbc);
        campoPeso = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoPeso, gbc);

        //  sexo
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Sexo"), gbc);
        campoSexo = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoSexo, gbc);

        //  idade
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Idade"), gbc);
        campoIdade = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoIdade, gbc);

        //  Botões
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
                String nomeAnimal = campoNomeAnimal.getText().trim();
                String cpfTutor = campoCpfTutor.getText().trim();
                String especie = (String) campoEspecie.getText().trim();
                String raca = campoRaca.getText().trim();
                String corPelagem = campoCorPelagem.getText().trim();
                String peso = campoPeso.getText().trim();
                char sexo = campoSexo.getText().trim().charAt(0);
                int idade = Integer.parseInt(campoIdade.getText().trim());

                salvarNovoAnimal(nomeAnimal, cpfTutor, especie, raca, corPelagem, peso, sexo, idade);
            }
        });
    }

    private void salvarNovoAnimal(String nome, String cpfTutor, String especie, String raca, String corPelagem, String peso, char  sexo, int idade) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO TBL_FICHA (NOME, CPF_DONO, ESPECIE, RACA, COR_PELAGEM, PESO, SEXO, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, cpfTutor);
                stmt.setString(3, especie);
                stmt.setString(4, raca);
                stmt.setString(5, corPelagem);
                stmt.setString(6, peso);
                stmt.setString(7, String.valueOf(sexo));
                java.sql.Date dataNascimento = new java.sql.Date(System.currentTimeMillis() - (long)idade * 365 * 24 * 60 * 60 * 1000);
                stmt.setDate(8, dataNascimento);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar novo animal.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new cadastroPet().setVisible(true);
            }
        });
    }
}
