/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wallison Carlos
 */
@Controller("cadastrarSelecaoController")
@RequestMapping("/cadastrarSelecao")
public class CadastrarSelecaoController { 

    private SelecaoServiceIfc selecaoServiceIfc;

    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }

    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {             
        return "cadastrar-selecao";
    }

    @RequestMapping(method = RequestMethod.POST)
<<<<<<< HEAD
    public String adiciona(@Valid SelecaoBeans selecao, BindingResult result) {           

=======
    public String adiciona(@Valid SelecaoBeans selecao, BindingResult result){           
>>>>>>> fb04a6d5c77d9e885e2e22e2122c3bb7e1c0e25b
        selecao.getResponsaveis().add(new UsuarioBeans());
        selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);
        if (result.hasErrors()) {
            return "cadastrar-selecao";
        }
        selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);
        return "forward:/selecao?codSelecao=" + selecao.getCodSelecao();
    }
}
