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
    public SelecaoBeans adicionaSelecao(SelecaoBeans selecao) throws IllegalAccessException;
    
    /**
     *
     * @param selecao
     * selecao - uma SelecaoBeans para ser atualizada
     * @return SelecaoBeans
     */
    public SelecaoBeans atualizaSelecao(SelecaoBeans selecao) throws IllegalAccessException;

    /**
     * @param selecao
     * selecao - Uma SelecaoBeans a ser removida
     */
    public void removeSelecao(SelecaoBeans selecao);


    /**
     * 
     * @return List
     */
    public List<SelecaoBeans> listaTodasSelecoes();
    
    /**
     * @param selecao
     * @return List
     */
    public List<SelecaoBeans> listaSelecoes(Selecao selecao);
    
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
    public SelecaoBeans getSelecao(long codSelecao);
    
     /**
     *
     * @param selecao
     * 
     */
    public EtapaBeans getEtapaAtual(SelecaoBeans selecao);
    
     /**
     *
     * @param selecao
     * 
     */
    public EtapaBeans getUltimaEtapa(SelecaoBeans selecao);
    
    /**
    *
    * @param selecao
    * 
    */
   public List<EtapaBeans> getEtapasNota(SelecaoBeans selecao);
    
    /**
     * 
     * @param selecoes
     * @return List
     */
    public List<SelecaoBeans> ordenaSelecoesPorData(List<SelecaoBeans> selecoes);
    
    /**
     * 
     * @param selecoes
     * @return List
     */

    public List<ResultadoParticipanteSelecaoBeans> getResultado(SelecaoBeans selecoes)  throws IllegalAccessException;

    public List<SelecaoBeans> listaTodasSelecoesDoBanco();
    
    public void divulgaResultadoSelecao(SelecaoBeans selecao);
    
    public void atualizaExibirNotas(SelecaoBeans selecao);
    
    public List<SelecaoBeans> listaSelecoesIgnorandoNotas(Selecao selecao);
    
    //Novos
    public List<SelecaoBeans> listaSelecoes(String categoria, EnumEstadoSelecao estado, int inicio, int qtd);
    public Long getQuantidade(String categoria, EnumEstadoSelecao estado);
    
    public List<SelecaoBeans> buscarSelecoesPorNome(String titulo, int inicio, int qtd);
    public Long getQuantidadePorNome(String titulo);
    
    public List<SelecaoBeans> buscarSelecoesAssociada(UsuarioBeans usuario, int inicio, int qtd);
    public Long getQuantidadeAssociada(UsuarioBeans usuario);
}
