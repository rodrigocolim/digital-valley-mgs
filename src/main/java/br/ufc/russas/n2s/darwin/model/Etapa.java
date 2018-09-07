/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import br.ufc.russas.n2s.darwin.model.Participante;

/**
 *
 * @author Wallison Carlos, Gilberto Lima
 */
@Entity
@Table(name = "etapa")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue("E")
public class Etapa implements Serializable, Atualizavel {
	@Id
	@Column(name = "codEtapa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codEtapa;
	private String titulo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "periodo", referencedColumnName = "codPeriodo")
	private Periodo periodo;
	private String descricao;
	
	@ManyToMany(targetEntity = UsuarioDarwin.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "avaliadores", joinColumns = {
			@JoinColumn(name = "etapa", referencedColumnName = "codEtapa") }, inverseJoinColumns = {
					@JoinColumn(name = "avaliador", referencedColumnName = "codUsuario") })
	private List<UsuarioDarwin> avaliadores;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@CollectionTable(name = "documentacoes_exigidas", joinColumns = @JoinColumn(name = "codEtapa"))
	@Column(name = "documentacao_exigida")
	private List<String> documentacaoExigida;
	
	@Column(name = "criterio_de_avaliacao")
	@Enumerated(EnumType.ORDINAL)
	private EnumCriterioDeAvaliacao criterioDeAvaliacao;
	
	@ManyToMany(targetEntity = Avaliacao.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "avaliacoes", joinColumns = {
			@JoinColumn(name = "etapa", referencedColumnName = "codEtapa") }, inverseJoinColumns = {
					@JoinColumn(name = "avaliacao", referencedColumnName = "codAvaliacao") })
	private List<Avaliacao> avaliacoes;
	
	@ManyToMany(targetEntity = Documentacao.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "documentacoes", joinColumns = {
			@JoinColumn(name = "etapa", referencedColumnName = "codEtapa") }, inverseJoinColumns = {
					@JoinColumn(name = "documentacao", referencedColumnName = "codDocumentacao") })
	private List<Documentacao> documentacoes;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumEstadoEtapa estado;
	
	@ManyToOne
	@JoinColumn(name = "prerequisito", referencedColumnName = "codEtapa")
	private Etapa prerequisito;
	private float notaMinima;
	private int limiteClassificados;
	private boolean divulgadoResultado;
	
	@ManyToMany(targetEntity = Participante.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "participantes_etapa", joinColumns = {
			@JoinColumn(name = "etapa", referencedColumnName = "codEtapa") }, inverseJoinColumns = {
					@JoinColumn(name = "participante", referencedColumnName = "codParticipante") })
	private List<Participante> participantes;

	public long getCodEtapa() {
		return codEtapa;
	}

