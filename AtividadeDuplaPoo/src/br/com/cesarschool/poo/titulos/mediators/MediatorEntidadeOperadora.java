package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;
import java.io.IOException;

public class MediatorEntidadeOperadora {
    private static MediatorEntidadeOperadora instancia;
    private final RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
    private MediatorEntidadeOperadora() {}
    public static MediatorEntidadeOperadora getInstance() {
        if (instancia == null) {
            instancia = new MediatorEntidadeOperadora();
        }
        return instancia;
    }
    private String validar(EntidadeOperadora entidade) {
        if (entidade.getIdentificador() < 100 || entidade.getIdentificador() >= 1000000) {
            return "Identificador deve estar entre 100 e 999999.";
        }
        if (entidade.getNome() == null || entidade.getNome().isBlank()) {
            return "Nome deve ser preenchido.";
        }
        if (entidade.getNome().length() < 5 || entidade.getNome().length() > 60) {
            return "Nome deve ter entre 5 e 60 caracteres.";
        }
        return null;
    }
    public String incluir(EntidadeOperadora entidade) throws IOException {
        String mensagemValidacao = validar(entidade);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean sucesso = repositorioEntidadeOperadora.incluir(entidade);
        return sucesso ? null : "Entidade já existente.";
    }
    public String alterar(EntidadeOperadora entidade) throws IOException {
        String mensagemValidacao = validar(entidade);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean sucesso = repositorioEntidadeOperadora.alterar(entidade);
        return sucesso ? null : "Entidade inexistente.";
    }
    public String excluir(long identificador) throws IOException {
        if (identificador < 100 || identificador >= 1000000) {
            return "Identificador deve estar entre 100 e 999999.";
        }
        boolean sucesso = repositorioEntidadeOperadora.excluir(identificador);
        return sucesso ? null : "Entidade inexistente.";
    }
    public EntidadeOperadora buscar(long identificador) throws IOException {
        if (identificador < 100 || identificador >= 1000000) {
            return null;
        }
        return repositorioEntidadeOperadora.buscar(identificador);
    }
}
