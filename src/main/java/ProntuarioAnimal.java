// package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class ProntuarioAnimal extends JFrame {

    private JTextField campoIdAnimal, campoNome, campoEspecie, campoRaca, campoCor, campoPeso, campoCpfTutor, campoIdade;
    private JButton btnSalvar;
    private boolean isEditing = false;

    public ProntuarioAnimal() {
        setTitle("Ficha");
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

        JButton btnNovoAnimal = new JButton("Novo");
        btnNovoAnimal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarNovoAnimal();
            }
        });

        JButton botaoEditar = new JButton("Editar");
        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarEdicao();
            }
        });

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });


        botaoEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarEdicao();
            }
        });

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarAnimal();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(btnVoltar, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(btnNovoAnimal, gbc);

        gbc.gridwidth = 1;

        JLabel labelIdAnimal = new JLabel("ID Paciente:");
        labelIdAnimal.setForeground(Color.BLACK);
        campoIdAnimal = new JTextField(20);
        campoIdAnimal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                preencherInformacoesAnimal();
            }
        });

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setForeground(Color.BLACK);
        campoNome = new JTextField(20);
        campoNome.setEditable(false);

        JLabel labelEspecie = new JLabel("E-mail:");
        labelEspecie.setForeground(Color.BLACK);
        campoEspecie = new JTextField(20);
        campoEspecie.setEditable(false);

        JLabel labelRaca = new JLabel("Nacionalidade:");
        labelRaca.setForeground(Color.BLACK);
        campoRaca = new JTextField(20);
        campoRaca.setEditable(false);

        JLabel labelCor = new JLabel("Estado civil:");
        labelCor.setForeground(Color.BLACK);
        campoCor = new JTextField(20);
        campoCor.setEditable(false);

        JLabel labelPeso = new JLabel("Peso:");
        labelPeso.setForeground(Color.BLACK);
        campoPeso = new JTextField(20);
        campoPeso.setEditable(false);

        JLabel labelCpfTutor = new JLabel("CPF:");
        labelCpfTutor.setForeground(Color.BLACK);
        campoCpfTutor = new JTextField(20);
        campoCpfTutor.setEditable(false);

        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setForeground(Color.BLACK);
        campoIdade = new JTextField(20);
        campoIdade.setEditable(false);

        addComponent(gbc, labelIdAnimal, 0, 1);
        addComponent(gbc, campoIdAnimal, 1, 1);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(botaoEditar, gbc); // Botão Editar ao lado do ID do Animal


        gbc.gridx = 3; // Nova posição para o botão Excluir
        gbc.gridy = 1; // Mesma linha que o ID do Animal
        add(botaoExcluir, gbc); // Botão Excluir ao lado do Editar


        addComponent(gbc, labelNome, 0, 2);
        addComponent(gbc, campoNome, 1, 2);
        addComponent(gbc, labelEspecie, 0, 3);
        addComponent(gbc, campoEspecie, 1, 3);
        addComponent(gbc, labelRaca, 0, 4);
        addComponent(gbc, campoRaca, 1, 4);
        addComponent(gbc, labelCor, 0, 5);
        addComponent(gbc, campoCor, 1, 5);
        addComponent(gbc, labelPeso, 0, 6);
        addComponent(gbc, campoPeso, 1, 6);
        addComponent(gbc, labelCpfTutor, 0, 7);
        addComponent(gbc, campoCpfTutor, 1, 7);
        addComponent(gbc, labelIdade, 0, 8);
        addComponent(gbc, campoIdade, 1, 8);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(btnSalvar, gbc); // Botão Salvar embaixo de tudo
    }

    private void addComponent(GridBagConstraints gbc, Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }


    private void preencherInformacoesAnimal() {
        String id = campoIdAnimal.getText().trim();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM TBL_FICHA WHERE ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        campoNome.setText(rs.getString("NOME"));
                        campoEspecie.setText(rs.getString("ESPECIE"));
                        campoRaca.setText(rs.getString("RACA"));
                        campoCor.setText(rs.getString("COR_PELAGEM"));
                        campoPeso.setText(rs.getString("PESO"));
                        campoCpfTutor.setText(rs.getString("CPF_DONO"));
                        campoIdade.setText(calculateAge(rs.getDate("DATA_NASCIMENTO")));
                    } else {
                        JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados do Paciente.");
        }
    }

    private String calculateAge(java.sql.Date birthDate) {
        java.util.Date currentDate = new java.util.Date();
        long ageInMillis = currentDate.getTime() - birthDate.getTime();
        long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
        return String.valueOf(ageInYears);
    }

    private void criarNovoAnimal() {
        campoIdAnimal.setText("");
        campoNome.setText("");
        campoEspecie.setText("");
        campoRaca.setText("");
        campoCor.setText("");
        campoPeso.setText("");
        campoCpfTutor.setText("");
        campoIdade.setText("");

        campoNome.setEditable(true);
        campoEspecie.setEditable(true);
        campoRaca.setEditable(true);
        campoCor.setEditable(true);
        campoPeso.setEditable(true);
        campoCpfTutor.setEditable(true);
        campoIdade.setEditable(true);

        isEditing = false;
    }

    private void habilitarEdicao() {
        campoNome.setEditable(true);
        campoEspecie.setEditable(true);
        campoRaca.setEditable(true);
        campoCor.setEditable(true);
        campoPeso.setEditable(true);
        campoCpfTutor.setEditable(true);
        campoIdade.setEditable(true);

        isEditing = true;
    }

    private void excluir() {
        String id = campoIdAnimal.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID para excluir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {


                String sql = "DELETE FROM TBL_FICHA WHERE ID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, id);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "excluído com sucesso!");
                        limparCampos(); // Limpa os campos após a exclusão
                    } else {
                        JOptionPane.showMessageDialog(this, "não encontrado.");
                    }
                }
            } catch (Exception e) {
                System.out.println("____________________________");
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao excluir.");
            }
        }
    }

    private void limparCampos() {
        campoIdAnimal.setText("");
        campoNome.setText("");
        campoEspecie.setText("");
        campoRaca.setText("");
        campoCor.setText("");
        campoPeso.setText("");
        campoCpfTutor.setText("");
        campoIdade.setText("");

        campoNome.setEditable(false);
        campoEspecie.setEditable(false);
        campoRaca.setEditable(false);
        campoCor.setEditable(false);
        campoPeso.setEditable(false);
        campoCpfTutor.setEditable(false);
        campoIdade.setEditable(false);
    }

    private void salvarAnimal() {
        String id = campoIdAnimal.getText().trim();
        String nome = campoNome.getText().trim();
        String especie = campoEspecie.getText().trim();
        String raca = campoRaca.getText().trim();
        String cor = campoCor.getText().trim();
        String peso = campoPeso.getText().trim();
        String cpfTutor = campoCpfTutor.getText().trim();
        int idade = Integer.parseInt(campoIdade.getText().trim());

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (isEditing) {
                String sql = "UPDATE TBL_FICHA SET NOME = ?, ESPECIE = ?, RACA = ?, COR_PELAGEM = ?, PESO = ?, CPF_DONO = ?, DATA_NASCIMENTO = ? WHERE ID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, nome);
                    stmt.setString(2, especie);
                    stmt.setString(3, raca);
                    stmt.setString(4, cor);
                    stmt.setString(5, peso);
                    stmt.setString(6, cpfTutor);
                    java.sql.Date dataNascimento = new java.sql.Date(System.currentTimeMillis() - (long)idade * 365 * 24 * 60 * 60 * 1000);
                    stmt.setDate(7, dataNascimento);
                    stmt.setString(8, id);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!");
                }
            } else {
                String sql = "INSERT INTO TBL_FICHA (ID, NOME, ESPECIE, RACA, COR_PELAGEM, PESO, CPF_DONO, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, id);
                    stmt.setString(2, nome);
                    stmt.setString(3, especie);
                    stmt.setString(4, raca);
                    stmt.setString(5, cor);
                    stmt.setString(6, peso);
                    stmt.setString(7, cpfTutor);
                    java.sql.Date dataNascimento = new java.sql.Date(System.currentTimeMillis() - (long)idade * 365 * 24 * 60 * 60 * 1000);
                    stmt.setDate(8, dataNascimento);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados do Paciente.");
        }

        campoNome.setEditable(false);
        campoEspecie.setEditable(false);
        campoRaca.setEditable(false);
        campoCor.setEditable(false);
        campoPeso.setEditable(false);
        campoCpfTutor.setEditable(false);
        campoIdade.setEditable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProntuarioAnimal();
            }
        });
    }
}
