/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.filter;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;

/**
 *
 * @author Wallison Carlos
 */
public class HibernateFilter extends OpenSessionInViewFilter {

 protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
    Session session = SessionFactoryUtils.getSession(sessionFactory, true);
    //set the FlushMode to auto in order to save objects.
    session.setFlushMode(FlushMode.AUTO);
    return session;
 }
 
 
 protected void closeSession(Session session, SessionFactory sessionFactory) {
  try{
   if (session != null && session.isOpen() && session.isConnected()) {
    try {
     session.flush();
    } catch (HibernateException e) {
     throw new CleanupFailureDataAccessException("Failed to flush session before close: " + e.getMessage(), e);
    } catch(Exception e){}
    }
  } finally{
  }
 }
}
