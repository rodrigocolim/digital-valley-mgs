/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.Usuario;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Alex Felipe
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
        request.getSession().setAttribute("selecao", selecao);
        return "editar-selecao";
    }
    
    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.POST)
    public @ResponseBody void edita(@ModelAttribute("selecao") SelecaoBeans selecao, @RequestParam("file") String file, @PathVariable long CodSelecao, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("\n\n\n\n\n");
        System.out.println(selecao.toString());
        System.out.println(selecao.getTitulo());
        System.out.println(selecao.getDescricao());
        System.out.println(selecao.getCodSelecao());
        System.out.println("\n\n\n\n\n");
        SelecaoBeans selecaoBeans = this.getSelecaoServiceIfc().getSelecao(CodSelecao);
        selecaoBeans.setTitulo(selecao.getTitulo());
        selecaoBeans.setDescricao(selecao.getDescricao());
        selecaoBeans.setDescricaoPreRequisitos(selecao.getDescricaoPreRequisitos());
        selecaoBeans.setAreaDeConcentracao(selecao.getAreaDeConcentracao());
     
        if (!file.isEmpty()) {
                ArquivoBeans edital = new ArquivoBeans();
                edital.setTitulo("Edital para ".concat(selecao.getTitulo()));
                edital.setData(LocalDateTime.now());
                InputStream inputStream = new URL(file).openStream();
                edital.setArquivo(FileManipulation.getFileStream(inputStream, ".pdf"));
                selecao.setEdital(edital);
            }
        //selecao.setCodSelecao(CodSelecao);
        HttpSession session = request.getSession();
        //SelecaoBeans selecao = (SelecaoBeans) session.getAttribute("selecao");
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        try{
            selecaoBeans = this.getSelecaoServiceIfc().atualizaSelecao(selecao);
            session.setAttribute("selecao", selecaoBeans);
            session.setAttribute("mensagem", "Seleção atualizada com sucesso!");
            session.setAttribute("status", "success");
            response.sendRedirect("selecao/" + selecao.getCodSelecao());
        }catch(IOException | IllegalAccessException e){
            session.setAttribute("mensagem", e.getMessage());
            session.setAttribute("status", "danger");
            response.sendRedirect("selecao/" + selecao.getCodSelecao());
        }
    }
    
    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.PUT)
    public String 
    
}
