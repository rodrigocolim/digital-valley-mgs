/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Wallison Carlos
 */
@Controller("permissoesUsuarioController")
@RequestMapping("/permissoes")
public class PermissoesUsuarioController {
    
    private UsuarioServiceIfc usuarioServiceIfc;
    
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc") UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model, HttpServletRequest request) {
        model.addAttribute("usuarios", this.usuarioServiceIfc.listaTodosUsuarios());
        return "acessarPermissoes"; 
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String getIndexSelectedUser(Model model, HttpServletRequest request) {
        model.addAttribute("usuarios", this.usuarioServiceIfc.listaTodosUsuarios());
        model.addAttribute("usuarioSelecionado", this.usuarioServiceIfc.getUsuario(Long.parseLong(request.getParameter("usuario")), 0));
        return "acessarPermissoes"; 
    }
    
    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public String adiciona(@RequestParam("codUsuario") long codUsuario, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
            System.out.println("CodUsuario"+codUsuario);
            UsuarioBeans autenticado = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            usuarioServiceIfc.setUsuario(autenticado);
            UsuarioBeans usuario = usuarioServiceIfc.getUsuario(codUsuario, 0);
            String[] permissoes  = request.getParameterValues("codPermissao");
            for(String num : permissoes) {
                System.out.println(num);
                int permissao = Integer.parseInt(num);
                EnumPermissao p = null;
                if (permissao == 1) {
                    p = EnumPermissao.PARTICIPANTE;
                } else if (permissao == 2) {
                    p = EnumPermissao.AVALIADOR;
                } else if (permissao == 3) {
                    p = EnumPermissao.RESPONSAVEL;
                } else if (permissao == 4) {
                    p = EnumPermissao.ADMINISTRADOR;
                }
                if (!usuario.getPermissoes().contains(p)) {
                    usuarioServiceIfc.adicionaNivel(usuario, p);
                }
            }
            model.addAttribute("mensagem", "Nível (is) atualizado (s) com sucesso!");
            model.addAttribute("status", "success");
            model.addAttribute("usuarios", this.usuarioServiceIfc.listaTodosUsuarios());
            model.addAttribute("usuarioSelecionado", this.usuarioServiceIfc.getUsuario(Long.parseLong(request.getParameter("codUsuario")), 0));
            return "acessarPermissoes";
        } catch (NumberFormatException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "nivel-usuario";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "nivel-usuario";
        }  catch (IllegalAccessException e) {
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "nivel-usuario";
        }
    }
    
    
}
