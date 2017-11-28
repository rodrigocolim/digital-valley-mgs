/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.AvaliacaoBeans;
import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Participante;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface EtapaServiceIfc extends ServiceIfc{
    public EtapaBeans adicionaEtapa(SelecaoBeans selecao, EtapaBeans etapa) throws IllegalAccessException;
    public EtapaBeans atualizaEtapa(SelecaoBeans selecao, EtapaBeans etapa) throws IllegalAccessException;
    public void removeEtapa(EtapaBeans etapa);
    public List<EtapaBeans> listaTodasEtapas();
    public EtapaBeans getEtapa(long codEtapa);
    public boolean isParticipante(ParticipanteBeans participante);
    public boolean isParticipante(UsuarioBeans participante);
    public ParticipanteBeans getParticipante(EtapaBeans etapa, UsuarioBeans usuario);
    public void participa(EtapaBeans etapa, DocumentacaoBeans documentacao) throws IllegalAccessException;
    public void avalia(EtapaBeans etapa, AvaliacaoBeans avaliacao) throws IllegalAccessException;
   
}
