package com.example.demo.controllers.EventoController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

import com.example.demo.entity.Compra;
import com.example.demo.entity.Evento;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Sector;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ICompraService;
import com.example.demo.service.IEmailService;
import com.example.demo.service.IEventoService;
import com.example.demo.service.IPersonaService;
import com.example.demo.service.ISectorService;
import com.example.demo.service.ITicketService;
import com.example.demo.service.ITipoEventoService;
import com.example.demo.service.IUsuarioService;

@Controller
public class EventoC_Controller {
    @Autowired
	private IEventoService eventoService;
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private ITipoEventoService tipoEventoService;
	@Autowired
	private ISectorService sectorService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private ICompraService compraService;
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IEmailService emailService;

    @RequestMapping(value = "/eventoCR", method = RequestMethod.GET) // Pagina principal
	public String PersonaM(Model model,HttpServletRequest request) {

		

		model.addAttribute("eventos", eventoService.findAll());
		model.addAttribute("tipoEventos", tipoEventoService.findAll());

			
		return "Evento/EventoC";
	}

	@RequestMapping(value = "/ComprarTicketsEmailF/{id_sector}", method = RequestMethod.POST) // Pagina principal
	public String eventoCR(Model model,HttpServletRequest request,@PathVariable("id_sector")Long id_sector,@RequestParam("correo")String correo) {
		Sector sector = sectorService.findOne(id_sector);
		if (usuarioService.RecuperarUsuario(correo, "C8") != null) {

			Long id_compra =  compraService.InsertCompra(correo, id_sector, "AC1");

			if (id_compra != 0) {
				Compra compra = compraService.findOne(id_compra);
				Usuario usuario = usuarioService.findOne(compra.getUsuario().getId_usuario());
				emailService.enviarMensajeRegistro(usuario.getCorreo(), "Reserva: "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(), sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra(),sector.getDesc_sector());
				return "redirect:/BuscarTickets";
			}
				return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
			/*Usuario usuario = usuarioService.findOne(usuarioService.RecuperarUsuario(correo, "C8"));	
			Random numAleatorio = new Random();
			System.out.println("entroooooooo");
			if (sector.getAsientosDisponibles() > 0) {
				if (compraService.Validar(correo, sector.getId_sector(), "V1") == 0) {
					System.out.println("entroooooooo2");
					Compra compra = new Compra();
					compra.setFecha_compra(new Date());
					compra.setEstado("NT");
					compra.setEstadoCompraPorcentaje("0");
					compra.setUsuario(usuario);
					int p = Integer.parseInt(sector.getPrecio_unitario());
					int res = p * sector.getAsientosDisponibles();
					compra.setMonto_pagar(res);
					compraService.save(compra);        

					for (int i = 1; i <= sector.getAsientosDisponibles(); i++) {

					Ticket ticket = new Ticket();
					ticket.setCompra(compra);
					ticket.setSector(sector);
					ticket.setCod(numAleatorio.nextInt(900000-100000+1) + 100000);
					ticket.setEstado("A"); // anular el ticket 
					ticket.setValida("P");  // validacion del pago
					ticket.setUtilizada("N"); // si ingreso o no
					ticket.setFecha_uso(compraService.Date2222()); // fecha y hora del ultimo uso del ticket
					ticketService.save(ticket);
					}

					sector.setAsientosDisponibles(0);
					sectorService.save(sector);
					emailService.enviarMensajeRegistro(usuario.getCorreo(), "Reserva: "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(), sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra());
					
					return "redirect:/BuscarTickets";
				}
				
				
					return "redirect:/eventoCR/"+sector.getEvento().getId_evento();		
				
			}*/

		}else{
			Persona persona = new Persona();
			persona.setEstado("C"); 
			personaService.save(persona);
			Usuario usuario = new Usuario();
			usuario.setPersona(persona);
			usuario.setCorreo(correo);
			usuarioService.save(usuario);
			return "redirect:/loginCR2/"+usuario.getId_usuario()+"/"+sector.getId_sector();
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

	@RequestMapping(value = "/eventoCRB/{id_tipoEvento}")
	public String BuscarEvento(@PathVariable("id_tipoEvento")Long id_tipoEvento, Model model){

		//model.addAttribute("eventos", eventoService.getAllEventosXTipoevento(id_tipoEvento));
		model.addAttribute("eventos", eventoService.getAllEventosXTipoevento2(id_tipoEvento, "c1"));
		model.addAttribute("tipoEventos", tipoEventoService.findAll());
		
		return "Evento/EventoC";
		
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
