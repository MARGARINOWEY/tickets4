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

import com.example.demo.Archivo;
import com.example.demo.entity.Evento;
import com.example.demo.entity.Sector;
import com.example.demo.entity.SectorBotella;
import com.example.demo.service.IEventoService;
import com.example.demo.service.ILugarService;
import com.example.demo.service.ISectorBotellaService;
import com.example.demo.service.ISectorService;
import com.example.demo.service.ITipoEventoService;

@Controller
public class EventoController {
    Archivo archivo = new Archivo();
    @Autowired
	private ILugarService lugarService;
    @Autowired
	private ITipoEventoService tipoEventoService;
    @Autowired
	private IEventoService eventoService;
	@Autowired
	private ISectorService sectorService;

	@Autowired
	private ISectorBotellaService sectorBotellaService;

    @RequestMapping(value = "/eventoR", method = RequestMethod.GET) // Pagina principal
	public String PersonaM(@Validated Evento evento,Model model,RedirectAttributes flash, HttpServletRequest request) {

		if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("evento", new Evento());
            model.addAttribute("tipoEventos", tipoEventoService.findAll());
            model.addAttribute("lugars", lugarService.findAll());
			model.addAttribute("eventos", eventoService.findAll());

			return "Evento/EventoR";
		} else {
			return "redirect:loginR";
		}

	}

	@RequestMapping(value = "/botellaElegida/{id_sector}/{num_sectorBotella}") // Pagina principal
	public String botellaElegida(@PathVariable("id_sector")Long id_sector, @PathVariable("num_sectorBotella")Integer num_sectorBotella, RedirectAttributes flash) {

		try {
			if (num_sectorBotella == 3) {
				sectorBotellaService.VO(id_sector, num_sectorBotella, "VO");
				flash.addAttribute("success", "https://i.imgur.com/51Pbh9s.png");
				flash.addAttribute("success2", "WHISKEY JOHNNIE WALKER ETIQUETA NEGRA");
				return "redirect:/eventoCR";
			
			}else{
				if (num_sectorBotella == 2) {
					sectorBotellaService.VO(id_sector, num_sectorBotella, "VO");
					flash.addAttribute("success", "https://i.imgur.com/40tZ3Ai.png");
					flash.addAttribute("success2", "WHISKEY JOHNNIE WALKER ETIQUETA ROJA");
					return "redirect:/eventoCR";
					
				}else{
					if (num_sectorBotella == 1) {
						sectorBotellaService.VO(id_sector, num_sectorBotella, "VO");
						flash.addAttribute("success", "https://i.imgur.com/7wCkS0R.png");
						flash.addAttribute("success2", "RON BARCELO");
						return "redirect:/eventoCR";
						
					}else{
						return "redirect:/eventoCR";
					}
				}
			}
			
		} catch (Exception e) {
			return "redirect:/eventoCR";	
		}
		
	}

    @RequestMapping(value = "/eventoF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String registrarLibro(@Validated Evento evento, @RequestParam(name = "archivo", required = false) MultipartFile portada,@RequestParam(name = "archivo2", required = false) MultipartFile portada2 ,RedirectAttributes flash,  HttpServletRequest request){ //validar los datos capturados (1)
		
		if (!portada.isEmpty()) { //ojojojojojojojojojojojojoj
			evento.setImg_evento(archivo.guardarArchivo(portada));
		}

		if (!portada2.isEmpty()) { //ojojojojojojojojojojojojoj
			evento.setImg_sala(archivo.guardarArchivo(portada2));
		}
		evento.setEst_mesa_completa_evento("H");
		evento.setHabilitado("H");
		evento.setEstado("A");
		eventoService.save(evento);
		int a = Integer.parseInt(evento.getFilas());
		int b = Integer.parseInt(evento.getColumnas());
		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++) {
				Sector sector = new Sector();
				sector.setAsientosDisponibles(12);
				sector.setFilas(i);
				sector.setColumnas(j);
				sector.setDesc_sector("0");
				sector.setPrecio_unitario("140");
				sector.setHabilitado("D");
				sector.setEst_mesa_completa("D");
				sector.setEvento(evento);
				sectorService.save(sector);
				Sector sector2 = sectorService.findOne(sector.getId_sector());
				sector2.setAsientosIniciales(sector.getAsientosDisponibles());
				sectorService.save(sector2);
			}
		}
		flash.addAttribute("Sectores creados");
		return "redirect:/eventoR";
	}
}


