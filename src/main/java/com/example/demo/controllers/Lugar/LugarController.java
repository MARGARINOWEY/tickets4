package com.example.demo.controllers.Lugar;

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

import com.example.demo.service.ILugarService;

@Controller
public class LugarController {
    @Autowired
	private ILugarService lugarService;

    @RequestMapping(value = "/lugarR", method = RequestMethod.GET) // Pagina principal
	public String ComprasAR(Model model, RedirectAttributes flash, HttpServletRequest request) {

		if (request.getSession().getAttribute("persona") != null) {
            
            
			return "Lugar/lugarVista";
		} else {
			return "redirect:loginR";
		}

	}
}
