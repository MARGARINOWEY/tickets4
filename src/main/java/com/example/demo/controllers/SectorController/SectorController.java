package com.example.demo.controllers.SectorController;
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

import com.example.demo.Archivo;
import com.example.demo.entity.Sector;
import com.example.demo.service.IEventoService;
import com.example.demo.service.ISectorService;

@Controller
public class SectorController {
    Archivo archivo = new Archivo();
    @Autowired
	private ISectorService sectorService;
    @Autowired
	private IEventoService eventoService;

    @RequestMapping(value = "/sectorR", method = RequestMethod.GET) // Pagina principal
	public String PersonaM(@Validated Sector sector,Model model,RedirectAttributes flash, HttpServletRequest request) {
		if (request.getSession().getAttribute("persona") != null) {
            model.addAttribute("eventos", eventoService.findAll());
			return "Sector/SectorR";
		} else {
			return "redirect:loginR";
		}
	}

    @RequestMapping(value = "/sectorF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String RegistrarPersona(@Validated Sector sector, RedirectAttributes flash, HttpServletRequest request,@RequestParam(name = "archivo", required = false) MultipartFile portada ) { //validar los datos capturados (1)
        if (!portada.isEmpty()) { //ojojojojojojojojojojojojoj
			sector.setImg_sector(archivo.guardarArchivo(portada));
		}
        Integer a = sector.getFilas();
        Integer b = sector.getColumnas();
        Integer c = a * b;
        sector.setAsientosDisponibles(String.valueOf(c));
		sectorService.save(sector);
		flash.addAttribute("success", "Registro realizado con exito");
		return "redirect:/sectorR";
	}
}
