/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
import br.ufc.russas.n2s.darwin.model.EstadoSelecao;
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
    public List<Selecao> listaSelecoesIgnorandoNotas(Selecao selecao) {
	    Session session;
	    session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    try {
		    Example example = Example.create(selecao).excludeZeroes().ignoreCase();
		    example.excludeProperty("exibirNotas");
		    		example.excludeProperty("divulgadoResultado");
	        List<Selecao> selecoes = (List<Selecao> ) session.createCriteria(selecao.getClass()).add(example).list();
	        System.out.println("tam: "+selecoes.size());
	        t.commit();
	        
		    return selecoes;
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally{
        	session.close();
        }
    }
    
    @Override
    public List<Selecao> listaSelecoesIgnorandoBooleanos() {
	    Session session;
	    session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    Selecao selecao = new Selecao();
	    try {
		    Example example = Example.create(selecao).excludeZeroes().ignoreCase().excludeProperty("exibirnotas")
		    		.excludeProperty("divulgadoresultado");
		    example.excludeProperty("divulgada");
		    //example.excludeProperty("estado");
	        List<Selecao> selecoes = (List<Selecao> ) session.createCriteria(selecao.getClass()).add(example).list();
	        t.commit();
		    
		    
		    for (Selecao s: selecoes) {
		    	System.out.println(s.getCodSelecao());
		    }
		    return selecoes;
           
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally{
        	session.close();
        }
    }
    
    @Override
    public List<Selecao> buscaTodasPorCriteria(boolean divulgada) {
    	Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            List<Selecao> selecoes;
            Criteria c = session.createCriteria(Selecao.class);
        	c.add(Restrictions.eq("deletada", false));
        	c.add(Restrictions.eq("divulgada", divulgada));
        	c.addOrder(Order.asc("titulo"));
        	selecoes = (List<Selecao>) c.list();
            return selecoes;
        } catch(RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<Selecao> buscaTodasPorCriteria() {
    	Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            List<Selecao> selecoes;
            Criteria c = session.createCriteria(Selecao.class);
        	c.add(Restrictions.eq("deletada", false));
        	c.addOrder(Order.asc("titulo"));
        	selecoes = (List<Selecao>) c.list();
            return selecoes;
        } catch(RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    @Override
    public void divulgaResutadoSelecao(Selecao selecao) {
    	Session session;
    	session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();    
	    
	    try {
	    	 // String hql = "delete from Selecao where codSelecao= :sid";
	    	  String hql = "UPDATE darwin.selecao SET divulgadoresultado=:verdade WHERE codselecao=:codS";
	    	  
	    	  SQLQuery query = session.createSQLQuery(hql);// createSqlQuery(hql);
	    	  query.setParameter("verdade", true);
	    	  query.setParameter("codS", selecao.getCodSelecao());
	    	  
	    	
	    	  query.executeUpdate();
	    	  t.commit();
	    	} catch (Throwable tb) {
	    	  t.rollback();
	    	  throw tb;
	    	}
    }
    
    @Override
    public void atualizaExibirNotas(Selecao selecao) {
    	Session session;
    	session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();    
	    
	    try {
	    	  String hql = "UPDATE darwin.selecao SET exibirnotas=:exibe WHERE codselecao=:codS";
	    	  
	    	  SQLQuery query = session.createSQLQuery(hql);// createSqlQuery(hql);
	    	  query.setParameter("exibe", !selecao.isExibirNotas());
	    	  query.setParameter("codS", selecao.getCodSelecao());
	    	
	    	  query.executeUpdate();
	    	  t.commit();
	    	} catch (Throwable tb) {
	    	  t.rollback();
	    	  throw tb;
	    	}
    }
    
    
    @Override
    public List<Selecao> BuscaSelecoesPorNome(String titulo) {
    	Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            List<Selecao> selecoes;
            Criteria c = session.createCriteria(Selecao.class);
        	c.add(Restrictions.ilike("titulo", "%"+titulo+"%"));
        	c.add(Restrictions.eq("deletada", false));
        	c.addOrder(Order.asc("titulo"));
        	selecoes = (List<Selecao>) c.list();
            return selecoes;
        } catch(RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

	@Override
	public List<Selecao> listaSelecoes(EnumEstadoSelecao estado, int inicio, int qtd) {
		Session session =  this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Selecao> lis = new ArrayList<>();

        try {
        	
    		Criteria cb = session.createCriteria(Selecao.class);
    		cb.add(Restrictions.eq("divulgada", true));
    		cb.add(Restrictions.eq("deletada",false));
            cb.addOrder(Order.desc("codSelecao"));
            
            if(estado != null){
            	cb.add(Restrictions.eq("estado", estado));
            }
            
            cb.setFirstResult(inicio);
            cb.setMaxResults(qtd);
                        
            lis = cb.list();
            
            t.commit();
            
        } catch (RuntimeException ex) {
            t.rollback();
            throw ex;
        } finally {
            session.close();
        }
        
        return lis;
	}

	@Override
	public Long getQuantidade(EnumEstadoSelecao estado) {
		Session session =  this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long qtd = 0;
        
        try {
        	Query qry;
        	if(estado != null){
        		qry = session.createQuery("select count(s) from Selecao s where s.estado = :estado and s.divulgada = 'true' and s.deletada = 'false'");
        		qry.setParameter("estado", estado.getEstado());
        	} else {
        		qry = session.createQuery("select count(s) from Selecao s where s.divulgada = 'true' and s.deletada = 'false'");
        	}
        	            
        	try{
        		qtd = (Long) qry.uniqueResult();
        	} catch(Exception e){
        		System.err.println("Sem resultados");
        	}
        	
            t.commit();
            
        } catch (RuntimeException ex) {
            t.rollback();
            throw ex;
        } finally {
            session.close();
        }
        
        return qtd;
	}
    

}
