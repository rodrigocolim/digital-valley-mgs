/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.Participante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class DocumentacaoBeans implements Beans {

    private long codDocumentacao;
    private ParticipanteBeans candidato;
    private List<ArquivoBeans> documentos;

    public long getCodDocumentacao() {
        return codDocumentacao;
    }

    public void setCodDocumentacao(long codDocumentacao) {
        this.codDocumentacao = codDocumentacao;
    }

    public ParticipanteBeans getCandidato() {
        return candidato;
    }

    public void setCandidato(ParticipanteBeans candidato) {
        this.candidato = candidato;
    }

    public List<ArquivoBeans> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ArquivoBeans> documentos) {
        this.documentos = documentos;
    }
    
    
    @Override
    public Object toBusiness() {
        Documentacao documentacao = new Documentacao();
        if(this.getCodDocumentacao()>0){
            documentacao.setCodDocumentacao(this.getCodDocumentacao());
        }
        documentacao.setCandidato((Participante) this.getCandidato().toBusiness());
        List<Arquivo> documentos = Collections.synchronizedList(new ArrayList<Arquivo>());
        for(ArquivoBeans documento : this.getDocumentos()){
            documentos.add((Arquivo) documento.toBusiness());
        }
        documentacao.setDocumentos(documentos);
        return documentacao;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Documentacao){
                Documentacao documentacao = (Documentacao) object;
                this.setCodDocumentacao(this.getCodDocumentacao());
                this.setCandidato((ParticipanteBeans) new ParticipanteBeans().toBeans(this.getCandidato()));
                List<ArquivoBeans> documentos = Collections.synchronizedList(new ArrayList<ArquivoBeans>());
                for(Arquivo documento : documentacao.getDocumentos()){
                    documentos.add((ArquivoBeans) new ArquivoBeans().toBeans(documento));
                }
                this.setDocumentos(documentos);
                return this;
            }else{
                throw new IllegalArgumentException("Isso não é uma Documentação!");
            }
        }else{
            throw new NullPointerException("Documentação não pode ser nula!");
        }
    }
    
}
