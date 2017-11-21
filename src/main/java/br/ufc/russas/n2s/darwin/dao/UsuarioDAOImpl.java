/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Usuario;
import java.util.List;
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

    private DAOIfc<Usuario> daoImpl;

    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Usuario> dao){
        this.daoImpl = dao;
    }

    @Override
    public Usuario adicionaUsuario(Usuario usuario) {
        return this.daoImpl.adiciona(usuario);
    }

    @Override
    public Usuario atualizaUsuario(Usuario usuario) {
        return this.daoImpl.atualiza(usuario);
    }

    @Override
    public void removeUsuario(Usuario usuario) {
        this.daoImpl.remove(usuario);
    }

    @Override
    public List<Usuario> listaUsuarios(Usuario usuario) {
        return this.daoImpl.lista(usuario);
    }

    @Override
    public Usuario getUsuario(Usuario usuario) {
        return this.daoImpl.getObject(usuario, usuario.getCodUsuario());
    }

    @Override
    public Usuario getUsuarioControleDeAcesso(Usuario usuario) {
        Session session = this.daoImpl.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            Example example = Example.create(usuario);
            usuario = (Usuario) session.createCriteria(Usuario.class).add(example).uniqueResult();
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
