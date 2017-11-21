/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.util.List;
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
        List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().listaTodasSelecoes();
        model.addAttribute("selecoes", selecoes);        
        return "index";
    }
    
    @RequestMapping(value="/{categoria}", method = RequestMethod.GET)
    public String getIndex(Model model, @PathVariable String categoria){
        Selecao selecao = new Selecao();
        selecao.setCategoria(categoria.replace("_", " "));
        List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().listaSelecoes(selecao);
        model.addAttribute("categoria", categoria);
        model.addAttribute("selecoes", selecoes);
        return "index";
    }
    
}
