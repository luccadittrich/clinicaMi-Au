package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class CadastroAcompanhante extends JFrame {

    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField contatoField;
    private JTextField dataNascimentoField;
    private JComboBox<String> sexoBox;
    private JTextField enderecoField;
    private JTextField cepField;

    public CadastroAcompanhante() {
        // Configura a frame principal
        setTitle("Cadastro de Acompanhante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);

        // Cria o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Adiciona o título
        JLabel titleLabel = new JLabel("Cadastro de Acompanhante");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Inicializa os campos do formulário com tamanhos maiores
        nomeField = new JTextField(20);
        sobrenomeField = new JTextField(20);
        cpfField = new JTextField(20);
        emailField = new JTextField(20);
        contatoField = new JTextField(20);
        dataNascimentoField = new JTextField(20);
        sexoBox = new JComboBox<>(new String[]{"Homem", "Mulher", "Campo vazio"});
        enderecoField = new JTextField(20);
        cepField = new JTextField(20);

        // Adiciona os componentes ao painel
        gbc.anchor = GridBagConstraints.WEST;

        // Linha Nome completo
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome"), gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel("Sobrenome"), gbc);
        gbc.gridx = 3;
        panel.add(sobrenomeField, gbc);

        // Linha Documentos
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CPF"), gbc);
        gbc.gridx = 1;
        panel.add(cpfField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;

        // Linha Registro
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("E-mail"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Linha Contato
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Contato"), gbc);
        gbc.gridx = 1;
        panel.add(contatoField, gbc);

        // Linha Data de Nascimento e Sexo
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(new JLabel("Data de Nascimento (AAAA-MM-DD)"), gbc);
        gbc.gridx = 1;
        panel.add(dataNascimentoField, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("SEXO"), gbc);
        gbc.gridx = 3;
        panel.add(sexoBox, gbc);

        // Linha Endereço e CEP
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Endereço"), gbc);
        gbc.gridx = 1;
        panel.add(enderecoField, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("CEP"), gbc);
        gbc.gridx = 3;
        panel.add(cepField, gbc);

        // Linha Botões
        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("CADASTRAR");
        JButton btnVoltar = new JButton("Voltar à Tela Principal");

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(btnVoltar);

        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Adiciona o painel à frame
        add(panel);

        // Action listener para o botão cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String sobrenome = sobrenomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String telefone = contatoField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String sexo = (String) sexoBox.getSelectedItem();
                String endereco = enderecoField.getText();
                String cep = cepField.getText();

                // Insere os dados no banco de dados
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO TBL_PESSOAS (CPF, NOME, EMAIL, DATA_NASCIMENTO, TELEFONE, ENDERECO, SEXO) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, cpf);
                    pstmt.setString(2, nome + " " + sobrenome);
                    pstmt.setString(3, email);
                    pstmt.setString(4, dataNascimento);
                    pstmt.setString(5, telefone);
                    pstmt.setString(6, endereco + ", " + cep);
                    pstmt.setString(7, sexo);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(panel, "Cadastro realizado com sucesso!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Erro ao cadastrar: " + ex.getMessage());
                }
            }
        });

        // Action listener para o botão voltar
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaPrincipal().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        // Executa o CadastroAcompanhante
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroAcompanhante().setVisible(true);
            }
        });
    }
}
