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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lavínia Matoso
 */
public class SelecaoBeans implements Beans {

    private long codSelecao;
    @NotNull @Size(min = 5)
    private String titulo;
    private String descricao;
    private List responsaveis = Collections.synchronizedList(new ArrayList<UsuarioBeans>());;
    private EtapaBeans inscricao;
    private List etapas;
    @Min(0)
    private int vagasRemuneradas;
    @Min(0)
    private int vagasVoluntarias;
    @NotNull
    private String descricaoPreRequisitos;
    @NotNull
    private String areaDeConcentracao;
    private List<ParticipanteBeans> candidatos;
    @NotNull
    private String categoria;
    private List aditivos;
    private List anexos;
    private ArquivoBeans edital;
    private EstadoSelecao estado = new EstadoSelecao();
    
    public SelecaoBeans(){}

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

    public List<UsuarioBeans> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List usuario) {
        this.responsaveis = usuario;
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

    public void setEtapas(List etapas) {
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

    public void setCandidatos(List candidatos) {
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

    public void setAditivos(List aditivos) {
        this.aditivos = aditivos;
    }

    public List<ArquivoBeans> getAnexos() {
        return anexos;
    }

    public void setAnexos(List anexos) {
        this.anexos = anexos;
    }

    public ArquivoBeans getEdital() {
        return edital;
    }

    public void setEdital(ArquivoBeans edital) {
        this.edital = edital;
    }

    public EstadoSelecao getEstado() {
        return estado;
    }

    public void setEstado(EstadoSelecao estado) {
        this.estado = estado;
    }
    
    @Override
    public Object toBusiness() { //Esse método transforma uma Beans em um objeto seleção
        Selecao selecao = new Selecao();
        
        if(this.getCodSelecao() > 0){
            selecao.setCodSelecao(this.getCodSelecao());
        }
        selecao.setTitulo(this.getTitulo());
        selecao.setDescricao(this.getDescricao());
        if (this.getInscricao() != null) {
            selecao.setInscricao((Etapa) this.getInscricao().toBusiness());
        }
        if(this.vagasRemuneradas >= 0) selecao.setVagasRemuneradas(this.getVagasRemuneradas());
        if(this.vagasVoluntarias >= 0) selecao.setVagasVoluntarias(this.getVagasVoluntarias());
        selecao.setDescricaoPreRequisitos(this.getDescricaoPreRequisitos());
        selecao.setAreaDeConcentracao(this.getAreaDeConcentracao());
        selecao.setCategoria(this.getCategoria());
        if (this.getEdital() != null) {
            selecao.setEdital((Arquivo) this.getEdital().toBusiness());
        }
        selecao.setEstado(this.getEstado());
        
        List<Usuario> responsaveis = Collections.synchronizedList(new ArrayList<Usuario>());
        //Ajeitar
        if(this.getResponsaveis()!=null){
            List<UsuarioBeans> resp = this.getResponsaveis();
            for(int i=0;i<resp.size();i++){
                responsaveis.add((Usuario) new UsuarioBeans().toBusiness());
            }
        }
        selecao.setResponsavel(responsaveis);
        
        List<Etapa> etapas = Collections.synchronizedList(new ArrayList<Etapa>()); 
        if(this.getEtapas()!=null){
            for(int i=0;i<this.getEtapas().size();i++){
                etapas.add((Etapa) ((EtapaBeans) this.getEtapas().get(i)).toBusiness());
            }
        }
        System.out.println("Etapas tamanho: "+etapas.size());
        selecao.setEtapas(etapas);
        
        List<Arquivo> aditivos = Collections.synchronizedList(new ArrayList<Arquivo>());
        if(this.getAditivos()!=null){
            for(int i=0;i<this.getAditivos().size();i++){
                aditivos.add((Arquivo) ((ArquivoBeans)this.getAditivos().get(i)).toBusiness());
            }
        }
        selecao.setAditivos(aditivos);
        
        List<Arquivo> anexos = Collections.synchronizedList(new ArrayList<Arquivo>());
        if(this.getAnexos()!=null){
            for(int i=0;i<this.getAnexos().size();i++){
                anexos.add((Arquivo) ((ArquivoBeans)this.getAnexos().get(i)).toBusiness());
            }
        }
        selecao.setAnexos(anexos);
        
        List<Participante> candidatos = Collections.synchronizedList(new ArrayList<Participante>());
        if(this.getCandidatos()!=null){
            for(int i=0;i<this.getCandidatos().size();i++){
                candidatos.add((Participante) ((ParticipanteBeans)this.getCandidatos().get(i)).toBusiness());
            }
        }
        selecao.setCandidatos(candidatos);

        return selecao;
    }

    @Override
    public Beans toBeans(Object object) { //Esse método transforma um objeto Seleção em uma Beans
        if(object != null){
            if(object instanceof Selecao){
                Selecao selecao = (Selecao) object;
                this.setCodSelecao(selecao.getCodSelecao());
                this.setTitulo(selecao.getTitulo());
                this.setDescricao(selecao.getDescricao());
                this.setVagasRemuneradas(selecao.getVagasRemuneradas());
                this.setVagasVoluntarias(selecao.getVagasVoluntarias());
                this.setDescricaoPreRequisitos(selecao.getDescricaoPreRequisitos());
                this.setAreaDeConcentracao(selecao.getAreaDeConcentracao());
                this.setCategoria(selecao.getCategoria());
                
                EtapaBeans eb = null;
                if(selecao.getInscricao()!=null){
                   eb = (EtapaBeans) (new EtapaBeans().toBeans(selecao.getInscricao()));
                }
                this.setInscricao(eb);
 
                ArquivoBeans ab = null;
                if(selecao.getEdital()!=null){
                   ab = (ArquivoBeans) (new ArquivoBeans().toBeans(selecao.getEdital()));
                }
                this.setEdital(ab);
                this.setResponsaveis(selecao.getResponsaveis());
                this.setAditivos(selecao.getAditivos());
                this.setAnexos(selecao.getAnexos());
                this.setCandidatos(selecao.getCandidatos());
                this.setEtapas(selecao.getEtapas());
                return this;
            }else{
                throw new IllegalArgumentException("O objeto a ser adicionado não é uma Seleção!");
            }
        }else{
            throw new NullPointerException("Seleção não pode ser nula!");
        }
    }
    
}
