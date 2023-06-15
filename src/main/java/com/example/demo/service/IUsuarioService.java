package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Usuario;

public interface IUsuarioService {
    public List<Usuario> findAll();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	public void delete(Long id);

	public Usuario getUsuarioContraseña(String usuario, String contrasena);

	Long RecuperarContraseña(String ci,String num_celular , String accion);

	Long RecuperarUsuario(String correo, String accion);
}
