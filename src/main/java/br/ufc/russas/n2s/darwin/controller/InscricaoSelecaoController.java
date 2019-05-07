/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Wallison Carlos
 */
@WebServlet(name = "InscricaoSelecaoController", urlPatterns = {"/inscricaoSelecao"})
public class InscricaoSelecaoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EtapaServiceIfc etapaServiceIfc;
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        @SuppressWarnings("deprecation")
		boolean isMultiPart = org.apache.commons.fileupload.FileUpload.isMultipartContent(request);
        
        if(isMultiPart){
            try{
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileItemFactory(factory);
                List<FileItem> itens = upload.parseRequest(request);
                
                HttpSession session = request.getSession();
                EtapaBeans etapa = etapaServiceIfc.getEtapa(Long.parseLong(request.getParameter("etapa")));
                UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
                this.etapaServiceIfc.setUsuario(usuario);
                List<ArquivoBeans> arquivos = Collections.synchronizedList(new ArrayList<ArquivoBeans>());
                String valorCampo = "Documento";
                for(int i=0;i<itens.size();i++){
                     FileItem item = (FileItem) itens.get(i);

                     //Escolhe o que vai fazer com os campos files
                     if(item.get().length>0){
                        File temp = File.createTempFile("temp", ".pdf");
                        InputStream input = item.getInputStream();
                        OutputStream output = new FileOutputStream(temp);
                        int read = 0;
                        byte[] bytes = new byte[10240];
                        while ((read = input.read(bytes)) != -1) {
                            output.write(bytes, 0, read);
                        }

                        ArquivoBeans documento = new ArquivoBeans();
                        documento.setTitulo(valorCampo);
                        documento.setData(LocalDateTime.now());
                        documento.setArquivo(temp);
                        arquivos.add(documento);
                        output.flush();
                        output.close();
                        input.close();
                     }
                }
                
                DocumentacaoBeans documentacao = new  DocumentacaoBeans();
                ParticipanteBeans participante = new ParticipanteBeans();
                participante.setCandidato(usuario);
                participante.setData(LocalDateTime.now());
                documentacao.setCandidato(participante);
                documentacao.setDocumentos(arquivos);
                if (arquivos.size()>0) {
                    etapaServiceIfc.participa(etapa, participante);
                } else {
                    etapaServiceIfc.participa(etapa, participante, documentacao);
                }    

            }catch(FileUploadException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Multpart not send");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
        
    @Override
    public void init(ServletConfig arg0) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        arg0.getServletContext());		
    }
}
