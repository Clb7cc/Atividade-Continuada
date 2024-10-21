package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;

public class TelaPrograma extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaPrograma() {
        setTitle("Sistema de Gestão de Ações, Entidades e Títulos de Dívida");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("                 Menu de Operações");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(0, 102, 0));
        gbc.gridx = 0; gbc.gridy = 0;
        painelPrincipal.add(titulo, gbc);

        JLabel subtitulo = new JLabel("Feito principalmente por Caio Lima Bezerra, e Felipe Bandeira");
        subtitulo.setFont(new Font("Arial", Font.ITALIC, 16));
        subtitulo.setForeground(new Color(0, 102, 0));
        gbc.gridy = 1;
        painelPrincipal.add(subtitulo, gbc);

        JButton btnTelaAcao = criarBotao("Tela de Gestão de Ações", new Color(0, 153, 0));
        JButton btnTelaEntidadeOperadora = criarBotao("Tela de Gestão de Entidades Operadoras", new Color(0, 153, 0));
        JButton btnTelaTituloDivida = criarBotao("Tela de Gestão de Títulos de Dívida", new Color(0, 153, 0));
        JButton btnTelaOperacao = criarBotao("Tela de Gestão de Operações", new Color(0, 153, 0));
        JButton btnSair = criarBotao("Sair", new Color(0, 153, 0));

        gbc.gridy = 2; painelPrincipal.add(btnTelaAcao, gbc);
        gbc.gridy = 3; painelPrincipal.add(btnTelaEntidadeOperadora, gbc);
        gbc.gridy = 4; painelPrincipal.add(btnTelaTituloDivida, gbc);
        gbc.gridy = 5; painelPrincipal.add(btnTelaOperacao, gbc);
        gbc.gridy = 6; painelPrincipal.add(btnSair, gbc);

        add(painelPrincipal);
        setVisible(true);
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        botao.addActionListener(e -> {
            switch (texto) {
                case "Tela de Gestão de Ações":
                    abrirTelaAcao();
                    break;
                case "Tela de Gestão de Entidades Operadoras":
                    abrirTelaEntidadeOperadora();
                    break;
                case "Tela de Gestão de Títulos de Dívida":
                    abrirTelaTituloDivida();
                    break;
                case "Tela de Gestão de Operações":
                    abrirTelaOperacao();
                    break;
                case "Sair":
                    System.exit(0);
                    break;
            }
        });

        return botao;
    }

    private void abrirTelaAcao() {
        new TelaAcao();
    }

    private void abrirTelaEntidadeOperadora() {
        new TelaEntidadeOperadora();
    }

    private void abrirTelaTituloDivida() {
        new TelaTituloDivida();
    }

    private void abrirTelaOperacao() {
        new TelaOperacao();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrograma::new);
    }
}
