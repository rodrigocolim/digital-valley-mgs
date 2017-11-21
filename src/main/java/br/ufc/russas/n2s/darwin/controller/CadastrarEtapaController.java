/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumCriterioDeAvaliacao;
import br.ufc.russas.n2s.darwin.model.EnumEstadoEtapa;
import br.ufc.russas.n2s.darwin.model.Usuario;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wallison Carlos
 */
@Controller("cadastrarEtapaController")
@RequestMapping("/cadastrarEtapa")
public class CadastrarEtapaController {

    private EtapaServiceIfc etapaServiceIfc;
    private SelecaoServiceIfc selecaoServiceIfc;
    private UsuarioServiceIfc usuarioServiceIfc;
    
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

    public UsuarioServiceIfc getUsuarioServiceIfc() {
        return usuarioServiceIfc;
    }
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
    }
           
    
    @RequestMapping(value="/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model) {
        SelecaoBeans selecaoBeans = this.selecaoServiceIfc.getSelecao(codSelecao);
        model.addAttribute("selecao", selecaoBeans);
        ArrayList<Usuario> avaliadores = new ArrayList<>();
        Usuario a = new Usuario();
        a.setCodUsuario(12312312);
        Usuario b = new Usuario();
        b.setCodUsuario(12346345);
        Usuario c = new Usuario();
        c.setCodUsuario(743224);
        Usuario d = new Usuario();
        d.setCodUsuario(86576353);
        avaliadores.add(a);
        avaliadores.add(b);
        avaliadores.add(c);
        avaliadores.add(d);
        model.addAttribute("avaliadores", avaliadores);
        return "cadastrar-etapa";
    }

    @RequestMapping(value="/{codSelecao}", method = RequestMethod.POST)
    public String adiciona(@PathVariable long codSelecao, EtapaBeans etapa, BindingResult result, Model model, HttpServletRequest request) {
        SelecaoBeans selecao = this.selecaoServiceIfc.getSelecao(codSelecao);
        model.addAttribute("selecao", selecao);
        String[] codAvaliadores = request.getParameterValues("codAvaliadores");
        String[] documentosExigidos = request.getParameterValues("documentosExigidos");
        int criterio = Integer.parseInt(request.getParameter("criterioDeAvaliacao"));
        if (criterio == 1) {
            etapa.setCriterioDeAvaliacao(EnumCriterioDeAvaliacao.NOTA);
        } else if(criterio == 2) {
            etapa.setCriterioDeAvaliacao(EnumCriterioDeAvaliacao.APROVACAO);
        } else if(criterio == 3) {
            etapa.setCriterioDeAvaliacao(EnumCriterioDeAvaliacao.DEFERIMENTO);
        }
        etapa.setEstado(EnumEstadoEtapa.ESPERA);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        etapa.setPeriodo(new PeriodoBeans(0, LocalDate.parse(request.getParameter("dataInicio"), formatter), LocalDate.parse(request.getParameter("dataTermino"), formatter)));
        ArrayList<UsuarioBeans> avaliadores = new ArrayList<>();
        if(codAvaliadores != null){
            for(String cod : codAvaliadores){
                UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
                if(u != null){
                    avaliadores.add(u);
                }
            }
        }
        ArrayList<String> docs = new ArrayList<>();
        for(String documento : documentosExigidos){
            docs.add(documento);
        }
        etapa.setDocumentacaoExigida(docs);
        etapa.setAvaliadores(avaliadores);
        etapa = getEtapaServiceIfc().adicionaEtapa(etapa);
        selecao.getEtapas().add(etapa);
        selecao.getResponsaveis().add(new UsuarioBeans());
        this.selecaoServiceIfc.atualizaSelecao(selecao);
        /*if (!result.hasErrors()) {
        etapas.add(this.getEtapaServiceIfc().adicionaEtapa(etapa));
        model.addAttribute("etapas", etapas);
        }*/
        return "cadastrar-etapa";
    }
    

}
