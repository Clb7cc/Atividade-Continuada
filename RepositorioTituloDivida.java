package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.*;
import java.time.LocalDate;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
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
public class RepositorioTituloDivida {

	    private static final String FILE_NAME = "TituloDivida.txt";
	    
	    public boolean incluir(TituloDivida tituloDivida) {
	        if (buscar(tituloDivida.getIdentificador()) != null) {
	            return false; 
	        }
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
	            writer.write(tituloDivida.getIdentificador() + ";" +
	                         tituloDivida.getNome() + ";" +
	                         tituloDivida.getDatadevalidade() + ";" +
	                         tituloDivida.getTaxaJuros() + "\n");
	            return true;
	        } catch (IOException e) {
	            return false; 
	        } finally {
	            if (writer != null) {
	                try {
	                    writer.close();
	                } catch (IOException e) {
	                    return false; 
	                }
	            }
	        }
	    }

	    public boolean alterar(TituloDivida tituloDivida) {
	        String[] linhas = lerArquivo();
	        if (linhas == null) {
	            return false;
	        }

	        boolean alterado = false;
	        for (int i = 0; i < linhas.length; i++) {
	            String[] partes = linhas[i].split(";");
	            if (Integer.parseInt(partes[0]) == tituloDivida.getIdentificador()) {
	                linhas[i] = tituloDivida.getIdentificador() + ";" +
	                            tituloDivida.getNome() + ";" +
	                            tituloDivida.getDatadevalidade() + ";" +
	                            tituloDivida.getTaxaJuros();
	                alterado = true;
	                break;
	            }
	        }

	        if (alterado) {
	            escreverArquivo(linhas);
	        }
	        return alterado;
	    }

	    public boolean excluir(int identificador) {
	        String[] linhas = lerArquivo();
	        if (linhas == null) {
	            return false;
	        }

	        boolean excluido = false;
	        String[] novasLinhas = new String[linhas.length - 1];
	        int index = 0;

	        for (int i = 0; i < linhas.length; i++) {
	            String[] partes = linhas[i].split(";");
	            if (Integer.parseInt(partes[0]) == identificador) {
	                excluido = true;
	            } else {
	                if (index < novasLinhas.length) {
	                    novasLinhas[index++] = linhas[i];
	                }
	            }
	        }

	        if (excluido) {
	            escreverArquivo(novasLinhas);
	        }
	        return excluido;
	    }

	    public TituloDivida buscar(int identificador) {
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(FILE_NAME));
	            String linha;
	            while ((linha = reader.readLine()) != null) {
	                String[] partes = linha.split(";");
	                if (Integer.parseInt(partes[0]) == identificador) {
	                    return new TituloDivida(
	                        identificador,
	                        partes[1],
	                        LocalDate.parse(partes[2]),
	                        Double.parseDouble(partes[3])
	                    );
	                }
	            }
	        } catch (IOException e) {
	            return null; 
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    return null;
	                }
	            }
	        }
	        return null; 
	    }
	    
	    private String[] lerArquivo() {
	        BufferedReader reader = null;
	        String[] linhas = null;
	        try {
	            reader = new BufferedReader(new FileReader(FILE_NAME));
	            int linhasCount = contarLinhas();
	            linhas = new String[linhasCount];
	            String linha;
	            int i = 0;
	            while ((linha = reader.readLine()) != null) {
	                linhas[i++] = linha;
	            }
	        } catch (IOException e) {
	            return null;
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    return null;
	                }
	            }
	        }
	        return linhas;
	    }

	    private int contarLinhas() {
	        int linhas = 0;
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(FILE_NAME));
	            while (reader.readLine() != null) {
	                linhas++;
	            }
	        } catch (IOException e) {
	            return 0; 
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    return 0;
	                }
	            }
	        }
	        return linhas;
	    }

	    private boolean escreverArquivo(String[] linhas) {
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(FILE_NAME));
	            for (String linha : linhas) {
	                writer.write(linha + "\n");
	            }
	            return true; 
	        } catch (IOException e) {
	            return false; 
	        } finally {
	            if (writer != null) {
	                try {
	                    writer.close();
	                } catch (IOException e) {
	                    return false;
	                }
	            }
	        }
	    }
	}
