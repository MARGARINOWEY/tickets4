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
public class EventoA_Controller {
    
    @Autowired
	private IEventoService eventoService;
	@Autowired
	private ISectorService sectorService;

    @RequestMapping(value = "/eventoAR/{id_evento}")
	public String eventoAR(@PathVariable("id_evento")Long id_evento, Model model,HttpServletRequest request){
		Evento evento = eventoService.findOne(id_evento);
		if (request.getSession().getAttribute("persona") != null) {
			
			model.addAttribute("evento", evento);
			model.addAttribute("sectores", sectorService.findAll());
			model.addAttribute("eventof", evento.getFilas());
			model.addAttribute("eventoc", evento.getColumnas());

			return "Evento/EventoA";
		} else {
			return "redirect:/loginR";
		}
		

        //Sector sector = sectorService.findOne(sector.getEvento().getId_evento().valueOf(a));
		//model.addAttribute("persona", persona);
		//model.addAttribute("tipoPersona", tipoPersonaService.findAll());
		//model.addAttribute("carrera", carreraService.findAll());
		//model.addAttribute("detalle", detallePersonaService.findAll());
		
		
		
	}

    @RequestMapping(value = "/habilitar_sector/{id_evento}/{id_sector}")
	public String hSector(@PathVariable("id_sector")Long id_sector,@PathVariable("id_evento")String id_evento, Model model){
		Sector sector = sectorService.findOne(id_sector);

		model.addAttribute("sector", sector);

        if (sector.getHabilitado().equals("D")) {
            sector.setHabilitado("H");
            sectorService.save(sector);
        }else{
            if (sector.getHabilitado().equals("H")) {
                sector.setHabilitado("D");
                sectorService.save(sector);
            }
        }
		return "redirect:/eventoAR/"+id_evento;
		
	}

	@RequestMapping(value = "/eliminiarEventoA/{id_evento}")
	public String eliminiarEventoA(@PathVariable("id_evento")Long id_evento, Model model){
		Evento evento = eventoService.findOne(id_evento);
		model.addAttribute("evento", evento);
		evento.setEstado("X");
		evento.setHabilitado("D");
		eventoService.save(evento);
		return "redirect:/eventoR";
	}

	@RequestMapping(value = "/habilitarEvento/{id_evento}")
	public String habilitarEvento(@PathVariable("id_evento")Long id_evento, Model model){
		Evento evento = eventoService.findOne(id_evento);
		model.addAttribute("evento", evento);
		if (evento.getHabilitado().equals("H")) {
			evento.setHabilitado("D");
			eventoService.save(evento);
		}else{
			evento.setHabilitado("H");
			eventoService.save(evento);
		}
		return "redirect:/eventoR";
	}

}
	
