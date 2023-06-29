package com.example.demo.controllers.Compra;
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
import com.example.demo.service.ICompraService;
import com.example.demo.service.ITicketService;

@Controller
public class CompraC_Controller {

	@Autowired
	private ICompraService compraService;
	@Autowired
	private ITicketService ticketService;

    @RequestMapping(value = "/BuscarTickets", method = RequestMethod.GET) // Pagina principal
	public String BuscarTickets(HttpServletRequest request,Model model) {
		model.addAttribute("tickets", ticketService.findAll());
		return "Compra/buscarCompra";
	}

	@RequestMapping(value = "/BuscarTicketsF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String BuscarTicketsF(@RequestParam(name = "correo") String correo ,RedirectAttributes flash,HttpServletRequest request, Model model){ //validar los datos capturados (1)
		
		model.addAttribute("compras", compraService.BuscarTickets(correo, "C9"));
		model.addAttribute("tickets", ticketService.findAll());

		return "Compra/buscarCompra";
	}

    
}
