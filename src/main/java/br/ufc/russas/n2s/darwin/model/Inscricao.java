/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Wallison Carlos
 */
@Entity
@DiscriminatorValue(value = "I")
public class Inscricao extends Etapa { 

    
    @ManyToMany(targetEntity = Participante.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "candidatos_selecao", joinColumns = {@JoinColumn(name = "etapaInscricao", referencedColumnName = "codEtapa")},
    inverseJoinColumns = {@JoinColumn(name = "participante", referencedColumnName = "codParticipante")})
    private List<Participante> candidatos;
    
    public Inscricao() {
        
    }
   
    public List<Participante> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Participante> candidatos) {
        this.candidatos = candidatos;
    }
    
    public void participa(Participante participante)  throws IllegalAccessException{
        if (participante ==  null) {
            throw new NullPointerException("Deve ser informado um participante!");
        } else if (getCandidatos().contains(participante) || isCanditado(participante.getCandidato())) {
            throw new IllegalArgumentException("Você já é um candidato desta seleção!");
        } else if (getAvaliadores().contains(participante.getCandidato())){
            throw new IllegalArgumentException("Você já é um avaliador desta seleção!");
        } else{
            getCandidatos().add(participante);
        }
    }
    
    public boolean isCanditado(UsuarioDarwin usuario) {
        for (Participante participante : getCandidatos()) {
            if (participante.getCandidato().equals(usuario)) {
                return true;
            }
        }
        return false;
    }
        
    @Override
    public boolean isParticipante(Participante participante) {
        if (participante != null) {
            if (this.getCandidatos().contains(participante)) {
                return true;
            }
        } else {
            throw new IllegalArgumentException("Participante não pode ser nulo!");
        }
        return false;
    }
    
    @Override
    public boolean isParticipante(UsuarioDarwin participante){
        for(Participante p : this.getCandidatos()){
            if(p.getCandidato().equals(participante)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public List<Participante> getParticipantes () {
        return getCandidatos();
    } 
    
    
    @Override
    public List<Participante> getAprovados() {
        List<Participante> aprovados = Collections.synchronizedList(new ArrayList<Participante>());
        if (getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.APROVACAO || getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.DEFERIMENTO) {
            for(Avaliacao avaliacao : this.getAvaliacoes()){
                if(avaliacao.isAprovado()){
                    aprovados.add(avaliacao.getParticipante());
                }
            }
        }
        return aprovados;
    }
}
