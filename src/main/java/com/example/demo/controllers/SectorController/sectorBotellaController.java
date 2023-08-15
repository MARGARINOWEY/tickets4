package com.example.demo.controllers.SectorController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Sector;
import com.example.demo.entity.SectorBotella;
import com.example.demo.service.ISectorBotellaService;
import com.example.demo.service.ISectorService;

@Controller
public class sectorBotellaController {
    
    @Autowired
	private ISectorService sectorService;
    @Autowired
	private ISectorBotellaService sectorBotellaService;

    @RequestMapping(value = "SectorBotellaElegidaV", method = RequestMethod.GET)
    public String SectorBotellaElegidaV(@Validated SectorBotella sectorBotella, Model model) throws Exception {

        return "Sector/SectorBotellaElegidaV";
    }

    @RequestMapping(value = "ListaSectorBotellaElegidaV", method = RequestMethod.GET)
    public String ListaSectorBotellaElegidaV(@Validated SectorBotella sectorBotella, Model model) throws Exception {

        
        model.addAttribute("sectorBotellas", sectorBotellaService.findAll());

        return "Sector/ListaSectorBotellaElegidaV";
    }

    @GetMapping("/loadFragment")
    public String loadFragment(@Validated Persona persona, Model model) throws Exception {
        
        model.addAttribute("sectorBotellas", sectorBotellaService.findAll());
        return "Sector/SectorBotellaElegidaF :: table";
    }

    @RequestMapping(value = "/RegistrarBotellaF/{id_sector}/{num_botella}")
	public String RegistrarBotellaF(@PathVariable("id_sector")Long id_sector, @PathVariable("num_botella") Integer num_botella,Model model){
		
        Sector sector = sectorService.findOne(id_sector);

        SectorBotella sectorBotella = new SectorBotella();
        sectorBotella.setSector(sector);
        sectorBotella.setNum_sectorBotella(num_botella);
        sectorBotella.setEstado_sectorBotella("A");
        sectorBotellaService.save(sectorBotella);

		return "redirect:/SectorBotellaElegidaV";
		
	}

}
