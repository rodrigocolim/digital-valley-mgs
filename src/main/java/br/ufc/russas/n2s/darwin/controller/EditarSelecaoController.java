/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public String atualiza(@PathVariable long codSelecao, @ModelAttribute("selecao") @Valid SelecaoBeans selecao, BindingResult result, @RequestParam("file") String file, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException {
        SelecaoBeans selecaoBeans = this.getSelecaoServiceIfc().getSelecao(codSelecao);
        HttpSession session = request.getSession();
        try{
            selecaoBeans.setTitulo(selecao.getTitulo());
            selecaoBeans.setDescricao(selecao.getDescricao());
            selecaoBeans.setDescricaoPreRequisitos(selecao.getDescricaoPreRequisitos());
            selecaoBeans.setAreaDeConcentracao(selecao.getAreaDeConcentracao());
            selecaoBeans.setCategoria(selecao.getCategoria());
            selecaoBeans.setVagasRemuneradas(selecao.getVagasRemuneradas());
            selecaoBeans.setVagasVoluntarias(selecao.getVagasVoluntarias());
            if (!file.isEmpty()) {
                ArquivoBeans edital = new ArquivoBeans();
                edital.setTitulo("Edital para ".concat(selecao.getTitulo()));
                edital.setData(LocalDateTime.now());
                InputStream inputStream = new URL(file).openStream();
                edital.setArquivo(FileManipulation.getFileStream(inputStream, ".pdf"));
                selecaoBeans.setEdital(edital);
            }
            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            this.getSelecaoServiceIfc().setUsuario(usuario);
            selecaoBeans = this.getSelecaoServiceIfc().atualizaSelecao(selecaoBeans);
            session.setAttribute("selecao", selecaoBeans);
            session.setAttribute("mensagemCadastraSelecao", "Seleção atualizada com sucesso!");
            session.setAttribute("statusCadastraSelecao", "success");
            return ("redirect:selecao/" + selecao.getCodSelecao());
        }catch(IOException | IllegalAccessException e){
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("cadastrar-selecao");
        }
    }
    
    @RequestMapping(value = "/divulga/{codSelecao}", method = RequestMethod.GET)
    public String divulga(@PathVariable long codSelecao, Model model, HttpServletRequest request){
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        HttpSession session = request.getSession();
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        try{
            selecaoServiceIfc.setUsuario(usuario);
            selecao.setDivulgada(true);
            selecao = selecaoServiceIfc.atualizaSelecao(selecao);
            request.getSession().setAttribute("selecao", selecao);
            return "redirect:/selecao/" + selecao.getCodSelecao();
            
        }catch(IllegalAccessException e){
            e.printStackTrace();
            return "redirect:/selecao/" + selecao.getCodSelecao();
        }catch(Exception e){
            e.printStackTrace();
             return "redirect:/selecao/" + selecao.getCodSelecao();
        }
    }
    
    /*
    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.PUT)
    public String 
*/
    
}
