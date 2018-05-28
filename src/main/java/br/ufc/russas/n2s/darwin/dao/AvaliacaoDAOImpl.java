/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Avaliacoes;
import br.ufc.russas.n2s.darwin.model.Etapa;

import java.util.List;

import org.hibernate.Query;
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
    
    @Override
    public List<Avaliacao> getAvaliacao2 (Etapa etapa) {
    	String hql = "SELECT avaliacao FROM Avaliacao as avaliacao, Avaliacoes as avaliacoes WHERE avaliacoes.etapa = "+etapa.getCodEtapa()+" AND avaliacao.codAvaliacao = avaliacoes.avaliacao";
    	Session session = this.daoImpl.getSessionFactory().openSession();
    	Query query = session.createQuery(hql);
    	Transaction t = session.beginTransaction();
        try {
            List<Avaliacao> objetos = query.list();
            t.commit();
            return objetos;
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }	
    }
    /*
    public static void main(String[] args) {
		AvaliacaoDAOImpl avaliacao = new AvaliacaoDAOImpl();
		Etapa etapa = new Etapa();
		etapa.setCodEtapa(7);
		List<Avaliacao> lista = avaliacao.getAvaliacao2(etapa);
		for(Avaliacao item:lista) {
			System.out.println(item.getCodAvaliacao());
		}
	}
	*/
    
}
