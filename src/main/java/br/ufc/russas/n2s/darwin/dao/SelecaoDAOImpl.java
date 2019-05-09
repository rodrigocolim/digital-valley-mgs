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
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
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
    @SuppressWarnings("unchecked")
    public List<Selecao> getSelecoesDivulgadas(){
	    Session session;
	    session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    List<Selecao> selecoes = new ArrayList<>();
	    
	    try{
	    	Criteria cr = session.createCriteria(Selecao.class);
		    cr.add(Restrictions.eq("divulgada", true)).add(Restrictions.eq("deletada",false));
		    selecoes = cr.list();
		    t.commit();
	    } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally{
        	session.close();
        }
	    return selecoes;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Selecao> listaSelecoesIgnorandoNotas(Selecao selecao) {
	    Session session;
	    session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    
	    try {
		    Example example = Example.create(selecao).excludeZeroes().ignoreCase();
		    example.excludeProperty("exibirNotas");
		    		example.excludeProperty("divulgadoResultado");
	        List<Selecao> selecoes = (List<Selecao> ) session.createCriteria(selecao.getClass()).add(example).list();
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
    @SuppressWarnings("unchecked")
    public List<Selecao> listaSelecoesIgnorandoBooleanos() {
	    Session session;
	    session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();
	    Selecao selecao = new Selecao();
	    try {
		    Example example = Example.create(selecao).excludeZeroes().ignoreCase().excludeProperty("exibirnotas")
		    		.excludeProperty("divulgadoresultado");
		    example.excludeProperty("divulgada");
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
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
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
			 
			String hql = "UPDATE darwin.selecao SET divulgadoresultado=:verdade WHERE codselecao=:codS";
		  
			SQLQuery query = session.createSQLQuery(hql);
			query.setParameter("verdade", true);
			query.setParameter("codS", selecao.getCodSelecao());
		  
		
			query.executeUpdate();
			t.commit();
		} catch (Throwable tb) {
			t.rollback();
			throw tb;
		} finally {
			session.close();
		}
    }
    
    @Override
    public void atualizaExibirNotas(Selecao selecao) {
    	Session session;
    	session = this.daoImpl.getSessionFactory().openSession();
	    Transaction t = session.beginTransaction();    
	    
	    try {
			String hql = "UPDATE darwin.selecao SET exibirnotas=:exibe WHERE codselecao=:codS";
			  
			SQLQuery query = session.createSQLQuery(hql);
			query.setParameter("exibe", !selecao.isExibirNotas());
			query.setParameter("codS", selecao.getCodSelecao());
			
			query.executeUpdate();
	    	t.commit();
    	} catch (Throwable tb) {
    	  t.rollback();
    	  throw tb;
    	} finally {
    		session.close();
    	}
    }
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Selecao> listaSelecoes(String categoria, EnumEstadoSelecao estado, int inicio, int qtd) {
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
            if(categoria != null){
            	cb.add(Restrictions.eq("categoria", categoria));
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
	public Long getQuantidade(String categoria, EnumEstadoSelecao estado) {
		Session session =  this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long qtd = 0;

        try {
        	Query qry;
        	if(estado != null){
        		String hql = "select count(s) from Selecao s where s.estado = :estado and s.divulgada = 'true' and s.deletada = 'false'";
        		
        		if(categoria != null){
        			hql += " and categoria = " + categoria;
                }
        		
        		qry = session.createQuery(hql);
        		qry.setParameter("estado", estado.getEstado());
        		
        	} else {
        		String hql = "select count(s) from Selecao s where s.divulgada = 'true' and s.deletada = 'false'";
        		
        		if(categoria != null){
                	hql +=  " and categoria = '" + categoria +"'";
                }
        		qry = session.createQuery(hql);
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
    
	@Override
	@SuppressWarnings("unchecked")
    public List<Selecao> buscarSelecoesPorNome(String titulo, int inicio, int qtd) {
    	Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Selecao> selecoes = new ArrayList<>();
        try {
            
            Criteria c = session.createCriteria(Selecao.class);
        	c.add(Restrictions.ilike("titulo", "%"+titulo+"%"));
        	c.add(Restrictions.eq("divulgada", true));
        	c.add(Restrictions.eq("deletada", false));
        	c.addOrder(Order.desc("codSelecao"));
        	
        	c.setFirstResult(inicio);
            c.setMaxResults(qtd);
            
        	selecoes = c.list();
        	t.commit();
            
        } catch(RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
        return selecoes;
    }
	
	@Override
	public Long getQuantidadePorNome(String titulo){
		Session session =  this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long qtd = 0;

        try {
        	Query qry = session.createQuery("select count(s) from Selecao s where s.titulo ilike '%" + titulo +"%' and s.divulgada = 'true' and s.deletada = 'false'");
        	            
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Selecao> buscarSelecoesAssociada(Long idUsuario, int inicio, int qtd) {
		Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Selecao> selecoes = new ArrayList<>();
        try {
            
            Criteria c = session.createCriteria(Selecao.class);
            
        	c.add(Restrictions.ilike("titulo", "%"+titulo+"%"));
        	c.add(Restrictions.eq("divulgada", true));
        	c.add(Restrictions.eq("deletada", false));
        	c.addOrder(Order.desc("codSelecao"));
        	
        	c.setFirstResult(inicio);
            c.setMaxResults(qtd);
            
        	selecoes = c.list();
        	t.commit();
            
        } catch(RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
        return selecoes;
	}

	@Override
	public Long getQuantidadeAssociada(Long idUsuario) {
		Session session =  this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long qtd = 0;

        try {
        	Query qry;
        	
        	long qtdParticipando = 0;
        	String sqlResponsavelSelecao = "select count(distinct s) " +
											"from darwin.selecao as s " +
											"inner join darwin.responsaveis_selecao as rs on (s.codselecao = rs.selecao) " +
											"inner join darwin.usuario as u on (u.codusuario = rs.usuario) " +
											"where u.codusuario = ? and s.divulgada = 'true' and s.deletada = 'false';";
        	
        	qry = session.createSQLQuery(sqlResponsavelSelecao).addScalar("count", LongType.INSTANCE)
        														.setLong(0, idUsuario);
        													
        	
        	try{
        		qtdParticipando = (Long) qry.uniqueResult();
        	} catch(Exception e){
        		System.err.println("Sem resultados");
        	}
        	
        	long qtdResponsavel = 0;
        	String sqlParticipandoSelecao = "select count(distinct s) " +
											"from darwin.participante as p " +
											"inner join darwin.participantes_etapa as pe on (pe.participante = p.codparticipante) " +
											"inner join darwin.etapas_selecao as es on (es.etapa = pe.etapa) " +
											"inner join darwin.selecao as s on (s.codselecao = es.selecao) " +
											"where p.candidato = ? and s.divulgada = 'true' and s.deletada = 'false';";
        	
        	
        	qry = session.createSQLQuery(sqlParticipandoSelecao).addScalar("count", LongType.INSTANCE)
															   .setLong(0, idUsuario);
        	
        	try{
        		qtdResponsavel = (Long) qry.uniqueResult();
        	} catch(Exception e){
        		System.err.println("Sem resultados");
        	}
        	
        	long qtdAvalidor = 0;
        	String sqlAvaliadorSelecao = 	"select count(distinct s) " +
											"from darwin.selecao as s " +
											"inner join darwin.etapas_selecao as es on (es.selecao = s.codselecao) " + 
											"inner join darwin.avaliadores as ava on (es.etapa = ava.etapa) " +
											"where ava.avaliador = 207 and s.divulgada = 'true' and s.deletada = 'false';";
        	
        	
        	qry = session.createSQLQuery(sqlAvaliadorSelecao).addScalar("count", LongType.INSTANCE)
															   .setLong(0, idUsuario);
        	
        	try{
        		qtdAvalidor = (Long) qry.uniqueResult();
        	} catch(Exception e){
        		System.err.println("Sem resultados");
        	}
        	            
        	qtd = qtdParticipando + qtdResponsavel + qtdAvalidor;
        	
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
