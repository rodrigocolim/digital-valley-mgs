/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Participante;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface EtapaServiceIfc {
    public EtapaBeans adicionaEtapa(EtapaBeans etapa);
    public EtapaBeans atualizaEtapa(EtapaBeans etapa);
    public void removeEtapa(EtapaBeans etapa);
    public List<EtapaBeans> listaTodasEtapas();
    public EtapaBeans getEtapa(long codEtapa);
    public boolean isParticipante(ParticipanteBeans participante);
    public boolean isParticipante(UsuarioBeans participante);
}
