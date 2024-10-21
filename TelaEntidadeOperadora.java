package org.cesarschool.telas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaEntidadeOperadora extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador;
    private JTextField txtNome;
    private JCheckBox chkAutorizadoAcao;
    private JTextField txtSaldoAcao;
    private JTextField txtSaldoTituloDivida;
    private JTextArea txtResultado;
    private MediatorEntidadeOperadora mediator;

    public TelaEntidadeOperadora() {
        mediator = MediatorEntidadeOperadora.getInstance();

        setTitle("Gestão de Entidades Operadoras");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelAndField(panelForm, gbc, 0, "Identificador:", txtIdentificador = new JTextField(15));
        addLabelAndField(panelForm, gbc, 1, "Nome:", txtNome = new JTextField(15));
        addLabelAndField(panelForm, gbc, 2, "Autorizado Ação:", chkAutorizadoAcao = new JCheckBox());
        addLabelAndField(panelForm, gbc, 3, "Saldo Ação:", txtSaldoAcao = new JTextField(15));
        addLabelAndField(panelForm, gbc, 4, "Saldo Título Dívida:", txtSaldoTituloDivida = new JTextField(15));

        JPanel panelButtons = new JPanel();
        JButton btnIncluir = new JButton("Incluir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnAlterar = new JButton("Alterar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar"); 
        panelButtons.add(btnIncluir);
        panelButtons.add(btnBuscar);
        panelButtons.add(btnAlterar);
        panelButtons.add(btnExcluir);
        panelButtons.add(btnVoltar);

        txtResultado = new JTextArea(10, 40);
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        btnIncluir.addActionListener(e -> incluirEntidade());
        btnBuscar.addActionListener(e -> buscarEntidade());
        btnAlterar.addActionListener(e -> alterarEntidade());
        btnExcluir.addActionListener(e -> excluirEntidade());
        btnVoltar.addActionListener(e -> voltarParaTelaPrincipal()); 

        add(panelForm, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void incluirEntidade() {
        try {
            EntidadeOperadora entidade = criarEntidadeAPartirDosCampos();
            String resultado = mediator.incluir(entidade);
            txtResultado.setText(resultado == null ? "Entidade incluída com sucesso." : "Erro: " + resultado);
        } catch (IOException | NumberFormatException ex) {
            txtResultado.setText("Erro ao incluir entidade: " + ex.getMessage());
        }
    }

    private void buscarEntidade() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            EntidadeOperadora entidade = mediator.buscar(identificador);
            if (entidade != null) {
                preencherCamposComEntidade(entidade);
                txtResultado.setText("Entidade encontrada.");
            } else {
                txtResultado.setText("Entidade não encontrada.");
            }
        } catch (IOException | NumberFormatException ex) {
            txtResultado.setText("Erro ao buscar entidade: " + ex.getMessage());
        }
    }

    private void alterarEntidade() {
        try {
            EntidadeOperadora entidade = criarEntidadeAPartirDosCampos();
            String resultado = mediator.alterar(entidade);
            txtResultado.setText(resultado == null ? "Entidade alterada com sucesso." : "Erro: " + resultado);
        } catch (IOException | NumberFormatException ex) {
            txtResultado.setText("Erro ao alterar entidade: " + ex.getMessage());
        }
    }

    private void excluirEntidade() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String resultado = mediator.excluir(identificador);
            txtResultado.setText(resultado == null ? "Entidade excluída com sucesso." : "Erro: " + resultado);
        } catch (IOException | NumberFormatException ex) {
            txtResultado.setText("Erro ao excluir entidade: " + ex.getMessage());
        }
    }

    private EntidadeOperadora criarEntidadeAPartirDosCampos() {
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String nome = txtNome.getText();
        boolean autorizadoAcao = chkAutorizadoAcao.isSelected();
        Double.parseDouble(txtSaldoAcao.getText());
        Double.parseDouble(txtSaldoTituloDivida.getText());

        return new EntidadeOperadora(identificador, nome, autorizadoAcao);
    }

    private void preencherCamposComEntidade(EntidadeOperadora entidade) {
        txtNome.setText(entidade.getNome());
        chkAutorizadoAcao.setSelected(entidade.isAutorizadoAcao());
        txtSaldoAcao.setText(String.valueOf(entidade.getSaldoAcao()));
        txtSaldoTituloDivida.setText(String.valueOf(entidade.getSaldoTituloDivida()));
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, int row, String label, JCheckBox field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void voltarParaTelaPrincipal() {
        dispose();
        new TelaPrograma(); 
    }

}
