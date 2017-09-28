/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao.hibernate;

import br.ufc.russas.n2s.darwin.model.Arquivo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Wallison Carlos
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration conf = new Configuration();
            conf.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            conf.setProperty("hibernate.connection.url", "jdbc:postgresql://192.169.1.2:5432/n2s-ufc");
            conf.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            conf.setProperty("hibernate.connection.username", "n2s");
            conf.setProperty("hibernate.connection.password", "N2S@UFC");
            //Adicionar class nas anotações
            conf.addAnnotatedClass(Arquivo.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sessionFactory = conf.configure("br/ufc/russas/n2s/darwin/dao/hibernate/hibernate.cfg.xml").buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }else{
            return sessionFactory;
        }
    }
}
