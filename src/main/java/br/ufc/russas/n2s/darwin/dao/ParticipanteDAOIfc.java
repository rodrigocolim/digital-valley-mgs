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

    /**
     * Método resposável por fazer a persistência de um participante.
     * @param participante
     */
    void adicionaParticipante(Participante participante);

    /**
     * Método resposável por fazer a atualização de um participante.
     * @param participante
     */
    void atualizaParticipante(Participante participante);

    /**
     * Método resposável por fazer a remoção de um participante.
     * @param participante
     */
    void removeParticipante(Participante participante);

    /**
     * Método resposável por fazer a listagem de todos os participantes.
     * @return
     */
    List<Participante> listaParticipantes(Participante participante);

    /**
     * Método resposável por pegar do banco de dados um
     * participante a partir do código informadao.
     * @param codigo
     * @return
     */
    Participante getParticipante(Participante participante);
    
    
    List<Participante> listaParticipantesPorEtapa(int codEtapa); 
}
