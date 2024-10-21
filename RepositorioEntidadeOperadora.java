package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class RepositorioEntidadeOperadora {
	public boolean incluir(EntidadeOperadora operadora) throws IOException {
	    String arquivo = "EntidadeOperadora.txt";
	    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
	    String linha;
	    while ((linha = reader.readLine()) != null) {
	        String[] data = linha.split(";");
	        long idExistente = Long.parseLong(data[0]);
	        if (idExistente == operadora.getIdentificador()) {
	            reader.close();
	            return false;
	        }
	    }
	    reader.close();
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
	    String novaLinha = operadora.getIdentificador() + ";" 
	                     + operadora.getNome() + ";" 
	                     + operadora.getAutorizadoAcao() + ";" 
	                     + operadora.getSaldoAcao() + ";" 
	                     + operadora.getSaldoTituloDivida();
	    
	    writer.write(novaLinha);
	    writer.newLine();
	    writer.close();
	    
	    return true;
	}

	
	public boolean alterar(EntidadeOperadora operadora) throws IOException {
	    File arquivo = new File("EntidadeOperadora.txt");
	    File temp = new File("EntidadeOperadora_temp.txt");
	    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
	    BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
	    String linha;
	    boolean encontrado = false;
	    while ((linha = reader.readLine()) != null) {
	        String[] data = linha.split(";");
	        long id = Long.parseLong(data[0]);
	        if (id == operadora.getIdentificador()) {
	            String novaLinha = operadora.getIdentificador() + ";"
	                             + operadora.getNome() + ";"
	                             + operadora.getAutorizadoAcao() + ";"
	                             + operadora.getSaldoAcao() + ";"
	                             + operadora.getSaldoTituloDivida();
	            writer.write(novaLinha);
	            encontrado = true;
	        } else {
	            writer.write(linha);
	        }
	        writer.newLine();
	    }
	    
	    writer.close();
	    reader.close();
	    if (arquivo.delete()) {
	        temp.renameTo(arquivo);
	    }
	    
	    return encontrado;
	}
	
	public boolean excluir(long identificador) throws IOException {
	    File arquivo = new File("EntidadeOperadora.txt");
	    File temp = new File("EntidadeOperadora_temp.txt");
	    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
	    BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
	    String linha;
	    boolean encontrado = false;
	    while ((linha = reader.readLine()) != null) {
	        String[] data = linha.split(";");
	        long id = Long.parseLong(data[0]);

	        if (id == identificador) {
	            encontrado = true;
	        } else {
	            writer.write(linha);
	            writer.newLine();
	        }
	    }

	    writer.close();
	    reader.close();
	    if (arquivo.delete()) {
	        temp.renameTo(arquivo);
	    }

	    return encontrado;
	}

	public EntidadeOperadora buscar(long identificador) throws IOException {
	    File arquivo = new File("EntidadeOperadora.txt");
	    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
	    String linha;
	    while ((linha = reader.readLine()) != null) {
	        String[] data = linha.split(";");
	        long id = Long.parseLong(data[0]);
	        if (id == identificador) {
	            String nome = data[1];
	            boolean autorizadoAcao = Boolean.parseBoolean(data[2]);
	            double saldoAcao = Double.parseDouble(data[3]);
	            double saldoTituloDivida = Double.parseDouble(data[4]);

	            EntidadeOperadora operadora = new EntidadeOperadora(id, nome, autorizadoAcao);
	            operadora.creditarSaldoAcao(saldoAcao);
	            operadora.creditarSaldoTituloDivida(saldoTituloDivida);

	            reader.close();
	            return operadora;
	        }
	    }

	    reader.close();
	    return null;
	}


}
