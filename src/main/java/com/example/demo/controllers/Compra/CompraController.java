package com.example.demo.controllers.Compra;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
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
	public String CompraC(@PathVariable("id_compra")Long id_compra,@RequestParam(name = "archivo3", required = false) MultipartFile img_comprobante, Model model,@RequestParam("nro_comprobante")String nro_comprobante) throws IOException{
		Compra compra = compraService.findOne(id_compra);
        if (!img_comprobante.isEmpty()) { //ojojojojojojojojojojojojoj
            byte[] bytes = img_comprobante.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            compra.setImg_comprobante(base64Image);
            compra.setNro_comprobante(nro_comprobante);
            compra.setFecha_pago(compraService.Date2222());
            compra.setEstado("P");
            compra.setEstadoCompraPorcentaje("2");
            compraService.save(compra);
		}
		return "redirect:/ticketCR/"+compra.getId_compra();	
	}
    //----------------------------------------------------------------
    @RequestMapping(value = "/CompraC2/{id_compra}")
	public String CompraC2(@PathVariable("id_compra")Long id_compra,@RequestParam(name = "archivo4", required = false) MultipartFile img_comprobante2, Model model,@RequestParam("nro_comprobante2")String nro_comprobante2) throws IOException{
		Compra compra = compraService.findOne(id_compra);
        if (!img_comprobante2.isEmpty()) { //ojojojojojojojojojojojojoj
            byte[] bytes2 = img_comprobante2.getBytes();
            String base64Image2 = Base64.getEncoder().encodeToString(bytes2);
            compra.setImg_comprobante2(base64Image2);
            compra.setNro_comprobante2(nro_comprobante2);
            compra.setFecha_pago(new Date());
            compra.setEstado("P");
            compraService.save(compra);
		}
		return "redirect:/ticketCR/"+compra.getId_compra();
	}
    //--------------------------------------------------------------
    @RequestMapping(value = "/CompraC3/{id_compra}")
	public String CompraC3(@PathVariable("id_compra")Long id_compra,@RequestParam(name = "archivo5", required = false) MultipartFile img_comprobante, Model model,@RequestParam("nro_comprobante3")String nro_comprobante3) throws IOException{
		Compra compra = compraService.findOne(id_compra);
        if (!img_comprobante.isEmpty()) { //ojojojojojojojojojojojojoj
            byte[] bytes = img_comprobante.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            compra.setImg_comprobante3(base64Image);
            compra.setNro_comprobante3(nro_comprobante3);
            compra.setFecha_pago(new Date());
            compra.setEstado("P");
            compraService.save(compra);
		}
		return "redirect:/ticketCR/"+compra.getId_compra();
	}

    @RequestMapping(value = "/CompraC4Email/{id_compra}/{num_btn_email}")
	public String CompraC4Email(@PathVariable("id_compra")Long id_compra, @PathVariable("num_btn_email")String num_btn_email, Model model) throws IOException{
		Compra compra = compraService.findOne(id_compra);
            if (num_btn_email.equals("1")) {
                if (compra.getEstadoCompraPorcentaje().equals("0")) {
                    compra.setEstadoCompraPorcentaje("1");
                    compraService.save(compra);
                    return "redirect:/ticketCR/"+compra.getId_compra();
                }else {
                    if (compra.getEstadoCompraPorcentaje() != "1") {
                        if (compra.getEstado().equals("NT")) {
                            compra.setEstadoCompraPorcentaje(num_btn_email);
                            compraService.save(compra);
                        }
                        return "redirect:/ticketCR/"+compra.getId_compra();
                    }
                }
                

            }else {
                if (num_btn_email.equals("3")) {
                 
                    if (compra.getEstadoCompraPorcentaje().equals("0")) {
                        compra.setEstadoCompraPorcentaje("3");
                        compraService.save(compra);
                        return "redirect:/ticketCR/"+compra.getId_compra();
                    }
                    if (compra.getEstadoCompraPorcentaje() != "3") {
                        if (compra.getEstado().equals("NT")) {
                            compra.setEstadoCompraPorcentaje(num_btn_email);
                            compraService.save(compra);
                        }
                        return "redirect:/ticketCR/"+compra.getId_compra();
                    }
                    
                }
            }
		return "redirect:/ticketCR/"+compra.getId_compra();
	}
}
