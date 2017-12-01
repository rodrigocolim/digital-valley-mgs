/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.util.List;

/**
 *
 * @author N2S-PC03
 */
public interface SelecaoDAOIfc {

    /**
     * Método resposável por fazer a persistência de uma seleção.
     * @param selecao
     * @return Selecao
     */
    Selecao adicionaSelecao(Selecao selecao);

    /**
     * Método resposável por fazer a atualização de uma seleção.
     * @param selecao
     * @return Selecao
     */
    Selecao atualizaSelecao(Selecao selecao);

    /**
     * Método resposável por fazer a remoção de uma seleção.
     * @param selecao
     */
    void removeSelecao(Selecao selecao);

    /**
     * Método resposável por fazer a listagem de todos as as seleções.
     * @return List<Selecao>
     */
    List<Selecao> listaSelecoes(Selecao selecao);

    /**
     * Método resposável por pegar do banco de dados uma
     * seleção a partir do código informadao.
     * @param codigo
     * @return
     */
    Selecao getSelecao(Selecao selecao);
    
   // List<Selecao> getMinhasSelecoes(UsuarioDarwin responsavel);

}
