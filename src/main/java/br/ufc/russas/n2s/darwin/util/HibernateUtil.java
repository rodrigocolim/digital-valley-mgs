/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.util;

import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Periodo;
import br.ufc.russas.n2s.darwin.model.Periodo;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.Usuario;
import br.ufc.russas.n2s.darwin.model.Usuario;
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
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration conf = new Configuration();
            conf.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            conf.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/darwin");
            conf.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            conf.setProperty("hibernate.connection.username", "n2s");
            conf.setProperty("hibernate.connection.password", "N2S@UFC");
            //Adicionar class nas anotações
            conf.addAnnotatedClass(Arquivo.class);
            conf.addAnnotatedClass(Documentacao.class);
            conf.addAnnotatedClass(Periodo.class);
            conf.addAnnotatedClass(Usuario.class);
            conf.addAnnotatedClass(Participante.class);       
            conf.addAnnotatedClass(Avaliacao.class);       
            conf.addAnnotatedClass(Etapa.class);
            conf.addAnnotatedClass(Selecao.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sessionFactory = conf.configure("/hibernate.cfg.xml").buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}
