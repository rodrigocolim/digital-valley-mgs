/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Documentacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Wallison Carlos
 */

public class DocumentacaoDAOImpl implements DocumentacaoDAOIfc{

    private DAOIfc<Documentacao> daoImpl;
     
    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Documentacao> dao){
        this.daoImpl = dao;
    }
    
    @Override
    public Documentacao adicionaDocumentacao(Documentacao documentacao) {
        return this.daoImpl.adiciona(documentacao);
    }

    @Override
    public Documentacao atualizaDocumentacao(Documentacao documentacao) {
        return this.daoImpl.atualiza(documentacao);
    }

    @Override
    public void removeDocumentacao(Documentacao documentacao) {
        this.daoImpl.remove(documentacao);
    }

    @Override
    public List<Documentacao> listaDocumentacoes() {
        return this.daoImpl.lista(Documentacao.class);
    }

    @Override
    public Documentacao getDocumentacao(long codDocumentacao) {
        return this.daoImpl.getObject(Documentacao.class, codDocumentacao);
    }
   
}
