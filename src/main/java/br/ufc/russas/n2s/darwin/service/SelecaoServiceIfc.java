/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface SelecaoServiceIfc {

    /**
     *
     * @param selecao
     * selecao - uma nova SelecaoBeans a ser armazenada
     * @return SelecaoBeans
     */
    SelecaoBeans adicionaSelecao(SelecaoBeans selecao);
    
    /**
     *
     * @param selecao
     * selecao - uma SelecaoBeans para ser atualizada
     * @return SelecaoBeans
     */
    SelecaoBeans atualizaSelecao(SelecaoBeans selecao);

    /**
     *
     * @param selecao
     * selecao - Uma SelecaoBeans a ser removida
     */
    void removeSelecao(SelecaoBeans selecao);

    /**
     *
     * @return  List
     */
    List<SelecaoBeans> listaNovasSelecoes();

    /**
     *
     * @return List
     */
    List<SelecaoBeans> listaTodasSelecoes();

    /**
     *
     * @param codSelecao
     * codSelecao - Identificador único da seleção que queira buscar
     * @return SelecaoBeans
     */
    SelecaoBeans getSelecao(long codSelecao);
    
    

}
