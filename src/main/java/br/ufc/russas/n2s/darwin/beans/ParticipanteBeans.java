/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Usuario;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class ParticipanteBeans implements Beans{

    private long codParticipante;
    private Usuario candidato;
    private List<Documentacao> documentacao;
    private boolean deferido;
    private LocalDateTime data;
    private boolean notificado;

    public ParticipanteBeans(){
    }

    public ParticipanteBeans(long codParticipante, Usuario candidato, List<Documentacao> documentacao, boolean deferido, LocalDateTime data, boolean notificado) {
        this.codParticipante = codParticipante;
        this.candidato = candidato;
        this.documentacao = documentacao;
        this.deferido = deferido;
        this.data = data;
        this.notificado = notificado;
    }
    
    
    
    public long getCodParticipante() {
        return codParticipante;
    }

    public void setCodParticipante(long codParticipante) {
        this.codParticipante = codParticipante;
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public void setCandidato(Usuario candidato) {
        this.candidato = candidato;
    }

    public List<Documentacao> getDocumentacao() {
        return documentacao;
    }

    public void setDocumentacao(List<Documentacao> documentacao) {
        this.documentacao = documentacao;
    }

    public boolean isDeferido() {
        return deferido;
    }

    public void setDeferido(boolean deferido) {
        this.deferido = deferido;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }
    
    
    
    
    @Override
    public Object toBusiness() {
        Participante participante = new Participante();
        
        if(this.getCodParticipante() >0){
            participante.setCodParticipante(this.getCodParticipante());
        }
        participante.setCandidato(this.getCandidato());
        participante.setData(this.getData());
        participante.setDeferido(this.isDeferido());
        participante.setNotificado(this.isNotificado());
        
        List<Documentacao> documentacoes = Collections.synchronizedList(new ArrayList<Documentacao>());
        if(this.getDocumentacao()!= null){
            for(int i=0;i<getDocumentacao().size();i++){
                documentacoes.add((Documentacao) this.getDocumentacao().get(i).toBusiness());
            }
        }
        participante.setDocumentacao(documentacoes);
        return participante;
    }

    @Override
    public Beans toBeans(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
