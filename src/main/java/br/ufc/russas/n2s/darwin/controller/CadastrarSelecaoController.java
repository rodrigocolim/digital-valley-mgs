/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UploadedFile;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Wallison Carlos
 */
@Controller("cadastrarSelecaoController")
@RequestMapping("/cadastrarSelecao")
public class CadastrarSelecaoController { 

    private SelecaoServiceIfc selecaoServiceIfc;

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

    @RequestMapping(method = RequestMethod.POST)

    public @ResponseBody String adiciona(@Valid SelecaoBeans selecao, BindingResult result, @RequestParam("edital") MultipartFile file) throws IOException {

        if (result.hasErrors()  && !result.hasFieldErrors("edital")) {

            System.out.println("\n\nde novo!!!\n\n");

            return "cadastrar-selecao";
        }

        if (result.hasErrors() && !result.hasFieldErrors("file")) {
            return "cadastrar-selecao";
        }
        
        selecao.getResponsaveis().add(new UsuarioBeans());
        System.out.println(file);
        if (!file.isEmpty()) {

            ArquivoBeans edital = new ArquivoBeans();
            edital.setTitulo("Edital para ".concat(selecao.getTitulo()));
            edital.setData(LocalDateTime.now());
            edital.setArquivo(FileManipulation.getFileStream(file.getInputStream(), ".pdf"));

            //System.out.println("\n\neu aqui1!!!\n\n");

            selecao.setEdital(edital);
            System.out.println(selecao.getCodSelecao());
            System.out.println(selecao.getDescricao());
            System.out.println(selecao.getTitulo());
            System.out.println(selecao.getCategoria());
        }

        
        
        System.out.println("\n\neu aqui!!!\n\n");

        selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);

        return "forward:/selecao/"+selecao.getCodSelecao();
    }
}
