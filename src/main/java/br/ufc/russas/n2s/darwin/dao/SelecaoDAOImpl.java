/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.russas.n2s.darwin.model.Selecao;

/**
 *
 * @author N2S-PC03
 */
@Repository("selecaoDAOIfc")
@Transactional
public class SelecaoDAOImpl implements SelecaoDAOIfc {

    private DAOIfc<Selecao> daoImpl;

    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Selecao> dao){
        this.daoImpl = dao;
    }

    @Override
    public Selecao adicionaSelecao(Selecao selecao) {
        Selecao s = this.daoImpl.adiciona(selecao);
        return s;
    }

    @Override
    public Selecao atualizaSelecao(Selecao selecao) {
        return this.daoImpl.atualiza(selecao);
    }

    @Override
    public void removeSelecao(Selecao selecao) {
        this.daoImpl.remove(selecao);
    }

    @Override
    public List<Selecao> listaSelecoes(Selecao selecao) {
        return this.daoImpl.lista(selecao);
    }

    @Override
    public Selecao getSelecao(Selecao selecao) {
        return this.daoImpl.getObject(selecao, selecao.getCodSelecao());
    }
    
    
    
    @Override
    public List<Selecao> getSelecoesDivulgadas(){
	    Session session;
	    session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    Criteria cr = session.createCriteria(Selecao.class);
	    cr.add(Restrictions.eq("divulgada", true)).add(Restrictions.eq("deletada",false));
	    List<Selecao> selecoes = cr.list();
	    t.commit();
	    session.close();
	    return selecoes;
    }
    
    @Override
    public void removerSelecao(Selecao selecao) {
    	Session session;
    	session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();    
	    
	    try {
	    	  String hql = "delete from Selecao where codSelecao= :sid";
	    	  Query query = session.createQuery(hql);
	    	  
	    	  query.setString("sid", selecao.getCodSelecao()+"");

	    	  t.commit();
	    	} catch (Throwable tb) {
	    	  t.rollback();
	    	  throw tb;
	    	}
    }
    

}
