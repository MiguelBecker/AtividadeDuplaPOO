package br.com.cesarschool.poo.titulos.entidades;
import java.time.LocalDate;

public class Ativo {
	private final int identificador;
	private String nome;
	private LocalDate datavalidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDatavalidade() {
		return datavalidade;
	}
	public void setDatavalidade(LocalDate datavalidade) {
		this.datavalidade = datavalidade;
	}
	public int getIdentificador() {
		return identificador;
	}
	
	public Ativo(int identificador, String nome, LocalDate datavalidade) {
		this.identificador = identificador;
		this.nome = nome;
		this.datavalidade = datavalidade;
	}
}