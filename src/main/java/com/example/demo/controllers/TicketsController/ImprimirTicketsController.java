package com.example.demo.controllers.TicketsController;
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

import com.example.demo.entity.Compra;
import com.example.demo.service.ICompraService;
import com.example.demo.service.ITicketService;

@Controller
public class ImprimirTicketsController {
    
    @Autowired
	private ITicketService ticketService;
    @Autowired
	private ICompraService compraService;

    @RequestMapping(value = "/imprimirR/{id_compra}")
	public String EditarPersona(@PathVariable("id_compra")Long id_compra, Model model){
		Compra compra = compraService.findOne(id_compra);

		model.addAttribute("compra", compra);
		model.addAttribute("tickets", ticketService.findAll());

		return "ImprimirTickets/ImprimirTicketsC";
		
	}
}
