package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

public class RepositorioTituloDivida {
	public boolean incluir(TituloDivida tituloDivida) throws IOException {
		String arquivo = "TituloDivida.txt";
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] data = linha.split(";");
            int idExistente = Integer.parseInt(data[0]);
            if (idExistente == tituloDivida.getIdentificador()) {
                reader.close();
                return false; 
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
        String novo = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDatavalidade() + ";" + tituloDivida.getTaxaJuros();
        writer.write(novo);
        writer.newLine();
        writer.close();
        return true;
	}
	
	
	public boolean alterar(TituloDivida tituloDivida) throws IOException {
		File arquivo = new File("TituloDivida.txt");
        File temp = new File("TituloDivida2.txt");
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String linha;
        boolean find = false;
		
        while((linha = reader.readLine()) != null) {
        	String[] data = linha.split(";");
        	int id = Integer.parseInt(data[0]);
        	if(id == tituloDivida.getIdentificador()) {
        		String novo = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDatavalidade() + ";" + tituloDivida.getTaxaJuros();
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
		File arquivo = new File("TituloDivida.txt");
        File temp = new File("TituloDivida2.txt");
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
	public TituloDivida buscar(int identificador) throws IOException {
		File arquivo = new File("TituloDivida.txt");
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while((linha = reader.readLine()) != null){
        	String[] data = linha.split(";");
        	int id = Integer.parseInt(data[0]);
        	if(id == identificador) {
        		String nome = data[1];
                LocalDate validade = LocalDate.parse(data[2]);
                double taxaJuros = Double.parseDouble(data[3]);
                TituloDivida tituloDivida = new TituloDivida(id, nome, validade, taxaJuros);
                reader.close();
                return tituloDivida;
        	}
        }
        reader.close();
        return null;
	}
}
