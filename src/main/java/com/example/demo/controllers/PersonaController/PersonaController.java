package com.example.demo.controllers.PersonaController;

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

import com.example.demo.dao.IPersonaDao;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Usuario;
import com.example.demo.service.IPersonaService;
import com.example.demo.service.IUsuarioService;

@Controller
public class PersonaController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IPersonaService personaService;

    @RequestMapping(value = "/prueba1", method = RequestMethod.GET) // Pagina principal
	public String PersonaM() {
        return "prueba1/prueba1";
	}

    @RequestMapping(value = "/personaR", method = RequestMethod.GET) // Pagina principal
	public String PersonaM(@Validated Persona persona,Model model,RedirectAttributes flash, HttpServletRequest request) {

		if (request.getSession().getAttribute("persona") != null) {
			
			model.addAttribute("usuarios",  usuarioService.findAll());

			return "Persona/personaR";
		} else {
			return "redirect:loginR";
		}

	}

	@RequestMapping(value = "/personaF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String RegistrarPersona(@Validated Persona persona, RedirectAttributes flash, HttpServletRequest request) { //validar los datos capturados (1)

		persona.setEstado("A");
		personaService.save(persona);

		flash.addAttribute("success", "Registro realizado con exito");

		return "redirect:/personaR";
	}
}
