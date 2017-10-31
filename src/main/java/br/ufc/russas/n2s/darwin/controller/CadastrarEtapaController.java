/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Lavínia Matoso
 */
@Controller("cadastrarEtapaController")
@RequestMapping("/cadastrarEtapas")
public class CadastrarEtapaController {
    
    private EtapaServiceIfc etapaServiceIfc;
    
    public EtapaServiceIfc getEtapaServiceIfc(){
        return etapaServiceIfc;
    }
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc){
        this.etapaServiceIfc = etapaServiceIfc;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(){             
        return "cadastrar-etapas";
    }
    
    
}
