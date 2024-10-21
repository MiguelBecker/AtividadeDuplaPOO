package br.com.cesarschool.poo.titulos.entidades;
import java.time.LocalDate;

public class Acao extends Ativo {
	private double valorUnitario;

	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public double calcularPrecoTransacao(double montante) {
		return montante * valorUnitario;
	}
	
	public Acao(int identificador, String nome, LocalDate datavalidade, double valorUnitario) {
		super(identificador, nome, datavalidade);
		this.valorUnitario = valorUnitario;
	}
}