/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.dao.DAO;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Wallison Carlos
 */
@Controller("SelecaoController")
@RequestMapping("/")
public class SelecaoController {
    ArrayList<SelecaoBeans> novasSelecoes = new ArrayList<>();

    public SelecaoController() {
        ArrayList<EtapaBeans> etapas = new ArrayList<>();
        etapas.add(new EtapaBeans(2, "Provas", null, "Querias estares in cahsah, mahs estois aquis con fomis y sonis", null, null, null, null, null, false, null));
        etapas.add(new EtapaBeans(3, "Entrevista", null, "Querias estares in cahsah, mahs estois aquis con fomis", null, null, null, null, null, false, null));
        EtapaBeans inscricao = new EtapaBeans(1, "Inscrição", new PeriodoBeans(0, LocalDateTime.now(), LocalDateTime.now()), "Querias estares in cahsah, mahs estois aquis con sedis y fomis y sonis", null, null, null, null, null, false, null);
        novasSelecoes.add(new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, inscricao, etapas, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));
    novasSelecoes.add(new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, inscricao, etapas, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));                
    novasSelecoes.add(new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, inscricao, etapas, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));        
    }
    

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model){
        //DAO<SelecaoBeans> dao = DAO.getInstancia();
        model.addAttribute("novasSelecoes", novasSelecoes);
        
        return "index";
    }
    
    @RequestMapping(value = "/selecao")
    public String verSelecao(@RequestParam(value = "codSelecao", required = true) int codSelecao, Model model){
        model.addAttribute("selecao",novasSelecoes.get(codSelecao - 1));
        return "selecao";
    }
    
}
