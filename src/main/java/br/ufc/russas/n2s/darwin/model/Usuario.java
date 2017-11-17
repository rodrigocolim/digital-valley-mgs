/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lavínia Matoso
 */
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codUsuario")
    private long codUsuario;
    @Column(name = "codUsuarioControleDeAcesso")
    private long codUsuarioControleDeAcesso;
    @Column
    @Enumerated
    @ElementCollection(targetClass = EnumPermissoes.class)
    private List<EnumPermissoes> permissoes;

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        if(codUsuario>0)
            this.codUsuario = codUsuario;
        else
            throw new IllegalCodeException("Código de usuário deve ser maior de zero!");
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
    
}
