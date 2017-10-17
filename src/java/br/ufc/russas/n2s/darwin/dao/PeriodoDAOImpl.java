/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.dao.hibernate.HibernateUtil;
import br.ufc.russas.n2s.darwin.model.Periodo;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Wallison Carlos
 */
public class PeriodoDAOImpl implements PeriodoDAO{
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private static PeriodoDAOImpl instancia;
    
    private PeriodoDAOImpl(){}
    
    public static PeriodoDAOImpl getInstancia(){
        if(instancia == null){
            return (instancia = new PeriodoDAOImpl());
        }else{
            return instancia;
        }
    }

    @Override
    public void adicionaPeriodo(Periodo periodo) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(periodo != null){
                session.persist(periodo);
                t.commit();
            }else{
                throw new NullPointerException("Período não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public void atualizaPeriodo(Periodo periodo) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(periodo != null){
                session.update(periodo);
                t.commit();
            }else{
                throw new NullPointerException("Período não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public void removePeriodo(Periodo periodo) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(periodo != null){
                session.delete(periodo);
                t.commit();
            }else{
                throw new NullPointerException("Período não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Periodo> listaPeriodos() {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        List<Periodo> periodos = Collections.synchronizedList(session.createCriteria(Periodo.class).list());
        t.commit();
        return periodos;
    }

    @Override
    public Periodo getPeriodo(long codPeriodo) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        Periodo periodo = (Periodo) session.get(Periodo.class, codPeriodo);
        t.commit();
        return periodo;
    }
}
