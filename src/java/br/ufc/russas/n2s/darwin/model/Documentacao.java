/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.util.List;

/**
 *
 * @author N2S-PC03
 */
public class Documentacao {
    	private long codDocumentacao;
	private Usuario candidato;
	private List<Arquivo> documentos;
        
        public Documentacao(){
        }

    public Documentacao(long codDocumentacao, Usuario candidato, List<Arquivo> documentos) {
        this.codDocumentacao = codDocumentacao;
        this.candidato = candidato;
        this.documentos = documentos;
    }

    public long getCodDocumentacao() {
        return codDocumentacao;
    }

    public void setCodDocumentacao(long codDocumentacao) {
        if(codDocumentacao > 0)
            this.codDocumentacao = codDocumentacao;
        else
            throw new IllegalArgumentException("Código da documentação deve ser maior que zero!");
        
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
