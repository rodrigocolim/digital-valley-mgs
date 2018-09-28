/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Etapa;

import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface AvaliacaoDAOIfc {

    /**
     * Método resposável por fazer a persistência de uma etapa.
     * @param etapa
     * @return Etapa
     */
	Avaliacao adicionaAvaliacao(Avaliacao avaliacao);

    /**
     * Método resposável por fazer a atualização de uma etapa.
     * @param etapa
     */
    Avaliacao atualizaAvaliacao(Avaliacao avaliacao);

    /**
     * Método resposável por fazer a remoção de uma etapa.
     * @param etapa
     */
    void removeAvaliacao(Avaliacao avaliacao);

    /**
     * Método resposável por fazer a listagem de todas as etapas.
     * @return List<Etapa>
     */
    List<Avaliacao> listaAvaliacoes(Avaliacao avaliacao);

    /**
     * Método resposável por pegar do banco de dados uma etapa a partir do
     * código informado.
     * @param codEtapa
     * @return Etapa
     */
    Avaliacao getAvaliacao(Avaliacao avaliacao);

	List<Avaliacao> getAvaliacao2(Etapa etapa);

}
