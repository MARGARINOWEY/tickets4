package com.example.demo.controllers.Usuario;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Evento;
import com.example.demo.entity.Usuario;
import com.example.demo.service.IUsuarioService;

@Controller
public class UsuarioController {

    @Autowired
	private IUsuarioService usuarioService;

    @RequestMapping(value = "/loginR", method = RequestMethod.GET) // Pagina principal
	public String LoginM() {
		
		return "login/Login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET) // Pagina principal
	public String LoginM2() {
		
		return "redirect:/eventoCR";
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET) // Pagina principal
	public String email() {
		
		return "Ticket/compraEmail";
	}

    @RequestMapping(value = "/loginF", method = RequestMethod.POST)
	public String LoginF(@RequestParam(value = "usuario") String user,@RequestParam(value = "contrasena") String contrasena, Model model, HttpServletRequest request,RedirectAttributes flash){
		
		Usuario usuario = usuarioService.getUsuarioContraseña(user, contrasena);
		
		if (usuario != null) {
			
			if (usuario.getEstado() != "A" || usuario.getEstado() == null) {
				return "redirect:/eventoCR";
			}
			if (usuario.getEstado().equals("A")) {
				HttpSession session = request.getSession(true);
			
				session.setAttribute("persona", usuario.getPersona());
				session.setAttribute("usuario", usuario);
			
				flash.addAttribute(" Inicio Sesion!"+ usuario.getPersona().getNombre());

			
			
			return "redirect:/BienvenidoR";	
			}
			return "redirect:/eventoCR";
		} else{
			return "redirect:/loginR";
		}
	
	}

    @RequestMapping("/cerrar_sesion")
    public String cerrarSesion(HttpServletRequest request, RedirectAttributes flash) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
            flash.addAttribute("validado", "Se cerro sesion con exito!");
        }
        return "redirect:/loginR";
    }

    @RequestMapping(value = "/BienvenidoR", method = RequestMethod.GET) // Pagina principal
	public String BienvenidoR(HttpServletRequest request,Model model) {
		

			//String b = "texto 1";
			//String cadenaCodificada = Base64.getEncoder().encodeToString(Base64.getDecoder().decode(a));	
			//byte [] bytesDecodificados = Base64.getDecoder().decode(a);
			//String cadenaDecodificada = new String(bytesDecodificados);
			//System.out.println(cadenaCodificada);
			//byte[] bytes = "Hola, mundo!".getBytes();
			//System.out.println(bytes.length);
			//String base64String = Base64.getEncoder().encodeToString();

			return "login/Bienvenido";
	}
}
