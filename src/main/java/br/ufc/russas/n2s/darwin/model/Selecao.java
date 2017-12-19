/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.dao.EtapaDAOIfc;
import br.ufc.russas.n2s.darwin.dao.EtapaDAOImpl;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOIfc;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOImpl;
import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author N2S-PC03
 */
@Entity
@Converter(autoApply = true)
@Table(name = "selecao")
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codSelecao")
    private long codSelecao;
    private String titulo;
    private String descricao;
    @ManyToMany(targetEntity = UsuarioDarwin.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "responsaveis_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "usuario", referencedColumnName = "codUsuario")})
    private List<UsuarioDarwin> responsaveis;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "periodo", referencedColumnName = "codPeriodo")
    private Periodo periodo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etapa_inscricao", referencedColumnName = "codEtapa")
    private Inscricao inscricao;
    @ManyToMany(targetEntity = Etapa.class,  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "etapas_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "etapa", referencedColumnName = "codEtapa")})
    private List<Etapa> etapas;
    private int vagasRemuneradas;
    private int vagasVoluntarias;
    private String descricaoPreRequisitos;
    private String areaDeConcentracao;
    private String categoria;
    @ManyToMany(targetEntity = Arquivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="aditivos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> aditivos;
    @ManyToMany(targetEntity = Arquivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="anexos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> anexos;
    @ManyToOne(targetEntity = Arquivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "edital", referencedColumnName = "codArquivo")
    private Arquivo edital;
    @Enumerated(EnumType.ORDINAL)
    private EnumEstadoSelecao estado;
    private boolean divulgada;

    public Selecao() {
    }

    public long getCodSelecao() {
        return codSelecao;
    }

    public void setCodSelecao(long codSelecao) {
        if (codSelecao > 0) {
            this.codSelecao = codSelecao;
        } else {
            throw new IllegalCodeException("Código da seleção deve ser maior que zero!");
        }
    }

    public final String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null) {
            this.titulo = titulo;
        } else {
            throw new IllegalArgumentException("Titulo da seleção não pode ser vazio!");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null) {
            this.descricao = descricao;
        } else {
            throw new IllegalArgumentException("Descrição da seleção não deve ser vazia!");
        }
    }

    public List<UsuarioDarwin> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsavel(List<UsuarioDarwin> responsaveis) {
        if (responsaveis != null) {
            this.responsaveis = responsaveis;
        } else {
            throw new IllegalArgumentException("Lista de responsaveis não pode ser nula!");
        }
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        if (periodo != null) {
            this.periodo = periodo;
        } else {
            throw new IllegalArgumentException("Deve ser selecionado um periodo para a seleção!");
        }
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        if (inscricao != null) {
            this.inscricao = inscricao;
        } else {
            throw new IllegalArgumentException("Etapa de inscrição não pode ser nula!");
        }
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        if (etapas != null) {
            this.etapas = etapas;
        } else {
            throw new IllegalArgumentException("Lista de etapas não pode ser nula!");
        }
    }

    public int getVagasRemuneradas() {
        return vagasRemuneradas;
    }

    public void setVagasRemuneradas(int vagasRemuneradas) {
        if (vagasRemuneradas >= 0) {
            this.vagasRemuneradas = vagasRemuneradas;
        } else {
            throw new IllegalArgumentException("O número de vagas remuneradas não pode ser menor que zero!");
        }
    }

    public int getVagasVoluntarias() {
        return vagasVoluntarias;
    }

    public void setVagasVoluntarias(int vagasVoluntarias) {
        if (vagasVoluntarias >= 0){
            this.vagasVoluntarias = vagasVoluntarias;
        } else {
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

    public void setAreaDeConcentracao(final String areaDeConcentracao) {
        if (areaDeConcentracao != null) {
            this.areaDeConcentracao = areaDeConcentracao;
        } else {
            throw new IllegalArgumentException("Área de concentração não pode ser vazia!");
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria != null) {
            this.categoria = categoria;
        } else {
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

    public EnumEstadoSelecao getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoSelecao estado) {
        if (estado != null) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado da seleção não pode ser nulo!");
        }
    }

    public boolean isDivulgada() {
        return divulgada;
    }

    public void setDivulgada(boolean divulgada) {
        this.divulgada = divulgada;
    }
        
    public Selecao adicionaSelecao() {
        return this;
    }
    
    public Selecao atualizaSelecao() {
        return this;
    }
    
    public Etapa adicionaEtapa(Etapa etapa) {
        if (etapa != null) {
            etapas.add(etapa);
            return etapa;
        } else {
            throw new IllegalArgumentException("Etapa adicionada não pode ser nula!");
        }
    }
    
    public Etapa atualizaEtapa(Etapa etapa) {
        if (etapa != null) {            
            EtapaDAOIfc etapaDAOIfc = new EtapaDAOImpl();
            return etapaDAOIfc.atualizaEtapa(etapa);
        } else {
            throw new IllegalArgumentException("Etapa para atualizar não pode ser nula!");
        }
    }
    
    public void adicionaResponsavel(UsuarioDarwin responsavel) {
        SelecaoDAOIfc selecaoDAOIfc = new SelecaoDAOImpl();
        if (responsaveis.isEmpty()) {
            if (responsavel != null) {
                this.responsaveis.add(responsavel);
                selecaoDAOIfc.atualizaSelecao(this);
            } else {
                throw new IllegalArgumentException("Deve ser selecionado um responsável para a seleção!");
            }
        } else {
            if (responsavel != null) {
                this.responsaveis.add(responsavel);
                selecaoDAOIfc.atualizaSelecao(this);
            } else {
                throw new IllegalArgumentException("Responsável não pode ser nulo!");
            }
        }
    }
    
    public boolean isResponsavel(UsuarioDarwin responsavel) {
        if (responsavel != null) {
            if (this.responsaveis.contains(responsavel)) {
                return true;
            }
        } else {
            throw new IllegalArgumentException("Responsavel não pode ser nulo!");
        }
        return false;
    }
    
    public void removeResponsavel(UsuarioDarwin responsavel) {    
        if (this.getResponsaveis().size() > 1) {
            if (responsavel != null) {
                this.responsaveis.remove(responsavel);
                SelecaoDAOIfc selecaoDAOIfc = new SelecaoDAOImpl();
                selecaoDAOIfc.atualizaSelecao(this);
            } else {
                throw new IllegalArgumentException("Responsavel não pode ser nulo!");
            }
        } else {
            throw new IllegalArgumentException("Não é possível remover responsável, pois só existe um na lista de responsáveis!");
        }
    }

    public void removeEtapa(Etapa etapa){
        if (etapa != null) {
            if (!etapa.getEstado().equals(EnumEstadoEtapa.ANDAMENTO)) {
                this.etapas.remove(etapa);
            } else {
                throw new IllegalArgumentException("Etapa em andamento, não sendo possível remove-la!");
            }
        } else {
            throw new IllegalArgumentException("Etapa não pode ser nula!");
        }
    }

    public Etapa getEtapaAtual() {
        if (this.etapas != null && !this.etapas.isEmpty()) {
            for (Etapa e: etapas) {
                if (e.getPeriodo().getInicio().isBefore(LocalDate.now()) && e.getPeriodo().getTermino().isAfter(LocalDate.now())) {
                    return e;
                }
            }
        } 
        return null;
    }
    
    public Etapa getUltimaEtapa() {
        Etapa etapa = null;
        if (this.etapas != null && !this.etapas.isEmpty()) {
            etapa = this.getEtapas().get(0);
            for (Etapa e: etapas) {
                if (e.getPeriodo().getInicio().isAfter(etapa.getPeriodo().getInicio())) {
                    etapa = e;
                }
            }
        } else {
            throw new RuntimeException("Lista de etapas está vazia!");
        }
        return etapa;
    }
    
    public void resultado () {
        List<Object[]> aprovados = getUltimaEtapa().getAprovados();
        int limiteClassificados =  getVagasRemuneradas() + getVagasVoluntarias();
        int n = aprovados.size();
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                Object[] temp = aprovados.get(i);
                int j = i;
                Object[] outro = aprovados.get(j - gap);
                for (j = i; j >= gap && ((float) outro[1]) > ( (float) temp[1]); j -= gap) {
                    aprovados.set(j, outro);
                    outro = aprovados.get(j - gap);
                }
                aprovados.set(j, temp);
            }
        }
        if (aprovados.size() > limiteClassificados && limiteClassificados != 0) {
            Object[] temp = aprovados.get(limiteClassificados-1);
            for (int i = limiteClassificados; i < aprovados.size(); i++) {
                Object[] aprovado = aprovados.get(i);
                if (((float) temp[1]) != ((float) aprovado[1])) {
                    aprovados.remove(i);
                } 
            }
        }
    }
    
}
