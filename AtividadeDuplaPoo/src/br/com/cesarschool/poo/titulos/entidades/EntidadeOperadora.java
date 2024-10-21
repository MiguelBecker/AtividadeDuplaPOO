package br.com.cesarschool.poo.titulos.entidades;

public class EntidadeOperadora {
	private final long identificador;
	private String nome;
	private boolean autorizadoAcao;
	private double saldoAcao;
	private double saldoTituloDivida;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean getAutorizadoAcao() {
		return autorizadoAcao;
	}
	public void setAutorizadoAcao(boolean autorizadoAcao) {
		this.autorizadoAcao = autorizadoAcao;
	}
	public long getIdentificador() {
		return identificador;
	}
	public double getSaldoAcao() {
		return saldoAcao;
	}
	public double getSaldoTituloDivida() {
		return saldoTituloDivida;
	}
	
	public void creditarSaldoAcao(double valor) {
		saldoAcao += valor;
	}
	public void debitarSaldoAcao(double valor) {
		saldoAcao -= valor;
	}
	public void creditarSaldoTituloDivida(double valor) {
		saldoTituloDivida += valor;
	}
	public void debitarSaldoTituloDivida(double valor) {
		saldoTituloDivida -= valor;
	}

	public EntidadeOperadora(long identificador, String nome, boolean autorizadoAcao) {
		this.identificador = identificador;
		this.nome = nome;
		this.autorizadoAcao = autorizadoAcao;
	}
}