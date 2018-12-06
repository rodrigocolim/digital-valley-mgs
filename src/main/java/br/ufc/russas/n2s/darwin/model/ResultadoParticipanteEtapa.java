package br.ufc.russas.n2s.darwin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoParticipanteEtapa {
	
	private Participante participante;
	private boolean avaliada;
	private boolean aprovado;
	private List<Avaliacao> avaliacoes = Collections.synchronizedList(new ArrayList<>());
	private float media;
	
	public ResultadoParticipanteEtapa() {
		
	}
	
	
	
	public ResultadoParticipanteEtapa(Participante participante, boolean avaliada, boolean aprovado,
			List<Avaliacao> avaliacoes) {
		super();
		this.participante = participante;
		this.avaliada = avaliada;
		this.aprovado = aprovado;
		this.avaliacoes = avaliacoes;
	}



	public ResultadoParticipanteEtapa(Participante participante, boolean avaliada, boolean aprovado,
			List<Avaliacao> avaliacoes, float media) {
		super();
		this.participante = participante;
		this.avaliada = avaliada;
		this.aprovado = aprovado;
		this.avaliacoes = avaliacoes;
		this.media = media;
	}
	
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public boolean isAvaliada() {
		return avaliada;
	}
	public void setAvaliada(boolean avaliada) {
		this.avaliada = avaliada;
	}
	public boolean isAprovado() {
		return aprovado;
	}
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}

	
	
}
