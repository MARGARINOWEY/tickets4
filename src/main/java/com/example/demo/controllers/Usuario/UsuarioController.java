package com.example.demo.controllers.Usuario;

import java.text.SimpleDateFormat;
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

    @RequestMapping(value = "/loginF", method = RequestMethod.POST)
	public String LoginF(@RequestParam(value = "usuario") String user,@RequestParam(value = "contrasena") String contrasena, Model model, HttpServletRequest request,RedirectAttributes flash){
		
		Usuario usuario = usuarioService.getUsuarioContraseña(user, contrasena);
		
		if (usuario != null) {
			
			HttpSession session = request.getSession(true);
			
			session.setAttribute("persona", usuario.getPersona());
			session.setAttribute("usuario", usuario);
			
			flash.addAttribute(" Inicio Sesion!"+ usuario.getPersona().getNombre());

			
			
			return "redirect:/BienvenidoR";
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
	public String BienvenidoR() {
		
		return "login/Bienvenido";
	}
}
