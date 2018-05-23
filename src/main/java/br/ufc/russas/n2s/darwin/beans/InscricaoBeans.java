/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Inscricao;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Periodo;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Wallison Carlos
 */
public class InscricaoBeans extends EtapaBeans{

    private List<ParticipanteBeans> candidatos;
    
    public InscricaoBeans() {
    
    }

    public List<ParticipanteBeans> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<ParticipanteBeans> candidatos) {
        this.candidatos = candidatos;
    }
    
    @Override
    public Object toBusiness() {
        Inscricao etapa = new Inscricao();

        if (this.getCodEtapa() > 0) {
            etapa.setCodEtapa(this.getCodEtapa());
        }
        etapa.setTitulo(this.getTitulo());
        etapa.setDescricao(this.getDescricao());
        etapa.setDocumentacaoExigida(this.getDocumentacaoExigida());
        etapa.setCriterioDeAvaliacao(this.getCriterioDeAvaliacao());
        etapa.setEstado(this.getEstado());
        etapa.setPeriodo((Periodo) this.getPeriodo().toBusiness());
        if(this.getPrerequisito() != null){
            etapa.setPrerequisito((Etapa) this.getPrerequisito().toBusiness());
        }
        List<UsuarioDarwin> avaliadores = Collections.synchronizedList(new ArrayList<UsuarioDarwin>());
        if (this.getAvaliadores() != null) {
            for (UsuarioBeans avaliador : this.getAvaliadores()) {
                avaliadores.add((UsuarioDarwin) avaliador.toBusiness());
            }
        }
        etapa.setAvaliadores(avaliadores);
        List<Participante> candidatos = Collections.synchronizedList(new ArrayList<Participante>());
                Participante c = null;
                if (this.getCandidatos()!= null) {
                    for (ParticipanteBeans part : this.getCandidatos()) {
                        c = (Participante) part.toBusiness();
                        candidatos.add(c);
                    }
                }
                etapa.setCandidatos(candidatos);
        List<Avaliacao> avaliacoes = Collections.synchronizedList(new ArrayList<Avaliacao>());
        if (this.getAvaliacoes() != null) {
            for (AvaliacaoBeans avaliacao : this.getAvaliacoes()) {
                avaliacoes.add((Avaliacao) avaliacao.toBusiness());
            }
        }
        etapa.setAvaliacoes(avaliacoes);

        List<Documentacao> documentacoes = Collections.synchronizedList(new ArrayList<Documentacao>());
        if (this.getDocumentacoes() != null) {
            for (DocumentacaoBeans documentacao : this.getDocumentacoes()) {
                documentacoes.add((Documentacao) documentacao.toBusiness());
            }
        }
        etapa.setDocumentacoes(documentacoes);
        etapa.setDivulgadoResultado(this.isDivulgadoResultado());
        return etapa;
    }

    @Override
    public Beans toBeans(Object object) {
        if (object != null) {
            if (object instanceof Etapa) {
                Inscricao etapa = (Inscricao) object;
                this.setCodEtapa(etapa.getCodEtapa());
                this.setTitulo(etapa.getTitulo());
                this.setDescricao(etapa.getDescricao());
                this.setDocumentacaoExigida(etapa.getDocumentacaoExigida());
                this.setEstado(etapa.getEstado());

                PeriodoBeans pb = null;
                if (etapa.getPeriodo() != null) {
                   pb = (PeriodoBeans) (new PeriodoBeans().toBeans(etapa.getPeriodo()));
                }
                this.setPeriodo(pb);

                this.setCriterioDeAvaliacao(etapa.getCriterioDeAvaliacao());

                EtapaBeans eb = null;
                if (etapa.getPrerequisito() != null) {
                   eb = (EtapaBeans) (new EtapaBeans().toBeans(etapa.getPrerequisito()));
                }
                this.setPrerequisito(eb);

                List<UsuarioBeans> avaliadoresBeans = Collections.synchronizedList(new ArrayList<UsuarioBeans>());
                UsuarioBeans ubs;
                if (etapa.getAvaliadores() != null) {
                    for (UsuarioDarwin usuario : etapa.getAvaliadores()) {
                        ubs = (UsuarioBeans) (new UsuarioBeans().toBeans(usuario));
                        avaliadoresBeans.add(ubs);
                    }
                }
                this.setAvaliadores(avaliadoresBeans);

                
                List<ParticipanteBeans> candidatos = Collections.synchronizedList(new ArrayList<ParticipanteBeans>());
                ParticipanteBeans c = null;
                if (etapa.getCandidatos()!= null) {
                    for (Participante part : etapa.getCandidatos()) {
                        c = (ParticipanteBeans) (new ParticipanteBeans().toBeans(part));
                        candidatos.add(c);
                    }
                }
                this.setCandidatos(candidatos);
                
                List<AvaliacaoBeans> avaliacoesBeans = Collections.synchronizedList(new ArrayList<AvaliacaoBeans>());
                AvaliacaoBeans ab = null;
                if (etapa.getAvaliacoes() != null) {
                    for (Avaliacao avaliacao : etapa.getAvaliacoes()) {
                        ab = (AvaliacaoBeans) (new AvaliacaoBeans().toBeans(avaliacao));
                        avaliacoesBeans.add(ab);
                    }
                }
                this.setAvaliacoes(avaliacoesBeans);

                List<DocumentacaoBeans> documentacoes = Collections.synchronizedList(new ArrayList<DocumentacaoBeans>());
                DocumentacaoBeans db;
                if (etapa.getDocumentacoes() != null) {
                    for (Documentacao documentacao : etapa.getDocumentacoes()) {
                        db = (DocumentacaoBeans) (new DocumentacaoBeans().toBeans(documentacao));
                        documentacoes.add(db);
                    }
                }
                this.setDocumentacoes(documentacoes);
                this.setDivulgaResultado(etapa.isDivulgadoResultado());
                return this;
            } else {
                throw new IllegalArgumentException("O objeto a ser adicionado não é uma Etapa!");
            }
        } else {
            throw new NullPointerException("Etapa não pode ser nula!");
        }

    }
    
}
