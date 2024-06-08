package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Centraliza a janela na tela
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Tela Principal");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 15));
        topPanel.add(titleLabel);

        // Criação dos botões com tamanho padronizado
        JButton botaoExame = createStandardButton("Consultar Exames");
        JButton btnAbrirProntuario = createStandardButton("Abrir Prontuário");

        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(botaoExame);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        btnAbrirProntuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProntuarioAnimal().setVisible(true);
                dispose();
            }
        });

        botaoExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new ConsultaExamesFrame().setVisible(true);
                // JOptionPane.showMessageDialog(null, "Consultar Exames não implementado.");
                dispose();
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnAbrirProntuario);
    }

    // Método para criar botões padronizados
    private JButton createStandardButton(String text) {
        JButton button = new JButton(text);
        Dimension buttonSize = new Dimension(200, 50); // Tamanho padrão para todos os botões
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
}
