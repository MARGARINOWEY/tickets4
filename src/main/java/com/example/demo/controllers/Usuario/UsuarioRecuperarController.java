package com.example.demo.controllers.Usuario;

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

import com.example.demo.entity.Compra;
import com.example.demo.entity.Sector;
import com.example.demo.entity.Usuario;
import com.example.demo.service.IUsuarioService;

@Controller
public class UsuarioRecuperarController {
    
    @Autowired
	private IUsuarioService usuarioService;

    @RequestMapping(value = "/recuperarC", method = RequestMethod.GET) // Pagina principal
	public String recuperarC() {
		
		return "login/loginRecuperar";
	}

    @RequestMapping(value = "/contrasenaRecuperarF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String contrasenaRecuperarF(@RequestParam(value = "numero") String numero,@RequestParam(value = "ci") String ci,RedirectAttributes flash, HttpServletRequest request, Model model){
        
        
        if (usuarioService.RecuperarContraseña(ci, numero, "C7") != null) {
            Usuario usuario = usuarioService.findOne(usuarioService.RecuperarContraseña(ci, numero, "C7"));
            model.addAttribute("usuario", usuario);
            return "login/loginRecuperar2";
        }
        
        return "redirect:/recuperarC";
	}

    @RequestMapping(value = "/contrasenaRecuperarF2", method = RequestMethod.POST) // Enviar datos de Registro a Lista
	public String contrasenaRecuperarF2(@RequestParam(value = "usuario_nom") String usuario_nom,@RequestParam(value = "contrasena") String contrasena,RedirectAttributes flash,@RequestParam(value = "id_usuario") Long id_usuario, HttpServletRequest request, Model model){
        
        Usuario usuario = usuarioService.findOne(id_usuario);
        usuario.setUsuario_nom(usuario_nom);
        usuario.setContrasena(contrasena);
        usuarioService.save(usuario);
        
        return "redirect:/";
	}
}

