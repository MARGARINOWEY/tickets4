package com.example.demo.controllers.Compra;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.example.demo.EncriptarUrl;
import com.example.demo.entity.Compra;
import com.example.demo.entity.Ticket;
import com.example.demo.service.ICompraService;
import com.example.demo.service.ITicketService;

@Controller
public class VerTicketsImprimirController {
    
    @Autowired
	private ICompraService compraService;
    @Autowired
	private ITicketService ticketService;

    @RequestMapping(value = "/VerTicketsImprimir/{id_compra}")
	public String ComprasAR(Model model, RedirectAttributes flash, HttpServletRequest request,@PathVariable("id_compra")String id_compra) throws Exception {
        
        try {
            String decryptedUrl = EncriptarUrl.decrypt(id_compra);
            Long id_com = Long.parseLong(decryptedUrl);

            Compra compra = compraService.findOne(id_com);

            for (Ticket ticket : compraService.findOne(id_com).getTickets()) {

                if(ticket.getCrypt() == null){
                    String encryptedUrl = EncriptarUrl.encrypt(Long.toString(ticket.getCod()));
                    ticket.setCrypt(encryptedUrl);
                    ticketService.save(ticket);
                }
            }

            model.addAttribute("compra", compra);
            model.addAttribute("tickets", ticketService.findAll());

            //model.addAttribute("compras", compraService.findAll());
            //model.addAttribute("tickets", ticketService.findAll());
            //model.addAttribute("pruebas", eventosDao.GrillaCompraA(1L, "C10"));
            //model.addAttribute("pruebas2", procedimientoAlmacenadoDao.GrillaCompraA2(1L, "C10"));

        
            return "ImprimirTickets/TicketsImprimirFinal";
        } catch (Exception e) {
            return "redirect:/eventoCR";
        }
        
        
		

	}
}
