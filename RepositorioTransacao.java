package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
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
	
	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) throws IOException {
	    File arquivo = new File("Transacao.txt");
	    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
	    List<Transacao> transacoes = new ArrayList<>();
	    String linha;

	    while ((linha = reader.readLine()) != null) {
	        String[] data = linha.split(";");
	        int id = Integer.parseInt(data[0]);
	        if (id == identificadorEntidadeCredito) {
	            int idCredito = Integer.parseInt(data[0]);
	            String nomeCredito = data[1];
	            boolean autorizadoCredito = Boolean.parseBoolean(data[2]);
	            double saldoCredito = Double.parseDouble(data[3]);
	            double tituloCredito = Double.parseDouble(data[4]);
	            EntidadeOperadora entidadeCredito = new EntidadeOperadora(idCredito, nomeCredito, autorizadoCredito);
	            entidadeCredito.creditarSaldoAcao(saldoCredito);
	            entidadeCredito.creditarSaldoTituloDivida(tituloCredito);

	            int idDebito = Integer.parseInt(data[5]);
	            String nomeDebito = data[6];
	            boolean autorizadoDebito = Boolean.parseBoolean(data[7]);
	            double saldoDebito = Double.parseDouble(data[8]);
	            double tituloDebito = Double.parseDouble(data[9]);
	            EntidadeOperadora entidadeDebito = new EntidadeOperadora(idDebito, nomeDebito, autorizadoDebito);
	            entidadeDebito.creditarSaldoAcao(saldoDebito);
	            entidadeDebito.creditarSaldoTituloDivida(tituloDebito);

	            int idAcao = Integer.parseInt(data[10]);
	            String nomeAcao = data[11];
	            LocalDate validadeAcao = LocalDate.parse(data[12]);
	            double valorUnitarioAcao = Double.parseDouble(data[13]);
	            Acao acao = new Acao(idAcao, nomeAcao, validadeAcao, valorUnitarioAcao);

	            int idTitulo = Integer.parseInt(data[14]);
	            String nomeTitulo = data[15];
	            LocalDate validadeTitulo = LocalDate.parse(data[16]);
	            double valorUnitarioTitulo = Double.parseDouble(data[17]);
	            TituloDivida tituloDivida = new TituloDivida(idTitulo, nomeTitulo, validadeTitulo, valorUnitarioTitulo);

	            double valorOperacao = Double.parseDouble(data[18]);
	            LocalDateTime dataHoraOperacao = LocalDateTime.parse(data[19]);

	            Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
	            transacoes.add(transacao);
	        }
	    }
	    
	    reader.close();
	    return transacoes.toArray(new Transacao[0]);
	}
}
