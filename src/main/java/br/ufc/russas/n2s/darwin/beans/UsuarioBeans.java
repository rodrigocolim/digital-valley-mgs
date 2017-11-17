/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.EnumPermissoes;
import br.ufc.russas.n2s.darwin.model.Usuario;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class UsuarioBeans implements Beans{
    
    private long codUsuario;
    private long codUsuarioControleDeAcesso;
    private List<EnumPermissoes> permissoes;

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public List<EnumPermissoes> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<EnumPermissoes> permissoes) {
        this.permissoes = permissoes;
    }

    public long getCodUsuarioControleDeAcesso() {
        return codUsuarioControleDeAcesso;
    }

    public void setCodUsuarioControleDeAcesso(long codUsuarioControleDeAcesso) {
        this.codUsuarioControleDeAcesso = codUsuarioControleDeAcesso;
    }
        
    
    @Override
    public Object toBusiness() {
        Usuario usuario = new Usuario();
        if (this.getCodUsuario() > 0) {
            usuario.setCodUsuario(this.getCodUsuario());
        }
        if (this.getCodUsuarioControleDeAcesso()> 0) {
            usuario.setCodUsuarioControleDeAcesso(this.getCodUsuarioControleDeAcesso());
        }
        usuario.setPermissoes(this.getPermissoes());
        return usuario;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Usuario){
                Usuario usuario = (Usuario) object;
                this.setCodUsuario(usuario.getCodUsuario());
                this.setPermissoes(usuario.getPermissoes());
                this.setCodUsuarioControleDeAcesso(usuario.getCodUsuarioControleDeAcesso());
                return this;
            }else{
                throw new IllegalArgumentException("Isso não é um usuário!");
            }
        }else{
            throw new NullPointerException("Usuário não pode ser vazio!");
        }  
    }
    
}
