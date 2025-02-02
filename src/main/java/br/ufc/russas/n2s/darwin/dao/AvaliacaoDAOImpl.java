/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Selecao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wallison Carlos
 */
@Repository("avaliacaoDAOIfc")
@Transactional
public class AvaliacaoDAOImpl implements AvaliacaoDAOIfc{

    private DAOIfc<Avaliacao> daoImpl;

    @Autowired(required = true)
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Avaliacao> dao){
        this.daoImpl = dao;
    }

    @Override
    public Avaliacao adicionaAvaliacao(Avaliacao avaliacao) {
        return this.daoImpl.adiciona(avaliacao);
    }

    @Override
    public Avaliacao atualizaAvaliacao(Avaliacao avaliacao) {
        return this.daoImpl.atualiza(avaliacao);
    }

    @Override
    public void removeAvaliacao(Avaliacao avaliacao) {
        this.daoImpl.remove(avaliacao);
    }

    @Override
    public List<Avaliacao> listaAvaliacoes(Avaliacao avaliacao) {
        return this.daoImpl.lista(avaliacao);
    }

    @Override
    public Avaliacao getAvaliacao(Avaliacao avaliacao) {
        return this.daoImpl.getObject(avaliacao, avaliacao.getCodAvaliacao());
    }
    
        
}
