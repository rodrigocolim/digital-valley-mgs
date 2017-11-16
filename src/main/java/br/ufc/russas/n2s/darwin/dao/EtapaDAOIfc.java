/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Etapa;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface EtapaDAOIfc {

    /**
     * Método resposável por fazer a persistência de uma etapa.
     * @param etapa
     * @return Etapa
     */
    Etapa adicionaEtapa(Etapa etapa);

    /**
     * Método resposável por fazer a atualização de uma etapa.
     * @param etapa
     */
    Etapa atualizaEtapa(Etapa etapa);

    /**
     * Método resposável por fazer a remoção de uma etapa.
     * @param etapa
     */
    void removeEtapa(Etapa etapa);

    /**
     * Método resposável por fazer a listagem de todas as etapas.
     * @return List<Etapa>
     */
    List<Etapa> listaEtapas(Etapa etapa);

    /**
     * Método resposável por pegar do banco de dados uma etapa a partir do
     * código informado.
     * @param codEtapa
     * @return Etapa
     */
    Etapa getEtapa(Etapa etapa);

}
