/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Wallison Carlos
 * @param <T>
 */
@Repository("daoImpl")
public class DAOImpl<T> implements DAOIfc<T> {

    private SessionFactory sessionFactory;

    /**
     * Método Construtor padrão da classe DAOImpl.
     */
    public DAOImpl() {

    }

    @Override
    public SessionFactory getSessionFactory()  {
        return sessionFactory;
    }

    /**
     *
     * @param sf
     */
    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public T adiciona(T object) {
        Session session = getSessionFactory().openSession();
        try {
            if (object != null) {
                session.persist(object);
                return object;
            } else {
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public T atualiza(T object) {
        Session session = getSessionFactory().openSession();
        try {
            if (object != null) {
                session.update(object);
                return object;
            } else {
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(T object) {
        Session session = getSessionFactory().openSession();
        try {
            if (object != null) {
                session.delete(object);
            } else {
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<T> lista(T object) {
        Session session = getSessionFactory().openSession();
        try {
<<<<<<< HEAD
            Example example = Example.create(object).excludeZeroes();
            List<T> objetos = session.createCriteria(object.getClass()).add(example).list();
            t.commit();
            return objetos;
=======
            Criteria criteria = session.createCriteria(object);
            return criteria.list();
>>>>>>> b7b6a5a500dd02507394c94ded36395de7c416f9
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public T getObject(T object, long codObject) {
        Session session = getSessionFactory().openSession();
        try {
<<<<<<< HEAD
            //Example example = Example.create(object).excludeZeroes();
           T o = (T) session.get(object.getClass(), codObject);;
           // T o = (T) session.createCriteria(object.getClass()).add(Restrictions.eq("codSelecao", codObject));
            t.commit();
            return o;
=======
            T object = (T) session.get(classe, codObject);
            return object;
>>>>>>> b7b6a5a500dd02507394c94ded36395de7c416f9
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
