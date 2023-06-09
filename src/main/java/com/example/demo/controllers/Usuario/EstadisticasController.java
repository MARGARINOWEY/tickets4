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

import com.example.demo.service.ICompraService;


@Controller
public class EstadisticasController {
    
	@Autowired
	private ICompraService compraService;

    @RequestMapping(value = "/Estadisticas", method = RequestMethod.GET) // Pagina principal
	public String BienvenidoR(HttpServletRequest request,Model model) {
		if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("prueba8", compraService.prueba8(2, "C10"));
			return "login/estadisticas";
		} else {
			return "redirect:loginR";
		}
	}
}
