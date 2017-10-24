/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wallison Carlos
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController{ 

    private SelecaoServiceIfc selecaoServiceIfc;
    
    public SelecaoServiceIfc getSelecaoServiceIfc(){
        return selecaoServiceIfc;
    }
    
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model){
        ArrayList<SelecaoBeans> lista = new ArrayList<>();
        lista.add(new SelecaoBeans(0,"Bolsa de Iniciação a Docência","asdlkasdjaslkdashdaskldashdlsadhaslkdahdsajdas awdash  ahdlashaczx,cn sdahlsalskhjd aslkdasjk asdkwjdala alex alidsudlkajh3i felipe  askldasldhaslkdhd", null, new EtapaBeans(0,"Inscrição", new PeriodoBeans(0, LocalDateTime.MIN, LocalDateTime.MAX), "asdasdasasdas", null, null, null, null, null, true, null), null, 0, 0, null, null, null, null, null, null, null, null));
        model.addAttribute("listaSelecoes", /*this.getSelecaoServiceIfc().listaTodasSelecoes()*/ lista);        
        return "index";
    }
}
