/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
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
@Controller("nivelUsuarioController")
@RequestMapping("/nivelUsuario")
public class NivelUsuarioController {
    
    private UsuarioServiceIfc usuarioServiceIfc;
    
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc") UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
    }
    
    public String getIndex() {
        return "nivel-usuario"; 
    }
    
    @RequestMapping(value = "/adicionar", method = RequestMethod.POST)
    public String adiciona(@RequestParam("usuario") long codUsuario, @RequestParam("permissao") int permissao, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
            UsuarioBeans autenticado = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            usuarioServiceIfc.setUsuario(autenticado);
            UsuarioBeans usuario = usuarioServiceIfc.getUsuario(codUsuario, 0);
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
            usuarioServiceIfc.adicionaNivel(usuario, p);
            model.addAttribute("mensagem", "Nível adicionado com sucesso!");
            model.addAttribute("status", "danger");  
            return "nivel-usuario";
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
    
    @RequestMapping(value = "/remover", method = RequestMethod.POST)
    public String remove(@RequestParam("usuario") long codUsuario, @RequestParam("permissao") int permissao, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
            UsuarioBeans autenticado = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            usuarioServiceIfc.setUsuario(autenticado);
            UsuarioBeans usuario = usuarioServiceIfc.getUsuario(codUsuario, 0);
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
            usuarioServiceIfc.removeNivel(usuario, p);
            model.addAttribute("mensagem", "Nível removido com sucesso!");
            model.addAttribute("status", "danger");  
            return "nivel-usuario";
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
