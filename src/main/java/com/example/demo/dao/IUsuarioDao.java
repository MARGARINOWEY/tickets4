package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
    @Query("select u from Usuario u where u.usuario_nom = ?1 and u.contrasena = ?2")
    public Usuario getUsuarioContraseña(String usuario, String contrasena);

    @Query(value = "exec Eventos @ci = ?1, @num_celular = ?2, @accion = ?3", nativeQuery = true)
    Long RecuperarContraseña(String ci,String num_celular , String accion);

    @Query(value = "exec Eventos @correo = ?1, @accion = ?2", nativeQuery = true)
    Long RecuperarUsuario(String correo, String accion);
}
