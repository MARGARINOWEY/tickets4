package com.example.demo.controllers.Usuario;

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
import com.example.demo.service.IPersonaService;
import com.example.demo.service.ISectorService;
import com.example.demo.service.ITicketService;
import com.example.demo.service.IUsuarioService;

@Controller
public class UsuarioC_Controller {
    
    @Autowired
	private IUsuarioService usuarioService;
    @Autowired
	private IPersonaService personaService;
	@Autowired
	private ISectorService sectorService;
	@Autowired
	private ICompraService compraService;
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IEmailService emailService;

    @RequestMapping(value = "/loginCR", method = RequestMethod.GET) // Pagina principal
	public String loginCR(@Validated Persona persona,@Validated Usuario usuario,RedirectAttributes flash, HttpServletRequest request) {
		
		return "login/RegistroPersonaC";
	}

	@RequestMapping(value = "/loginCR2/{id_usuario}/{id_sector}") // Pagina principal
	public String loginCR2(@PathVariable("id_usuario")Long id_usuario,@PathVariable("id_sector")Long id_sector,RedirectAttributes flash, HttpServletRequest request,Model model) {
		Usuario usuario = usuarioService.findOne(id_usuario);
		Sector sector = sectorService.findOne(id_sector);

		model.addAttribute("usuario", usuario);
		model.addAttribute("sector", sector);

		return "login/RegistroPersonaC2";
	}

    @RequestMapping(value = "/loginCF", method = RequestMethod.POST)
	public String LoginF(@Validated Persona persona,@Validated Usuario usuario, Model model, HttpServletRequest request,RedirectAttributes flash){
		
        persona.setEstado("A");
		personaService.save(persona);

        usuario.setEstado("C");
        usuario.setPersona(persona);
        usuarioService.save(usuario);


		flash.addAttribute("success", "Registro realizado con exito");

		return "redirect:/loginR";
	
	}

	@RequestMapping(value = "/loginCF2", method = RequestMethod.POST)
	public String LoginF(Model model, HttpServletRequest request,RedirectAttributes flash,
	@RequestParam("id_persona")Long id_persona,
	@RequestParam("id_sector")Long id_sector,
	@RequestParam("id_usuario")Long id_usuario,
	@RequestParam("nombre")String nombre,
	@RequestParam("apellido_p")String apellido_p,
	@RequestParam("apellido_m")String apellido_m,
	@RequestParam("celular")String celular,
	@RequestParam("ci")String ci,
	@RequestParam("correo")String correo
	){
		Sector sector = sectorService.findOne(id_sector);
		Persona persona = personaService.findOne(id_persona);
		persona.setNombre(nombre);
		persona.setApellido_p(apellido_p);
		persona.setApellido_m(apellido_m);
		persona.setNum_celular(celular);
		persona.setCi(ci);
		personaService.save(persona);

		Long id_compra = compraService.InsertCompra(correo, id_sector, "AC1");
		if (id_compra != 0) {
			Compra compra = compraService.findOne(id_compra);
			Usuario usuario = usuarioService.findOne(compra.getUsuario().getId_usuario());
			emailService.enviarMensajeRegistro2(usuario.getCorreo(), "Reserva: "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(), sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra(),sector.getDesc_sector());
			//emailService.enviarMensajeRegistro(usuario.getCorreo(), "Reserva: "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(), sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra(),sector.getDesc_sector());
			return "redirect:/BuscarTickets";
		}else{
			return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
		}
		

		
		//Usuario usuario = usuarioService.findOne(id_usuario);
		//Compra compra = compraService.findOne(id_compra);


		//Random numAleatorio = new Random();

		

		/*if (sector.getAsientosDisponibles() > 0) {
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
			}else{
				System.out.println("entro2");
				return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
			}*/
	
	}
}
