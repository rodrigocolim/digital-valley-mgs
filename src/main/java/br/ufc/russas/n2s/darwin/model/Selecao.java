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
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
    //@Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "responsaveis_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "usuario", referencedColumnName = "codUsuario")})
    private List<UsuarioDarwin> responsaveis;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "periodo", referencedColumnName = "codPeriodo")
    private Periodo periodo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etapa_inscricao", referencedColumnName = "codEtapa")
    private Etapa inscricao;
    @ManyToMany(targetEntity = Etapa.class,  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "etapas_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "etapa", referencedColumnName = "codEtapa")})
    private List<Etapa> etapas;
    private int vagasRemuneradas;
    private int vagasVoluntarias;
    private String descricaoPreRequisitos;
    private String areaDeConcentracao;
    @ManyToMany(targetEntity = Participante.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "candidatos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "participante", referencedColumnName = "codParticipante")})
    private List<Participante> candidatos;
    private String categoria;
    @ManyToMany(targetEntity = Arquivo.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="aditivos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> aditivos;
    @ManyToMany(targetEntity = Arquivo.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="anexos_selecao", joinColumns = {@JoinColumn(name = "selecao", referencedColumnName = "codSelecao")},
    inverseJoinColumns = {@JoinColumn(name = "arquivo", referencedColumnName = "codArquivo")})
    private List<Arquivo> anexos;
    @ManyToOne(targetEntity = Arquivo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "edital", referencedColumnName = "codArquivo")
    private Arquivo edital;
    @Embedded
    private EstadoSelecao estado;

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

    public Etapa getInscricao() {
        return inscricao;
    }

    public void setInscricao(Etapa inscricao) {
        if (inscricao != null) {
            this.inscricao = inscricao;
            adicionaEtapa(inscricao);
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

    public List<Participante> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Participante> candidatos) {
        this.candidatos = candidatos;
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

    public EstadoSelecao getEstado() {
        return estado;
    }

    public void setEstado(EstadoSelecao estado) {
        if (estado != null) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado da seleção não pode ser nulo!");
        }
    }
    public Selecao adicionaSelecao() {
        SelecaoDAOIfc selecaoDAOIfc = new SelecaoDAOImpl();
        return selecaoDAOIfc.adicionaSelecao(this);
    }
    
    public Selecao atualizaSelecao() {
        SelecaoDAOIfc selecaoDAOIfc = new SelecaoDAOImpl();
        return selecaoDAOIfc.atualizaSelecao(this);
    }
    
    public Etapa adicionaEtapa(Etapa etapa) {
        if (etapa != null) {
            if (inscricao == null) {
                etapas.add(etapa);
            } else {
                inscricao = etapa;
            }
            SelecaoDAOIfc selecaoDAOIfc = new SelecaoDAOImpl();
            selecaoDAOIfc.atualizaSelecao(this);
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
<<<<<<< HEAD
        if (responsaveis.isEmpty() || responsaveis == null) {
=======
        SelecaoDAOIfc selecaoDAOIfc = new SelecaoDAOImpl();
        if (responsaveis.isEmpty()) {
>>>>>>> fc89868448f9bfd11d393f642b416e9d63aa4f2f
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
    
    public boolean isParticipante(UsuarioDarwin participante) {
        if (participante != null) {
            if (this.candidatos.contains(participante)) {
                return true;
            }
        } else {
            throw new IllegalArgumentException("Participante não pode ser nulo!");
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

    public void inscreve(Participante participante) {
        if (participante != null) {
            this.candidatos.add(participante);
        } else {
            throw new IllegalArgumentException("Participante não pode ser nulo!");
        }
    }
    public Etapa getEtapaAtual() {
        if (this.etapas != null && !this.etapas.isEmpty()) {
            for (Etapa e: etapas) {
                if (e.getPeriodo().getInicio().isBefore(LocalDate.now()) && e.getPeriodo().getTermino().isAfter(LocalDate.now())) {
                    return e;
                }
            }
        } else {
            throw new RuntimeException("Lista de etapas está vazia!");
        }
        return null;
    }

}
