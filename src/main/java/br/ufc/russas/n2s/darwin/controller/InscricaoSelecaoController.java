/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Wallison Carlos
 */
@Controller("inscricaoSelecaoController")
@RequestMapping("/selecao/inscricao")
public class InscricaoSelecaoController {
    private EtapaServiceIfc etapaServiceIfc;
    private SelecaoServiceIfc selecaoServiceIfc;
    private UsuarioServiceIfc usuarioServiceIfc;
    public EtapaServiceIfc getEtapaServiceIfc() {
        return etapaServiceIfc;
    }

    public UsuarioServiceIfc getUsuarioServiceIfc() {
        return usuarioServiceIfc;
    }
    @Autowired(required = true)
    public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
        this.usuarioServiceIfc = usuarioServiceIfc;
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
    
    
    @RequestMapping(value="/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model) {
        SelecaoBeans selecaoBeans = this.selecaoServiceIfc.getSelecao(codSelecao);
        model.addAttribute("selecao", selecaoBeans);
        return "participar-etapa";
    }

    @RequestMapping(value="/{codSelecao}", method = RequestMethod.POST)
    public @ResponseBody void participar(@PathVariable long codSelecao, HttpServletRequest request, HttpServletResponse response, @RequestParam("nomeDocumento") String[] nomeDocumento, @RequestParam("documento") MultipartFile[] documentos) throws IOException {    
        HttpSession session = request.getSession();
        try {
            SelecaoBeans selecaoBeans = this.selecaoServiceIfc.getSelecao(codSelecao);
            UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            this.etapaServiceIfc.setUsuario(usuario);
            List<Arquivo> arquivos = Collections.synchronizedList(new ArrayList<Arquivo>());
            Participante participante = (Participante) this.etapaServiceIfc.getParticipante(selecaoBeans.getInscricao(), usuario).toBusiness();
            if(documentos != null) {
                for (int i = 0; i < documentos.length;i++) {
                    String nome = nomeDocumento[i];
                    MultipartFile file = documentos[i];
                    if (!file.isEmpty()) {
                        Arquivo documento = new Arquivo();
                        java.io.File convFile = new java.io.File(file.getOriginalFilename());
                        convFile.createNewFile(); 
                        FileOutputStream fos = new FileOutputStream(convFile); 
                        fos.write(file.getBytes());
                        fos.close(); 
                        documento.setTitulo(nome);
                        documento.setData(LocalDateTime.now());
                        documento.setArquivo(FileManipulation.getFileStream(file.getInputStream(), ".pdf"));
                        arquivos.add(documento);
                    }        
                }
                Documentacao documentacao = new  Documentacao();
                documentacao.setCandidato(participante);
                documentacao.setDocumentos(arquivos);
                this.selecaoServiceIfc.participa(selecaoBeans, (ParticipanteBeans) new ParticipanteBeans().toBeans(participante),(DocumentacaoBeans) new DocumentacaoBeans().toBeans(documentacao));
            } else {
                this.selecaoServiceIfc.participa(selecaoBeans, (ParticipanteBeans) new ParticipanteBeans().toBeans(participante));
            }
            session.setAttribute("mensagem", "Agora você está inscrito na seleção".concat(selecaoBeans.getTitulo()));
            session.setAttribute("status", "success");
            //response.sendRedirect("selecao/" + selecao.getCodSelecao());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("mensagem", e.getMessage());
            session.setAttribute("status", "danger");
            response.sendRedirect("participar-etapa");
        } catch (IllegalArgumentException | NullPointerException | IllegalAccessException e) {
            e.printStackTrace();
            session.setAttribute("mensagem", e.getMessage());
            session.setAttribute("status", "danger");
            response.sendRedirect("participar-etapa");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("mensagem", e.getMessage());
            session.setAttribute("status", "danger");
            response.sendRedirect("participar-etapa");
        }
    }
    
}
