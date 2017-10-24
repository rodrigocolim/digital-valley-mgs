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
    public void adicionaEtapa(Etapa etapa);
    public void atualizaEtapa(Etapa etapa);
    public void removeEtapa(Etapa etapa);
    public List<Etapa> listaEtapas();
    public Etapa getEtapa(long codEtapa);

}
