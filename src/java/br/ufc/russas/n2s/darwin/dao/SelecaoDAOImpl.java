/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.dao.hibernate.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author N2S-PC03
 */
public class SelecaoDAOImpl implements SelecaoDAO{
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private static SelecaoDAOImpl instancia;

    private SelecaoDAOImpl(){
    }
    
    public static SelecaoDAOImpl getInstancia(){
        if(instancia == null){
            return (instancia = new SelecaoDAOImpl());
        }else{
            return instancia;
        }
    }
    
    @Override
    public void adicionaSelecao(Selecao selecao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t= session.beginTransaction();
        try{
            if(selecao != null){
                session.persist(selecao);
                t.commit();
            }else{
                throw new NullPointerException("Seleção não pode ser nula!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
        
    }

    @Override
    public void atualizaSelecao(Selecao selecao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t= session.beginTransaction();
        try{
            if(selecao != null){
                session.update(selecao);
                t.commit();
            }else{
                throw new NullPointerException("Seleção não pode ser nula!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public void removeSelecao(Selecao selecao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t= session.beginTransaction();
        try{
            if(selecao != null){
                session.delete(selecao);
                t.commit();
            }else{
                throw new NullPointerException("Seleção não pode ser nula!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public List<Selecao> listaSelecao() {
        Session session = SESSION_FACTORY.openSession();
        Transaction t= session.beginTransaction();
        try{
            List<Selecao> selecoes = Collections.synchronizedList(session.createCriteria(Selecao.class).list());
            t.commit();
            return selecoes;
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public Selecao getSelecao(long codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
