package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;
import java.io.IOException;
import java.time.LocalDate;

public class MediatorTituloDivida {

    private static MediatorTituloDivida instancia;
    private final RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();
    private MediatorTituloDivida() {}
    public static MediatorTituloDivida getInstancia() {
        if (instancia == null) {
            instancia = new MediatorTituloDivida();
        }
        return instancia;
    }
    private String validar(TituloDivida titulo) {
        if (titulo.getIdentificador() <= 0 || titulo.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        if (titulo.getNome() == null || titulo.getNome().isBlank()) {
            return "Nome deve ser preenchido.";
        }
        if (titulo.getNome().length() < 10 || titulo.getNome().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        if (titulo.getDatavalidade().isBefore(LocalDate.now().plusDays(180))) {
            return "Data de validade deve ter pelo menos 180 dias na frente da data atual.";
        }
        if (titulo.getTaxaJuros() <= 0) {
            return "Valor unitário deve ser maior que zero.";
        }
        return null;
    }
    public String incluir(TituloDivida titulo) throws IOException {
        String mensagemValidacao = validar(titulo);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean sucesso = repositorioTituloDivida.incluir(titulo);
        return sucesso ? null : "Título já existente.";
    }
    public String alterar(TituloDivida titulo) throws IOException {
        String mensagemValidacao = validar(titulo);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean sucesso = repositorioTituloDivida.alterar(titulo);
        return sucesso ? null : "Título inexistente.";
    }
    public String excluir(int identificador) throws IOException {
        if (identificador <= 0 || identificador >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        boolean sucesso = repositorioTituloDivida.excluir(identificador);
        return sucesso ? null : "Título inexistente.";
    }
    public TituloDivida buscar(int identificador) throws IOException {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        return repositorioTituloDivida.buscar(identificador);
    }
}
