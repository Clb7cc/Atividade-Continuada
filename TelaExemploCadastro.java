package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;

public class TelaExemploCadastro {

    private JFrame frame;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtRenda;
    private JButton btnNovo;
    private JButton btnBuscar;
    private JButton btnIncluirAlterar;
    private JButton btnCancelar;
    private JButton btnLimpar;
    private static EntidadeMediator mediator = new EntidadeMediator();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaExemploCadastro window = new TelaExemploCadastro();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaExemploCadastro() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(50, 50, 70, 20);
        frame.getContentPane().add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 50, 100, 26);
        frame.getContentPane().add(txtCodigo);
        txtCodigo.setColumns(10);

        btnNovo = new JButton("Novo");
        btnNovo.setBounds(240, 50, 90, 30);
        frame.getContentPane().add(btnNovo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(350, 50, 90, 30);
        frame.getContentPane().add(btnBuscar);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(50, 110, 70, 20);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setEnabled(false);
        txtNome.setBounds(120, 110, 220, 26);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblRenda = new JLabel("Renda");
        lblRenda.setBounds(50, 170, 70, 20);
        frame.getContentPane().add(lblRenda);

        txtRenda = new JTextField();
        txtRenda.setEnabled(false);
        txtRenda.setBounds(120, 170, 120, 26);
        frame.getContentPane().add(txtRenda);
        txtRenda.setColumns(10);

        btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.setBounds(120, 230, 90, 30);
        frame.getContentPane().add(btnIncluirAlterar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setBounds(230, 230, 90, 30);
        frame.getContentPane().add(btnCancelar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(350, 230, 90, 30);
        frame.getContentPane().add(btnLimpar);

        // Action Listeners
        btnNovo.addActionListener(e -> {
            Entidade ent = mediator.buscar(txtCodigo.getText());
            if (ent != null) {
                JOptionPane.showMessageDialog(null, "Entidade já existente!");
            } else {
                btnIncluirAlterar.setEnabled(true);
                btnCancelar.setEnabled(true);
                txtNome.setEnabled(true);
                txtRenda.setEnabled(true);
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCodigo.setEnabled(false);
            }
        });

        btnBuscar.addActionListener(e -> {
            Entidade ent = mediator.buscar(txtCodigo.getText());
            if (ent == null) {
                JOptionPane.showMessageDialog(null, "Entidade não existente!");
            } else {
                txtNome.setText(ent.getNome());
                txtRenda.setText(String.valueOf(ent.getRenda()));
                btnIncluirAlterar.setText("Alterar");
                btnIncluirAlterar.setEnabled(true);
                btnCancelar.setEnabled(true);
                txtNome.setEnabled(true);
                txtRenda.setEnabled(true);
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCodigo.setEnabled(false);
            }
        });

        btnIncluirAlterar.addActionListener(e -> {
            Entidade ent = new Entidade(txtCodigo.getText(), txtNome.getText(),
                    Double.parseDouble(txtRenda.getText()));
            String msg;
            if (btnIncluirAlterar.getText().equals("Incluir")) {
                msg = mediator.incluir(ent);
            } else {
                msg = mediator.alterar(ent);
            }
            if (msg != null) {
                JOptionPane.showMessageDialog(null, msg);
            } else {
                resetFields();
            }
        });

        btnCancelar.addActionListener(e -> resetFields());

        btnLimpar.addActionListener(e -> {
            txtCodigo.setText("");
            txtNome.setText("");
            txtRenda.setText("");
        });
    }

    private void resetFields() {
        btnIncluirAlterar.setEnabled(false);
        btnCancelar.setEnabled(false);
        txtNome.setEnabled(false);
        txtRenda.setEnabled(false);
        btnNovo.setEnabled(true);
        btnBuscar.setEnabled(true);
        txtCodigo.setEnabled(true);
        txtCodigo.setText("");
        txtRenda.setText("");
        txtNome.setText("");
        btnIncluirAlterar.setText("Incluir");
    }
}