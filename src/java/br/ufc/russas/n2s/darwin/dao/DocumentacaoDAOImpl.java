/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.dao.hibernate.HibernateUtil;
import br.ufc.russas.n2s.darwin.model.Documentacao;
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
public class DocumentacaoDAOImpl implements DocumentacaoDAO{
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    
    private static DocumentacaoDAOImpl instancia;
    
    private DocumentacaoDAOImpl(){}
    
    public static DocumentacaoDAOImpl getInstancia(){
        if(instancia == null){
            return (instancia = new DocumentacaoDAOImpl());
        }else{
            return instancia;
        }
    }

    @Override
    public void adicionaDocumentacao(Documentacao documentacao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(documentacao != null){
                session.persist(documentacao);
                t.commit();
            }else{
                throw new NullPointerException("Documentação não pode ser nula!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public void atualizaDocumentacao(Documentacao documentacao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(documentacao != null){
                session.update(documentacao);
                t.commit();
            }else{
                throw new NullPointerException("Documentação não pode ser nula!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public void removeDocumentacao(Documentacao documentacao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        try{
            if(documentacao != null){
                session.delete(documentacao);
                t.commit();
            }else{
                throw new NullPointerException("Documentação não pode ser nula!");
            }
        }catch(RuntimeException e){
            t.rollback();
            throw e;
        }
    }

    @Override
    public List<Documentacao> listaDocumentacoes() {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        List<Documentacao> documentacoes = Collections.synchronizedList(session.createCriteria(Documentacao.class).list());
        t.commit();
        return documentacoes;
    }

    @Override
    public Documentacao getDocumentacao(long codDocumentacao) {
        Session session = SESSION_FACTORY.openSession();
        Transaction t = session.beginTransaction();
        Documentacao documentacao = (Documentacao) session.get(Documentacao.class, codDocumentacao);
        t.commit();
        return documentacao;
    }
}
