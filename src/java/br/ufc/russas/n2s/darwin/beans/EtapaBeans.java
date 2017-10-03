/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.CriterioDeAvaliacao;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Periodo;
import br.ufc.russas.n2s.darwin.model.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class EtapaBeans implements Beans{


    private long codEtapa;
    private String titulo;
    private PeriodoBeans periodo;
    private String descricao;
    private List<UsuarioBeans> avaliadores;
    private String documentacao;
    private CriterioDeAvaliacaoBeans criterioDeAvaliacao;
    private List<AvaliacaoBeans> avaliacoes;
    private List<DocumentacaoBeans> documentacoes;
    private boolean status;
    private EtapaBeans prerequisito;
    
    public EtapaBeans(){}
    
    public EtapaBeans(long codEtapa, String titulo, PeriodoBeans periodo, String descricao, List<UsuarioBeans> avaliadores, String documentacao, CriterioDeAvaliacaoBeans criterioDeAvaliacao, List<AvaliacaoBeans> avaliacoes, List<DocumentacaoBeans> documentacoes, boolean status, EtapaBeans prerequisito) {
        this.codEtapa = codEtapa;
        this.titulo = titulo;
        this.periodo = periodo;
        this.descricao = descricao;
        this.avaliadores = avaliadores;
        this.documentacao = documentacao;
        this.criterioDeAvaliacao = criterioDeAvaliacao;
        this.avaliacoes = avaliacoes;
        this.documentacoes = documentacoes;
        this.status = status;
        this.prerequisito = prerequisito;
    }

    public long getCodEtapa() {
        return codEtapa;
    }
    public void setCodEtapa(long codEtapa) {
        this.codEtapa = codEtapa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PeriodoBeans getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoBeans periodo) {
        this.periodo = periodo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<UsuarioBeans> getAvaliadores() {
        return avaliadores;
    }

    public void setAvaliadores(List<UsuarioBeans> avaliadores) {
        this.avaliadores = avaliadores;
    }

    public String getDocumentacao() {
        return documentacao;
    }

    public void setDocumentacao(String documentacao) {
        this.documentacao = documentacao;
    }

    public CriterioDeAvaliacaoBeans getCriterioDeAvaliacao() {
        return criterioDeAvaliacao;
    }

    public void setCriterioDeAvaliacao(CriterioDeAvaliacaoBeans criterioDeAvaliacao) {
        this.criterioDeAvaliacao = criterioDeAvaliacao;
    }

    public List<AvaliacaoBeans> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoBeans> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<DocumentacaoBeans> getDocumentacoes() {
        return documentacoes;
    }

    public void setDocumentacoes(List<DocumentacaoBeans> documentacoes) {
        this.documentacoes = documentacoes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public EtapaBeans getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(EtapaBeans prerequisito) {
        this.prerequisito = prerequisito;
    }
    
    @Override
    public Object toBusiness() {
        Etapa etapa = new Etapa();
        
        if(this.getCodEtapa() > 0){
            etapa.setCodEtapa(this.getCodEtapa());
        }
        etapa.setTitulo(this.getTitulo());
        etapa.setDescricao(this.getDescricao());
        etapa.setDocumentacao(this.getDocumentacao());
        etapa.setCriterioDeAvaliacao((CriterioDeAvaliacao)this.getCriterioDeAvaliacao().toBusiness());
        etapa.setStatus(this.isStatus());
        etapa.setPrerequisito((Etapa) this.getPrerequisito().toBusiness());
        etapa.setPeriodo((Periodo) this.getPeriodo().toBusiness());
        
        List<Usuario> avaliadores = Collections.synchronizedList(new ArrayList<Usuario>());
        if(this.getAvaliadores()!=null){
            for(int i=0;i<this.getAvaliadores().size();i++){
                avaliadores.add((Usuario) this.getAvaliadores().get(i).toBusiness());
            }
        }
        etapa.setAvaliadores(avaliadores);
        
        List<Avaliacao> avaliacoes = Collections.synchronizedList(new ArrayList<Avaliacao>());
        if(this.getAvaliacoes()!=null){
            for(int i=0;i<this.getAvaliacoes().size();i++){
                avaliacoes.add((Avaliacao) this.getAvaliacoes().get(i).toBusiness());
            }
        }
        etapa.setAvaliacoes(avaliacoes);
        
        List<Documentacao> documentacoes = Collections.synchronizedList(new ArrayList<Documentacao>());
        if(this.getDocumentacoes()!=null){
            for(int i=0;i<this.getDocumentacoes().size();i++){
                documentacoes.add((Documentacao) this.getDocumentacoes().get(i).toBusiness());
            }
        }
        etapa.setDocumentacoes(documentacoes);
        
        return etapa; 
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Etapa){
                Etapa etapa = (Etapa) object;
                this.setCodEtapa(etapa.getCodEtapa());
                this.setTitulo(etapa.getTitulo());
                this.setDescricao(etapa.getDescricao());
                this.setDocumentacao(etapa.getDocumentacao());
                this.setStatus(etapa.isStatus());
                
                
                PeriodoBeans pb = null;
                if(etapa.getPeriodo()!=null){
                   pb = (PeriodoBeans) (new PeriodoBeans().toBeans(etapa.getPeriodo()));
                }
                this.setPeriodo(pb);
                
                CriterioDeAvaliacaoBeans cab = null;
                if(etapa.getCriterioDeAvaliacao()!=null){
                   cab = (CriterioDeAvaliacaoBeans) (new CriterioDeAvaliacaoBeans().toBeans(etapa.getCriterioDeAvaliacao()));
                }
                this.setCriterioDeAvaliacao(cab);
                
                EtapaBeans eb = null;
                if(etapa.getPrerequisito()!=null){
                   eb = (EtapaBeans) (new EtapaBeans().toBeans(etapa.getPrerequisito()));
                }
                this.setPrerequisito(eb);
                
                List<UsuarioBeans> avaliadores = Collections.synchronizedList(new ArrayList<>());
                UsuarioBeans ubs = null;
                if(etapa.getAvaliadores()!= null){
                    for(int i=0;i<etapa.getAvaliadores().size();i++){
                        ubs = (UsuarioBeans) (new UsuarioBeans().toBeans(etapa.getAvaliadores().get(i)));
                        avaliadores.add(ubs);
                    }
                }
                this.setAvaliadores(avaliadores);
                
                List<AvaliacaoBeans> avaliacoes = Collections.synchronizedList(new ArrayList<>());
                AvaliacaoBeans ab = null;
                if(etapa.getAvaliacoes()!= null){
                    for(int i=0;i<etapa.getAvaliacoes().size();i++){
                        ab = (AvaliacaoBeans) (new AvaliacaoBeans().toBeans(etapa.getAvaliacoes().get(i)));
                        avaliacoes.add(ab);
                    }
                }
                this.setAvaliacoes(avaliacoes);
                
                List<DocumentacaoBeans> documentacoes = Collections.synchronizedList(new ArrayList<>());
                DocumentacaoBeans db = null;
                if(etapa.getDocumentacoes()!= null){
                    for(int i=0;i<etapa.getDocumentacoes().size();i++){
                        db = (DocumentacaoBeans) (new DocumentacaoBeans().toBeans(etapa.getDocumentacoes().get(i)));
                        documentacoes.add(db);
                    }
                }
                this.setDocumentacoes(documentacoes);
                
                return this;
            }else{
                throw new IllegalArgumentException("O objeto a ser adicionado não é uma Etapa!");
            }
        }else{
            throw new NullPointerException("Etapa não pode ser nula!");
        }
            
    }
    
}
