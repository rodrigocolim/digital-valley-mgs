/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.util.List;


/**
 *
 * @author Lavínia Matoso
 */
class Etapa {
    private long codEtapa;
    private String titulo;
    private Periodo periodo;
    private String descricao;
    private List<Usuario> avaliadores;
    private String documentacao;
    private CriterioDeAvaliacao criterioDeAvaliacao;
    private List<Avaliacao> avaliacoes;
    private List<Documentacao> documentacoes;
    private boolean status;
    private Etapa prerequisito;
    
    
}
