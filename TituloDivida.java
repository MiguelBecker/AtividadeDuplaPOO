package br.com.cesarschool.poo.titulos.entidades;
import java.time.LocalDate;

public class TituloDivida extends Ativo {
	private double taxaJuros;

	public double getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	
	public double calcularPrecoTransacao(double montante) {
		return montante * (1 - taxaJuros/100);
	}
	
	public TituloDivida(int identificador, String nome, LocalDate datavalidade, double taxaJuros) {
		super(identificador, nome, datavalidade);
		this.taxaJuros = taxaJuros;
	}
}