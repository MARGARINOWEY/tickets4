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

import com.example.demo.Archivo;
import com.example.demo.entity.Compra;
import com.example.demo.service.ICompraService;

@Controller
public class CompraController {
    Archivo archivo = new Archivo();
    @Autowired
	private ICompraService compraService;

    @RequestMapping(value = "/CompraC/{id_compra}")
	public String eventoAR(@PathVariable("id_compra")Long id_compra,@RequestParam(name = "archivo3", required = false) MultipartFile img_comprobante, Model model){
		Compra compra = compraService.findOne(id_compra);
        if (!img_comprobante.isEmpty()) { //ojojojojojojojojojojojojoj
            compra.setImg_comprobante(archivo.guardarArchivo(img_comprobante));
            compra.setEstado("P");
            compraService.save(compra);
		}

		return "redirect:/ticketCR/"+compra.getId_compra();
		
	}
}