	public void setCodEtapa(long codEtapa) {
		if (codEtapa > 0) {
			this.codEtapa = codEtapa;
		} else {
			//throw new IllegalCodeException("Código de etapa deve ser maior que zero!");
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null) {
			throw new NullPointerException("Título não pode ser nulo!");
		} else if (titulo.isEmpty()) {
			throw new NullPointerException("Título não pode ser vazio!");
		} else {
			this.titulo = titulo;
		}
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		if (periodo != null) {
			this.periodo = periodo;
		} else {
			throw new NullPointerException("Período não pode ser nulo!");
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<UsuarioDarwin> getAvaliadores() {
		return avaliadores;
	}

	public void setAvaliadores(List<UsuarioDarwin> avaliadores) {
		this.avaliadores = avaliadores;
	}

	public List<String> getDocumentacaoExigida() {
		return documentacaoExigida;
	}

	public void setDocumentacaoExigida(List<String> documentacao) {
		this.documentacaoExigida = documentacao;
	}

	public EnumCriterioDeAvaliacao getCriterioDeAvaliacao() {
		return criterioDeAvaliacao;
	}

	public void setCriterioDeAvaliacao(EnumCriterioDeAvaliacao criterioDeAvaliacao) {
		if (criterioDeAvaliacao != null) {
			this.criterioDeAvaliacao = criterioDeAvaliacao;
		} else {
			throw new NullPointerException("Deve ser selecionado um critério de avaliação!");
		}
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Documentacao> getDocumentacoes() {
		return documentacoes;
	}

	public void setDocumentacoes(List<Documentacao> documentacoes) {
		this.documentacoes = documentacoes;
	}

	public EnumEstadoEtapa getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoEtapa estado) {
		this.estado = estado;
	}

	public Etapa getPrerequisito() {
		return prerequisito;
	}
	
	

	public float getNotaMinima() {
		return notaMinima;
	}

	public void setNotaMinima(float notaMinima) {
		if (notaMinima >= 0 && notaMinima <= 10) {
			this.notaMinima = notaMinima;
		} else {
			throw new IllegalArgumentException(
					"Nota miníma inválida. Nota miníma deve ser maior igual a zero e menor igual a 10!");
		}
	}

	public int getLimiteClassificados() {
		return limiteClassificados;
	}

	public void setLimiteClassificados(int limiteClassificados) {
		if (limiteClassificados >= 0) {
			this.limiteClassificados = limiteClassificados;
		} else {
			throw new IllegalArgumentException("Limite de Classificados deve ser maior igual a zero!");
		}
	}

	public boolean isDivulgadoResultado() {
		return divulgadoResultado;
	}

	public void setDivulgadoResultado(boolean divulgadoResultado) {
		this.divulgadoResultado = divulgadoResultado;
	}
	
	public List<Participante> getParticipantes() {
		return this.participantes;
	}
	
	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}
	

	/**
	 *
	 * @param prerequisito
	 */
	public void setPrerequisito(Etapa prerequisito) throws IllegalArgumentException {
		if (prerequisito != null && prerequisito.getPeriodo().isAntes(this.getPeriodo())) {
			this.prerequisito = prerequisito;
		}
	}

	
	/**
	 *
	 * @param maisDocumentacao
	 */
	public void adicionaDocumentacaoExigida(List<String> maisDocumentacao) {
		if (maisDocumentacao != null && !maisDocumentacao.isEmpty()) {
			this.documentacaoExigida.addAll(maisDocumentacao);
		}
	}

	/**
	 * Adiciona um novo avaliador a etapa.
	 * 
	 * @param usuario
	 */
	public void adicionaAvaliador(UsuarioDarwin usuario) {
		if (this.getAvaliadores() != null) {
			ArrayList<UsuarioDarwin> usuarios = new ArrayList<>();
			List<UsuarioDarwin> sync = Collections.synchronizedList(usuarios);
			this.setAvaliadores(sync);
		}
		if (!this.getAvaliadores().contains(usuario)) {
			// Falta adicionar uma verificação para saber se o usuário está inscrito ou não
			// na seleção
			this.getAvaliadores().add(usuario);
			atualiza();
		} else {
			throw new IllegalArgumentException("Esse usuário já é avaliador desssa etapa!");
		}
	}

	/**
	 * Método resposável por remover um avaliador desta etapa.
	 * 
	 * @param usuario
	 */
	public void removeAvaliador(UsuarioDarwin usuario) {
		if (this.getAvaliadores() != null) {
			if (this.getAvaliadores().contains(usuario)) {
				this.getAvaliadores().remove(usuario);
				atualiza();
			} else {
				throw new IllegalArgumentException("Usuário não é avaliador dessa etapa!");
			}
		} else {
			throw new NullPointerException("Não existe avaliadores cadastrados para esta etapa!");
		}
	}

	public List<Participante> getAprovados() {
		List<Participante> aprovados = Collections.synchronizedList(new ArrayList<Participante>());
		if (getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.APROVACAO
				|| getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.DEFERIMENTO) {
			for (Object[] p : getResultado()) {
				if (((Avaliacao) p[1]).isAprovado()) {
					aprovados.add((Participante) p[0]);
				}
			}
		} else if (getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.NOTA) {
			for (Object[] participante : getResultado()) {
				if (((float) participante[1]) >= getNotaMinima()) {
					aprovados.add((Participante) participante[0]);
				}
			}
		}
		return aprovados;
	}

	public List<Object[]> getResultado() {
		List<Object[]> resultado = Collections.synchronizedList(new ArrayList<Object[]>());
		if (getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.APROVACAO
				|| getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.DEFERIMENTO) {
			for (Participante p : listParticipantes()) {
				int aprovacao = 0;
				int reprovacao = 0;
				for (Avaliacao a : getAvaliacoes()) {
					if (a.getParticipante().equals(p)) {
						Object[] aprovado = {p, a};
						resultado.add(aprovado);
					}		
				}

			}
		} else if (getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.NOTA) {
			for (Participante participante : listParticipantes()) {
				float media = 0;
				float soma = 0;
				int count = 0;
				for (Avaliacao avaliacao : getAvaliacoes()) {
					if (avaliacao.getParticipante().equals(participante)) {
						soma += avaliacao.getNota();
						count++;
					}
				}
				media = soma / count;
				Object[] aprovado = { participante, media };
				resultado.add(aprovado);
			}
		}
		return resultado;
	}

	public Object[] getSituacao(UsuarioDarwin usuario) {
		List<Object[]> resultado = getResultado();
		for (Object[] participante : resultado) {
			Object[] p1 = (Object[]) participante[0];
			if (((Participante) p1[0]).getCandidato().equals(usuario)) {
				return participante;
			}
		}
		return null;
	}

	public List<Participante> listParticipantes() {
		if (this.getPrerequisito() != null) {
			this.setParticipantes(this.getPrerequisito().getAprovados());
			return this.getParticipantes();
		} else {
			return this.getParticipantes();
		}
	}

	public boolean isParticipante(Participante participante) {
		return this.getParticipantes().contains(participante);
	}

	public boolean foiAprovado(Participante participante) {
		return this.getAprovados().contains(participante);
	}

	public boolean isParticipante(UsuarioDarwin participante) {
		for (Participante p : this.getParticipantes()) {
			if (p.getCandidato().equals(participante)) {
				return true;
			}
		}
		return false;
	}

	public Participante getParticipante(UsuarioDarwin usuario) {
		Participante participante = null;
		for (Participante p : this.getParticipantes()) {
			if (p.getCandidato().equals(usuario)) {
				participante = p;
				break;
			}
		}
		return participante;
	}

	public void anexaDocumentacao(Documentacao documentacao) throws IllegalAccessException {
		if (documentacao == null) {
			throw new NullPointerException("Documentacao não pode ser vazia!");
		} else {
			Participante par = documentacao.getCandidato();
			if (!foiAprovado(par)) {
				throw new IllegalAccessException("Você não é um participante desta Etapa");

			} else {
				this.getDocumentacoes().add(documentacao);
			}
		}
	}

	public void avalia(Avaliacao avaliacao) {
		if (this.getAvaliacoes() != null) {
			this.getAvaliacoes().add(avaliacao);
		} else {
			this.setAvaliacoes(Collections.synchronizedList(new ArrayList<Avaliacao>()));
			this.getAvaliacoes().add(avaliacao);
		}
	}
	
	

	/**
	 * Verifica se o usuário passado é um avaliador.
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean isAvaliador(UsuarioDarwin usuario) {
		return this.getAvaliadores().contains(usuario);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (int) (this.codEtapa ^ (this.codEtapa >>> 32));
		return hash;
	}

	@Override
	public boolean equals(final Object o) {
		return (this.getCodEtapa() == ((Etapa) o).getCodEtapa());
	}

	@Override
	public void atualiza() {
		// Chama o dao atualiza etapa
	}
}
