/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import java.util.List;

/**
 *
 * @author N2S-PC03
 */
public interface SelecaoDAOIfc {
    public void adicionaSelecao(Selecao  selecao);
    public void atualizaSelecao(Selecao  selecao);
    public void removeSelecao(Selecao  selecao);
    public List<Selecao> listaSelecoes();
    public Selecao getSelecao(long codigo);
    public List<Participante> getParticipantes();
}
