package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

public class MediatorOperacao {
	private MediatorAcao mediatorAcao = new MediatorAcao();
	private MediatorTituloDivida mediatorTituloDivida = new MediatorTituloDivida();
	private MediatorEntidadeOperadora mediatorEntidadeOperadora = new MediatorEntidadeOperadora();
	private RepositorioTransacao repositorioTransacao = new RepositorioTransacao();
	
	public String realizarOperacao(boolean ehAcao, int entidadeCredito,int idEntidadeDebito, int idAcaoOuTitulo, double valor) {
		if(Transacao.getValorOperacao() < 0) {
			return "Valor inválido";
		}
	}
}
