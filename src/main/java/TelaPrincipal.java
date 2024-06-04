package main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.ProntuarioAnimal;

public class TelaPrincipal {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clínica Veterinária");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Tela Principal");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 15));
        topPanel.add(titleLabel);

        JButton botaoFicha = new JButton("Consultar Ficha");
        JButton botaoExame = new JButton("Consultar Exames");

        botaoFicha.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoExame.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.add(botaoFicha);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(botaoExame);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        JButton btnAbrirProntuario = new JButton("Abrir Prontuário");
        btnAbrirProntuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela atual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
                frame.dispose();

                // Abre a janela do Prontuário Animal
                new ProntuarioAnimal();
            }
        });

        botaoExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Consultar Exames não implementado.");
            }
        });

        frame.setVisible(true);
    }
}
