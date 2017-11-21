/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.dao.UsuarioDAOIfc;
import br.ufc.russas.n2s.darwin.model.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Wallison Carlos
 */
public class UsuarioServiceImpl implements UsuarioServiceIfc {

    private UsuarioDAOIfc usuarioDAOIfc;

    public UsuarioDAOIfc getUsuarioDAOIfc() {
        return usuarioDAOIfc;
    }

    @Autowired(required = true)
    public void setUsuarioDAOIfc(@Qualifier("usuarioDAOIfc")UsuarioDAOIfc usuarioDAOIfc) {
        this.usuarioDAOIfc = usuarioDAOIfc;
    }
    
    
    @Override
    public UsuarioBeans adicionaUsuario(UsuarioBeans usuario) {
        return (UsuarioBeans) new UsuarioBeans().toBeans(this.getUsuarioDAOIfc().adicionaUsuario((Usuario)usuario.toBusiness()));
    }

    @Override
    public UsuarioBeans atualizaUsuario(UsuarioBeans usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUsuario(UsuarioBeans usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioBeans> listaTodosUsuarios() {
        List<Usuario> result = this.getUsuarioDAOIfc().listaUsuarios(new Usuario());
        List<UsuarioBeans> usuarios = Collections.synchronizedList(new ArrayList<UsuarioBeans>());
        for(Usuario usuario : result){
            usuarios.add((UsuarioBeans) new UsuarioBeans().toBeans(usuario));
        }
        return usuarios;
    }

    @Override
    public UsuarioBeans getUsuario(long codUsuario, long codUsuarioControleDeAcesso) {
        Usuario usuario = new Usuario();
        usuario.setCodUsuario(codUsuario);
        usuario.setCodUsuarioControleDeAcesso(codUsuarioControleDeAcesso);
        Usuario u = this.getUsuarioDAOIfc().getUsuario(usuario);
        if(u != null){
            return (UsuarioBeans) new UsuarioBeans().toBeans(u);
        }else{
            return null;
        }
    }
    
    @Override
    public UsuarioBeans getUsuarioControleDeAcesso(long codUsuarioControleDeAcesso) {
        Usuario usuario = new Usuario();
        usuario.setCodUsuarioControleDeAcesso(codUsuarioControleDeAcesso);
        Usuario u = this.getUsuarioDAOIfc().getUsuario(usuario);
        if(u != null){
            return (UsuarioBeans) new UsuarioBeans().toBeans(u);
        }else{
            return null;
        }
    }
    
}
