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
    public Documentacao adicionaDocumentacao(Documentacao documentacao);
    public Documentacao atualizaDocumentacao(Documentacao documentacao);
    public void removeDocumentacao(Documentacao documentacao);
    public List<Documentacao> listaDocumentacoes();
    public Documentacao getDocumentacao(long codDocumentacao);
}
