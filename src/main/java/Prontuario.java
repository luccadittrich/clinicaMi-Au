package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Prontuario extends JFrame {

    private JTextField campoIdPaciente, campoNomePessoa, campoSexo, campoRaca, campoCorPele, campoPeso, campoCpf, campoIdade;
    private JButton btnSalvar;
    private boolean isEditing = false;

    public Prontuario() {
        setTitle("Prontuário do Paciente");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton btnVoltar = new JButton("Voltar à Tela Principal");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaPrincipal().setVisible(true);
                dispose();
            }
        });

        JButton btnNovoPaciente = new JButton("Novo");
        btnNovoPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarNovoPaciente();
            }
        });

        JButton botaoEditar = new JButton("Editar");
        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarEdicao();
            }
        });

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarPaciente();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(btnVoltar, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(btnNovoPaciente, gbc);

        gbc.gridwidth = 1;

        JLabel labelIdPaciente = new JLabel("ID do Paciente:");
        labelIdPaciente.setForeground(Color.BLACK);
        campoIdPaciente = new JTextField(20);
        campoIdPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                preencherInformacoesPaciente();
            }
        });

        // nome
        JLabel labelNomePessoa = new JLabel("Nome Completo:");
        labelNomePessoa.setForeground(Color.BLACK);
        campoNomePessoa = new JTextField(20);
        campoNomePessoa.setEditable(false);

        // sexo
        JLabel labelSexo = new JLabel("Sexo:");
        labelSexo.setForeground(Color.BLACK);
        campoSexo = new JTextField(20);
        campoSexo.setEditable(false);

        // raca
        JLabel labelRaca = new JLabel("Estado civil:");
        labelRaca.setForeground(Color.BLACK);
        campoRaca = new JTextField(20);
        campoRaca.setEditable(false);

        JLabel labelCorPele = new JLabel("Nacionalidade:");
        labelCorPele.setForeground(Color.BLACK);
        campoCorPele = new JTextField(20);
        campoCorPele.setEditable(false);

        JLabel labelPeso = new JLabel("Peso:");
        labelPeso.setForeground(Color.BLACK);
        campoPeso = new JTextField(20);
        campoPeso.setEditable(false);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setForeground(Color.BLACK);
        campoCpf = new JTextField(20);
        campoCpf.setEditable(false);

        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setForeground(Color.BLACK);
        campoIdade = new JTextField(20);
        campoIdade.setEditable(false);

        addComponent(gbc, labelIdPaciente, 0, 1);
        addComponent(gbc, campoIdPaciente, 1, 1);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(botaoEditar, gbc);

        addComponent(gbc, labelNomePessoa, 0, 2);
        addComponent(gbc, campoNomePessoa, 1, 2);
        addComponent(gbc, labelSexo, 0, 3);
        addComponent(gbc, campoSexo, 1, 3);
        addComponent(gbc, labelRaca, 0, 4);
        addComponent(gbc, campoRaca, 1, 4);
        addComponent(gbc, labelCorPele, 0, 5);
        addComponent(gbc, campoCorPele, 1, 5);
        addComponent(gbc, labelPeso, 0, 6);
        addComponent(gbc, campoPeso, 1, 6);
        addComponent(gbc, labelCpf, 0, 7);
        addComponent(gbc, campoCpf, 1, 7);
        addComponent(gbc, labelIdade, 0, 8);
        addComponent(gbc, campoIdade, 1, 8);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(btnSalvar, gbc);
    }

    private void addComponent(GridBagConstraints gbc, Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    private void preencherInformacoesPaciente() {
        String id = campoIdPaciente.getText().trim();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM TBL_FICHA WHERE ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        campoNomePessoa.setText(rs.getString("NOME"));
                        campoSexo.setText(rs.getString("SEXO"));
                        campoRaca.setText(rs.getString("RACA"));
                        campoCorPele.setText(rs.getString("COR_PELE"));
                        campoPeso.setText(rs.getString("PESO"));
                        campoCpf.setText(rs.getString("CPF"));
                        campoIdade.setText(calculateAge(rs.getDate("DATA_NASCIMENTO")));
                    } else {
                        JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados do paciente.");
        }
    }

    private String calculateAge(java.sql.Date birthDate) {
        java.util.Date currentDate = new java.util.Date();
        long ageInMillis = currentDate.getTime() - birthDate.getTime();
        long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
        return String.valueOf(ageInYears);
    }

    private void criarNovoPaciente() {
        campoIdPaciente.setText("");
        campoNomePessoa.setText("");
        campoSexo.setText("");
        campoRaca.setText("");
        campoCorPele.setText("");
        campoPeso.setText("");
        campoCpf.setText("");
        campoIdade.setText("");

        campoNomePessoa.setEditable(true);
        campoSexo.setEditable(true);
        campoRaca.setEditable(true);
        campoCorPele.setEditable(true);
        campoPeso.setEditable(true);
        campoCpf.setEditable(true);
        campoIdade.setEditable(true);

        isEditing = false;
    }

    private void habilitarEdicao() {
        campoNomePessoa.setEditable(true);
        campoSexo.setEditable(true);
        campoRaca.setEditable(true);
        campoCorPele.setEditable(true);
        campoPeso.setEditable(true);
        campoCpf.setEditable(true);
        campoIdade.setEditable(true);

        isEditing = true;
    }

    private void salvarPaciente() {
        String id = campoIdPaciente.getText().trim();
        String nomePessoa = campoNomePessoa.getText().trim();
        String sexo = campoSexo.getText().trim();
        String raca = campoRaca.getText().trim();
        String corPele = campoCorPele.getText().trim();
        String peso = campoPeso.getText().trim();
        String cpf = campoCpf.getText().trim();
        int idade = Integer.parseInt(campoIdade.getText().trim());

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (isEditing) {
                String sql = "UPDATE TBL_FICHA SET NOME = ?, SEXO = ?, RACA = ?, COR_PELE = ?, PESO = ?, CPF = ?, DATA_NASCIMENTO = ? WHERE ID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, nomePessoa);
                    stmt.setString(2, sexo);
                    stmt.setString(3, raca);
                    stmt.setString(4, corPele);
                    stmt.setString(5, peso);
                    stmt.setString(6, cpf);
                    java.sql.Date dataNascimento = new java.sql.Date(System.currentTimeMillis() - (long) idade * 365 * 24 * 60 * 60 * 1000);
                    stmt.setDate(7, dataNascimento);
                    stmt.setString(8, id);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!");
                }
            } else {
                String sql = "INSERT INTO TBL_FICHA (ID, NOME, SEXO, RACA, COR_PELE, PESO, CPF, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, id);
                    stmt.setString(2, nomePessoa);
                    stmt.setString(3, sexo);
                    stmt.setString(4, raca);
                    stmt.setString(5, corPele);
                    stmt.setString(6, peso);
                    stmt.setString(7, cpf);
                    java.sql.Date dataNascimento = new java.sql.Date(System.currentTimeMillis() - (long) idade * 365 * 24 * 60 * 60 * 1000);
                    stmt.setDate(8, dataNascimento);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Novo paciente salvo com sucesso!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar paciente.");
        }
    }

    public static void main(String[] args) {
        new Prontuario();
    }
}
