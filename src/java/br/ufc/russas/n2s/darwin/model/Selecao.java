/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class Selecao {

    private long codSelecao;
    private String titulo;
    private String descricao;
    private Usuario usuario;
    private Etapa inscricao;
    private List<Etapa> etapas;
    private int vagasRemuneradas;
    private int vagasVoluntarias;
    private String descricaoPreRequisitos;
    private String areaDeConcentracao;
    private List<Participante> candidatos;
    private String categoria;
    private List<Arquivo> aditivos;
    private List<Arquivo> anexos;
    private Arquivo edital;
    private EstadoSelecao estado;
    
    
    public long getCodSelecao() {
        return codSelecao;
    }

    public void setCodSelecao(long codSelecao) {
        this.codSelecao = codSelecao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Etapa getInscricao() {
        return inscricao;
    }

    public void setInscricao(Etapa inscricao) {
        this.inscricao = inscricao;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(ArrayList<Etapa> etapas) {
        this.etapas = etapas;
    }

    public int getVagasRemuneradas() {
        return vagasRemuneradas;
    }

    public void setVagasRemuneradas(int vagasRemuneradas) {
        this.vagasRemuneradas = vagasRemuneradas;
    }

    public int getVagasVoluntarias() {
        return vagasVoluntarias;
    }

    public void setVagasVoluntarias(int vagasVoluntarias) {
        this.vagasVoluntarias = vagasVoluntarias;
    }

    public String getDescricaoPreRequisitos() {
        return descricaoPreRequisitos;
    }

    public void setDescricaoPreRequisitos(String descricaoPreRequisitos) {
        this.descricaoPreRequisitos = descricaoPreRequisitos;
    }

    public String getAreaDeConcentracao() {
        return areaDeConcentracao;
    }

    public void setAreaDeConcentracao(String areaDeConcentracao) {
        this.areaDeConcentracao = areaDeConcentracao;
    }

    public List<Participante> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Participante> candidatos) {
        this.candidatos = candidatos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Arquivo> getAditivos() {
        return aditivos;
    }

    public void setAditivos(ArrayList<Arquivo> aditivos) {
        this.aditivos = aditivos;
    }

    public List<Arquivo> getAnexos() {
        return anexos;
    }

    public void setAnexos(ArrayList<Arquivo> anexos) {
        this.anexos = anexos;
    }

    public Arquivo getEdital() {
        return edital;
    }

    public void setEdital(Arquivo edital) {
        this.edital = edital;
    }

    public EstadoSelecao getEstado() {
        return estado;
    }

    public void setEstado(EstadoSelecao estado) {
        this.estado = estado;
    }
    
    public void adicionaEtapa(Etapa etapa){
        
    }
    
    public void adicionaResponsavel(Usuario responsavel){
        
    }
    
    public boolean isResponsavel(Usuario responsavel){
        return false;
    }
    
    public boolean isParticipante(Usuario participante){
        return false;
    }
    
    public void removeResponsavel(Usuario responsavel){
        
    }
    
    public void removeEtapa(Etapa etapa){
        
    }
    
    public void inscreve(Usuario participante){
        
    }
    
    public void adicionaSelecao(Selecao selecao){
        
    }
    
    public void removeSelecao(Selecao selecao){
        
    }
    
    public boolean isFinalizado(){
        return false;
    }
}
