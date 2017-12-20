/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.InscricaoBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumCriterioDeAvaliacao;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@Controller("editarEtapaController")
@RequestMapping("/editarEtapa")
public class EditarEtapaController {

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
           
    
    @RequestMapping(value="/{codSelecao}/{codEtapa}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, @PathVariable long codEtapa, Model model) {
        EtapaBeans etapaBeans = this.etapaServiceIfc.getEtapa(codEtapa);
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        if (etapaBeans.getCodEtapa() == selecao.getInscricao().getCodEtapa()) {
            model.addAttribute("tipo", "inscricao"); 
        } else {
            model.addAttribute("tipo", "etapa");
        }
        model.addAttribute("selecao", selecao);
        model.addAttribute("etapa", etapaBeans);
        return "editar-etapa";
    }

    @RequestMapping(value="/{codSelecao}/{codEtapa}", method = RequestMethod.POST)
    public String atualiza(@PathVariable long codSelecao, @PathVariable long codEtapa, EtapaBeans etapa, BindingResult result, Model model, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            SelecaoBeans selecao = this.selecaoServiceIfc.getSelecao(codSelecao);
            EtapaBeans etapaBeans= this.etapaServiceIfc.getEtapa(codEtapa);
            String[] codAvaliadores = request.getParameterValues("codAvaliadores");
            String[] documentosExigidos = request.getParameterValues("documentosExigidos");
            int criterio = Integer.parseInt(request.getParameter("criterioDeAvaliacao"));
            if (criterio == 1) {
                etapaBeans.setCriterioDeAvaliacao(EnumCriterioDeAvaliacao.NOTA);
            } else if(criterio == 2) {
                etapaBeans.setCriterioDeAvaliacao(EnumCriterioDeAvaliacao.APROVACAO);
            } else if(criterio == 3) {
                etapaBeans.setCriterioDeAvaliacao(EnumCriterioDeAvaliacao.DEFERIMENTO);
            }
            etapaBeans.setTitulo(etapa.getTitulo());
            etapaBeans.setDescricao(etapa.getDescricao());
            etapaBeans.setPrerequisito(etapa.getPrerequisito());
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            etapaBeans.setPeriodo(new PeriodoBeans(0, LocalDate.parse(request.getParameter("dataInicio"), formatter), LocalDate.parse(request.getParameter("dataTermino"), formatter)));
            ArrayList<UsuarioBeans> avaliadores = new ArrayList<>();
            if (codAvaliadores != null) {
                for (String cod : codAvaliadores) {
                    UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
                    if (u != null) {
                        avaliadores.add(u);
                    }
                }
            }
            if (documentosExigidos != null) {
                ArrayList<String> docs = new ArrayList<>();
                for(String documento : documentosExigidos){
                    docs.add(documento);
                }
                etapaBeans.setDocumentacaoExigida(docs);
            }
            etapaBeans.setAvaliadores(avaliadores);
            this.getSelecaoServiceIfc().setUsuario(usuario);
            
            selecao.getEtapas().remove(etapaBeans);
            selecao.getEtapas().add(etapaBeans);
            
            selecao = this.getSelecaoServiceIfc().atualizaSelecao(selecao);
            //this.etapaServiceIfc.atualizaEtapa(selecao, etapaBeans);
            model.addAttribute("selecao", selecao);
            return "redirect:/editarEtapa/" + codSelecao+"/"+codEtapa;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
             return "redirect:/editarEtapa/" + codSelecao+"/"+codEtapa;
        }
         
    }
    
    @RequestMapping(value="/{codSelecao}/inscricao/{codInscricao}", method = RequestMethod.POST)
    public String atualizaInscricao(@PathVariable long codSelecao, @PathVariable long codInscricao, InscricaoBeans inscricao, BindingResult result, Model model, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            SelecaoBeans selecao = this.getSelecaoServiceIfc().getSelecao(codSelecao);
            InscricaoBeans inscricaoBeans = this.getEtapaServiceIfc().getInscricao(codInscricao);
            String[] codAvaliadores = request.getParameterValues("codAvaliadores");
            String[] documentosExigidos = request.getParameterValues("documentosExigidos");
            inscricaoBeans.setTitulo(inscricao.getTitulo());
            inscricaoBeans.setDescricao(inscricao.getDescricao());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            inscricaoBeans.setPeriodo(new PeriodoBeans(0, LocalDate.parse(request.getParameter("dataInicio"), formatter), LocalDate.parse(request.getParameter("dataTermino"), formatter)));
            ArrayList<UsuarioBeans> avaliadores = new ArrayList<>();
            if (codAvaliadores != null) {
                for (String cod : codAvaliadores) {
                    UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
                    if (u != null) {
                        avaliadores.add(u);
                    }
                }
            }
            if (documentosExigidos != null) {
                ArrayList<String> docs = new ArrayList<>();
                for (String documento : documentosExigidos) {
                    docs.add(documento);
                }
                inscricaoBeans.setDocumentacaoExigida(docs);
            }
            inscricaoBeans.setAvaliadores(avaliadores);
            this.getSelecaoServiceIfc().setUsuario(usuario);
            
            selecao.setInscricao(inscricaoBeans);
            selecao = this.getSelecaoServiceIfc().atualizaSelecao(selecao);
            session.setAttribute("selecao", selecao);
            session.setAttribute("mensagem", "Etapa atualizada com sucesso!");
            session.setAttribute("status", "success");
            return "redirect:/editarEtapa/" + selecao.getCodSelecao()+"/"+codInscricao;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "redirect:/editarEtapa/" + codSelecao+"/"+codInscricao;
        }
         
    }
    

}
