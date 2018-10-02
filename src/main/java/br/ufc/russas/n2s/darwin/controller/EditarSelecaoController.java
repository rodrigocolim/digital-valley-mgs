/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Email;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import util.Constantes;

/**
 *
 * @author Gilberto
 */
@Controller("editarSelecaoController")
@RequestMapping("/editarSelecao")
public class EditarSelecaoController { 

    private SelecaoServiceIfc selecaoServiceIfc;
    private UsuarioServiceIfc usuarioServiceIfc;
    
    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }

    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    public UsuarioServiceIfc getUsuarioServiceIfc() {
        return usuarioServiceIfc;
    }
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
    }

    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model, HttpServletRequest request){
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        List<UsuarioBeans> usuarios = this.getUsuarioServiceIfc().listaUsuariosComPermissao(EnumPermissao.RESPONSAVEL);
        List<UsuarioBeans> responsaveis = selecao.getResponsaveis();
        for (UsuarioBeans usuarioBeans : responsaveis) {
			System.out.println(usuarioBeans.getNome());
		}
        model.addAttribute("selecao", selecao);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("responsaveis", responsaveis);
 
        return "editar-selecao";
    }
    
    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.POST)
    public String atualiza(@PathVariable long codSelecao, @ModelAttribute("selecao") @Valid SelecaoBeans selecao, BindingResult result, @RequestParam("file") String file, @RequestParam("categoria") String categoria, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException, EmailException {
        SelecaoBeans selecaoBeans = this.getSelecaoServiceIfc().getSelecao(codSelecao);
        HttpSession session = request.getSession();
        
        try{
            selecaoBeans.setTitulo(selecao.getTitulo());
            selecaoBeans.setDescricao(selecao.getDescricao());
            selecaoBeans.setDescricaoPreRequisitos(selecao.getDescricaoPreRequisitos());
            selecaoBeans.setAreaDeConcentracao(selecao.getAreaDeConcentracao());
            selecaoBeans.setCategoria(categoria);
            selecaoBeans.setVagasRemuneradas(selecao.getVagasRemuneradas());
            selecaoBeans.setVagasVoluntarias(selecao.getVagasVoluntarias());
            
            String[] nomeAnexos = request.getParameterValues("listaNomeAnexo");
            String[] nomeAditivos = request.getParameterValues("listaNomeAditivo");
            String[] linkAnexos = request.getParameterValues("listaLinkAnexo");
            String[] linkAditivos = request.getParameterValues("listaLinkAditivo");
            
            File dir = new File(Constantes.getDocumentsDir()+File.separator+"Seleção_"+selecao.getTitulo()+File.separator);
            dir.mkdir();
            
            if (!file.isEmpty()) {
                ArquivoBeans edital = new ArquivoBeans();
                edital.setTitulo("Edital para ".concat(selecao.getTitulo()));
                edital.setData(LocalDateTime.now());
                InputStream inputStream = new URL(file).openStream();
                edital.setArquivo(FileManipulation.getFileStream(inputStream, ".pdf"));
                selecaoBeans.setEdital(edital);
            }
            
            if (nomeAnexos != null && linkAnexos != null) {
            	ArrayList<ArquivoBeans> anexos = new ArrayList<>();
                for (int i=0; i < nomeAnexos.length; i++) {
                    ArquivoBeans anexo = new ArquivoBeans();
                    anexo.setTitulo(nomeAnexos[i]);
                    File temp = File.createTempFile(Constantes.getDocumentsDir()+File.separator+nomeAnexos[i], ".pdf", dir);
                    InputStream input = FileManipulation.getStreamFromURL(linkAnexos[i]);
                    OutputStream output = new FileOutputStream(temp);
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    while ((read = input.read(bytes)) != -1) {
                        output.write(bytes, 0, read);
                    }
                    anexo.setArquivo(temp);
                    anexo.setData(LocalDateTime.now());
                    anexos.add(anexo);
                }
                selecaoBeans.setAnexos(anexos);
            	
            }
            
            if (nomeAditivos != null && linkAditivos != null) {
            	ArrayList<ArquivoBeans> aditivos = new ArrayList<>();
                for (int i=0; i < nomeAditivos.length; i++) {
                    ArquivoBeans aditivo = new ArquivoBeans();
                    aditivo.setTitulo(nomeAditivos[i]);
                    File temp = File.createTempFile(Constantes.getDocumentsDir()+File.separator+nomeAditivos[i], ".pdf", dir);
                    InputStream input = FileManipulation.getStreamFromURL(linkAditivos[i]);
                    OutputStream output = new FileOutputStream(temp);
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    while ((read = input.read(bytes)) != -1) {
                        output.write(bytes, 0, read);
                    }
                    aditivo.setArquivo(temp);
                    aditivo.setData(LocalDateTime.now());
                    aditivos.add(aditivo);
                }
                selecaoBeans.setAditivos(aditivos);
            }

            String[] codResponsaveis = request.getParameterValues("codResponsaveis");
            ArrayList<UsuarioBeans> responsaveis = new ArrayList<>();
            if (codResponsaveis != null) {
            	
                for (String cod : codResponsaveis) {
                	System.out.println(cod);
                	if (cod.contains("-")) {
                		cod = cod.substring(0,cod.indexOf("-"));
                	}
                    UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
                    System.out.println("usuario retornado: "+u.getNome());
                    if (u != null) {
                    	responsaveis.add(u);
                    }
                }
            }
            selecaoBeans.setResponsaveis(responsaveis);
            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            this.getSelecaoServiceIfc().setUsuario(usuario);
            selecaoBeans = this.getSelecaoServiceIfc().atualizaSelecao(selecaoBeans);
            EtapaBeans etapaAtual = this.getSelecaoServiceIfc().getEtapaAtual(selecaoBeans);
            session.setAttribute("selecao", selecaoBeans);
            session.setAttribute("mensagem", "Seleção atualizada com sucesso!");
            session.setAttribute("status", "success");
            return "redirect:/selecao/" + selecaoBeans.getCodSelecao();
        }catch(IOException | IllegalAccessException e){
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("editar-selecao");
        }
    }
    
    @RequestMapping(value = "/divulga/{codSelecao}", method = RequestMethod.GET)
    public String divulga(@PathVariable long codSelecao, Model model, HttpServletRequest request) {
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        HttpSession session = request.getSession();
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        try {
        	if (selecao.getInscricao().getPeriodo().getTermino().isBefore(LocalDate.now())) {
        		session.setAttribute("mensagem", "Esta Seleção não pode ser divulgada! Verifique o periodo de inscrição.");
                session.setAttribute("status", "warning");
        		return "redirect:/selecao/" + selecao.getCodSelecao();
        	} else {
	            selecaoServiceIfc.setUsuario(usuario);
	            selecao.setDivulgada(true);
	            selecao = selecaoServiceIfc.atualizaSelecao(selecao);
	            Email email = new Email();
	            email.sendHtmlEmail(usuarioServiceIfc.listaTodosUsuarios(), "Nova seleção divulgada!", "Seleção "+selecao.getTitulo(), selecao.getDescricao());
	            Set<UsuarioBeans> avaliadoresSet = Collections.synchronizedSet(new HashSet<UsuarioBeans>());
	            for (int i =0;i < selecao.getEtapas().size();i++) {
	            	EtapaBeans e = selecao.getEtapas().get(i);
	            	avaliadoresSet.addAll(e.getAvaliadores());
	            }
	            email.sendHtmlEmail(avaliadoresSet, "Avaliador de Etapa!", "Avaliador de Etapa", "Você é avaliador de etapas da <b>Selção"+selecao.getTitulo()+"</b>!");
	            session.setAttribute("selecao", selecao);
	            session.setAttribute("mensagem", "Seleção divulgada com sucesso!");
                session.setAttribute("status", "success");
	            return "redirect:/selecao/" + selecao.getCodSelecao();
        	}
        } catch(IllegalAccessException e) {
            e.printStackTrace();
            return "redirect:/selecao/" + selecao.getCodSelecao();
        } catch(Exception e) {
            e.printStackTrace();
             return "redirect:/selecao/" + selecao.getCodSelecao();
        }
    }
    
    
    @RequestMapping(value = "/remove/{codSelecao}", method = RequestMethod.GET)
    public String removeSelecao(@PathVariable long codSelecao, Model model, HttpServletRequest request) {
    	 HttpSession session = request.getSession();
    	 UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
    	 try {
	    	 if (usuario != null) {
	    		 SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
	    		 selecaoServiceIfc.setUsuario(usuario);
	    		 selecaoServiceIfc.removeSelecao(selecao);
	    		 session.setAttribute("mensagem", "Seleção removida com sucesso!");
	             session.setAttribute("status", "success");
	    	 }
    	 } catch (Exception e) {
    		 e.printStackTrace();
    		 session.setAttribute("mensagem", e.getMessage());
             session.setAttribute("status", "danger");
             return "minhas-selecoes";
		}
    	return "minhas-selecoes";
    }

    
}
