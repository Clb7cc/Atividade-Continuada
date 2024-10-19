package org.cesarschool.telas;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaExemploEntidade {

    private JFrame frame;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtRenda;
    private JButton btnNovo;
    private JButton btnBuscar;
    private JButton btnIncluirAlterar;
    private JButton btnCancelar;
    private JButton btnLimpar;
    private EntidadeMediator mediator = new EntidadeMediator();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExemploEntidade window = new TelaExemploEntidade();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaExemploEntidade() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(36, 46, 70, 20);
        frame.getContentPane().add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(129, 46, 113, 26);
        frame.getContentPane().add(txtCodigo);
        txtCodigo.setColumns(10);

        btnNovo = new JButton("Novo");
        btnNovo.setBounds(264, 42, 90, 30);
        frame.getContentPane().add(btnNovo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(360, 42, 90, 30);
        frame.getContentPane().add(btnBuscar);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(36, 109, 70, 20);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setEnabled(false);
        txtNome.setBounds(129, 109, 225, 26);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblRenda = new JLabel("Renda");
        lblRenda.setBounds(36, 167, 70, 20);
        frame.getContentPane().add(lblRenda);

        txtRenda = new JTextField();
        txtRenda.setEnabled(false);
        txtRenda.setBounds(129, 164, 113, 26);
        frame.getContentPane().add(txtRenda);
        txtRenda.setColumns(10);

        btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.setBounds(131, 220, 90, 30);
        frame.getContentPane().add(btnIncluirAlterar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setBounds(239, 220, 90, 30);
        frame.getContentPane().add(btnCancelar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(347, 220, 90, 30);
        frame.getContentPane().add(btnLimpar);

        // Listeners
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entidade ent = mediator.buscar(txtCodigo.getText());
                if (ent != null) {
                    JOptionPane.showMessageDialog(frame, "Entidade já existente!");
                } else {
                    enableFormFields();
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entidade ent = mediator.buscar(txtCodigo.getText());
                if (ent == null) {
                    JOptionPane.showMessageDialog(frame, "Entidade não existente!");
                } else {
                    txtNome.setText(ent.getNome());
                    txtRenda.setText(String.valueOf(ent.getRenda()));
                    btnIncluirAlterar.setText("Alterar");
                    enableFormFields();
                }
            }
        });

        btnIncluirAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entidade ent = new Entidade(txtCodigo.getText(), txtNome.getText(),
                        Double.parseDouble(txtRenda.getText()));
                String msg = null;
                if (btnIncluirAlterar.getText().equals("Incluir")) {
                    msg = mediator.incluir(ent);
                } else {
                    msg = mediator.alterar(ent);
                }
                if (msg != null) {
                    JOptionPane.showMessageDialog(frame, msg);
                } else {
                    resetForm();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCodigo.setText("");
                txtNome.setText("");
                txtRenda.setText("");
            }
        });
    }

    private void enableFormFields() {
        btnIncluirAlterar.setEnabled(true);
        btnCancelar.setEnabled(true);
        txtNome.setEnabled(true);
        txtRenda.setEnabled(true);
        btnNovo.setEnabled(false);
        btnBuscar.setEnabled(false);
        txtCodigo.setEnabled(false);
    }

    private void resetForm() {
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
