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
import br.ufc.russas.n2s.darwin.model.Email;
import br.ufc.russas.n2s.darwin.model.EnumCriterioDeAvaliacao;
import br.ufc.russas.n2s.darwin.model.EnumEstadoEtapa;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import util.Constantes;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
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
        List<UsuarioBeans> avaliadores = this.getUsuarioServiceIfc().listaUsuariosComPermissao(EnumPermissao.AVALIADOR);
        model.addAttribute("avaliadores", avaliadores);
        return "cadastrar-etapa";
    }

    @RequestMapping(value="/{codSelecao}", method = RequestMethod.POST)
    public String adiciona(@PathVariable long codSelecao, EtapaBeans etapa, BindingResult result, Model model, HttpServletRequest request) {
        try {
        	if (request.getParameter("prereq") != null && Long.parseLong(request.getParameter("prereq")) > 0) {
	            HttpSession session = request.getSession();
	            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
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
	            if (etapa.getPeriodo().getInicio().isBefore(LocalDate.now())) {
	            	throw new IllegalArgumentException("A data selecionada para o inicio da atividade já passou!");
	            }
	            ArrayList<UsuarioBeans> avaliadores = new ArrayList<>();
	            if (codAvaliadores != null) {
	                for (String cod : codAvaliadores) {
	                    UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
	                    if (u != null) {
	                        avaliadores.add(u);	                        
	                    }
	                }
	            }
	           
	            this.etapaServiceIfc.setUsuario(usuario);
	            long codPrerequisito = Long.parseLong(request.getParameter("prereq"));
            	EtapaBeans pre = etapaServiceIfc.getEtapa(codPrerequisito);
	            etapa.setPrerequisito(pre);
	            etapa.setAvaliadores(avaliadores);
	            if (documentosExigidos != null) {
	                ArrayList<String> docs = new ArrayList<>();
	                for(String documento : documentosExigidos){
	                    docs.add(documento);
	                }
	                etapa.setDocumentacaoExigida(docs);
	            }
	            
	            selecao.getEtapas().add(etapa);
	            this.selecaoServiceIfc.setUsuario(usuario);
	            this.selecaoServiceIfc.atualizaSelecao(selecao);
	            selecao = this.selecaoServiceIfc.getSelecao(codSelecao);
	           
	            for (EtapaBeans et : selecao.getEtapas()) {
	            	File dir = new File(Constantes.getDocumentsDir()+File.separator+"Selecao_"+selecao.getCodSelecao()+File.separator+"Etapa_"+et.getCodEtapa()+File.separator);
		            if (!dir.exists()) {
		            	dir.mkdir();
		            }
	            }
	            
	            session.setAttribute("mensagem", "Etapa cadastrada com sucesso!");
	            session.setAttribute("status", "success");
	            return ("redirect:/selecao/" + selecao.getCodSelecao());
        	} else {
            	model.addAttribute("mensagem", "Selecione uma etapa para pré-requisito!");
                model.addAttribute("status", "danger");
                return "cadastrar-etapa";
            }
        } catch (NullPointerException | NumberFormatException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "cadastrar-etapa";
        } catch (IllegalArgumentException | IllegalAccessException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "cadastrar-etapa";
        } catch (Exception e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "cadastrar-etapa";
        }
    }
    
    
    @RequestMapping(value="/inscricao/{codSelecao}", method = RequestMethod.POST)
    public String adicionaInscricao(@PathVariable long codSelecao, @RequestParam("prerequisito") long codPrerequisito, EtapaBeans etapa, BindingResult result, Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
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
            etapa.setDivulgaResultado(false);
            etapa.setEstado(EnumEstadoEtapa.ESPERA);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            etapa.setPeriodo(new PeriodoBeans(0, LocalDate.parse(request.getParameter("dataInicio"), formatter), LocalDate.parse(request.getParameter("dataTermino"), formatter)));
            ArrayList<UsuarioBeans> avaliadores = new ArrayList<>();
            if (codAvaliadores != null) {
                for (String cod : codAvaliadores) {
                    UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
                    if (u != null) {
                        avaliadores.add(u);
                    }
                }
            }
            if (codPrerequisito > 0) {
                etapa.setPrerequisito(this.getEtapaServiceIfc().getEtapa(codPrerequisito));
            }
            etapa.setAvaliadores(avaliadores);
            if (selecao.getInscricao() != null) {
                EtapaBeans e = (EtapaBeans) etapa;
                selecao.getEtapas().add(e);
            } else {
                selecao.setInscricao(etapa);
            }
           
            if (documentosExigidos != null) {
                ArrayList<String> docs = new ArrayList<>();
                for(String documento : documentosExigidos){
                    docs.add(documento);
                }
                etapa.setDocumentacaoExigida(docs);
            }
            
            this.selecaoServiceIfc.setUsuario(usuario);
            this.selecaoServiceIfc.atualizaSelecao(selecao);
            
            selecao =  selecaoServiceIfc.getSelecao(selecao.getCodSelecao());
            
            File dir = new File(Constantes.getDocumentsDir()+File.separator+"Selecao_"+selecao.getCodSelecao()+File.separator+"Etapa_"+selecao.getInscricao().getCodEtapa()+File.separator);
            if (!dir.exists()) {
            	dir.mkdir();
            }
            session.setAttribute("mensagem", "Etapa cadastrada com sucesso!");
            session.setAttribute("status", "success");
            return ("redirect:/selecao/" + selecao.getCodSelecao());
        } catch (NullPointerException | NumberFormatException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "cadastrar-etapa";
        } catch (IllegalArgumentException | IllegalAccessException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "cadastrar-etapa";
        }  catch (Exception e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "cadastrar-etapa";
        }
    }
    

}
