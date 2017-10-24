/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lavínia Matoso
 */
@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codAvaliacao")
    private long codAvaliacao;
    @ManyToOne
    @JoinColumn(name="participante", referencedColumnName="codParticipante")
    private Participante participante;
    private float nota;
    private boolean aprovado;
    private String observacao;
    @ManyToOne
    @JoinColumn(name="avaliador", referencedColumnName="codUsuario")
    private Usuario avaliador;

    public long getCodAvaliacao() {
        return codAvaliacao;
    }

    public void setCodAvaliacao(long codAvaliacao) {
        if(codAvaliacao > 0){
            this.codAvaliacao = codAvaliacao;
        }else{
            throw new IllegalCodeException("Código de avaliação deve ser maior que zero!");
        }
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        if(participante != null){
            this.participante = participante;
        }else{
            throw new NullPointerException("Participante deve ser informado!");
        }
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        if(nota>=0 && nota<=10){
            this.nota = nota;
        }else{
            throw new IllegalArgumentException("Nota inválida! A nota deve ser maior ou igual a zero e menor ou igual a dez!");
        }
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        if(avaliador != null){
            this.avaliador = avaliador;
        }else{
            throw new NullPointerException("Avaliador deve ser informado!");
        }
    }
    
    

}
