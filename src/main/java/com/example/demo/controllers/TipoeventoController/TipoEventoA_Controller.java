package com.example.demo.controllers.TipoeventoController;

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

import com.example.demo.entity.TipoEvento;
import com.example.demo.service.ITipoEventoService;

@Controller
public class TipoEventoA_Controller {
    @Autowired
	private ITipoEventoService tipoEventoService;

    @RequestMapping(value = "/tipoEventoR", method = RequestMethod.GET) // Pagina principal
	public String ComprasAR(@Validated TipoEvento tipoEvento,Model model, RedirectAttributes flash, HttpServletRequest request) {
		if (request.getSession().getAttribute("persona") != null) {
            
            
			return "TipoEvento/tipoEventoA";
		} else {
			return "redirect:loginR";
		}
	}

    @RequestMapping(value = "/tipoEventoF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String lugarF(@Validated TipoEvento tipoEvento, RedirectAttributes flash, HttpServletRequest request) { //validar los datos capturados (1)

		tipoEvento.setEstado("A");
		tipoEventoService.save(tipoEvento);

		flash.addAttribute("success", "Registro realizado con exito");

		return "redirect:/tipoEventoR";
	}
}
