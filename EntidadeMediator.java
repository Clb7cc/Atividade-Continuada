package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntidadeMediator {
    private JFrame frame;
    private JTextField codigoField, nomeField, rendaField;
    private JLabel resultadoLabel;
    private Entidade entidadeAtual;

    public EntidadeMediator() {
        setupTela();
    }

    public String incluir(Entidade ent) {
        String msg = validar(ent);
        if (msg == null) {
            resultadoLabel.setText("Entidade incluída com sucesso!");
        }
        return msg;
    }

    public String alterar(Entidade ent) {
        String msg = validar(ent);
        if (msg == null) {
            resultadoLabel.setText("Entidade alterada com sucesso!");
        }
        return msg;
    }

    private String validar(Entidade ent) {
        if (ent.getNome() == null || ent.getNome().trim().equals("")) {
            return "Nome deve ser preenchido";
        } else {
            return null;
        }
    }

    public Entidade buscar(String codigo) {
        if (codigo == null || !codigo.equals("001")) {
            return null;
        } else {
            return new Entidade("001", "Carlos", 1000.00);
        }
    }

    private void setupTela() {
        frame = new JFrame("Operações de Entidade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        codigoField = new JTextField(15);
        nomeField = new JTextField(15);
        rendaField = new JTextField(15);

        JLabel codigoLabel = new JLabel("Código:");
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel rendaLabel = new JLabel("Renda:");
        resultadoLabel = new JLabel("Resultado:");

        JButton incluirButton = new JButton("Incluir");
        JButton alterarButton = new JButton("Alterar");
        JButton buscarButton = new JButton("Buscar");

        incluirButton.setBackground(new Color(0, 120, 215));
        incluirButton.setForeground(Color.WHITE);
        alterarButton.setBackground(new Color(0, 120, 215));
        alterarButton.setForeground(Color.WHITE);
        buscarButton.setBackground(new Color(0, 120, 215));
        buscarButton.setForeground(Color.WHITE);

        incluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entidade ent = new Entidade(codigoField.getText(), nomeField.getText(), Double.parseDouble(rendaField.getText()));
                String msg = incluir(ent);
                if (msg != null) {
                    resultadoLabel.setText(msg);
                }
            }
        });

        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (entidadeAtual != null) {
                    entidadeAtual.setNome(nomeField.getText());
                    entidadeAtual.setRenda(Double.parseDouble(rendaField.getText()));
                    String msg = alterar(entidadeAtual);
                    if (msg != null) {
                        resultadoLabel.setText(msg);
                    }
                } else {
                    resultadoLabel.setText("Entidade não encontrada para alteração.");
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entidadeAtual = buscar(codigoField.getText());
                if (entidadeAtual != null) {
                    nomeField.setText(entidadeAtual.getNome());
                    rendaField.setText(String.valueOf(entidadeAtual.getRenda()));
                    resultadoLabel.setText("Entidade encontrada.");
                } else {
                    resultadoLabel.setText("Entidade não encontrada.");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(codigoLabel, gbc);
        gbc.gridx = 1;
        panel.add(codigoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(rendaLabel, gbc);
        gbc.gridx = 1;
        panel.add(rendaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(incluirButton, gbc);
        gbc.gridx = 1;
        panel.add(alterarButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(buscarButton, gbc);
        gbc.gridy = 5;
        panel.add(resultadoLabel, gbc);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EntidadeMediator();
    }
}
