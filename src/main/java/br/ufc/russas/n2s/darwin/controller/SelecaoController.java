package br.ufc.russas.n2s.darwin.controller;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.darwin.beans.AvaliacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.dao.AvaliacaoDAOIfc;
import br.ufc.russas.n2s.darwin.model.Avaliacao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.service.AvaliacaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;

/**
 *
 * @author Wallison Carlos
 */
@Controller("selecaoController")
@RequestMapping(value = "/selecao")
public class SelecaoController {
    
    private SelecaoServiceIfc selecaoServiceIfc;
    private EtapaServiceIfc etapaServiceIfc;
    private AvaliacaoServiceIfc avaliacaoServiceIfc;
    private AvaliacaoDAOIfc avaliacaoDAOIfc;
    
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
    this.etapaServiceIfc = etapaServiceIfc;
    }
    
    @Autowired(required = true)
    public void setAvaliacaoServiceIfc(@Qualifier("avaliacaoServiceIfc")AvaliacaoServiceIfc avaliacaoServiceIfc) {
    this.avaliacaoServiceIfc = avaliacaoServiceIfc;
    }
    
    @Autowired(required = true)
    public void setAvaliacaoDAOIfc(@Qualifier("avaliacaoDAOIfc")AvaliacaoDAOIfc avaliacaoDAOIfc) {
    this.avaliacaoDAOIfc = avaliacaoDAOIfc;
    }

    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model, HttpServletRequest request){
        SelecaoBeans selecao = this.selecaoServiceIfc.getSelecao(codSelecao);
        HttpSession session = request.getSession();
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        boolean isParticipante = false;
        if (selecao.getInscricao() != null && selecao.getInscricao().getParticipantes() != null) {
        	for (ParticipanteBeans participante : selecao.getInscricao().getParticipantes()) {
            	System.out.println(participante.getCandidato().getNome());
    			if (participante.getCandidato().getCodUsuario() == usuario.getCodUsuario()) {
    				isParticipante = true;
    			}
    		}
        }
        selecao.setEtapas(this.etapaServiceIfc.ordenaEtapasPorData(selecao.getEtapas()));
        model.addAttribute("isParticipante", isParticipante);
        if (!selecao.isDivulgada() && selecao.getResponsaveis().contains(usuario)) {
            model.addAttribute("selecao", selecao);        
            model.addAttribute("etapaAtual", this.selecaoServiceIfc.getEtapaAtual(selecao));
            model.addAttribute("isResponsavel", true);
            request.getSession().setAttribute("selecao", selecao);
            return "selecao";
        } else if(selecao.isDivulgada()) {
            HashMap<EtapaBeans, Object[]> situacao = new HashMap<>();
            int i = 0;
            Etapa etapabusiness = (Etapa) etapaServiceIfc.getEtapa(selecao.getInscricao().getCodEtapa()).toBusiness();
            selecao.getInscricao().getAvaliacoes().removeAll(selecao.getInscricao().getAvaliacoes());
            for(Avaliacao avaliacao:etapabusiness.getAvaliacoes()) {
            	AvaliacaoBeans avli = new AvaliacaoBeans();
        		avli.toBeans(avaliacao);
        		selecao.getInscricao().getAvaliacoes().add(avli);
            }
            situacao.put(selecao.getInscricao(), this.etapaServiceIfc.getSituacao(selecao.getInscricao(), usuario));
            for (i = 0; i<selecao.getEtapas().size(); i++) {
            	EtapaBeans etapa = (EtapaBeans) selecao.getEtapas().get(i);
            	etapabusiness = (Etapa) etapaServiceIfc.getEtapa(etapa.getCodEtapa()).toBusiness();
            	etapa.getAvaliacoes().removeAll(etapa.getAvaliacoes());
            	selecao.getEtapas().remove(i);
            	selecao.getEtapas().add(i, etapa);
                situacao.put(etapa, this.etapaServiceIfc.getSituacao(etapa, usuario));
                i++;
            }
            model.addAttribute("situacao", situacao);
            model.addAttribute("selecao", selecao);        
            model.addAttribute("etapaAtual", this.selecaoServiceIfc.getEtapaAtual(selecao));
            request.getSession().setAttribute("selecao", selecao);
            model.addAttribute("isResponsavel", false);
            return "selecao";
        } else {
            return "elements/error404";
        }
    }
    
    @RequestMapping(value = "/editar-selecao/{codSelecao}", method = RequestMethod.GET)
    public String remove(@PathVariable String selecaoCodigo, SelecaoBeans selecao, Model model, BindingResult result, HttpServletRequest request){
        this.selecaoServiceIfc.removeSelecao(selecao);
        request.getSession().setAttribute("selecao", selecao);
        return "selecao";
    }
  
    @RequestMapping(value = "/{codSelecao}/resultado", method = RequestMethod.GET)
    public String resultado(@PathVariable long codSelecao, Model model) {
    	SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
    	try {
	        model.addAttribute("etapasComNota", selecaoServiceIfc.getEtapasNota(selecao));
	        model.addAttribute("selecao", selecao);
	        model.addAttribute("etapa", selecaoServiceIfc.getUltimaEtapa(selecao));
	        return "resultado";
    	} catch (Exception e) {
 	        model.addAttribute("quantidadeEtapasPorNota", selecaoServiceIfc.getEtapasNota(selecao).size());
 	        model.addAttribute("selecao", selecao);
 	        model.addAttribute("etapa", selecaoServiceIfc.getUltimaEtapa(selecao));
 	        return "resultado";
     	}
    }

    
}
