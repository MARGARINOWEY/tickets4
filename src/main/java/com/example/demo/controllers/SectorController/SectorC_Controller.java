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

import com.example.demo.entity.Evento;
import com.example.demo.entity.Sector;
import com.example.demo.service.IEventoService;
import com.example.demo.service.ISectorService;

@Controller
public class SectorC_Controller {
    @Autowired
	private IEventoService eventoService;
    @Autowired
	private ISectorService sectorService;

    @RequestMapping(value = "/sectorC/{id_sector}")
	public String EditarPersona(@PathVariable("id_sector")Long id_sector, Model model){
		
		Sector sector = sectorService.findOne(id_sector);
		model.addAttribute("sector", sector);
		//model.addAttribute("tipoPersona", tipoPersonaService.findAll());
		//model.addAttribute("carrera", carreraService.findAll());
		//model.addAttribute("detalle", detallePersonaService.findAll());
		return "Sector/SectorC";
		
	}
}
