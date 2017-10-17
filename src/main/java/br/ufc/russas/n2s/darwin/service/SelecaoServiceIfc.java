/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface SelecaoServiceIfc {
    public void adicionaSelecao(SelecaoBeans selecao);
    public List<SelecaoBeans> listaNovasSelecoes();
    public List<SelecaoBeans> listaTodasSelecoes();
}
