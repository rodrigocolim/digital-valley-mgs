/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Usuario;

/**
 *
 * @author Lavínia Matoso
 */
public class AvaliacaoBeans implements Beans{

    private long codAvaliacao;
    private ParticipanteBeans participante;
    private float nota;
    private boolean aprovado;
    private String observacao;
    private UsuarioBeans avaliador;

    public long getCodAvaliacao() {
        return codAvaliacao;
    }

    public void setCodAvaliacao(long codAvaliacao) {
        this.codAvaliacao = codAvaliacao;
    }

    public ParticipanteBeans getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteBeans participante) {
        this.participante = participante;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
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

    public UsuarioBeans getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(UsuarioBeans avaliador) {
        this.avaliador = avaliador;
    }
    
    @Override
    public Object toBusiness() {
        Avaliacao avaliacao = new Avaliacao();
        if(this.getCodAvaliacao() > 0){
            avaliacao.setCodAvaliacao(this.getCodAvaliacao());
        }
        avaliacao.setParticipante((Participante) this.getParticipante().toBusiness());
        avaliacao.setAvaliador((Usuario) this.getAvaliador().toBusiness());
        avaliacao.setAprovado(this.isAprovado());
        avaliacao.setObservacao(this.getObservacao());
        avaliacao.setNota(this.getNota());
        return avaliacao;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Avaliacao){
                Avaliacao avaliacao = (Avaliacao) object;
                this.setCodAvaliacao(avaliacao.getCodAvaliacao());
                this.setParticipante((ParticipanteBeans) new ParticipanteBeans().toBeans(avaliacao.getParticipante()));
                this.setAvaliador((UsuarioBeans) new UsuarioBeans().toBeans(avaliacao.getAvaliador()));
                this.setAprovado(avaliacao.isAprovado());
                this.setObservacao(avaliacao.getObservacao());
                this.setNota(avaliacao.getNota());
                return this;
            }else{
                throw new IllegalArgumentException("Isso não é uma avaliação!");
            }
        }else{
            throw new NullPointerException("Avaliação informada não pode ser nula!");
        }
    }
    
}
