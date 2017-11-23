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
import javax.persistence.CascadeType;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 *
 * @author Lavínia Matoso
 */
@Entity
@Table(name = "etapa")
public class Etapa implements Serializable, Atualizavel {
    @Id
    @Column(name = "codEtapa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codEtapa;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "periodo", referencedColumnName = "codPeriodo")
    private Periodo periodo;
    private String descricao;
    @ManyToMany(targetEntity = UsuarioDarwin.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "avaliadores", joinColumns = {@JoinColumn(name = "etapa", referencedColumnName = "codEtapa")},
            inverseJoinColumns = {@JoinColumn(name = "avaliador", referencedColumnName = "codUsuario")})
    private List<UsuarioDarwin> avaliadores;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(name = "documentacoes_exigidas", joinColumns = @JoinColumn(name = "codEtapa"))
    @Column(name = "documentacao_exigida")
    private List<String> documentacaoExigida;
    @Column(name = "criterio_de_avaliacao")
    @Enumerated(EnumType.ORDINAL)
    private EnumCriterioDeAvaliacao criterioDeAvaliacao;
    @ManyToMany(targetEntity = Avaliacao.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "avaliacoes", 
            joinColumns = {@JoinColumn(name = "etapa", referencedColumnName = "codEtapa")},
            inverseJoinColumns = {@JoinColumn(name = "avaliacao", referencedColumnName = "codAvaliacao")})
    private List<Avaliacao> avaliacoes;
    @ManyToMany(targetEntity = Documentacao.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "documentacoes", joinColumns = {@JoinColumn(name = "etapa", referencedColumnName = "codEtapa")},
            inverseJoinColumns = {@JoinColumn(name = "documentacao", referencedColumnName = "codDocumentacao")})
    private List<Documentacao> documentacoes;
    @Enumerated(EnumType.ORDINAL)
    private EnumEstadoEtapa estado;
    @ManyToOne
        @JoinColumn(name = "prerequisito", referencedColumnName = "codEtapa")
    private Etapa prerequisito;

    public long getCodEtapa() {
        return codEtapa;
    }

    public void setCodEtapa(long codEtapa) {
        if (codEtapa > 0) {
            this.codEtapa = codEtapa;
        } else {
            throw new IllegalCodeException("Código de etapa deve ser maior que zero!");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null) {
            throw new NullPointerException("Título não pode ser nulo!");
        } else if (titulo.isEmpty()) {
            throw new NullPointerException("Título não pode ser vazio!");
        } else {
            this.titulo = titulo;
        }
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        if (periodo != null) {
            this.periodo = periodo;
        } else {
            throw new NullPointerException("Período não pode ser nulo!");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<UsuarioDarwin> getAvaliadores() {
        return avaliadores;
    }

    public void setAvaliadores(List<UsuarioDarwin> avaliadores) {
        this.avaliadores = avaliadores;
    }

    public List<String> getDocumentacaoExigida() {
        return documentacaoExigida;
    }

    public void setDocumentacaoExigida(List<String> documentacao) {
        this.documentacaoExigida = documentacao;
    }

    public EnumCriterioDeAvaliacao getCriterioDeAvaliacao() {
        return criterioDeAvaliacao;
    }

    public void setCriterioDeAvaliacao(EnumCriterioDeAvaliacao criterioDeAvaliacao) {
        if (criterioDeAvaliacao != null) {
            this.criterioDeAvaliacao = criterioDeAvaliacao;
        } else {
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

    public EnumEstadoEtapa getEstado () {
        return estado;
    }

    public void setEstado (EnumEstadoEtapa estado) {
        this.estado = estado;
    }

    public Etapa getPrerequisito() {
        return prerequisito;
    }

    /**
     *
     * @param prerequisito
     */
    public void setPrerequisito(Etapa prerequisito) {
        if (prerequisito.getPeriodo().isAntes(this.getPeriodo())) {
            this.prerequisito = prerequisito;
        } else {
            throw new IllegalArgumentException("Essa é tapa não pode ser pré-requisito da etapa "+this.getTitulo()+" pois não ocorre antes!");
        }
    }

    /**
     *
     * @param maisDocumentacao
     */
    public void adicionaDocumentacaoExigida(List<String> maisDocumentacao) {
        if (maisDocumentacao != null && !maisDocumentacao.isEmpty()) {
            this.documentacaoExigida.addAll(maisDocumentacao);
        }
    }

    /**
     * Adiciona um novo avaliador a etapa.
     * @param usuario
     */
    public void adicionaAvaliador(UsuarioDarwin usuario) {
        if (this.getAvaliadores() != null) {
            ArrayList<UsuarioDarwin> usuarios = new ArrayList<>();
            List<UsuarioDarwin> sync = Collections.synchronizedList(usuarios);
            this.setAvaliadores(sync);
        }
        if (!this.getAvaliadores().contains(usuario)) {
            //Falta adicionar uma verificação para saber se o usuário está inscrito ou não na seleção
            this.getAvaliadores().add(usuario);
            atualiza();
        } else {
            throw new IllegalArgumentException("Esse usuário já é avaliador desssa etapa!");
        }
    }

    /**
     * Método resposável por remover um avaliador desta etapa.
     * @param usuario
     */
    public void removeAvaliador(UsuarioDarwin usuario) {
        if (this.getAvaliadores() != null) {
            if (this.getAvaliadores().contains(usuario)) {
                this.getAvaliadores().remove(usuario);
                atualiza();
            } else {
                throw new IllegalArgumentException("Usuário não é avaliador dessa etapa!");
            }
        } else {
            throw new NullPointerException("Não existe avaliadores cadastrados para esta etapa!");
        }
    }

    /**
     * Verifica se o usuário passado é um avaliador.
     * @param usuario
     * @return
     */
    public boolean isAvaliador(UsuarioDarwin usuario) {
        return this.getAvaliadores().contains(usuario);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.codEtapa ^ (this.codEtapa >>> 32));
        return hash;
    }

    @Override
    public boolean equals(final Object o) {
        return (this.getCodEtapa() == ((Etapa) o).getCodEtapa());
    }

    @Override
    public void atualiza() {
        //Chama o dao atualiza etapa
    }
}
