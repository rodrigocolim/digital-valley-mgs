/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
<<<<<<< HEAD
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceImpl;
=======
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
>>>>>>> 7732332fcbb97d74092b09f8ce1e7aa29ce650cc
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletConfig;
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
<<<<<<< HEAD
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
=======
>>>>>>> 7732332fcbb97d74092b09f8ce1e7aa29ce650cc

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
    
    
    private SelecaoServiceIfc selecaoServiceIfc;

    public SelecaoServiceIfc getSelecaoServiceIfc() {
        return selecaoServiceIfc;
    }

    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }
    
    @Override
    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
          config.getServletContext());
      }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         boolean isMultiPart = FileUpload.isMultipartContent(request);
        if(isMultiPart){
            HttpSession session = request.getSession();
            
            try{
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                //upload.setFileItemFactory(factory);
                List itens = upload.parseRequest(request);
                SelecaoBeans selecao = new SelecaoBeans();
                selecao.setTitulo(request.getParameter("titulo"));
                selecao.setDescricao(request.getParameter("descricao"));
                selecao.setDescricaoPreRequisitos(request.getParameter("descricaoPreRequisitos"));
                selecao.setCategoria(request.getParameter("categoria"));
                selecao.setAreaDeConcentracao(request.getParameter("areaDeConcentracao"));
                selecao.getResponsaveis().add(new UsuarioBeans());
                File file = null;
                String nomeCampo = "";
                String valorCampo = "";
                int k=0;
            
                for(int i=0;i<itens.size();i++){
                    FileItem item = (FileItem) itens.get(i);
                    String campo = item.getFieldName();
                    String valor = item.getString();
                    System.out.println(campo+": \n "+valor);
                    if(item.isFormField()){
                        //Escolhe o que vai fazer com os campos normais
                        nomeCampo = item.getFieldName();
                        if(campo.equals("titulo")){
                            valorCampo = item.getString();
                            selecao.setTitulo(valor);
                        }
                        if(campo.equals("descricao")){
                            valorCampo = item.getString();
                            selecao.setDescricao(valorCampo);
                        }
                        if(campo.equals("descricaoPreRequisitos")){
                            valorCampo = item.getString();
                            selecao.setDescricaoPreRequisitos(valorCampo);
                        }
                        if(campo.equals("categoria")){
                            valorCampo = item.getString();
                            selecao.setCategoria(valorCampo);
                        }
                        if(campo.equals("areaDeConcentracao")){
                            valorCampo = item.getString();
                            selecao.setAreaDeConcentracao(valorCampo);
                        }
<<<<<<< HEAD
                        if(campo.equals("vagasVoluntarias")){
                            valorCampo = item.getString();
                             selecao.setVagasVoluntarias(Integer.parseInt(valorCampo));
                        }
                        if(campo.equals("vagasRemuneradas")){
=======
                        if(nomeCampo.equals("vagasRemuneradas")){
                            valorCampo = item.getString();
                            selecao.setVagasRemuneradas(Integer.parseInt(valorCampo));
                        }
                        if(nomeCampo.equals("vagasVoluntarias")){
>>>>>>> 7732332fcbb97d74092b09f8ce1e7aa29ce650cc
                            valorCampo = item.getString();
                            selecao.setVagasRemuneradas(Integer.parseInt(valorCampo));
                        }
                    }else{
                        System.out.println("br.ufc.russas.n2s.darwin.controller.CadastraSelecaoServletContrfffffffffffffffffffffff");
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
                            ArquivoBeans ab = (ArquivoBeans) new ArquivoBeans().toBeans(file);
                            selecao.setEdital(ab);
                        }
                    }
                }
<<<<<<< HEAD
               
             
                System.err.println("Obriado Deus! 1");
                selecao = this.getSelecaoServiceIfc().adicionaSelecao(selecao);
=======
                if(file != null){
                    ArquivoBeans arquivo = new ArquivoBeans();
                    arquivo.setTitulo("Edital "+selecao.getTitulo());
                    arquivo.setData(LocalDateTime.now());
                    arquivo.setArquivo(file);
                    selecao.setEdital(arquivo);
                }
                this.selecaoServiceIfc.adicionaSelecao(selecao);
>>>>>>> 7732332fcbb97d74092b09f8ce1e7aa29ce650cc
                System.err.println("Obriado Deus!");
                response.sendRedirect("selecao/"+selecao.getCodSelecao());
                request.setAttribute("msg", "Imagem adicionada com sucesso!");
               
            }catch(FileUploadException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
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
