package com.example.demo.controllers.TicketsController;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

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
public class TickeController {
    @Autowired
	private ISectorService sectorService;
    @Autowired
	private IUsuarioService usuarioService;
    @Autowired
	private ICompraService compraService;
    @Autowired
	private ITicketService ticketService;
    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IEmailService emailService;

    @RequestMapping(value = "/ticketF/{id_sector}", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String registrarLibro(
        @PathVariable("id_sector")Long id_sector, 
        @RequestParam(value = "num_asientos") Integer num_asientos,
        @RequestParam("correo")String correo,
        RedirectAttributes flash,  
        HttpServletRequest request){

        Sector sector = sectorService.findOne(id_sector);

        try {
            
            if (num_asientos == sector.getAsientosIniciales()) {
            System.out.println("entro1");
            if (usuarioService.RecuperarUsuario(correo, "C8") != null) {

                Long id_compra =  compraService.InsertCompra2(correo, id_sector, num_asientos, "AC1");

                System.out.println(id_compra);
                
                Compra compra = compraService.findOne(id_compra);
                if (id_compra != 0) {
                    Usuario usuario = usuarioService.findOne(compra.getUsuario().getId_usuario());
                    emailService.enviarMensajeRegistro(usuario.getCorreo(), "Reserva: "+sector.getEvento().getDesc_evento(), compra.getMonto_pagar(), sector.getEvento().getDesc_evento(),"CompraC4Email/"+compra.getId_compra(),sector.getDesc_sector());
                    return "redirect:/BuscarTickets";
                }else{
                    return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
                }
                
                

            }else{
                Persona persona = new Persona();
                persona.setEstado("C"); 
                personaService.save(persona);
                Usuario usuario = new Usuario();
                usuario.setPersona(persona);
                usuario.setCorreo(correo);
                usuarioService.save(usuario);
                return "redirect:/loginCR2/"+usuario.getId_usuario()+"/"+sector.getId_sector()+"/"+num_asientos;
            }
        }else{ //================================================================
            if (usuarioService.RecuperarUsuario(correo, "C8") != null) {

                Long id_compra =  compraService.InsertCompra2(correo, id_sector, num_asientos, "AC1");

                System.out.println("compra"+id_compra);
                
                Compra compra = compraService.findOne(id_compra);
                if (id_compra != 0) {
                    Usuario usuario = usuarioService.findOne(compra.getUsuario().getId_usuario());

                    System.out.println("================================================================");
                    System.out.println(usuario.getCorreo());
                    System.out.println("================================================================");

                    emailService.enviarMensajeRegistro3(
                        num_asientos,
                        usuario.getPersona().getNombre() +" "+ usuario.getPersona().getApellido_p() +" "+ usuario.getPersona().getApellido_m()+ ".",
                        usuario.getCorreo(), 
                        "Reserva: "+sector.getEvento().getDesc_evento(), 
                        compra.getMonto_pagar(), 
                        sector.getEvento().getDesc_evento(),
                        "CompraC4Email/"+compra.getId_compra(),
                        sector.getDesc_sector()
                        );
                    return "redirect:/BuscarTickets";
                }else{
                    return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
                }
                
                

            }else{
                Persona persona = new Persona();
                persona.setEstado("C"); 
                personaService.save(persona);
                Usuario usuario = new Usuario();
                usuario.setPersona(persona);
                usuario.setCorreo(correo);
                usuarioService.save(usuario);
                return "redirect:/loginCR2/"+usuario.getId_usuario()+"/"+sector.getId_sector()+"/"+num_asientos;
            }

        }

        } catch (Exception e) {
            return "redirect:/BuscarTickets";
        }

        

        

	}

    @RequestMapping(value = "/ticketCR/{id_compra}")
	public String eventoAR(@PathVariable("id_compra")Long id_compra, Model model){

        try {
            Compra compra = compraService.findOne(id_compra);

            model.addAttribute("compra", compra);
            model.addAttribute("tickets", ticketService.findAll());

            // String[] qrData = compraService.Qr(Math.toIntExact(compra.getId_compra()), "QR1");
            // String[] qrData2 = qrData[0].split(",");        

            String[] qrData3 = compraService.Qr2(Math.toIntExact(compra.getId_compra()), "QR2");
            String[] qrData4 = qrData3[0].split(",");

            

            if (qrData4[13].equals("qrs")) {
                model.addAttribute("qrTicket", "qr5.png");
                model.addAttribute("qrTicket2", "qr4.png");
            }else{
                model.addAttribute("qrTicket", qrData4[13]);
            }

            String[] t1 = compraService.T1(Math.toIntExact(compra.getId_compra()), "T1");
            String[] t1_x = t1[0].split(",");
            
            model.addAttribute("dias", t1_x[4]);



            return "Ticket/ticketC";    
        } catch (Exception e) {
            
            return"redirect:/";

        }

		
		
	}

    @RequestMapping(value = "/ticketF2/{id_sector}")
	public String ticketF2(@PathVariable("id_sector")Long id_sector, Model model, HttpServletRequest request){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario"); //Recuperar Usuario de session
		usuario = usuarioService.findOne(usuario.getId_usuario()); // Recuperamos Usuario de BD
        Sector sector = sectorService.findOne(id_sector);
        Random numAleatorio = new Random();

        if (sector.getAsientosDisponibles() > 0) {
            Compra compra = new Compra();
            compra.setFecha_compra(new Date());
            compra.setEstado("NT");
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
            return "redirect:/ticketCR/"+compra.getId_compra();
        }else{
            return "redirect:/eventoCR/"+sector.getEvento().getId_evento();
        }

	}

    @RequestMapping(value = "/sectorEditar/{id_sector}", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String sectorEditar(@PathVariable("id_sector")Long id_sector, @RequestParam(value = "desc_sector") String desc_sector,@RequestParam(value = "precio_unitario") String precio_unitario,@RequestParam(value = "asientosDisponibles") Integer asientosDisponibles, RedirectAttributes flash,  HttpServletRequest request){ //validar los datos capturados (1)
        Sector sector = sectorService.findOne(id_sector);
        sector.setDesc_sector(desc_sector);
        sector.setPrecio_unitario(precio_unitario);
        sector.setAsientosDisponibles(asientosDisponibles);
        sector.setHabilitado("H");
        sectorService.save(sector);


        return "redirect:/eventoAR/"+sector.getEvento().getId_evento();

	}
}
