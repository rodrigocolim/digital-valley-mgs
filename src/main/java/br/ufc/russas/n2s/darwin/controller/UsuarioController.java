package br.ufc.russas.n2s.darwin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.service.UsuarioServiceIfc;

@Controller("usuarioController")
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	private UsuarioServiceIfc usuarioServiceIfc;
	
	@Autowired(required = true)
	public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
		this.usuarioServiceIfc = usuarioServiceIfc;
	}
	
	@RequestMapping(value="/recebeEmail", method = RequestMethod.GET)
	 public @ResponseBody void recebeEmail(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		 HttpSession session = request.getSession();
		 UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioDarwin");
		 try {
			 usuario.setRecebeEmail(!usuario.isRecebeEmail());
			 usuarioServiceIfc.atualizaUsuario(usuario);
			 if (usuario.isRecebeEmail()) {
				 session.setAttribute("mensagem", "Agora você receberá atualizações por e-mail!");
			 } else {
				 session.setAttribute("mensagem", "Agora você não receberá atualizações por e-mail!");
			 }
			 session.setAttribute("status", "success");
			 response.sendRedirect("/Darwin/");
		 } catch (NullPointerException e) {
			 session.setAttribute("mensagem", e.getMessage());
			 session.setAttribute("status", "danger");
			 response.sendRedirect("/Darwin/");
		 } catch (Exception e) {
			 session.setAttribute("mensagem", e.getMessage());
			 session.setAttribute("status", "danger");
			 response.sendRedirect("/Darwin/");
		 }
	 }
}
