/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author N2S-PC03
 */

@Controller("etapaController")
@RequestMapping(value = "/etapa")
public class EtapaController {
    private EtapaServiceIfc etapaServiceIfc;
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }
    
    @RequestMapping(value = "/{codEtapa}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codEtapa, Model model){
        EtapaBeans etapa  = etapaServiceIfc.getEtapa(codEtapa);
        model.addAttribute("etapa", etapa);
        return "editar-etapa";
    }
    
    
    
}
