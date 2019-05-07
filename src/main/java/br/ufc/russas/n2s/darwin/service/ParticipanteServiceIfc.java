/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import java.util.List;

/**
 *
 * @author N2S-PC03
 */
public interface ParticipanteServiceIfc {
     void adicionaParticipante(ParticipanteBeans participante);
     void atualizaParticipante(ParticipanteBeans participante);
     void removeParticipante(ParticipanteBeans participante);
     List<ParticipanteBeans> listaTodosParticipantes();
     ParticipanteBeans getParticipante(long codParticipante);
     List<ParticipanteBeans> ordenarPorNome(List<ParticipanteBeans> participantes);
}
