/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class UsuarioBeans implements Beans{
    
    private long codUsuario;
    private long codUsuarioControleDeAcesso;
    private String nome;
    private List<EnumPermissao> permissoes;

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public List<EnumPermissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<EnumPermissao> permissoes) {
        this.permissoes = permissoes;
    }

    public long getCodUsuarioControleDeAcesso() {
        return codUsuarioControleDeAcesso;
    }

    public void setCodUsuarioControleDeAcesso(long codUsuarioControleDeAcesso) {
        this.codUsuarioControleDeAcesso = codUsuarioControleDeAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
        
    
    
    @Override
    public Object toBusiness() {
        UsuarioDarwin usuario = new UsuarioDarwin();
        if (this.getCodUsuario() > 0) {
            usuario.setCodUsuario(this.getCodUsuario());
        }
        if (this.getCodUsuarioControleDeAcesso()> 0) {
            usuario.setCodUsuarioControleDeAcesso(this.getCodUsuarioControleDeAcesso());
        }
        usuario.setNome(this.getNome());
        usuario.setPermissoes(this.getPermissoes());
        return usuario;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof UsuarioDarwin){
                UsuarioDarwin usuario = (UsuarioDarwin) object;
                this.setCodUsuario(usuario.getCodUsuario());
                this.setPermissoes(usuario.getPermissoes());
                this.setCodUsuarioControleDeAcesso(usuario.getCodUsuarioControleDeAcesso());
                this.setNome(usuario.getNome());
                return this;
            }else{
                throw new IllegalArgumentException("Isso não é um usuário!");
            }
        }else{
            throw new NullPointerException("Usuário não pode ser vazio!");
        }  
    }
    
    @Override
    public boolean equals(Object o) {
        return this.getCodUsuario() == ((UsuarioBeans) o).getCodUsuario() ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.codUsuario ^ (this.codUsuario >>> 32));
        return hash;
    }
}
