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
    
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model){
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        EtapaBeans e1 = new EtapaBeans();
        e1.setTitulo("Entrevista");
        e1.setDescricao("Teste de etapa");
        e1.setCodEtapa(11);
        e1.setPeriodo(new PeriodoBeans(codSelecao, LocalDateTime.MIN, LocalDateTime.MIN));
        
        EtapaBeans e2 = new EtapaBeans();
        e2.setTitulo("Prova");
        e2.setDescricao("Teste de etapa");
        e2.setCodEtapa(12);
        e2.setPeriodo(new PeriodoBeans(codSelecao, LocalDateTime.MIN, LocalDateTime.MIN));
        
        ArrayList<EtapaBeans> etapas = new ArrayList<>();
        etapas.add(e1);
        etapas.add(e2);
        selecao.setEtapas(etapas);
        model.addAttribute("selecao", selecao);
        return "selecao";
    }
}
