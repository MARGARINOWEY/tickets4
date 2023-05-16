package com.example.demo.controllers.EventoController;

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
public class EventoC_Controller {
    @Autowired
	private IEventoService eventoService;
	@Autowired
	private ISectorService sectorService;

    @RequestMapping(value = "/eventoCR", method = RequestMethod.GET) // Pagina principal
	public String PersonaM(Model model,HttpServletRequest request) {

		if (request.getSession().getAttribute("persona") != null) {

			model.addAttribute("eventos", eventoService.findAll());

			return "Evento/EventoC";
		} else {
			return "redirect:loginR";
		}
	}

	@RequestMapping(value = "/eventoCR/{id_evento}")
	public String EditarPersona(@PathVariable("id_evento")Long id_evento, Model model){
		Evento evento = eventoService.findOne(id_evento);

		model.addAttribute("evento", evento);
		model.addAttribute("sectores", sectorService.findAll());
		model.addAttribute("eventof", evento.getFilas());
		model.addAttribute("eventoc", evento.getColumnas());

        //Sector sector = sectorService.findOne(sector.getEvento().getId_evento().valueOf(a));
		//model.addAttribute("persona", persona);
		//model.addAttribute("tipoPersona", tipoPersonaService.findAll());
		//model.addAttribute("carrera", carreraService.findAll());
		//model.addAttribute("detalle", detallePersonaService.findAll());
		
		return "Evento/EventoC2";
		
	}

	@RequestMapping(value = "/eventoCR/{id_evento}/{fila}/{columna}")
	public String EditarPersona(@PathVariable("id_evento")Long id_evento,@PathVariable("fila")String fila,@PathVariable("columna")String columna, Model model){
		Sector sector = sectorService.findOne(sectorService.getSector(id_evento, fila, columna));
		model.addAttribute("sector", sector);

        //Sector sector = sectorService.findOne(sector.getEvento().getId_evento().valueOf(a));
		//model.addAttribute("persona", persona);
		//model.addAttribute("tipoPersona", tipoPersonaService.findAll());
		//model.addAttribute("carrera", carreraService.findAll());
		//model.addAttribute("detalle", detallePersonaService.findAll());
		
		return "Sector/SectorC";
		
	}
}
