/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface DocumentacaoServiceIfc {
    public DocumentacaoBeans adicionaDocumentacao(DocumentacaoBeans documentacao);
    public DocumentacaoBeans atualizaDocumentacao(DocumentacaoBeans documentacao);
    public void removeDocumentacao(DocumentacaoBeans documentacao);
    public List<DocumentacaoBeans> listaTodasDocumentacoes();
    public DocumentacaoBeans getDocumentacao(long codDocumentacao);
}
