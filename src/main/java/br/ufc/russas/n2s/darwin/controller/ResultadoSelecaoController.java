package br.ufc.russas.n2s.darwin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.ResultadoSelecaoForm;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;

@Controller("resultadoSelecaoController")
@RequestMapping("/resultadoSelecao")
public class ResultadoSelecaoController {
	private SelecaoServiceIfc selecaoServiceIfc;
	private EtapaServiceIfc etapaServiceIfc;
    private UsuarioServiceIfc usuarioServiceIfc;
    
    
    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }
    @Autowired(required = true)
    public void setSelecaoSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc) {
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
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
    
	@RequestMapping(value = "/{codSelecao}", method = RequestMethod.GET)
    public String getResultadoDaselecao(@PathVariable long codSelecao, Model model){
        SelecaoBeans selecao  = this.getSelecaoServiceIfc().getSelecao(codSelecao);
        ResultadoSelecaoForm resultadoForm = new ResultadoSelecaoForm();
        resultadoForm.setEtapas( this.getSelecaoServiceIfc().getEtapasNota(selecao));
        model.addAttribute("resultadoSelecaoForm", resultadoForm);
        return "calculo-resultado-selecao";
    }
	
	@RequestMapping(value = "/salvar/{codSelecao}", method = RequestMethod.POST)
    public String calculaResultadoDaselecao(@PathVariable long codSelecao, Model model, HttpServletRequest request, @ModelAttribute("resultadoSelecaoForm") ResultadoSelecaoForm resultadoForm) {
        SelecaoBeans selecao  = selecaoServiceIfc.getSelecao(codSelecao);
        UsuarioBeans usuario = (UsuarioBeans) request.getSession().getAttribute("usuarioDarwin");
        this.getEtapaServiceIfc().setUsuario(usuario);
        this.getSelecaoServiceIfc().setUsuario(usuario);

        List<EtapaBeans> etapas = new ArrayList<>();
        etapas = resultadoForm.getEtapas();
        EtapaBeans etapaAux;
        if (etapas != null && etapas.size() > 0) {
        	for (EtapaBeans eb : etapas) {
        		etapaAux = this.getEtapaServiceIfc().getEtapa(eb.getCodEtapa());
        		etapaAux.setPesoNota(eb.getPesoNota());
        		etapaAux.setPosicaoCriterioDesempate(eb.getPosicaoCriterioDesempate());
        		etapaAux.setCriterioDesempate(eb.isCriterioDesempate());     		
        		this.getEtapaServiceIfc().atualizaEtapa(etapaAux);      		
        	}
        }
                
        selecao = this.getSelecaoServiceIfc().getSelecao(selecao.getCodSelecao());
        resultadoForm.setEtapas(this.getSelecaoServiceIfc().getEtapasNota(selecao));
        model.addAttribute("resultadoSelecaoForm", resultadoForm);      
        model.addAttribute("status", "success");
        model.addAttribute("mensagem", "Cálculo do resultado definido com sucesso!");
        return "calculo-resultado-selecao";
    }
}