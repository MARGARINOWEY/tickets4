package com.example.demo.controllers.TicketsController;

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
import com.example.demo.service.ICompraService;
import com.example.demo.service.IUsuarioService;

@Controller
public class TicketsC_Controller {
    
    @Autowired
	private ICompraService compraService;
    @Autowired
	private IUsuarioService usuarioService;

    @RequestMapping(value = "/ComprasR", method = RequestMethod.GET) // Pagina principal
	public String PersonaM(Model model, RedirectAttributes flash, HttpServletRequest request) {

		if (request.getSession().getAttribute("persona") != null) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario"); //Recuperar Usuario de session
		    usuario = usuarioService.findOne(usuario.getId_usuario()); // Recuperamos Usuario de BD

			model.addAttribute("compras", compraService.getCompraXusuario(usuario.getId_usuario()));

			return "Compra/CompraC";
		} else {
			return "redirect:loginR";
		}

	}
}
