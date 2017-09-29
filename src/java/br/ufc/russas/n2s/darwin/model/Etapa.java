/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author Lavínia Matoso
 */
@Entity
@Table(name="etapa")
public class Etapa implements Serializable, Atualizavel{
    @Id
    @Column(name="codArquivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codEtapa;
    private String titulo;
    @ManyToOne
    @JoinColumn(name="periodo", referencedColumnName="codPeriodo")
    private Periodo periodo;
    private String descricao;
    @ManyToMany(targetEntity = Etapa.class)
    @JoinTable(name="avaliadores", joinColumns = {@JoinColumn(name="etapa", referencedColumnName = "codEtapa")},
            inverseJoinColumns = {@JoinColumn(name="avaliador", referencedColumnName = "codUsuario")})
    private List<Usuario> avaliadores;
    private String documentacao;
    @Column(name="criterio_de_avaliacao")
    private CriterioDeAvaliacao criterioDeAvaliacao;
    @ManyToMany(targetEntity = Etapa.class)
    @JoinTable(name="avaliacoes", joinColumns = {@JoinColumn(name="etapa", referencedColumnName = "codEtapa")},
            inverseJoinColumns = {@JoinColumn(name="avaliacao", referencedColumnName = "codAvaliacao")})
    private List<Avaliacao> avaliacoes;
    @ManyToMany(targetEntity = Etapa.class)
    @JoinTable(name="documentacoes", joinColumns = {@JoinColumn(name="etapa", referencedColumnName = "codEtapa")},
            inverseJoinColumns = {@JoinColumn(name="documentacoes", referencedColumnName = "codDocumentacao")})
    private List<Documentacao> documentacoes;
    private boolean status;
    @ManyToOne
        @JoinColumn(name="prerequisito", referencedColumnName="codEtapa")
    private Etapa prerequisito;

    public long getCodEtapa() {
        return codEtapa;
    }

    public void setCodEtapa(long codEtapa) {
        if(codEtapa >0){
            this.codEtapa = codEtapa;
        }else{
            throw new IllegalCodeException("Código de etapa deve ser maior que zero!");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo == null){
            throw new NullPointerException("Título não pode ser nulo!");
        }else if(titulo.isEmpty()){
            throw new NullPointerException("Título não pode ser vazio!");
        }else{
            this.titulo = titulo;
        }
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        if(periodo !=null){
            this.periodo = periodo;
        }else{
            throw new NullPointerException("Período não pode ser nulo!");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getAvaliadores() {
        return avaliadores;
    }

    public void setAvaliadores(List<Usuario> avaliadores) {
        this.avaliadores = avaliadores;
    }

    public String getDocumentacao() {
        return documentacao;
    }

    public void setDocumentacao(String documentacao) {
        this.documentacao = documentacao;
    }

    public CriterioDeAvaliacao getCriterioDeAvaliacao() {
        return criterioDeAvaliacao;
    }

    public void setCriterioDeAvaliacao(CriterioDeAvaliacao criterioDeAvaliacao) {
        if(criterioDeAvaliacao != null){
            this.criterioDeAvaliacao = criterioDeAvaliacao;
        }else{
            throw new NullPointerException("Deve ser selecionado um critério de avaliação!");
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Documentacao> getDocumentacoes() {
        return documentacoes;
    }

    public void setDocumentacoes(List<Documentacao> documentacoes) {
        this.documentacoes = documentacoes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Etapa getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(Etapa prerequisito) {
        if(prerequisito.getPeriodo().isAntes(this.getPeriodo())){
            this.prerequisito = prerequisito;
        }else{
            throw new IllegalArgumentException("Essa é tapa não pode ser pré-requisito da etapa "+this.getTitulo()+" pois não ocorre antes!");
        }
    }
    
    
    public void adicionaAvaliador(Usuario usuario){
        if(this.getAvaliadores() != null){
            this.setAvaliadores(Collections.synchronizedList(new ArrayList<Usuario>()));
        }
        if(!this.getAvaliadores().contains(usuario)){
            //Falta adicionar uma verificação para saber se o usuário está inscrito ou não na seleção 
            this.getAvaliadores().add(usuario);
            //Chama o dao para atualizar a etapa
        }else{
            throw new IllegalArgumentException("Esse usuário já é avaliador desssa etapa!");
        }
    }

    public void removeAvaliador(Usuario usuario){
        if(this.getAvaliadores() != null){
            if(this.getAvaliadores().contains(usuario)){
                this.getAvaliadores().remove(usuario);
                //Chama o método do dao para atualizar a etapa
            }else{
                throw new IllegalArgumentException("Usuário não é avaliador dessa etapa!");
            }
        }else{
            throw new NullPointerException("Não existe avaliadores cadastrados para esta etapa!");
        }
    }
    
    public boolean isAvaliador(Usuario usuario){
        return this.getAvaliadores().contains(usuario);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.codEtapa ^ (this.codEtapa >>> 32));
        return hash;
    }
    
    
    @Override
    public boolean equals(Object o){
        return (this.getCodEtapa() == ((Etapa) o).getCodEtapa());
    }

    @Override
    public void atualiza() {
        //Chama o dao atualiza etapa
    }
}
