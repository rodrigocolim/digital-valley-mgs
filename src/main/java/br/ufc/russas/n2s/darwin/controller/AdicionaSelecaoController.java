/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.dao.DAOImpl;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author N2S-PC03
 */
@Controller
public class AdicionaSelecaoController {

    @RequestMapping("adicionaSelecao")
    public String adiciona(/*@Valid*/ SelecaoBeans selecaoBeans, BindingResult result){
        if(result.hasErrors()){
            return (" ");
        }
        Selecao selecao = (Selecao) selecaoBeans.toBusiness();

        return (" ");
    }
    
    @RequestMapping("listaSelecoes")
    public String lista(Model model){

        return ("forward:listaSelecoes");
    }
    
    
}
