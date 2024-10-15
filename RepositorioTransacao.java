package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RepositorioTransacao {

    private static final String FILE_NAME = "Transacao.txt";

    public void incluir(Transacao transacao) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        String linha = transacao.toString(); 
        writer.write(linha);
        writer.newLine();
        writer.close();
    }

    public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) throws IOException {
        int contador = 0;

        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            int idEntidadeCredito = Integer.parseInt(partes[0]);

            if (idEntidadeCredito == identificadorEntidadeCredito) {
                contador++;
            }
        }
        reader.close();

        Transacao[] transacoes = new Transacao[contador];
        int indice = 0;

        reader = new BufferedReader(new FileReader(FILE_NAME));

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            int idEntidadeCredito = Integer.parseInt(partes[0]);

            if (idEntidadeCredito == identificadorEntidadeCredito) {
                EntidadeOperadora entidadeCredito = new EntidadeOperadora(Integer.parseInt(partes[0]), partes[1], Double.parseDouble(partes[3]));
                EntidadeOperadora entidadeDebito = new EntidadeOperadora(Integer.parseInt(partes[5]), partes[6], Double.parseDouble(partes[8]));

                Acao acao = null;
                if (!partes[10].equals("null")) {
                    acao = new Acao(Integer.parseInt(partes[10]), partes[11], LocalDate.parse(partes[12]), Double.parseDouble(partes[13]));
                }

                TituloDivida tituloDivida = null;
                if (!partes[14].equals("null")) {
                    tituloDivida = new TituloDivida(Integer.parseInt(partes[14]), partes[15], LocalDate.parse(partes[16]), Double.parseDouble(partes[17]));
                }

                double valorOperacao = Double.parseDouble(partes[18]);
                LocalDateTime dataHoraOperacao = LocalDateTime.parse(partes[19], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                transacoes[indice] = new Transacao(idEntidadeCredito, linha, null, valorOperacao, entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
                indice++;
            }
        }
        reader.close();

        return transacoes;
    }
}
