/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
import br.ufc.russas.n2s.darwin.model.EnumPermissao;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
/**
 *
 * @author Wallison Carlos
 */
@Controller("cadastrarSelecaoController")
@RequestMapping("/cadastrarSelecao")
public class CadastrarSelecaoController { 

    private SelecaoServiceIfc selecaoServiceIfc;
    private UsuarioServiceIfc usuarioServiceIfc;
    
    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }

    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "cadastrar-selecao";
    }

    public UsuarioServiceIfc getUsuarioServiceIfc() {
        return usuarioServiceIfc;
    }
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
    }
    
   @RequestMapping(method = RequestMethod.POST)
    public String adiciona(@ModelAttribute("selecao") @Valid SelecaoBeans selecao, BindingResult result, @RequestParam("file") String file, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException {
        HttpSession session = request.getSession();
        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        this.selecaoServiceIfc.setUsuario(usuario);
        String[] codResponsaveis = request.getParameterValues("codResponsaveis");
        
        String[] nomeAnexos = request.getParameterValues("listaNomeAnexos");
        String[] linkAnexos = request.getParameterValues("listaLinkAnexos");
        String[] nomeAditivos = request.getParameterValues("listaNomeAditivos");
        String[] linkAditivos = request.getParameterValues("listaLinkAditivos");
        
        ArrayList<UsuarioBeans> responsaveis = new ArrayList<>();
        if (codResponsaveis != null) {
            for (String cod : codResponsaveis) {
                UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod),0);
                if (u != null) {
                    responsaveis.add(u);
                }
            }
        }
        try {
            if (!file.isEmpty()) {// para o edital
                ArquivoBeans edital = new ArquivoBeans();
                edital.setTitulo("Edital para ".concat(selecao.getTitulo()));
                File temp = File.createTempFile("temp", ".pdf");
                InputStream input = new URL(file).openStream();
                OutputStream output = new FileOutputStream(temp);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = input.read(bytes)) != -1) {
                    output.write(bytes, 0, read);
                }
                edital.setArquivo(temp);
                edital.setData(LocalDateTime.now());
                selecao.setEdital(edital);
            }
            //para anexos
            if (nomeAnexos != null && linkAnexos != null) {
                for (int i=0; i < nomeAnexos.length; i++) {
                    ArquivoBeans anexo = new ArquivoBeans();
                    anexo.setTitulo(nomeAnexos[i]);
                    File temp = File.createTempFile("temp", ".pdf");
                    InputStream input = FileManipulation.getStreamFromURL(linkAnexos[i]);
                    OutputStream output = new FileOutputStream(temp);
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    while ((read = input.read(bytes)) != -1) {
                        output.write(bytes, 0, read);
                    }
                    selecao.getAnexos().add(anexo);
                }
            }
            //para aditivos
            if (nomeAditivos != null && linkAditivos != null) {
                for (int i=0; i < nomeAditivos.length; i++) {
                    ArquivoBeans aditivo = new ArquivoBeans();
                    aditivo.setTitulo(nomeAditivos[i]);
                    File temp = File.createTempFile("temp", ".pdf");
                    InputStream input = FileManipulation.getStreamFromURL(linkAditivos[i]);
                    OutputStream output = new FileOutputStream(temp);
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    while ((read = input.read(bytes)) != -1) {
                        output.write(bytes, 0, read);
                    }
                    selecao.getAditivos().add(aditivo);
                }
            }
            
            selecao.setEstado(EnumEstadoSelecao.ESPERA);
            selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);
            if(!usuario.getPermissoes().contains(EnumPermissao.RESPONSAVEL)){
                usuario.getPermissoes().add(EnumPermissao.RESPONSAVEL);
            }
            selecao.setResponsaveis(responsaveis);
            selecao.getResponsaveis().add((UsuarioDarwin) usuario.toBusiness());
            selecao = this.getSelecaoServiceIfc().atualizaSelecao(selecao);
            session.setAttribute("mensagemCadastraSelecao", "Seleção cadastrada com sucesso!");
            session.setAttribute("statusCadastraSelecao", "success");
            return ("redirect:selecao/" + selecao.getCodSelecao());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("cadastrar-selecao");
        } catch (IllegalArgumentException | NullPointerException | IllegalAccessException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("cadastrar-selecao");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("cadastrar-selecao");
        }

    }

    
    
}
