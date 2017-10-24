/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author Wallison Carlos
 * @param <T>
 */
public interface DAOIfc<T> {
    public SessionFactory getSessionFactory();
    public T adiciona(T object);
    public T atualiza(T object);
    public void remove(T object);
    public List<T> lista(Class object);
    public T getObject(Class<T> classe, long codObject);
}
