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

import com.example.demo.entity.Persona;
import com.example.demo.entity.Usuario;
import com.example.demo.service.IPersonaService;
import com.example.demo.service.IUsuarioService;

@Controller
public class UsuarioC_Controller {
    
    @Autowired
	private IUsuarioService usuarioService;
    @Autowired
	private IPersonaService personaService;

    @RequestMapping(value = "/loginCR", method = RequestMethod.GET) // Pagina principal
	public String loginCR(@Validated Persona persona,@Validated Usuario usuario,RedirectAttributes flash, HttpServletRequest request) {
		
		return "login/RegistroPersonaC";
	}

    @RequestMapping(value = "/loginCF", method = RequestMethod.POST)
	public String LoginF(@Validated Persona persona,@Validated Usuario usuario, Model model, HttpServletRequest request,RedirectAttributes flash){
		
        persona.setEstado("A");
		personaService.save(persona);

        usuario.setEstado("C");
        usuario.setPersona(persona);
        usuarioService.save(usuario);


		flash.addAttribute("success", "Registro realizado con exito");

		return "redirect:/loginR";
	
	}
}
