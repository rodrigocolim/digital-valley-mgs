/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

/**
 *
 * @author Wallison Carlos
 */
public class UsuarioDarwinProxy extends UsuarioDarwin{
    private UsuarioDarwin usuario;
    
    public UsuarioDarwinProxy(UsuarioDarwin usuario) {
        setUsuario(usuario);
    }

    public UsuarioDarwin getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDarwin usuario) {
        if (usuario != null) {
            this.usuario = usuario;
        } else {
            throw new NullPointerException("Usuário do Darwin não pode ser vazio!");
        }
    }
    
    @Override
    public void adicionaNivel(UsuarioDarwin usuario, EnumPermissao permissao) throws IllegalAccessException{
        if (getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
            super.adicionaNivel(usuario, permissao);
        } else {
            throw new IllegalAccessException("Você não é um administrador do Darwin!");
        }
    }
    
    @Override
    public void removeNivel(UsuarioDarwin usuario, EnumPermissao permissao) throws IllegalAccessException{
        if (getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
            super.removeNivel(usuario, permissao);
        } else {
            throw new IllegalAccessException("Você não é um administrador do Darwin!");
        }
    }
}
