package org.cesarschool.telas;

import java.awt.GridLayout;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaAcao extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador;
    private JTextField txtNome;
    private JTextField txtValidade;
    private JTextField txtValor;
    private JTextArea txtResultado;
    private MediatorAcao mediator;

    public TelaAcao() {
        mediator = MediatorAcao.getInstance();

        setTitle("Gestão de Ações");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2)); 

        JLabel lblIdentificador = new JLabel("Identificador:");
        txtIdentificador = new JTextField();
        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        JLabel lblValidade = new JLabel("Data de Validade (yyyy-mm-dd):");
        txtValidade = new JTextField();
        JLabel lblValor = new JLabel("Valor Unitário:");
        txtValor = new JTextField();

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.addActionListener(e -> {
            try {
                incluirAcao();
            } catch (Exception ex) {
                txtResultado.setText("Erro ao incluir ação: " + ex.getMessage());
            }
        });

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
            try {
                buscarAcao();
            } catch (Exception ex) {
                txtResultado.setText("Erro ao buscar ação: " + ex.getMessage());
            }
        });

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(e -> {
            try {
                alterarAcao();
            } catch (Exception ex) {
                txtResultado.setText("Erro ao alterar ação: " + ex.getMessage());
            }
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            try {
                excluirAcao();
            } catch (Exception ex) {
                txtResultado.setText("Erro ao excluir ação: " + ex.getMessage());
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarParaTelaPrincipal());

        add(lblIdentificador);
        add(txtIdentificador);
        add(lblNome);
        add(txtNome);
        add(lblValidade);
        add(txtValidade);
        add(lblValor);
        add(txtValor);
        add(btnIncluir);
        add(btnBuscar);
        add(btnAlterar);
        add(btnExcluir);
        add(new JScrollPane(txtResultado));
        add(btnVoltar); 

        setVisible(true);
    }

    private void incluirAcao() throws IOException {
        if (camposValidos()) {
            Acao acao = criarAcaoAPartirDosCampos();
            String resultado = mediator.incluir(acao);
            if (resultado == null) {
                txtResultado.setText("Ação incluída com sucesso.");
            } else {
                txtResultado.setText("Erro: " + resultado);
            }
        } else {
            txtResultado.setText("Preencha todos os campos.");
        }
    }

    private void buscarAcao() throws IOException {
        if (!txtIdentificador.getText().isEmpty()) {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            Acao acao = mediator.buscar(identificador);
            if (acao != null) {
                txtNome.setText(acao.getNome());
                txtValidade.setText(acao.getDatadevalidade().toString());
                txtValor.setText(String.valueOf(acao.getValorUnitario()));
                txtResultado.setText("Ação encontrada.");
            } else {
                txtResultado.setText("Ação não encontrada.");
            }
        } else {
            txtResultado.setText("Informe o identificador.");
        }
    }

    private void alterarAcao() throws IOException {
        if (camposValidos()) {
            Acao acao = criarAcaoAPartirDosCampos();
            String resultado = mediator.alterar(acao);
            if (resultado == null) {
                txtResultado.setText("Ação alterada com sucesso.");
            } else {
                txtResultado.setText("Erro: " + resultado);
            }
        } else {
            txtResultado.setText("Preencha todos os campos.");
        }
    }

    private void excluirAcao() throws IOException {
        if (!txtIdentificador.getText().isEmpty()) {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String resultado = mediator.excluir(identificador);
            if (resultado == null) {
                txtResultado.setText("Ação excluída com sucesso.");
            } else {
                txtResultado.setText("Erro: " + resultado);
            }
        } else {
            txtResultado.setText("Informe o identificador.");
        }
    }

    private Acao criarAcaoAPartirDosCampos() {
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String nome = txtNome.getText();
        LocalDate validade = LocalDate.parse(txtValidade.getText());
        double valor = Double.parseDouble(txtValor.getText());
        return new Acao(identificador, nome, validade, valor);
    }

    private boolean camposValidos() {
        return !txtIdentificador.getText().isEmpty() && !txtNome.getText().isEmpty() &&
               !txtValidade.getText().isEmpty() && !txtValor.getText().isEmpty();
    }

    private void voltarParaTelaPrincipal() {
        dispose();
        new TelaPrograma(); 
    }
}
