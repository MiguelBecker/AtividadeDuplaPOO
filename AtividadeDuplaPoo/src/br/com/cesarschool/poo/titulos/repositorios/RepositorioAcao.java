package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;

public class RepositorioAcao {
	public boolean incluir(Acao acao) throws IOException {
        String arquivo = "Acao.txt";
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] data = linha.split(";");
            int idExistente = Integer.parseInt(data[0]);
            if (idExistente == acao.getIdentificador()) {
                reader.close();
                return false; 
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
        String novo = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDatavalidade() + ";" + acao.getValorUnitario();
        writer.write(novo);
        writer.newLine();
        writer.close();
        return true;
    }
	
	public boolean alterar(Acao acao) throws IOException {
		File arquivo = new File("Acao.txt");
        File temp = new File("Acao2.txt");
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String linha;
        boolean find = false;
		
        while((linha = reader.readLine()) != null) {
        	String[] data = linha.split(";");
        	int id = Integer.parseInt(data[0]);
        	if(id == acao.getIdentificador()) {
        		String novo = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDatavalidade() + ";" + acao.getValorUnitario();
        		writer.write(novo);
        		find = true;
        	}
        	else { 
        		writer.write(linha);
        	}
        	writer.newLine();
        }
        writer.close();
        reader.close();
        if (arquivo.delete()) {
            temp.renameTo(arquivo);
        }
        return find;
	}
	
	public boolean excluir(int identificador) throws IOException {
		File arquivo = new File("Acao.txt");
        File temp = new File("Acao2.txt");
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String linha;
        boolean find = false;
        
        while((linha = reader.readLine()) != null){
        	String[] data = linha.split(";");
        	int id = Integer.parseInt(data[0]);
        	if(id == identificador) {
        		find = true;
        	}
        	else {
        		writer.write(linha);
        	}
        	writer.newLine();
        }
        writer.close();
        reader.close();
        if (arquivo.delete()) {
            temp.renameTo(arquivo);
        }
        return find;
	}
	
	
	public Acao buscar(int identificador) throws IOException {
		File arquivo = new File("Acao.txt");
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while((linha = reader.readLine()) != null){
        	String[] data = linha.split(";");
        	int id = Integer.parseInt(data[0]);
        	if(id == identificador) {
        		String nome = data[1];
                LocalDate validade = LocalDate.parse(data[2]);
                double valorUnitario = Double.parseDouble(data[3]);
                Acao acao = new Acao(id, nome, validade, valorUnitario);
                reader.close();
                return acao;
        	}
        }
        reader.close();
        return null;
	}
}

	







