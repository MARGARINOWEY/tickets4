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
	public String loginCR(@Validated Persona persona, @Validated Usuario usuario, RedirectAttributes flash,
			HttpServletRequest request) {

		return "login/RegistroPersonaC";
	}

	@RequestMapping(value = "/loginCR2/{id_usuario}/{id_sector}/{num_asientos}") // Pagina principal
	public String loginCR2(@PathVariable("id_usuario") Long id_usuario, @PathVariable("id_sector") Long id_sector,
			@PathVariable("num_asientos") Integer num_asientos, RedirectAttributes flash, HttpServletRequest request,
			Model model) {
		Usuario usuario = usuarioService.findOne(id_usuario);
		Sector sector = sectorService.findOne(id_sector);

		model.addAttribute("usuario", usuario);
		model.addAttribute("sector", sector);
		model.addAttribute("num_asientos", num_asientos);

		return "login/RegistroPersonaC2";
	}

	@RequestMapping(value = "/loginCF", method = RequestMethod.POST)
	public String LoginF(@Validated Persona persona, @Validated Usuario usuario, Model model,
			HttpServletRequest request, RedirectAttributes flash) {

		persona.setEstado("A");
		personaService.save(persona);

		usuario.setEstado("C");
		usuario.setPersona(persona);
		usuarioService.save(usuario);

		flash.addAttribute("success", "Registro realizado con exito");

		return "redirect:/loginR";

	}

	@RequestMapping(value = "/loginCF2", method = RequestMethod.POST)
	public String LoginF(Model model, HttpServletRequest request, RedirectAttributes flash,
			@RequestParam("id_persona") Long id_persona,
			@RequestParam("id_sector") Long id_sector,
			@RequestParam("id_usuario") Long id_usuario,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellido_p") String apellido_p,
			@RequestParam("apellido_m") String apellido_m,
			@RequestParam("celular") String celular,
			@RequestParam("ci") String ci,
			@RequestParam("correo") String correo,
			@RequestParam("num_asientos") Integer num_asientos) {

				
		Sector sector = sectorService.findOne(id_sector);
		Persona persona = personaService.findOne(id_persona);
		persona.setNombre(nombre);
		persona.setApellido_p(apellido_p);
		persona.setApellido_m(apellido_m);
		persona.setNum_celular(celular);
		persona.setCi(ci);
		personaService.save(persona);

		try {

			if (num_asientos == sector.getAsientosIniciales()) {
				System.out.println("entro1");

				Long id_compra = compraService.InsertCompra2(correo, id_sector, num_asientos, "AC1");

				System.out.println(id_compra);

				Compra compra = compraService.findOne(id_compra);

				String[] t1 = compraService.T1(Math.toIntExact(compra.getId_compra()), "T1");
				String[] t1_x = t1[0].split(",");


				if (id_compra != 0) {
					Usuario usuario = usuarioService.findOne(compra.getUsuario().getId_usuario());
					emailService.enviarMensajeRegistro(usuario.getCorreo(),
							"Reserva: " + sector.getEvento().getDesc_evento(), compra.getMonto_pagar(),
							sector.getEvento().getDesc_evento(), "CompraC4Email/" + compra.getId_compra(),
							sector.getDesc_sector(),t1_x[4]);
					return "redirect:/BuscarTickets";
				} else {
					return "redirect:/eventoCR/" + sector.getEvento().getId_evento();
				}

			} else { // ================================================================

				Long id_compra = compraService.InsertCompra2(correo, id_sector, num_asientos, "AC1");				

				Compra compra = compraService.findOne(id_compra);
				if (id_compra != 0) {
					Usuario usuario = usuarioService.findOne(compra.getUsuario().getId_usuario());		
					
					String[] t1 = compraService.T1(Math.toIntExact(compra.getId_compra()), "T1");
					String[] t1_x = t1[0].split(",");

					emailService.enviarMensajeRegistro3(
							num_asientos,
							usuario.getPersona().getNombre() + " " + usuario.getPersona().getApellido_p() + " "
									+ usuario.getPersona().getApellido_m() + ".",
							usuario.getCorreo(),
							"Reserva: " + sector.getEvento().getDesc_evento(),
							compra.getMonto_pagar(),
							sector.getEvento().getDesc_evento(),
							"CompraC4Email/" + compra.getId_compra(),
							sector.getDesc_sector(),t1_x[4]);
					return "redirect:/BuscarTickets";
				} else {
					return "redirect:/eventoCR/" + sector.getEvento().getId_evento();
				}
			}

		} catch (Exception e) {
			return "redirect:/BuscarTickets";
		}
		// try {

		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		// Long id_compra = compraService.InsertCompra(correo, id_sector, "AC1");
		// if (id_compra != 0) {
		// Compra compra = compraService.findOne(id_compra);
		// Usuario usuario =
		// usuarioService.findOne(compra.getUsuario().getId_usuario());
		// //emailService.enviarMensajeRegistro2(usuario.getCorreo(), "Reserva:
		// "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(),
		// sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra(),sector.getDesc_sector());
		// emailService.enviarMensajeRegistro(usuario.getCorreo(), "Reserva:
		// "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(),
		// sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra(),sector.getDesc_sector());
		// return "redirect:/BuscarTickets";
		// }else{
		// return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
		// }

	}
}
