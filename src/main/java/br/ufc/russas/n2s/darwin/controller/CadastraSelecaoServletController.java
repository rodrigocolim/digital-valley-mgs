/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Wallison Carlos
 */
@WebServlet(name = "CadastraSelecaoServletController", urlPatterns = {"/adicionaSelecao"})
public class CadastraSelecaoServletController extends HttpServlet {

    private SelecaoServiceIfc selecaoServiceIfc;

    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }

    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
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
        response.setContentType("text/html;charset=UTF-8");
         boolean isMultiPart = FileUpload.isMultipartContent(request);
        if(isMultiPart){
            HttpSession session = request.getSession();
            
            try{
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileItemFactory(factory);
                List itens = upload.parseRequest(request);
                SelecaoBeans selecao = new SelecaoBeans();
                File file = null;
                String nomeCampo = "";
                String valorCampo = "";
                int k=0;
                for(int i=0;i<itens.size();i++){
                    FileItem item = (FileItem) itens.get(i);
                    String campo = item.getFieldName();
                    String valor = item.getString();
                    
                    if(item.isFormField()){
                        //Escolhe o que vai fazer com os campos normais
                        nomeCampo = item.getFieldName();
                        if(nomeCampo.equals("titulo")){
                            valorCampo = item.getString();
                            selecao.setTitulo(valorCampo);
                        }
                        if(nomeCampo.equals("descricao")){
                            valorCampo = item.getString();
                            selecao.setDescricao(valorCampo);
                        }
                        if(nomeCampo.equals("descricaoPreRequisitos")){
                            valorCampo = item.getString();
                            selecao.setDescricaoPreRequisitos(valorCampo);
                        }
                        if(nomeCampo.equals("categoria")){
                            valorCampo = item.getString();
                            selecao.setCategoria(valorCampo);
                        }
                        if(nomeCampo.equals("areaDeConcentracao")){
                            valorCampo = item.getString();
                            selecao.setAreaDeConcentracao(valorCampo);
                        }
                        if(nomeCampo.equals("vagasRemuneradas")){
                            valorCampo = item.getString();
                            selecao.setVagasRemuneradas(Integer.parseInt(valorCampo));
                        }
                        if(nomeCampo.equals("vagasVoluntarias")){
                            valorCampo = item.getString();
                            selecao.setVagasRemuneradas(Integer.parseInt(valorCampo));
                        }
                    }else{
                        //Escolhe o que vai fazer com os campos files
                        if(item.get().length>0){
                            File temp = File.createTempFile("temp", ".pdf");
                            InputStream input = item.getInputStream();
                            OutputStream output = new FileOutputStream(temp);
                            int read = 0;
                            byte[] bytes = new byte[1024];
                            while ((read = input.read(bytes)) != -1) {
                                output.write(bytes, 0, read);
                            }
                            file = temp;
                            
                            output.flush();
                            output.close();
                            input.close();
                        }
                    }
                }
                if(file != null){
                    ArquivoBeans arquivo = new ArquivoBeans();
                    arquivo.setTitulo("Edital "+selecao.getTitulo());
                    arquivo.setData(LocalDateTime.now());
                    arquivo.setArquivo(file);
                    selecao.setEdital(arquivo);
                }
                this.selecaoServiceIfc.adicionaSelecao(selecao);
                System.err.println("Obriado Deus!");
                request.setAttribute("msg", "Imagem adicionada com sucesso!");
               
            }catch(FileUploadException e){
                e.printStackTrace();
            }catch(Exception e){
                request.setAttribute("msg", e.getMessage());
            }
        }else{
            System.out.println("Não foi enviado um multipart!");
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

}
