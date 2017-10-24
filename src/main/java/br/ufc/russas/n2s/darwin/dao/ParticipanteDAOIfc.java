/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Participante;
import java.util.List;

/**
 *
 * @author N2S-PC03
 */
public interface ParticipanteDAOIfc {
    public void adicionaParticipante(Participante participante);
    public void atualizaParticipante(Participante participante);
    public void removeParticipante(Participante participante);
    public List<Participante> listaParticipantes();
    public Participante getParticipante(long codigo);
}
