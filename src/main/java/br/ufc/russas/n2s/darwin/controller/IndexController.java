/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import util.Constantes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class IndexController { 

    private SelecaoServiceIfc selecaoServiceIfc;
    
    public SelecaoServiceIfc getSelecaoServiceIfc(){
        return selecaoServiceIfc;
    }
    
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
        List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().listaTodasSelecoes();
        HashMap<Long, EtapaBeans> etapasAtuais = new  HashMap<>();
        for (SelecaoBeans s : selecoes) {
            etapasAtuais.put(s.getCodSelecao(), this.getSelecaoServiceIfc().getEtapaAtual(s));
        }
        model.addAttribute("categoria", "Início");
        model.addAttribute("estado", "início");
        model.addAttribute("selecoes", selecoes); 
        model.addAttribute("agora", LocalDate.now());
        model.addAttribute("etapasAtuais", etapasAtuais); 
        return "index";
    }
    
    @RequestMapping(value="/{categoria}", method = RequestMethod.GET)
    public String getIndex(Model model, @PathVariable String categoria) {
        Selecao selecao = new Selecao();
        selecao.setCategoria(categoria.replace("_", " "));
        List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().listaSelecoesIgnorandoNotas(selecao);
        HashMap<Long, EtapaBeans> etapasAtuais = new  HashMap<>();
        for (SelecaoBeans s : selecoes) {
            etapasAtuais.put(s.getCodSelecao(), this.getSelecaoServiceIfc().getEtapaAtual(s));
        }
        model.addAttribute("categoria", categoria);
       
        model.addAttribute("selecoes", selecoes);
        model.addAttribute("etapasAtuais", etapasAtuais); 
        return "index";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String getPorTitulo(Model model, HttpServletRequest request) {
      // Selecao selecao = new Selecao();
      String titulo = (String) request.getParameter("campoBuscaSelecao");
      this.getSelecaoServiceIfc().setUsuario((UsuarioBeans)request.getSession().getAttribute("usuarioDarwin"));
        List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().BuscaSelecoesPorNome(titulo) ;
        HashMap<Long, EtapaBeans> etapasAtuais = new  HashMap<>();
        for (SelecaoBeans s : selecoes) {
            etapasAtuais.put(s.getCodSelecao(), this.getSelecaoServiceIfc().getEtapaAtual(s));
        }
        model.addAttribute("categoria", "Resultados para busca por \""+titulo+"\"");
       
        model.addAttribute("selecoes", selecoes);
        model.addAttribute("etapasAtuais", etapasAtuais); 
        return "index";
    }
    
    
    @RequestMapping(value = "/estado/{estado}", method = RequestMethod.GET)
    public String getEstados(Model model, @PathVariable String estado){
        Selecao selecao = new Selecao();
        EnumEstadoSelecao e = null;
        System.out.println(estado);
        if (estado.equals("aberta")){
            e = EnumEstadoSelecao.ABERTA;
            model.addAttribute("categoria", "Seleções abertas");
        } else if( estado.equals("andamento")) {
            e = EnumEstadoSelecao.ANDAMENTO;
            model.addAttribute("categoria", "Seleções em andamento");
        } else if (estado.equals("finalizada")) {
            e = EnumEstadoSelecao.FINALIZADA;
            model.addAttribute("categoria", "Seleções finalizadas");
        } else {
        	 e = EnumEstadoSelecao.ESPERA;
             model.addAttribute("categoria", "Seleções em espera");
        }
        selecao.setEstado(e);
       // List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().listaSelecoes(selecao);
        List<SelecaoBeans> selecoes = this.getSelecaoServiceIfc().listaSelecoesIgnorandoNotas(selecao);
        HashMap<Long, EtapaBeans> etapasAtuais = new  HashMap<>();
        for (SelecaoBeans s : selecoes) {
            etapasAtuais.put(s.getCodSelecao(), this.getSelecaoServiceIfc().getEtapaAtual(s));
        }
        
        model.addAttribute("estado", e);
        model.addAttribute("selecoes", selecoes);
        model.addAttribute("etapasAtuais", etapasAtuais);
        return "index";
    }
    
    @RequestMapping(value="/minhas_Selecoes", method = RequestMethod.GET)

    public String getMinhasSelecoes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        List<SelecaoBeans> selecoes;
        if (usuario.getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
        	selecoes = this.getSelecaoServiceIfc().listaTodasSelecoesDoBanco();
        } else {
        	selecoes = this.getSelecaoServiceIfc().listaSelecoesAssociada(usuario);
        }
        HashMap<Long, EtapaBeans> etapasAtuais = new  HashMap<>();
        for (SelecaoBeans s : selecoes) {
            etapasAtuais.put(s.getCodSelecao(), this.getSelecaoServiceIfc().getEtapaAtual(s));
        }
        model.addAttribute("categoria", "Minhas seleções");
        model.addAttribute("selecoes", selecoes);
        model.addAttribute("agora", LocalDate.now());
        LocalDate.now().toEpochDay();
        model.addAttribute("etapasAtuais", etapasAtuais);
        return "index";
    }
    
    @RequestMapping(value = "/sair", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	HttpSession session = request.getSession();
        session.removeAttribute("usuario");
        response.sendRedirect(Constantes.getAppGuardiaoUrl()+"/logout");
    }
           
}
