/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ResultadoParticipanteSelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
import br.ufc.russas.n2s.darwin.model.EstadoSelecao;
import br.ufc.russas.n2s.darwin.model.ResultadoParticipanteSelecao;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface SelecaoServiceIfc extends ServiceIfc{

    /**
     *
     * @param selecao
     * selecao - uma nova SelecaoBeans a ser armazenada
     * @return SelecaoBeans
     */
    SelecaoBeans adicionaSelecao(SelecaoBeans selecao) throws IllegalAccessException;
    
    /**
     *
     * @param selecao
     * selecao - uma SelecaoBeans para ser atualizada
     * @return SelecaoBeans
     */
    SelecaoBeans atualizaSelecao(SelecaoBeans selecao) throws IllegalAccessException;

    /**
     * @param selecao
     * selecao - Uma SelecaoBeans a ser removida
     */
    void removeSelecao(SelecaoBeans selecao);


    /**
     * 
     * @return List
     */
    List<SelecaoBeans> listaTodasSelecoes();
    
    /**
     * @param selecao
     * @return List
     */
    List<SelecaoBeans> listaSelecoes(Selecao selecao);
    
    /**
     * 
     * @param usuario
     * @return List
     */
    public List<SelecaoBeans> listaSelecoesAssociada(UsuarioBeans usuario);
    
    /**
     *
     * @param codSelecao
     * codSelecao - Identificador único da seleção que queira buscar
     * @return SelecaoBeans
     */
    SelecaoBeans getSelecao(long codSelecao);
    
     /**
     *
     * @param selecao
     * 
     */
    EtapaBeans getEtapaAtual(SelecaoBeans selecao);
    
     /**
     *
     * @param selecao
     * 
     */
    EtapaBeans getUltimaEtapa(SelecaoBeans selecao);
    
    /**
    *
    * @param selecao
    * 
    */
   List<EtapaBeans> getEtapasNota(SelecaoBeans selecao);
    
    /**
     * 
     * @param selecoes
     * @return List
     */
    List<SelecaoBeans> ordenaSelecoesPorData(List<SelecaoBeans> selecoes);
    
    /**
     * 
     * @param selecoes
     * @return List
     */

    List<ResultadoParticipanteSelecaoBeans> getResultado(SelecaoBeans selecoes)  throws IllegalAccessException;

    List<SelecaoBeans> listaTodasSelecoesDoBanco();
    
    void divulgaResultadoSelecao(SelecaoBeans selecao);
    
    void atualizaExibirNotas(SelecaoBeans selecao);
    
    List<SelecaoBeans> BuscaSelecoesPorNome(String titulo);
    List<SelecaoBeans> listaSelecoesIgnorandoNotas(Selecao selecao);
    
    //Novos
    public List<SelecaoBeans> listaSelecoes(EnumEstadoSelecao estado, int inico, int qtd);
    public Long getQuantidade(EnumEstadoSelecao estado);
}
