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

import com.example.demo.dao.ICompraDao;
import com.example.demo.entity.Compra;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ICompraService;
import com.example.demo.service.IEventoService;
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
			//model.addAttribute("pruebas", eventosDao.GrillaCompraA(1L, "C10"));
			//model.addAttribute("pruebas2", procedimientoAlmacenadoDao.GrillaCompraA2(1L, "C10"));

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

	@RequestMapping(value = "/reiniciarC_A/{id_compra}/{num_compra}")
	public String reiniciarC_A(@PathVariable("id_compra")Long id_compra,@PathVariable("num_compra")Integer num_compra,Model model){
		Compra compra = compraService.findOne(id_compra);

		if (num_compra == 1) {
			compra.setEstadoCompraPorcentaje("1");
			compra.setEstado("NT");
			compra.setImg_comprobante(null);
			compraService.save(compra);
			return "redirect:/ComprasAR";
		}
		if (num_compra == 2) {
			compra.setEstadoCompraPorcentaje("2");
			compra.setImg_comprobante2(null);
			compraService.save(compra);
			return "redirect:/ComprasAR";
		}
		if (num_compra == 3) {
			compra.setEstadoCompraPorcentaje("3");
			compra.setEstado("NT");
			compra.setImg_comprobante3(null);
			compraService.save(compra);
			return "redirect:/ComprasAR";
		}
		return "redirect:/ComprasAR";
		
	}
}
