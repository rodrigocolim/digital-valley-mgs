/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author N2S-PC03
 */
@Converter(autoApply = true)
@Entity
@Table(name="documentacao")
public class Documentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codDocumentacao")
    private long codDocumentacao;

    @ManyToOne
    @JoinColumn(name="usuario", referencedColumnName="codUsuario")
    private Usuario candidato;

    @ManyToMany(targetEntity = Arquivo.class)
    @JoinTable(name="arquivos_documentacao", joinColumns = {@JoinColumn(name = "documentacao", referencedColumnName = "codDocumentacao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> documentos;

    public Documentacao(){
    }

    public Documentacao(long codDocumentacao, Usuario candidato, List<Arquivo> documentos) {
        setCodDocumentacao(codDocumentacao);
        setCandidato(candidato);
        setDocumentos(documentos);
    }

    public long getCodDocumentacao() {
        return codDocumentacao;
    }

    public void setCodDocumentacao(long codDocumentacao) {
        if(codDocumentacao > 0)
            this.codDocumentacao = codDocumentacao;
        else
            throw new IllegalCodeException("Código da documentação deve ser maior que zero!");
        
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public void setCandidato(Usuario candidato) {
        if(candidato != null)
            this.candidato = candidato;
        else
            throw new IllegalArgumentException("Candidato não pode ser nulo!");
    }

    public List<Arquivo> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Arquivo> documentos) {
        if(documentos != null)
            this.documentos = documentos;
        else
            throw new IllegalArgumentException("Lista de documentos não pode ser nula!");
    }
}
