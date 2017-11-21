/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface UsuarioServiceIfc {
    public UsuarioBeans adicionaUsuario(UsuarioBeans usuario);
    public UsuarioBeans atualizaUsuario(UsuarioBeans usuario);
    public void removeUsuario(UsuarioBeans usuario);
    public List<UsuarioBeans> listaTodosUsuarios();
    public UsuarioBeans getUsuario(long codUsuario, long codUsuarioControleDeAcesso);
    public UsuarioBeans getUsuarioControleDeAcesso(long codUsuarioControleDeAcesso);
}
