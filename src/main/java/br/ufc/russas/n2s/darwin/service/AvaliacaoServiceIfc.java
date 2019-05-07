/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.AvaliacaoBeans;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface AvaliacaoServiceIfc extends ServiceIfc{
    void removeAvaliacao(AvaliacaoBeans etapa);
    List<AvaliacaoBeans> listaTodasAvaliacoes();
    AvaliacaoBeans getAvaliacao(long codAvaliacao);
    AvaliacaoBeans atualizarAvaliacao(AvaliacaoBeans avaliacao);
	AvaliacaoBeans adicionaAvaliacao(AvaliacaoBeans avaliacao);
}
