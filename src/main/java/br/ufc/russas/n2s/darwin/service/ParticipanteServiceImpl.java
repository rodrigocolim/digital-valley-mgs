/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.dao.ParticipanteDAOIfc;
import br.ufc.russas.n2s.darwin.model.Participante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author N2S-PC03
 */
public class ParticipanteServiceImpl implements ParticipanteServiceIfc {

     private ParticipanteDAOIfc participanteDAOIfc;

    public ParticipanteServiceImpl() {
    }

    public final ParticipanteDAOIfc getParticipanteDAOIfc() {
        return participanteDAOIfc;
    }

    @Autowired(required = true)
    public void setParticipanteDAOIfc(@Qualifier("participanteDAOIfc") ParticipanteDAOIfc participanteDAOIfc) {
        this.participanteDAOIfc = participanteDAOIfc;
    }

    @Override
    public final void adicionaParticipante(final ParticipanteBeans participante) {
        this.participanteDAOIfc.adicionaParticipante((Participante) participante.toBusiness());
    }

    @Override
    public final void atualizaParticipante(final ParticipanteBeans participante) {
        this.participanteDAOIfc.atualizaParticipante((Participante) participante.toBusiness());
    }

    @Override
    public final void removeParticipante(final ParticipanteBeans participante) {
        this.participanteDAOIfc.removeParticipante((Participante) participante.toBusiness());
    }

    @Override
    public final List<ParticipanteBeans> listaTodosParticipantes() {
        List<ParticipanteBeans> participantes = Collections.synchronizedList(new ArrayList<ParticipanteBeans>());
        List<Participante> resultados = this.participanteDAOIfc.listaParticipantes();
        for (Participante p : resultados) {
            participantes.add((ParticipanteBeans) new ParticipanteBeans().toBeans(p));
        }
        return participantes;
    }

    @Override
    public final ParticipanteBeans getParticipante(final long codParticipante) {
        return (ParticipanteBeans) new ParticipanteBeans().toBeans(this.participanteDAOIfc.getParticipante(codParticipante));
    }

}
