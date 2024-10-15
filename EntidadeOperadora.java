package br.com.cesarschool.poo.titulos.entidades;

/*
 * Esta classe contém os seguintes atributos:
 * - identificador, do tipo long (read-only fora da classe)
 * - nome, do tipo String
 * - autorizadoAcao, do tipo double
 * - saldoAcao, do tipo double (somente getter)
 * - saldoTituloDivida, do tipo double (somente getter)
 * 
 * Construtor público que inicializa os atributos identificador, nome e autorizadoAcao.
 * Métodos set/get públicos para identificador (read-only), nome e autorizadoAcao.
 * Métodos públicos para creditar e debitar valores de saldoAcao e saldoTituloDivida.
 */
public class EntidadeOperadora {
    // Atributos privados
    private long identificador;
    private String nome;
    private boolean autorizadoAcao;
    private double saldoAcao;
    private double saldoTituloDivida;

    // Construtor que inicializa identificador, nome e autorizadoAcao
    public EntidadeOperadora(long identificador, String nome, boolean autorizadoAcao) {
        this.identificador = identificador;
        this.nome = nome;
        this.autorizadoAcao = autorizadoAcao;
    }

    public long getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isgetAutorizadoAcao() {
        return autorizadoAcao;
    }

    public void setAutorizadoAcao(boolean autorizadoAcao) {
        this.autorizadoAcao = autorizadoAcao;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }

    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }

    public void creditarSaldoAcao(double valor) {
        saldoAcao += valor;
    }

    public void debitarSaldoAcao(double valor) {
        saldoAcao -= valor;
    }

    public void creditarSaldoTituloDivida(double valor) {
        saldoTituloDivida += valor;
    }

    public void debitarSaldoTituloDivida(double valor) {
        saldoTituloDivida -= valor;
    }
}
