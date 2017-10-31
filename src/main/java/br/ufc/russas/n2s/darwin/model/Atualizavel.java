/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

/**
 *
 * @author Wallison Carlos
 */
public interface Atualizavel {

    /**
     * Operação resposável por atualizar uma entidade no banco de dados.
     */
    void atualiza();
}
