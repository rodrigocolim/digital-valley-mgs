/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Documentacao;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface DocumentacaoDAOIfc {

    /**
     * Método resposável por fazer a persistência de uma documentacao.
     * @param documentacao
     * @return
     */
    Documentacao adicionaDocumentacao(Documentacao documentacao);

    /**
     * Método resposável por fazer a atualização de uma documentacao.
     * @param documentacao
     * @return
     */
    Documentacao atualizaDocumentacao(Documentacao documentacao);

    /**
     * Método resposável por fazer a remoção de uma documentacao.
     * @param documentacao
     */
    void removeDocumentacao(Documentacao documentacao);

    /**
     * Método resposável por fazer a listagem de todas as documentações.
     * @return List<Documentacao>
     */
    List<Documentacao> listaDocumentacoes(Documentacao documentacao);

    /**
     * Método resposável por pegar do banco de dados uma documentacao a
     * partir do código informado.
     * @param codDocumentacao
     * @return Documentacao
     */
    Documentacao getDocumentacao(Documentacao documentacao);
}
