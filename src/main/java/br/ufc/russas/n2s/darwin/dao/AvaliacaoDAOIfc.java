package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Avaliacao;

import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface AvaliacaoDAOIfc {

	Avaliacao adicionaAvaliacao(Avaliacao avaliacao);

    Avaliacao atualizaAvaliacao(Avaliacao avaliacao);

    void removeAvaliacao(Avaliacao avaliacao);

    List<Avaliacao> listaAvaliacoes(Avaliacao avaliacao);

    Avaliacao getAvaliacao(Avaliacao avaliacao);

}
