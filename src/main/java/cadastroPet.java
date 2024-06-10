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
    private JComboBox<String> campoEspecie;
    private JTextField campoRaca;
    private JTextField campoCorPelagem;
    private JTextField campoPeso;
    private JTextField campoSexo;
    private JTextField campoIdade;

    public cadastroPet() {
        setTitle("Cadastro de Animais");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Cria o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Adiciona o título
        JLabel titleLabel = new JLabel("Cadastro do Pet");
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
        campoNomeAnimal = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoNomeAnimal, gbc);

        // Linha Proprietario
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CPF Tutor"), gbc);
        campoCpfTutor = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoCpfTutor, gbc);

        // Linha especie
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Espécie"), gbc);
        campoEspecie = new JComboBox<>(new String[] { "Gato", "Cachorro" });
        gbc.gridx = 1;
        panel.add(campoEspecie, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Raça"), gbc);
        campoRaca = new JTextField(10);
        gbc.gridx = 3;
        panel.add(campoRaca, gbc);

        // Linha pelagem
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Cor/Pelagem"), gbc);
        campoCorPelagem = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoCorPelagem, gbc);

        // Linha peso
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Peso (Kg)"), gbc);
        campoPeso = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoPeso, gbc);

        // Linha sexo
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Sexo"), gbc);
        campoSexo = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoSexo, gbc);

        // Linha idade
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Idade"), gbc);
        campoIdade = new JTextField(10);
        gbc.gridx = 1;
        panel.add(campoIdade, gbc);

        // Linha 11 - Botões
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
                String especie = (String) campoEspecie.getSelectedItem();
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
                JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso!");
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
