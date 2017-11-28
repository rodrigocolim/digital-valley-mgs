/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumPermissoes;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
@Controller("editarr=SelecaoController")
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

    @RequestMapping(value = "/{selecaoCodigo}", method = RequestMethod.GET)
    public String getIndex(@PathVariable String selecaoCodigo, Model model, HttpServletRequest request){
       // String[] part = selecaoCodigo.split("_");
       // long codSelecao = Long.parseLong(part[part.length-1]);
        long codSelecao = Long.parseLong(selecaoCodigo);
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        request.getSession().setAttribute("selecao", selecao);
        return "editar-selecao";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void edita(@ModelAttribute("selecao") @Valid SelecaoBeans selecao, BindingResult result, @RequestParam("edital") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (result.hasErrors()) {
            response.sendRedirect("editarSelecao/"+selecao.getCodSelecao());
        }
        if (!file.isEmpty()) {
            ArquivoBeans edital = new ArquivoBeans();
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile(); 
            FileOutputStream fos = new FileOutputStream(convFile); 
            fos.write(file.getBytes());
            fos.close(); 
            edital.setTitulo("Edital para ".concat(selecao.getTitulo()));
            edital.setData(LocalDateTime.now());
            edital.setArquivo(convFile);
            selecao.setEdital(edital);
        }
        try {
            selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(EditarSelecaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession();
        UsuarioBeans usuario = this.getUsuarioServiceIfc().getUsuarioControleDeAcesso(((Usuario) session.getAttribute("usuario")).getPessoa().getId());
        if(!usuario.getPermissoes().contains(EnumPermissoes.RESPONSAVEL)){
            usuario.getPermissoes().add(EnumPermissoes.RESPONSAVEL);
        }
        selecao.getResponsaveis().add((UsuarioDarwin) usuario.toBusiness());
        try {
            selecao = selecaoServiceIfc.atualizaSelecao(selecao);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(EditarSelecaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("selecao/" + selecao.getCodSelecao());
        //return "forward:/selecao/"+selecao.getCodSelecao();


    }
    
}
