/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import br.ufc.russas.n2s.darwin.beans.InscricaoBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Arquivo;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.FileManipulation;
import br.ufc.russas.n2s.darwin.model.Inscricao;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import util.Facade;

/**
 *
 * @author Wallison Carlos
 */
@WebServlet(name = "InscricaoSelecaoController", urlPatterns = {"/inscricaoSelecao"})
public class InscricaoSelecaoController extends HttpServlet {

    private EtapaServiceIfc etapaServiceIfc;
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultiPart = org.apache.commons.fileupload.FileUpload.isMultipartContent(request);
        if(isMultiPart){
            try{
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileItemFactory(factory);
                List itens = upload.parseRequest(request);

                Selecao selecao = new Selecao();
                HttpSession session = request.getSession();
                SelecaoBeans sb = new SelecaoBeans();
                InscricaoBeans etapa = etapaServiceIfc.getInscricao(Long.parseLong(request.getParameter("etapa")));
                UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
                this.etapaServiceIfc.setUsuario(usuario);
                List<ArquivoBeans> arquivos = Collections.synchronizedList(new ArrayList<ArquivoBeans>());
                int k=0;
                String nomeCampo = "";
                String valorCampo = "Documento";
                for(int i=0;i<itens.size();i++){
                     FileItem item = (FileItem) itens.get(i);
                     String campo = item.getFieldName();
                     String valor = item.getString();

                     if(item.isFormField()){
                         //Escolhe o que vai fazer com os campos normais
                         nomeCampo = item.getFieldName();
                         valorCampo = item.getString();
                     }else{
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
                            k++;
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
                 }
                DocumentacaoBeans documentacao = new  DocumentacaoBeans();
                ParticipanteBeans participante = new ParticipanteBeans();
                participante.setCandidato((UsuarioDarwin) usuario.toBusiness());
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
        
    
    @Override
    public void init(ServletConfig arg0) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        arg0.getServletContext());		
    }
}
