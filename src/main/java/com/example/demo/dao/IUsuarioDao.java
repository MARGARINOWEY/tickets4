package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
    @Query("select u from Usuario u where u.usuario_nom = ?1 and u.contrasena = ?2")
    public Usuario getUsuarioContraseña(String usuario, String contrasena);
}
