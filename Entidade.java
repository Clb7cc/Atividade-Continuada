package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entidade {
    private String codigo;
    private String nome;
    private double renda;

    private JLabel codigoLabel;
    private JLabel nomeLabel;
    private JLabel rendaLabel;

    private JTextField codigoField;
    private JTextField nomeField;
    private JTextField rendaField;

    public Entidade(String codigo, String nome, double renda) {
        this.codigo = codigo;
        this.nome = nome;
        this.renda = renda;
        setupTela();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        codigoLabel.setText("Código: " + codigo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        nomeLabel.setText("Nome: " + nome);
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
        rendaLabel.setText("Renda: " + renda);
    }

    private void setupTela() {
        JFrame frame = new JFrame("Informações da Entidade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        codigoField = new JTextField(codigo, 15);
        nomeField = new JTextField(nome, 15);
        rendaField = new JTextField(String.valueOf(renda), 15);
        codigoLabel = new JLabel("Código: " + codigo);
        nomeLabel = new JLabel("Nome: " + nome);
        rendaLabel = new JLabel("Renda: " + renda);
        codigoLabel.setForeground(new Color(50, 50, 50));
        nomeLabel.setForeground(new Color(50, 50, 50));
        rendaLabel.setForeground(new Color(50, 50, 50));
        JButton updateButton = new JButton("Atualizar Dados");
        updateButton.setBackground(new Color(0, 120, 215));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCodigo(codigoField.getText());
                setNome(nomeField.getText());
                try {
                    setRenda(Double.parseDouble(rendaField.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido para a renda.");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Digite o código:"), gbc);
        gbc.gridx = 1;
        panel.add(codigoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Digite o nome:"), gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Digite a renda:"), gbc);
        gbc.gridx = 1;
        panel.add(rendaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(codigoLabel, gbc);
        gbc.gridx = 1;
        panel.add(nomeLabel, gbc);
        gbc.gridx = 2;
        panel.add(rendaLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(updateButton, gbc);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Entidade("000", "Vazio", 0.0);
    }
}