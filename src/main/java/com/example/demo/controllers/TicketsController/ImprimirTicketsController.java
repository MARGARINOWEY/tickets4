package com.example.demo.controllers.TicketsController;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Desencriptar;
import com.example.demo.EncriptarUrl;
import com.example.demo.entity.Compra;
import com.example.demo.entity.Ticket;
import com.example.demo.service.ICompraService;
import com.example.demo.service.ITicketService;

@Controller
public class ImprimirTicketsController {
    
	EncriptarUrl encriptarUrl = new EncriptarUrl();
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

	@GetMapping("/encrypt/{id_ticket}")
    public String encryptUrl(@PathVariable String id_ticket) throws Exception {
        String encryptedUrl = EncriptarUrl.encrypt(id_ticket);


        return "redirect:/imprimirR2/"+encryptedUrl;
    }

	@RequestMapping(value = "/imprimirR2/{cod_ticket}")
	public String imprimirR2(@PathVariable("cod_ticket")String cod_ticket, Model model)throws Exception{

		try {
			String decryptedUrl = EncriptarUrl.decrypt(cod_ticket);
			Integer cod_ticket2 = Integer.parseInt(decryptedUrl);
			Ticket ticket = ticketService.findOne(ticketService.imprimirTicket(cod_ticket2, "I1"));

			model.addAttribute("ticket", ticket);
			//String plaintext = "164240";
			//int[] key = {2, 0, 1};
			//String a = Desencriptar.encrypt(plaintext, key);
			//System.out.println(a);

			return "ImprimirTickets/imprimirTicket";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/";
		}

		
		
	}
}
