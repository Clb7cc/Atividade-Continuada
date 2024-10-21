package org.cesarschool.telas;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TelaTituloDivida extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador;
    private JTextField txtNome;
    private JTextField txtTaxaJuros;
    private JTextField txtDataValidade;
    private JTextArea txtResultado;
    private MediatorTituloDivida mediatorTituloDivida;

    public TelaTituloDivida() {
        mediatorTituloDivida = MediatorTituloDivida.getInstance();

        setTitle("Gestão de Títulos de Dívida");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblIdentificador = new JLabel("Identificador:");
        txtIdentificador = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JLabel lblTaxaJuros = new JLabel("Taxa de Juros:");
        txtTaxaJuros = new JTextField();

        JLabel lblDataValidade = new JLabel("Data de Validade (YYYY-MM-DD):");
        txtDataValidade = new JTextField();

        panelFormulario.add(lblIdentificador);
        panelFormulario.add(txtIdentificador);
        panelFormulario.add(lblNome);
        panelFormulario.add(txtNome);
        panelFormulario.add(lblTaxaJuros);
        panelFormulario.add(txtTaxaJuros);
        panelFormulario.add(lblDataValidade);
        panelFormulario.add(txtDataValidade);

        add(panelFormulario, BorderLayout.CENTER);
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnIncluir = new JButton("Incluir Título");
        JButton btnAlterar = new JButton("Alterar Título");
        JButton btnExcluir = new JButton("Excluir Título");
        JButton btnBuscar = new JButton("Buscar Título");
        JButton btnVoltar = new JButton("Voltar"); 

        panelBotoes.add(btnIncluir);
        panelBotoes.add(btnAlterar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnBuscar);
        panelBotoes.add(btnVoltar);

        add(panelBotoes, BorderLayout.SOUTH);
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(new JScrollPane(txtResultado), BorderLayout.NORTH);
        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTitulo();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTitulo();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTitulo();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTitulo();
            }
        });

        // Ação do botão de Voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaPrincipal();
            }
        });

        setVisible(true);
    }

    private void incluirTitulo() {
        try {
            TituloDivida titulo = obterTituloDoFormulario();
            if (validarFormulario()) {
                String resultado = mediatorTituloDivida.incluir(titulo);
                if (resultado == null) {
                    txtResultado.setText("Título incluído com sucesso!");
                    limparCampos();
                } else {
                    txtResultado.setText("Erro: " + resultado);
                }
            }
        } catch (Exception ex) {
            txtResultado.setText("Erro ao incluir título: " + ex.getMessage());
        }
    }

    private void alterarTitulo() {
        try {
            TituloDivida titulo = obterTituloDoFormulario();
            if (validarFormulario()) {
                String resultado = mediatorTituloDivida.alterar(titulo);
                if (resultado == null) {
                    txtResultado.setText("Título alterado com sucesso!");
                    limparCampos();
                } else {
                    txtResultado.setText("Erro: " + resultado);
                }
            }
        } catch (Exception ex) {
            txtResultado.setText("Erro ao alterar título: " + ex.getMessage());
        }
    }

    private void excluirTitulo() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o título?", 
                "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String resultado = mediatorTituloDivida.excluir(identificador);
                if (resultado == null) {
                    txtResultado.setText("Título excluído com sucesso!");
                    limparCampos();
                } else {
                    txtResultado.setText("Erro: " + resultado);
                }
            }
        } catch (Exception ex) {
            txtResultado.setText("Erro ao excluir título: " + ex.getMessage());
        }
    }

    private void buscarTitulo() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            TituloDivida titulo = mediatorTituloDivida.buscar(identificador);
            if (titulo != null) {
                txtResultado.setText("Título encontrado: \n" +
                        "Nome: " + titulo.getNome() + "\n" +
                        "Taxa de Juros: " + titulo.getTaxaJuros() + "\n" +
                        "Data de Validade: " + titulo.getDatadevalidade());
            } else {
                txtResultado.setText("Título não encontrado.");
            }
        } catch (Exception ex) {
            txtResultado.setText("Erro ao buscar título: " + ex.getMessage());
        }
    }

    private TituloDivida obterTituloDoFormulario() {
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String nome = txtNome.getText();
        double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());
        LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText());

        return new TituloDivida(identificador, nome, taxaJuros, dataValidade);
    }

    private boolean validarFormulario() {
        if (txtIdentificador.getText().isEmpty() || txtNome.getText().isEmpty() ||
                txtTaxaJuros.getText().isEmpty() || txtDataValidade.getText().isEmpty()) {
            txtResultado.setText("Todos os campos são obrigatórios.");
            return false;
        }
        try {
            Integer.parseInt(txtIdentificador.getText());
            Double.parseDouble(txtTaxaJuros.getText());
            LocalDate.parse(txtDataValidade.getText());
        } catch (Exception ex) {
            txtResultado.setText("Erro nos dados: " + ex.getMessage());
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtIdentificador.setText("");
        txtNome.setText("");
        txtTaxaJuros.setText("");
        txtDataValidade.setText("");
    }

    private void voltarTelaPrincipal() {
        dispose();
        new TelaPrograma(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaTituloDivida());
    }
}
