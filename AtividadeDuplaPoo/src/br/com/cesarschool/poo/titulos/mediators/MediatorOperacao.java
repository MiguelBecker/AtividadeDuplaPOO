package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MediatorOperacao {

	private static MediatorOperacao instancia;
    private final MediatorAcao mediatorAcao = MediatorAcao.getInstance();
    private final MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstancia();
    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();
    private final RepositorioTransacao repositorioTransacao = new RepositorioTransacao();
    private MediatorOperacao() {}
    
    public static MediatorOperacao getInstancia() {
        if (instancia == null) {
            instancia = new MediatorOperacao();
        }
        return instancia;
    }
    public String realizarOperacao(boolean ehAcao, int entidadeCredito, int entidadeDebito, int idAcaoOuTitulo, double valor) throws IOException {
        if (valor <= 0) {
            return "Valor inválido";
        }

        EntidadeOperadora credora = mediatorEntidadeOperadora.buscar(entidadeCredito);
        if (credora == null) {
            return "Entidade crédito inexistente";
        }

        EntidadeOperadora devedora = mediatorEntidadeOperadora.buscar(entidadeDebito);
        if (devedora == null) {
            return "Entidade débito inexistente";
        }

        if (ehAcao) {
            if (!credora.getAutorizadoAcao()) {
                return "Entidade de crédito não autorizada para ação";
            }
            if (!devedora.getAutorizadoAcao()) {
                return "Entidade de débito não autorizada para ação";
            }
            Acao acao = mediatorAcao.buscar(idAcaoOuTitulo);
            if (acao == null || acao.getValorUnitario() > valor) {
                return "Valor da operação é menor do que o valor unitário da ação";
            }
            if (devedora.getSaldoAcao() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
            credora.creditarSaldoAcao(valor);
            devedora.debitarSaldoAcao(valor);
        } else {
            TituloDivida titulo = mediatorTituloDivida.buscar(idAcaoOuTitulo);
            if (titulo == null) {
                return "Título não encontrado";
            }
            double valorOperacao = titulo.calcularPrecoTransacao(valor);
            if (devedora.getSaldoTituloDivida() < valorOperacao) {
                return "Saldo da entidade débito insuficiente";
            }
            credora.creditarSaldoTituloDivida(valorOperacao);
            devedora.debitarSaldoTituloDivida(valorOperacao);
        }
        String msgCredito = mediatorEntidadeOperadora.alterar(credora);
        if (msgCredito != null) {
            return msgCredito;
        }
        String msgDebito = mediatorEntidadeOperadora.alterar(devedora);
        if (msgDebito != null) {
            return msgDebito;
        }

        Transacao transacao = new Transacao(
            credora, devedora,
            ehAcao ? mediatorAcao.buscar(idAcaoOuTitulo) : null,
            ehAcao ? null : mediatorTituloDivida.buscar(idAcaoOuTitulo),
            valor, LocalDateTime.now()
        );
        repositorioTransacao.incluir(transacao);
        return null;
    }
    public Transacao[] gerarExtrato(int entidade) throws IOException {
        Transacao[] transacoesCredoras = repositorioTransacao.buscarPorEntidadeCredora(entidade);
        Transacao[] transacoesDevedoras = repositorioTransacao.buscarPorEntidadeCredora(entidade);
        ArrayList<Transacao> todasTransacoes = new ArrayList<>();
        todasTransacoes.addAll(Arrays.asList(transacoesCredoras));
        todasTransacoes.addAll(Arrays.asList(transacoesDevedoras));

        Transacao[] resultado = todasTransacoes.toArray(new Transacao[0]);
        Arrays.sort(resultado, Comparator.comparing(Transacao::getDataHoraOperacao).reversed());

        return resultado;
    }
}
