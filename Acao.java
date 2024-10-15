package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;

/*
 * Esta classe herda de Ativo.
 * Possui o atributo valorUnitario do tipo double.
 * 
 * Contém um construtor público que inicializa os atributos, 
 * além de métodos set/get públicos para os atributos.
 * 
 * Inclui um método público calcularPrecoTransacao(double montante): o preço 
 * da transação é o produto do montante pelo valorUnitario.
 */
public class Acao extends Ativo {
    private double valorUnitario;

    public Acao(int identificador, String nome, LocalDate datadevalidade, double valorUnitario) {
        super(identificador, nome, datadevalidade);
        this.valorUnitario = valorUnitario;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double calcularPrecoTransacao(double montante) {
        return montante * valorUnitario;
    }
}
