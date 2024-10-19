package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicao {

    private JFrame janela;
    private JTextField campoPrimeiroNumero;
    private JTextField campoSegundoNumero;
    private JTextField campoResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	TelaAdicao app = new TelaAdicao();
                app.janela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaAdicao() {
        configurarJanela();
    }

    private void configurarJanela() {
        janela = new JFrame("Calculadora de Soma");
        janela.setBounds(100, 100, 400, 250);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.getContentPane().setLayout(new GridLayout(4, 2, 10, 10));
        janela.getContentPane().setBackground(new Color(230, 240, 255));

        JLabel labelPrimeiroNumero = new JLabel("Insira o primeiro número:");
        janela.getContentPane().add(labelPrimeiroNumero);

        campoPrimeiroNumero = new JTextField();
        janela.getContentPane().add(campoPrimeiroNumero);
        campoPrimeiroNumero.setColumns(10);

        JLabel labelSegundoNumero = new JLabel("Insira o segundo número:");
        janela.getContentPane().add(labelSegundoNumero);

        campoSegundoNumero = new JTextField();
        janela.getContentPane().add(campoSegundoNumero);
        campoSegundoNumero.setColumns(10);

        JLabel labelResultado = new JLabel("Resultado:");
        janela.getContentPane().add(labelResultado);

        campoResultado = new JTextField();
        campoResultado.setEditable(false);
        janela.getContentPane().add(campoResultado);
        campoResultado.setColumns(10);
        campoResultado.setBackground(new Color(220, 220, 220));

        JButton botaoSomar = new JButton("Somar");
        botaoSomar.setBackground(new Color(60, 180, 60));
        botaoSomar.setForeground(Color.WHITE);
        botaoSomar.setFocusPainted(false);
        botaoSomar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numero1 = Double.parseDouble(campoPrimeiroNumero.getText());
                    double numero2 = Double.parseDouble(campoSegundoNumero.getText());
                    double soma = numero1 + numero2;
                    campoResultado.setText(String.valueOf(soma));
                } catch (NumberFormatException ex) {
                    campoResultado.setText("Entrada Inválida");
                }
            }
        });
        janela.getContentPane().add(botaoSomar);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBackground(new Color(255, 70, 70));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoPrimeiroNumero.setText("");
                campoSegundoNumero.setText("");
                campoResultado.setText("");
            }
        });
        janela.getContentPane().add(botaoLimpar);
    }
}