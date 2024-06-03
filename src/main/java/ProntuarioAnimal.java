package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ProntuarioAnimal extends JFrame {

    // Campos de texto e outros componentes
    private JTextField txtIdAnimal, txtNome, txtEspecie, txtRaca, txtCor, txtPeso, txtCpfTutor, txtIdade;

    // Simulação de banco de dados de animais
    private Map<String, Animal> animaisDb;

    public ProntuarioAnimal() {
        // Inicializa o "banco de dados"
        initDatabase();

        // Configurações da janela
        setTitle("Ficha");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia a janela em tela cheia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Criação dos componentes
        initComponents();

        // Torna a janela visível
        setVisible(true);
    }

    private void initDatabase() {
        animaisDb = new HashMap<>();
        animaisDb.put("1", new Animal(1, "Bidu", "Cachorro", "Labrador", "Marrom", "123456789", 30, 5));
        animaisDb.put("2", new Animal(2, "Garfield", "Gato", "Persa", "Laranja", "987654321", 5, 3));
        animaisDb.put("3", new Animal(3, "Nemo", "Peixe", "Palhaço", "Laranja e Branco", "123123123", 1, 2));
        // Adicione mais animais conforme necessário
    }

    private void initComponents() {
        // Inicializa os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Fundo azul
        //getContentPane().setBackground(new Color(33, 72, 193));

        // Imagem no topo
        JLabel lblImagem = new JLabel();
        lblImagem.setPreferredSize(new Dimension(200, 200)); // Ajusta o tamanho do JLabel da imagem

        ImageIcon imageIcon = new ImageIcon("img/4.png"); // Substitua pelo caminho das suas imagens
        lblImagem.setIcon(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(lblImagem, gbc);

        gbc.gridwidth = 1; // Reseta o gridwidth

        // Informações Básicas do Animal
        JLabel lblIdAnimal = new JLabel("ID do Animal:");
        lblIdAnimal.setForeground(Color.BLACK);
        txtIdAnimal = new JTextField(20);
        txtIdAnimal.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                preencherInformacoesAnimal();
            }
        });

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.BLACK);
        txtNome = new JTextField(20);
        txtNome.setEditable(false);

        JLabel lblEspecie = new JLabel("Espécie:");
        lblEspecie.setForeground(Color.BLACK);
        txtEspecie = new JTextField(20);
        txtEspecie.setEditable(false);

        JLabel lblRaca = new JLabel("Raça:");
        lblRaca.setForeground(Color.BLACK);
        txtRaca = new JTextField(20);
        txtRaca.setEditable(false);

        JLabel lblCor = new JLabel("Cor/Pelagem:");
        lblCor.setForeground(Color.BLACK);
        txtCor = new JTextField(20);
        txtCor.setEditable(false);

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setForeground(Color.BLACK);
        txtPeso = new JTextField(20);
        txtPeso.setEditable(false);

        JLabel lblCpfTutor = new JLabel("CPF do Tutor:");
        lblCpfTutor.setForeground(Color.BLACK);
        txtCpfTutor = new JTextField(20);
        txtCpfTutor.setEditable(false);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setForeground(Color.BLACK);
        txtIdade = new JTextField(20);
        txtIdade.setEditable(false);

        // Adiciona os componentes ao layout
        addComponent(gbc, lblIdAnimal, 0, 1);
        addComponent(gbc, txtIdAnimal, 1, 1);
        addComponent(gbc, lblNome, 0, 2);
        addComponent(gbc, txtNome, 1, 2);
        addComponent(gbc, lblEspecie, 0, 3);
        addComponent(gbc, txtEspecie, 1, 3);
        addComponent(gbc, lblRaca, 0, 4);
        addComponent(gbc, txtRaca, 1, 4);
        addComponent(gbc, lblCor, 0, 5);
        addComponent(gbc, txtCor, 1, 5);
        addComponent(gbc, lblPeso, 0, 6);
        addComponent(gbc, txtPeso, 1, 6);
        addComponent(gbc, lblCpfTutor, 0, 7);
        addComponent(gbc, txtCpfTutor, 1, 7);
        addComponent(gbc, lblIdade, 0, 8);
        addComponent(gbc, txtIdade, 1, 8);

        pack();
    }

    private void addComponent(GridBagConstraints gbc, Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    private void preencherInformacoesAnimal() {
        String id = txtIdAnimal.getText().trim();
        Animal animal = animaisDb.get(id);

        if (animal != null) {
            txtNome.setText(animal.getNome());
            txtEspecie.setText(animal.getEspecie());
            txtRaca.setText(animal.getRaca());
            txtCor.setText(animal.getCor());
            txtPeso.setText(String.valueOf(animal.getPeso()));
            txtCpfTutor.setText(animal.getCpfTutor());
            txtIdade.setText(String.valueOf(animal.getIdade()));
        }
    }

    public static void main(String[] args) {
        new ProntuarioAnimal();
    }
}

class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private String cor;
    private String cpfTutor;
    private int peso;
    private int idade;

    public Animal(int id, String nome, String especie, String raca, String cor, String cpfTutor, int peso, int idade) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.cpfTutor = cpfTutor;
        this.peso = peso;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public String getCor() {
        return cor;
    }

    public String getCpfTutor() {
        return cpfTutor;
    }

    public int getPeso() {
        return peso;
    }

    public int getIdade() {
        return idade;
    }
}
