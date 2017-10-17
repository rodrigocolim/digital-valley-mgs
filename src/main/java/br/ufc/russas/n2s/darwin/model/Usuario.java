/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import org.hibernate.annotations.*;

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
    //Usuário do Jar do controle de acesso(talvez, o mais correto seria extender);
    @ElementCollection(targetClass = EnumPermissoes.class, fetch = FetchType.EAGER)
    @JoinTable(name = "niveis_de_acesso", joinColumns = @JoinColumn(name = "nivel"))
    @Column(name = "nivel", nullable = false) 
    @Enumerated(EnumType.ORDINAL) 
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
    
    
    
}
