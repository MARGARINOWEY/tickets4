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

import com.example.demo.entity.Compra;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ICompraService;
import com.example.demo.service.ITicketService;

@Controller
public class CompraA_Controller {
    @Autowired
	private ICompraService compraService;
    @Autowired
	private ITicketService ticketService;

    @RequestMapping(value = "/ComprasAR", method = RequestMethod.GET) // Pagina principal
	public String ComprasAR(Model model, RedirectAttributes flash, HttpServletRequest request) {

		if (request.getSession().getAttribute("persona") != null) {

			model.addAttribute("compras", compraService.findAll());
            model.addAttribute("tickets", ticketService.findAll());

			return "Compra/CompraA";
		} else {
			return "redirect:loginR";
		}

	}

    @RequestMapping(value = "/Compra_editarA/{id_compra}")
	public String Compra_editarA(@PathVariable("id_compra")Long id_compra,Model model){
		Compra compra = compraService.findOne(id_compra);
        compra.setEstado("V");
        compraService.save(compra);

		return "redirect:/ComprasAR";
		
	}
	@RequestMapping(value = "/Cancelar_CA/{id_compra}")
	public String Cancelar_CA(@PathVariable("id_compra")Long id_compra,Model model){
		Compra compra = compraService.findOne(id_compra);
		compraService.CancelarCompra(id_compra, "C6");
        compra.setEstado("X");
        compraService.save(compra);

		return "redirect:/ComprasAR";
		
	}
}
