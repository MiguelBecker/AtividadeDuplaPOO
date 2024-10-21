package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import java.io.IOException;
import java.time.LocalDate;

public class MediatorAcao {
    private static MediatorAcao instancia;
    private final RepositorioAcao repositorioAcao = new RepositorioAcao();
    private MediatorAcao() {}
    public static MediatorAcao getInstance() {
        if (instancia == null) {
            instancia = new MediatorAcao();
        }
        return instancia;
    }
    private String validar(Acao acao) {
        if (acao.getIdentificador() <= 0 || acao.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        if (acao.getNome() == null || acao.getNome().isBlank()) {
            return "Nome deve ser preenchido.";
        }
        if (acao.getNome().length() < 10 || acao.getNome().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        if (acao.getDatavalidade().isBefore(LocalDate.now().plusDays(30))) {
            return "Data de validade deve ter pelo menos 30 dias a partir da data atual.";
        }
        if (acao.getValorUnitario() <= 0) {
            return "Valor unitário deve ser maior que zero.";
        }
        return null;
    }

    public String incluir(Acao acao) throws IOException {
        String mensagemValidacao = validar(acao);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean sucesso = repositorioAcao.incluir(acao);
        return sucesso ? null : "Ação já existente."; // Braz isso é uma expressão ternaria, ela substitui o if(true){return X}else{return Y} por meio da expressão CONDIÇÃO ? TRUE : FALSE -> a condição é uma condição que deve retornar true ou false, pode ate ser um calculo como (x > y) mas deve representar um booleano, o true é oq vai acontecer se for verdadeiro, e o false é oq vai acontecer se false, nesse caso dai se for verdadeiro vai retornar null, se for falso vai printar uma mensagem.
    }
    
    public String alterar(Acao acao) throws IOException {
        String mensagemValidacao = validar(acao);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean sucesso = repositorioAcao.alterar(acao);
        return sucesso ? null : "Ação inexistente.";
    }
    
    public String excluir(int identificador) throws IOException {
        if (identificador <= 0 || identificador >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        boolean sucesso = repositorioAcao.excluir(identificador);
        return sucesso ? null : "Ação inexistente.";
    }
    
    public Acao buscar(int identificador) throws IOException {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        return repositorioAcao.buscar(identificador);
    }
}
