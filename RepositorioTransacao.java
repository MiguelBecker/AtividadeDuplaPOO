package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.Ativo;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

public class RepositorioTransacao {
	public void incluir(Transacao transacao) throws IOException  {
		String arquivo = "Transacao.txt";
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        while (reader.readLine() != null) {}
        reader.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
        String linha = transacao.getEntidadeCredito().getIdentificador() + ";" + transacao.getEntidadeCredito().getNome() + ";" + transacao.getEntidadeCredito().getAutorizadoAcao() + ";" + transacao.getEntidadeCredito().getSaldoAcao() + ";" + transacao.getEntidadeCredito().getSaldoTituloDivida() + ";" + transacao.getEntidadeDebito().getIdentificador() + ";" + transacao.getEntidadeDebito().getNome() + ";" + transacao.getEntidadeDebito().getAutorizadoAcao() + ";" + transacao.getEntidadeDebito().getSaldoAcao() + ";" + transacao.getEntidadeDebito().getSaldoTituloDivida() + ";" + transacao.getAcao().getIdentificador() + ";" + transacao.getAcao().getNome() + ";" + transacao.getAcao().getDatavalidade() + ";" + transacao.getAcao().getValorUnitario() + ";" + transacao.getTituloDivida().getIdentificador() + ";" + transacao.getTituloDivida().getNome() + ";" + transacao.getTituloDivida().getDatavalidade() + ";" + transacao.getTituloDivida().getTaxaJuros() + ";" + transacao.getValorOperacao() + ";" + transacao.getDataHoraOperacao();
        writer.write(linha);
        writer.newLine();
        writer.close();
	}
	
	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
		File arquivo = new File("Acao.txt");
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while((linha = reader.readLine()) != null){
        	String[] data = linha.split(";");
        	int id = Integer.parseInt(data[0]);
        	if(id == identificadorEntidadeCredito) {
        		String nomeCredito = data[1];
        		boolean autorizadoCredito = Boolean.parseBoolean(data[2]);
        		double saldoCredito = Double.parseDouble(data[3]);
        		double tituloCredito = Double.parseDouble(data[4]);
        		EntidadeOperadora entidadeCredito = new EntidadeOperadora(id, nomeCredito, autorizadoCredito);
        		entidadeCredito.creditarSaldoAcao(saldoCredito);
        		entidadeCredito.creditarSaldoTituloDivida(tituloCredito);
                Transacao[] transacao = new Transacao();
                reader.close();
                return transacao;
        	}
        }
        reader.close();
        return null;
	}
}
