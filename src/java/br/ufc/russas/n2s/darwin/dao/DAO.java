/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.dao.hibernate.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Wallison Carlos
 */
public class DAO<T>{
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private static DAO instancia;
    
    private DAO(){}
    
    public static DAO getInstancia(){
        if(instancia == null){
            return (instancia = new DAO());
        }else{
            return instancia;
        }
    }
    
    public T adiciona(T object) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(object != null){
                session.persist(object);
                t.commit();
                return object;
            }else{
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public T atualiza(T object) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(object != null){
                session.update(object);
                t.commit();
                return object;
            }else{
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public void remove(T object) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(object != null){
                session.delete(object);
                t.commit();
            }else{
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public List<T> lista(Class object) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            List<T> objects = Collections.synchronizedList(session.createCriteria(object.getClass()).list());
            t.commit();
            return objects;
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    
    public T getObject(Class<T> classe, long codObject) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            T object = (T) session.get(classe, codObject);
            t.commit();
            return object;
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
}
