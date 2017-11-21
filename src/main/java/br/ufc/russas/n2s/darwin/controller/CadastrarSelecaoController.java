/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.dao.DocumentacaoDAOImpl;
import br.ufc.russas.n2s.darwin.model.EnumPermissoes;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public @ResponseBody void adiciona(@ModelAttribute("selecao") @Valid SelecaoBeans selecao, BindingResult result, @RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {

        if (result.hasErrors()) {

            System.out.println("\n\nde novo!!!\n\n");
            response.sendRedirect("casdastrarSelecao");
            //return "cadastrar-selecao";
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
            
            //edital.setArquivo(FileManipulation.getFileStream(file.getInputStream(), ".pdf"));

            //System.out.println("\n\neu aqui1!!!\n\n");

            selecao.setEdital(edital);
            
        }

        selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);
        UsuarioBeans u = new UsuarioBeans();
        u.setNome("Alex");
        ArrayList<EnumPermissoes> p = new ArrayList();
        p.add(EnumPermissoes.RESPONSAVEL);
        u.setPermissoes(p);
        u.setCodUsuario(12);
        selecao.setResponsaveis(new ArrayList<UsuarioBeans>());
        selecao.getResponsaveis().add(u);
        selecao = selecaoServiceIfc.atualizaSelecao(selecao);
        response.sendRedirect("selecao/" + selecao.getCodSelecao());
        //return "forward:/selecao/"+selecao.getCodSelecao();


    }
    
}
