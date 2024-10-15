package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * Esta classe contém os seguintes atributos:
 * - entidadeCredito, do tipo EntidadeOperadora
 * - entidadeDebito, do tipo EntidadeOperadora
 * - acao, do tipo Acao
 * - tituloDivida, do tipo TituloDivida
 * - valorOperacao, do tipo double
 * - dataHoraOperacao, do tipo LocalDateTime
 * 
 * A classe deve ter um construtor público que inicializa todos os atributos.
 * Além disso, deve ter métodos get públicos para todos os atributos e métodos
 * set públicos somente se necessário.
 */
public class Transacao extends Acao{
    private EntidadeOperadora entidadeCredito;
    private EntidadeOperadora entidadeDebito;
    private Acao acao;
    private TituloDivida tituloDivida;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;

    public Transacao(int identificador, String nome, LocalDate datadevalidade, double valorUnitario,EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao,TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        super(identificador, nome, datadevalidade, valorOperacao);
    	this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

	public EntidadeOperadora getEntidadeCredito() {
        return entidadeCredito;
    }

    public EntidadeOperadora getEntidadeDebito() {
        return entidadeDebito;
    }

    public Acao getAcao() {
        return acao;
    }

    public TituloDivida getTituloDivida() {
        return tituloDivida;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }

    public void setEntidadeCredito(EntidadeOperadora entidadeCredito) {
        this.entidadeCredito = entidadeCredito;
    }

    public void setEntidadeDebito(EntidadeOperadora entidadeDebito) {
        this.entidadeDebito = entidadeDebito;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public void setTituloDivida(TituloDivida tituloDivida) {
        this.tituloDivida = tituloDivida;
    }

    public void setValorOperacao(double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public void setDataHoraOperacao(LocalDateTime dataHoraOperacao) {
        this.dataHoraOperacao = dataHoraOperacao;
    }
}
