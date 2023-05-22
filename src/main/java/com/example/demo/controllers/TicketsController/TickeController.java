package com.example.demo.controllers.TicketsController;

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
import com.example.demo.entity.Sector;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ICompraService;
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

    @RequestMapping(value = "/ticketF/{id_sector}", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String registrarLibro(@PathVariable("id_sector")Long id_sector, @RequestParam(value = "num_asientos") Integer num_asientos, RedirectAttributes flash,  HttpServletRequest request){ //validar los datos capturados (1)
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario"); //Recuperar Usuario de session
		usuario = usuarioService.findOne(usuario.getId_usuario()); // Recuperamos Usuario de BD
        Sector sector = sectorService.findOne(id_sector);
        int x = sector.getAsientosDisponibles();
        Random numAleatorio = new Random();
        if (num_asientos <= x) {

            int z = x - num_asientos;
            sector.setAsientosDisponibles(z);
            sectorService.save(sector);

            Compra compra = new Compra();
            compra.setFecha_compra(new Date());
            compra.setEstado("NT");
            compra.setUsuario(usuario);
            int a = num_asientos;  
            int b = Integer.parseInt(sector.getPrecio_unitario());
            int c = a*b;
            compra.setMonto_pagar(String.valueOf(c));
            compraService.save(compra);

            for (int i = 1; i <= num_asientos; i++) {
                Ticket ticket = new Ticket();
                ticket.setCompra(compra);
                ticket.setSector(sector);
                ticket.setCod(numAleatorio.nextInt(900000-100000+1) + 100000);
                ticket.setEstado("A"); // anular el ticket 
                ticket.setValida("P");  // validacion del pago
                ticket.setUtilizada("N"); // si ingreso o no
                ticket.setFecha_uso(new Date()); // fecha y hora del ultimo uso del ticket
                ticketService.save(ticket);
            }
            

            flash.addAttribute("success", "Registro realizado con exito");
            return "redirect:/ticketCR/"+compra.getId_compra();
        }

        return "redirect:/eventoCR/"+sector.getEvento().getId_evento();

	}

    @RequestMapping(value = "/ticketCR/{id_compra}")
	public String eventoAR(@PathVariable("id_compra")Long id_compra, Model model){
		Compra compra = compraService.findOne(id_compra);

		model.addAttribute("compra", compra);
		model.addAttribute("tickets", ticketService.findAll());
		
		return "Ticket/ticketC";
		
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
