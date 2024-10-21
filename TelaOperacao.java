package org.cesarschool.telas;

import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaOperacao extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtEntidadeCreditoId;
    private JTextField txtEntidadeDebitoId;
    private JTextField txtIdAcaoOuTitulo;
    private JTextField txtValor;
    private JCheckBox chkEhAcao;
    private JTextArea txtResultado;
    private MediatorOperacao mediatorOperacao;

    public TelaOperacao() {
        mediatorOperacao = MediatorOperacao.getInstance();

        setTitle("Gestão de Operações");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        JLabel lblEntidadeCreditoId = new JLabel("ID Entidade Crédito:");
        txtEntidadeCreditoId = new JTextField();

        JLabel lblEntidadeDebitoId = new JLabel("ID Entidade Débito:");
        txtEntidadeDebitoId = new JTextField();

        JLabel lblIdAcaoOuTitulo = new JLabel("ID Ação ou Título:");
        txtIdAcaoOuTitulo = new JTextField();

        JLabel lblValor = new JLabel("Valor:");
        txtValor = new JTextField();

        JLabel lblEhAcao = new JLabel("É Ação?");
        chkEhAcao = new JCheckBox();

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        JButton btnRealizarOperacao = new JButton("Realizar Operação");
        btnRealizarOperacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarOperacao();
                } catch (IOException ex) {
                    txtResultado.setText("Erro ao realizar operação: " + ex.getMessage());
                }
            }
        });

        JButton btnGerarExtrato = new JButton("Gerar Extrato");
        btnGerarExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gerarExtrato();
                } catch (IOException ex) {
                    txtResultado.setText("Erro ao gerar extrato: " + ex.getMessage());
                }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaPrincipal();
            }
        });

        add(lblEntidadeCreditoId);
        add(txtEntidadeCreditoId);
        add(lblEntidadeDebitoId);
        add(txtEntidadeDebitoId);
        add(lblIdAcaoOuTitulo);
        add(txtIdAcaoOuTitulo);
        add(lblValor);
        add(txtValor);
        add(lblEhAcao);
        add(chkEhAcao);
        add(btnRealizarOperacao);
        add(btnGerarExtrato);
        add(btnVoltar);
        add(new JScrollPane(txtResultado));

        setVisible(true);
    }

    private void realizarOperacao() throws IOException {
        boolean ehAcao = chkEhAcao.isSelected();
        int entidadeCreditoId = Integer.parseInt(txtEntidadeCreditoId.getText());
        int entidadeDebitoId = Integer.parseInt(txtEntidadeDebitoId.getText());
        int idAcaoOuTitulo = Integer.parseInt(txtIdAcaoOuTitulo.getText());
        double valor = Double.parseDouble(txtValor.getText());

        String resultado = mediatorOperacao.realizarOperacao(ehAcao, entidadeCreditoId, entidadeDebitoId, idAcaoOuTitulo, valor);
        txtResultado.setText(resultado);
    }

    private void gerarExtrato() throws IOException {
        int entidadeId = Integer.parseInt(txtEntidadeCreditoId.getText());
        Transacao[] transacoes = mediatorOperacao.gerarExtrato(entidadeId);

        StringBuilder extrato = new StringBuilder("Extrato de Transações:\n");
        for (Transacao transacao : transacoes) {
            extrato.append("Data: ").append(transacao.getDataHoraOperacao())
                   .append(", Crédito: ").append(transacao.getEntidadeCredito().getNome())
                   .append(", Débito: ").append(transacao.getEntidadeDebito().getNome())
                   .append(", Valor: ").append(transacao.getValorOperacao()).append("\n");
        }

        txtResultado.setText(extrato.toString());
    }

    private void voltarTelaPrincipal() {
        dispose();
        new TelaPrograma();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaOperacao());
    }
}
