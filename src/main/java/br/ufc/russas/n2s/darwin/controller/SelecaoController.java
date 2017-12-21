package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
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

/**
 *
 * @author Wallison Carlos
 */
@Controller("selecaoController")
@RequestMapping(value = "/selecao")
public class SelecaoController {
    
    private SelecaoServiceIfc selecaoServiceIfc;
    private EtapaServiceIfc etapaServiceIfc;
    
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }


    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model, HttpServletRequest request){
        SelecaoBeans selecao = this.selecaoServiceIfc.getSelecao(codSelecao);
        HttpSession session = request.getSession();
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");

        if (!selecao.isDivulgada() && selecao.getResponsaveis().contains(usuario)) {
            model.addAttribute("selecao", selecao);        
            model.addAttribute("etapaAtual", this.selecaoServiceIfc.getEtapaAtual(selecao));
            model.addAttribute("isResponsavel", true);
            request.getSession().setAttribute("selecao", selecao);
            return "selecao";
        } else if(selecao.isDivulgada()) {
            model.addAttribute("selecao", selecao);        
            model.addAttribute("etapaAtual", this.selecaoServiceIfc.getEtapaAtual(selecao));
            request.getSession().setAttribute("selecao", selecao);
            model.addAttribute("isResponsavel", false);
            return "selecao";
        } else {
            return "elements/error404";
        }
    }
   
    /*@RequestMapping(value = "/editar-selecao/{codSelecao}", method = RequestMethod.POST)
    public String atualiza(@PathVariable String selecaoCodigo, SelecaoBeans selecao, Model model, BindingResult result, HttpServletRequest request){
    if(result.hasErrors()){
    return "selecao";
    }
    selecao = this.selecaoServiceIfc.atualizaSelecao(selecao);
    request.getSession().setAttribute("selecao", selecao);
    return "selecao";
    }*/
   
   
    
    @RequestMapping(value = "/editar-selecao/{codSelecao}", method = RequestMethod.GET)
    public String remove(@PathVariable String selecaoCodigo, SelecaoBeans selecao, Model model, BindingResult result, HttpServletRequest request){
        this.selecaoServiceIfc.removeSelecao(selecao);
        request.getSession().setAttribute("selecao", selecao);
        return "selecao";
    }
    
    @RequestMapping(value = "/{codSelecao}/resultado", method = RequestMethod.GET)
    public String resultado(@PathVariable long codSelecao, Model model) {
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        model.addAttribute("classificados", etapaServiceIfc.getAprovados(selecaoServiceIfc.getUltimaEtapa(selecao)));
        model.addAttribute("selecao", selecao);
        model.addAttribute("etapa", selecaoServiceIfc.getUltimaEtapa(selecao));
        return "resultado";
    }

    
}
