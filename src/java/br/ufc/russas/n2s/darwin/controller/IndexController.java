/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.dao.DAO;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.util.ArrayList;
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
public class IndexController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model){
        //DAO<SelecaoBeans> dao = DAO.getInstancia();
        ArrayList<SelecaoBeans> novasSelecoes = new ArrayList<>();
        novasSelecoes.add(new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, null, null, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));

        model.addAttribute("novasSelecoes", novasSelecoes);
        
        return "index";
    }
}
