/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

<<<<<<< HEAD

=======
>>>>>>> edef1eb94b98c6ae1ef394857bbc0f6e46b26681
import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author N2S-PC03
 */
@Converter(autoApply = true)
@Entity
@Table(name="selecao")
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codSelecao")
    private long codSelecao;
    private String titulo;
    private String descricao;
    @ManyToMany(targetEntity = Usuario.class)
    @JoinTable(name="responsaveis_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "usuario", referencedColumnName = "codUsuario")})
    private List<Usuario> responsaveis;
    @ManyToOne
    @JoinColumn(name="periodo", referencedColumnName="codPeriodo")
    private Periodo periodo;
    @ManyToOne
    @JoinColumn(name="etapa", referencedColumnName="codEtapa")
    private Etapa inscricao;
    @ManyToMany(targetEntity = Etapa.class)
    @JoinTable(name="etapas_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "etapa", referencedColumnName = "codEtapa")})
    private List<Etapa> etapas;
    private int vagasRemuneradas;
    private int vagasVoluntarias;
    private String descricaoPreRequisitos;
    private String areaDeConcentracao;
    @ManyToMany(targetEntity = Usuario.class)
    @JoinTable(name="candidatos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "participante", referencedColumnName = "codParticipante")})
    private List<Participante> candidatos;
    private String categoria;
    @ManyToMany(targetEntity = Arquivo.class)
    @JoinTable(name="aditivos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> aditivos;
    @ManyToMany(targetEntity = Arquivo.class)
    @JoinTable(name="anexos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> anexos;
    @ManyToOne
    @JoinColumn(name="arquivo", referencedColumnName="codArquivo")
    private Arquivo edital;
    @ManyToOne
    @JoinColumn(name="estadoSelecao", referencedColumnName="codEstadoSelecao")
    private EstadoSelecao estado;
    
    
    
    public long getCodSelecao() {
        return codSelecao;
    }

    public void setCodSelecao(long codSelecao) {
        if(codSelecao > 0){
            this.codSelecao = codSelecao;
        }else{
            throw new IllegalCodeException("Código da seleção deve ser maior que zero!");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo != null){
            this.titulo = titulo;
        }else{
            throw new IllegalArgumentException("Titulo da seleção não pode ser vazio!");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao != null){
            this.descricao = descricao;
        }else{
            throw new IllegalArgumentException("Descrição da seleção não deve ser vazia!");
        }
    }

    public List<Usuario> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsavel(List<Usuario> responsaveis) {
        if(responsaveis != null){
            if(!responsaveis.isEmpty()){
                this.responsaveis = responsaveis;
            }else{
                throw new IllegalArgumentException("Lista de responsaveis está vazia!");
            }
        }else{
                throw new IllegalArgumentException("Lista de responsaveis não pode ser nula!");
            }
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        if(periodo != null){
            this.periodo = periodo;
        }else{
            throw new IllegalArgumentException("Deve ser selecionado um periodo para a seleção!");
        }
    }

    public Etapa getInscricao() {
        return inscricao;
    }

    public void setInscricao(Etapa inscricao) {
        if(inscricao != null){
            this.inscricao = inscricao;
        }else{
            throw new IllegalArgumentException("Etapa de inscrição não pode ser nula!");
        }
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        if(etapas != null){
            this.etapas = etapas;
        }else{
            throw new IllegalArgumentException("Lista de etapas não pode ser nula!");
        }
    }

    public int getVagasRemuneradas() {
        return vagasRemuneradas;
    }

    public void setVagasRemuneradas(int vagasRemuneradas) {
        if(vagasRemuneradas >= 0){
            this.vagasRemuneradas = vagasRemuneradas;
        }else{
            throw new IllegalArgumentException("O número de vagas remuneradas não pode ser menor que zero!");
        }
    }

    public int getVagasVoluntarias() {
        return vagasVoluntarias;
    }

    public void setVagasVoluntarias(int vagasVoluntarias) {
        if(vagasVoluntarias >= 0){
            this.vagasVoluntarias = vagasVoluntarias;
        }else{
            throw new IllegalArgumentException("O número de vagas voluntarias não pode ser menor que zero!");
        }
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
        if(areaDeConcentracao != null){
            this.areaDeConcentracao = areaDeConcentracao;
        }else{
            throw new IllegalArgumentException("Área de concentração não pode ser vazia!");
        }
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
        if(categoria != null){
            this.categoria = categoria;
        }else{
            throw new IllegalArgumentException("Categoria da seleção não pode ser nula!");
        }
    }

    public List<Arquivo> getAditivos() {
        return aditivos;
    }

    public void setAditivos(List<Arquivo> aditivos) {
        this.aditivos = aditivos;
    }

    public List<Arquivo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Arquivo> anexos) {
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
        if(estado != null){
            this.estado = estado;
        }else{
            throw new IllegalArgumentException("Estado da seleção não pode ser nulo!");
        }
    }
    
    public void adicionaEtapa(Etapa etapa){
        if(etapa != null){
            etapas.add(etapa);
        }else{
            throw new IllegalArgumentException("Etapa adicionada não pode ser nula!");
        }
    }
    
    public void adicionaResponsavel(Usuario responsavel){
        if(responsaveis.isEmpty()){
            if(responsavel != null){
                this.responsaveis.add(responsavel);
            }else{
                throw new IllegalArgumentException("Deve ser selecionado um responsável para a seleção!");
            }
        }else{
            if(responsavel != null){
                this.responsaveis.add(responsavel);
            }else{
                throw new IllegalArgumentException("Responsável não pode ser nulo!");
            }
        }
    }
    
    public boolean isResponsavel(Usuario responsavel){
        if(responsavel != null){
            if(this.responsaveis.contains(responsavel)){
                return true;
            }
        }
        else{
            throw new IllegalArgumentException("Responsavel não pode ser nulo!");
        }
        return false;
    }
    
    public boolean isParticipante(Usuario participante){
        if(participante != null){
            if(this.candidatos.contains(participante)){
                return true;
            }
        }
        else{
            throw new IllegalArgumentException("Participante não pode ser nulo!");
        }
        return false;
    }
    
    public void removeResponsavel(Usuario responsavel){
        if(responsavel != null){
            this.responsaveis.remove(responsavel);
        }
        else{
            throw new IllegalArgumentException("Responsavel não pode ser nulo!");
        }
    }
    
    public void removeEtapa(Etapa etapa){
        if(etapa != null){
            this.etapas.remove(etapa);
        }
        else{
            throw new IllegalArgumentException("Etapa não pode ser nula!");
        }
    }
    
    public void inscreve(Participante participante){
        if(participante != null){
            this.candidatos.add(participante);
        }
        else{
            throw new IllegalArgumentException("Participante não pode ser nulo!");
        }
    }

    public void setResponsavel(UsuarioBeans responsavel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
