package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 * 
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */
import java.io.*;
import java.time.LocalDate;

public class RepositorioAcao {
    private static final String FILE_NAME = "Acao.txt";

    public boolean incluir(Acao acao) throws IOException {
        if (buscar(acao.getIdentificador()) != null) {
            return false; 
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(formatarAcao(acao));
        writer.newLine();
        writer.close();
        
        return true;
    }

    public boolean alterar(Acao acao) throws IOException {
        File arquivoOriginal = new File(FILE_NAME);
        File arquivoTemp = new File("Temp_" + FILE_NAME);

        BufferedReader reader = new BufferedReader(new FileReader(arquivoOriginal));
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

        String line;
        boolean encontrado = false;

        while ((line = reader.readLine()) != null) {
            Acao acaoExistente = parseAcao(line);
            if (acaoExistente != null && acaoExistente.getIdentificador() == acao.getIdentificador()) {
                writer.write(formatarAcao(acao));
                encontrado = true;
            } else {
                writer.write(line);
            }
            writer.newLine();
        }

        reader.close();
        writer.close();

        if (!encontrado) {
            return false;
        }

        if (!arquivoOriginal.delete() || !arquivoTemp.renameTo(arquivoOriginal)) {
            throw new IOException("Erro ao substituir o arquivo original");
        }
        return true;
    }

    public boolean excluir(int identificador) throws IOException {
        File arquivoOriginal = new File(FILE_NAME);
        File arquivoTemp = new File("Temp_" + FILE_NAME);

        BufferedReader reader = new BufferedReader(new FileReader(arquivoOriginal));
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

        String linhas;
        boolean encontrado = false;

        while ((linhas = reader.readLine()) != null) {
            Acao acaoExistente = parseAcao(linhas);
            if (acaoExistente != null && acaoExistente.getIdentificador() == identificador) {
                encontrado = true;
                continue; 
            }
            writer.write(linhas);
            writer.newLine();
        }

        reader.close();
        writer.close();

        if (!encontrado) {
            return false;
        }

        if (!arquivoOriginal.delete() || !arquivoTemp.renameTo(arquivoOriginal)) {
            throw new IOException("Erro ao substituir o arquivo original");
        }

        return true;
    }

    public Acao buscar(int identificador) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String linhas;
        while ((linhas = reader.readLine()) != null) {
            Acao acao = parseAcao(linhas);
            if (acao != null && acao.getIdentificador() == identificador) {
                reader.close();
                return acao;
            }
        }
        reader.close();
        return null;
    }

    private Acao parseAcao(String linhas) {
        String[] partes = linhas.split(";");
        if (partes.length == 4) {
            int identificador = Integer.parseInt(partes[0]);
            String nome = partes[1];
            LocalDate dataValidade = LocalDate.parse(partes[2]);
            double valorUnitario = Double.parseDouble(partes[3]);
            return new Acao(identificador, nome, dataValidade, valorUnitario);
        }
        return null;
    }

    private String formatarAcao(Acao acao) {
        return acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDatadevalidade() + ";" + acao.getValorUnitario();
    }
}