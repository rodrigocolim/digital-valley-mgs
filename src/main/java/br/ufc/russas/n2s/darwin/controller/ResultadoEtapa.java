package br.ufc.russas.n2s.darwin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.darwin.beans.AvaliacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;

@Controller("resultadoEtapaController")
@RequestMapping("/resultadoEtapa")
public class ResultadoEtapa {
	private EtapaServiceIfc etapaServiceIfc;
    private UsuarioServiceIfc usuarioServiceIfc;
    
    
    public EtapaServiceIfc getEtapaServiceIfc() {
        return etapaServiceIfc;
    }
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }
    
    public UsuarioServiceIfc getUsuarioServiceIfc() {
        return usuarioServiceIfc;
    }
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
    }
	@RequestMapping(value = "/{codEtapa}", method = RequestMethod.GET)
    public String resultadoDaEtapa(@PathVariable long codEtapa, Model model){
        EtapaBeans etapa  = etapaServiceIfc.getEtapa(codEtapa);
        List<ParticipanteBeans> participantesEtapa = new ArrayList<ParticipanteBeans>();
        for(AvaliacaoBeans avaliacao:etapa.getAvaliacoes()) {
        	participantesEtapa.add(avaliacao.getParticipante());
        }
        model.addAttribute("participantesEtapa", participantesEtapa);
        model.addAttribute("etapa", etapa);
        return "/resultadoEtapa";
    }
}
