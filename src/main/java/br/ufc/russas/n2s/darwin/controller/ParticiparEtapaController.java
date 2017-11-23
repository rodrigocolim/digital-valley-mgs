/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.validation.Valid;
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
 * @author Lavínia Matoso
 */
@Controller("participarEtapaController")
@RequestMapping("/participarEtapa")
public class ParticiparEtapaController {

    private EtapaServiceIfc etapaServiceIfc;
    private SelecaoServiceIfc selecaoServiceIfc;

    public EtapaServiceIfc getEtapaServiceIfc() {
        return etapaServiceIfc;
    }

    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }

    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc") SelecaoServiceIfc selecaoServiceIfc) {
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    
    @RequestMapping(value="/{codEtapa}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codEtapa, Model model) {
        EtapaBeans etapaBeans = this.etapaServiceIfc.getEtapa(codEtapa);
        model.addAttribute("etapa", etapaBeans);
        return "participar-etapa";
    }

    @RequestMapping(value="/{codSelecao}", method = RequestMethod.POST)
    public String adiciona(@PathVariable long codSelecao, @Valid EtapaBeans etapa, BindingResult result, Model model) {
        SelecaoBeans selecaoBeans = this.selecaoServiceIfc.getSelecao(codSelecao);
        model.addAttribute("selecao", selecaoBeans);
        selecaoBeans.getEtapas().add((Etapa) etapa.toBusiness());
        this.selecaoServiceIfc.atualizaSelecao(selecaoBeans);
        /*if (!result.hasErrors()) {
        etapas.add(this.getEtapaServiceIfc().adicionaEtapa(etapa));
        model.addAttribute("etapas", etapas);
        }*/
        return "cadastrar-etapa";
    }
    

}
