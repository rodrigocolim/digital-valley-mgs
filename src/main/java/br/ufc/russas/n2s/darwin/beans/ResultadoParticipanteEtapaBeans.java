package br.ufc.russas.n2s.darwin.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.ResultadoParticipanteEtapa;
import br.ufc.russas.n2s.darwin.model.ResultadoParticipanteSelecao;

public class ResultadoParticipanteEtapaBeans implements Beans{
	
	private ParticipanteBeans participante;
	private boolean avaliada;
	private boolean aprovado;
	private List<AvaliacaoBeans> avaliacoes = Collections.synchronizedList(new ArrayList<>());
	private float media;
	
	public ResultadoParticipanteEtapaBeans() {
		
	}
	
	
	
	public ResultadoParticipanteEtapaBeans(ParticipanteBeans participante, boolean avaliada, boolean aprovado,
			List<AvaliacaoBeans> avaliacoes) {
		super();
		this.participante = participante;
		this.avaliada = avaliada;
		this.aprovado = aprovado;
		this.avaliacoes = avaliacoes;
	}



	public ResultadoParticipanteEtapaBeans(ParticipanteBeans participante, boolean avaliada, boolean aprovado,
			List<AvaliacaoBeans> avaliacoes, float media) {
		super();
		this.participante = participante;
		this.avaliada = avaliada;
		this.aprovado = aprovado;
		this.avaliacoes = avaliacoes;
		this.media = media;
	}
	
	public ParticipanteBeans getParticipante() {
		return participante;
	}
	public void setParticipante(ParticipanteBeans participante) {
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
	public List<AvaliacaoBeans> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<AvaliacaoBeans> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}

	@Override
	public Object toBusiness() {
		
		ResultadoParticipanteEtapa resultado = new ResultadoParticipanteEtapa();
		
		if (this.getParticipante() != null) {
			resultado.setParticipante((Participante)this.getParticipante().toBusiness());
		}
		
		resultado.setAprovado(this.isAprovado());
		
		for (AvaliacaoBeans avaliacao : this.getAvaliacoes()) {
			resultado.getAvaliacoes().add((Avaliacao) avaliacao.toBusiness());	
		}
		
		resultado.setMedia(this.getMedia());
		
		resultado.setAvaliada(this.isAvaliada());
		
		return resultado;
	}

	@Override
	public Beans toBeans(Object object) {
		if (object != null) {
			if (object instanceof ResultadoParticipanteSelecao) {

				ResultadoParticipanteEtapa resultado = (ResultadoParticipanteEtapa) object;
				
				if (resultado.getParticipante() != null) {
					this.setParticipante((ParticipanteBeans) new ParticipanteBeans().toBeans(resultado.getParticipante()));
				}
				
				this.setAprovado(resultado.isAprovado());
				
				for (Avaliacao avaliacao : resultado.getAvaliacoes()) {
					this.getAvaliacoes().add((AvaliacaoBeans) new AvaliacaoBeans().toBeans(avaliacao));	
				}
				
				this.setMedia(resultado.getMedia());
				
				this.setAvaliada(resultado.isAvaliada());
				
				return this;
			} else {
				throw new IllegalArgumentException("Era esperado um ResultadoParticipanteSelecao!");
			}			
		} else {
			throw new NullPointerException("Resultado Participante Seleção não pode ser vazio!");			
		}
	}
	
}
