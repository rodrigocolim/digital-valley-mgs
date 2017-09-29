/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;


import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Usuario;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.EstadoSelecao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lavínia Matoso
 */
public class SelecaoBeans implements Beans {

    private long codSelecao;
    private String titulo;
    private String descricao;
    private UsuarioBeans responsavel;
    private EtapaBeans inscricao;
    private List<EtapaBeans> etapas;
    private int vagasRemuneradas;
    private int vagasVoluntarias;
    private String descricaoPreRequisitos;
    private String areaDeConcentracao;
    private List<ParticipanteBeans> candidatos;
    private String categoria;
    private List<ArquivoBeans> aditivos;
    private List<ArquivoBeans> anexos;
    private ArquivoBeans edital;
    private EstadoSelecaoBeans estado;
    
    public SelecaoBeans(){}
    public SelecaoBeans(long codSelecao, String titulo, String descricao, UsuarioBeans responsavel, EtapaBeans inscricao, List<EtapaBeans> etapas, int vagasRemuneradas, int vagasVoluntarias, String descricaoPreRequisitos, String areaDeConcentracao, List<ParticipanteBeans> candidatos, String categoria, List<ArquivoBeans> aditivos, List<ArquivoBeans> anexos, ArquivoBeans edital, EstadoSelecaoBeans estado) {
        this.codSelecao = codSelecao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.inscricao = inscricao;
        this.etapas = etapas;
        this.vagasRemuneradas = vagasRemuneradas;
        this.vagasVoluntarias = vagasVoluntarias;
        this.descricaoPreRequisitos = descricaoPreRequisitos;
        this.areaDeConcentracao = areaDeConcentracao;
        this.candidatos = candidatos;
        this.categoria = categoria;
        this.aditivos = aditivos;
        this.anexos = anexos;
        this.edital = edital;
        this.estado = estado;
    }

    
    
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

    public UsuarioBeans getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioBeans usuario) {
        this.responsavel = usuario;
    }

    public EtapaBeans getInscricao() {
        return inscricao;
    }

    public void setInscricao(EtapaBeans inscricao) {
        this.inscricao = inscricao;
    }

    public List<EtapaBeans> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<EtapaBeans> etapas) {
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

    public List<ParticipanteBeans> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<ParticipanteBeans> candidatos) {
        this.candidatos = candidatos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<ArquivoBeans> getAditivos() {
        return aditivos;
    }

    public void setAditivos(List<ArquivoBeans> aditivos) {
        this.aditivos = aditivos;
    }

    public List<ArquivoBeans> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<ArquivoBeans> anexos) {
        this.anexos = anexos;
    }

    public ArquivoBeans getEdital() {
        return edital;
    }

    public void setEdital(ArquivoBeans edital) {
        this.edital = edital;
    }

    public EstadoSelecaoBeans getEstado() {
        return estado;
    }

    public void setEstado(EstadoSelecaoBeans estado) {
        this.estado = estado;
    }
    
    @Override
    public Object toBusiness() { 
        Selecao selecao = new Selecao();
        
        if(this.getCodSelecao() > 0){
            selecao.setCodSelecao(this.getCodSelecao());
        }
        selecao.setTitulo(this.getTitulo());
        selecao.setDescricao(this.getDescricao());
        selecao.setResponsavel((Usuario)this.getResponsavel());
        selecao.setInscricao((Etapa) this.getInscricao().toBusiness());
        if(this.vagasRemuneradas >= 0) selecao.setVagasRemuneradas(this.getVagasRemuneradas());
        if(this.vagasVoluntarias >= 0) selecao.setVagasVoluntarias(this.getVagasVoluntarias());
        selecao.setDescricaoPreRequisitos(this.getDescricaoPreRequisitos());
        selecao.setAreaDeConcentracao(this.getAreaDeConcentracao());
        selecao.setCategoria(this.getCategoria());
        selecao.setEdital((Arquivo) this.getEdital().toBusiness());
        selecao.setEstado((EstadoSelecao)this.getEstado().toBusiness());
        List<Etapa> etapas = Collections.synchronizedList(new ArrayList<Etapa>()); //Tem que codificar os que tiverem List. Pq assim tá declarado.
        List<Arquivo> aditivos = Collections.synchronizedList(new ArrayList<Arquivo>());
        List<Arquivo> anexos = Collections.synchronizedList(new ArrayList<Arquivo>());
        List<Participante> candidatos = Collections.synchronizedList(new ArrayList<Participante>());
        
        
        return selecao;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Selecao){
                Selecao selecao = (Selecao) object;
                this.setCodSelecao(selecao.getCodSelecao());
                this.setTitulo(selecao.getTitulo());
                this.setDescricao(selecao.getDescricao());
                
                UsuarioBeans ub = null;
                if(selecao.getUsuario()!=null){
                   ub = (UsuarioBeans) (new UsuarioBeans().toBeans(selecao.getUsuario()));
                }
                this.setResponsavel(ub);
                
                EtapaBeans eb = null;
                if(selecao.getInscricao()!=null){
                   eb = (EtapaBeans) (new EtapaBeans().toBeans(selecao.getInscricao()));
                }
                this.setInscricao(eb);
                
                this.setVagasRemuneradas(selecao.getVagasRemuneradas());
                this.setVagasVoluntarias(selecao.getVagasVoluntarias());
                this.setDescricaoPreRequisitos(selecao.getDescricaoPreRequisitos());
                this.setAreaDeConcentracao(selecao.getAreaDeConcentracao());
                this.setCategoria(selecao.getCategoria());
                
                ArquivoBeans ab = null;
                if(selecao.getEdital()!=null){
                   ab = (ArquivoBeans) (new ArquivoBeans().toBeans(selecao.getEdital()));
                }
                this.setEdital(ab);
                
                List<EtapaBeans> etapas = Collections.synchronizedList(new ArrayList<EtapaBeans>());
                List<ArquivoBeans> aditivos = Collections.synchronizedList(new ArrayList<ArquivoBeans>());
                List<ArquivoBeans> anexos = Collections.synchronizedList(new ArrayList<ArquivoBeans>());
                List<ParticipanteBeans> candidatos = Collections.synchronizedList(new ArrayList<ParticipanteBeans>());
                
                return this;
            }else{
                throw new IllegalArgumentException("O objeto a ser adicionado não é uma Seleção!");
            }
        }else{
            throw new NullPointerException("Seleção não pode ser nula!");
        }
    }
    
}
