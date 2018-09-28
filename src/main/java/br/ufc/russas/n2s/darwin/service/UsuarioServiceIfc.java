/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface UsuarioServiceIfc extends ServiceIfc{
    UsuarioBeans adicionaUsuario(UsuarioBeans usuario);
    UsuarioBeans atualizaUsuario(UsuarioBeans usuario);
    void removeUsuario(UsuarioBeans usuario);
    List<UsuarioBeans> listaTodosUsuarios();
    List<UsuarioBeans> BuscaUsuariosPorNome(String nome);
    UsuarioBeans getUsuario(long codUsuario, long codUsuarioControleDeAcesso);
    UsuarioBeans getUsuarioControleDeAcesso(long codUsuarioControleDeAcesso);
    void adicionaNivel(UsuarioBeans usuario, EnumPermissao permissao) throws IllegalAccessException;
    void atualizaNiveis(UsuarioBeans usuario, List<EnumPermissao> permissoes) throws IllegalAccessException;
    void removeNivel(UsuarioBeans usuario, EnumPermissao permissao) throws IllegalAccessException;
    List<UsuarioBeans> listaUsuariosComPermissao(EnumPermissao permisssao);
}
