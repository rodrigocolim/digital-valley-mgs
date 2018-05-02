/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Wallison Carlos
 */
public class UsuarioDAOImpl implements UsuarioDAOIfc{

    private DAOIfc<UsuarioDarwin> daoImpl;

    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<UsuarioDarwin> dao){
        this.daoImpl = dao;
    }

    @Override
    public UsuarioDarwin adicionaUsuario(UsuarioDarwin usuario) {
        return this.daoImpl.adiciona(usuario);
    }

    @Override
    public UsuarioDarwin atualizaUsuario(UsuarioDarwin usuario) {
        return this.daoImpl.atualiza(usuario);
    }

    @Override
    public void removeUsuario(UsuarioDarwin usuario) {
        this.daoImpl.remove(usuario);
    }

    @Override
    public List<UsuarioDarwin> listaUsuarios(UsuarioDarwin usuario) {
        return this.daoImpl.lista(usuario);
    }
    
    @Override
    public List<UsuarioDarwin> listaUsuariosPeloNome(String busca) {
    	  Session session = this.daoImpl.getSessionFactory().openSession();
          Transaction t = session.beginTransaction();
          try {
        	  Query query = session.createQuery("from UsuarioDarwin u where lower(u.nome) like :nome");
        	  query.setParameter("nome", '%'+busca.toLowerCase()+'%');
              List<UsuarioDarwin> objetos = query.list();
              t.commit();
              return objetos;
          } catch (RuntimeException e) {
              t.rollback();
              throw e;
          } finally {
              session.close();
          }   
    }

    @Override
    public UsuarioDarwin getUsuario(UsuarioDarwin usuario) {
        return this.daoImpl.getObject(usuario, usuario.getCodUsuario());
    }

    @Override
    public UsuarioDarwin getUsuarioControleDeAcesso(UsuarioDarwin usuario) {
        Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            Example example = Example.create(usuario);
            usuario = (UsuarioDarwin) session.createCriteria(UsuarioDarwin.class).add(example).uniqueResult();
            t.commit();
            return usuario;
        } catch(RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    
    
}
