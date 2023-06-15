package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUsuarioDao;
import com.example.demo.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAll() {
        // TODO Auto-generated method stub
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        // TODO Auto-generated method stub
        usuarioDao.save(usuario);
    }

    @Override
    public Usuario findOne(Long id) {
        // TODO Auto-generated method stub
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        usuarioDao.deleteById(id);
    }

    @Override
    public Usuario getUsuarioContraseña(String usuario, String contrasena) {
        // TODO Auto-generated method stub
        return usuarioDao.getUsuarioContraseña(usuario, contrasena);
    }

    @Override
    public Long RecuperarContraseña(String ci, String num_celular, String accion) {
        // TODO Auto-generated method stub
        return usuarioDao.RecuperarContraseña(ci, num_celular, accion);
    }

    @Override
    public Long RecuperarUsuario(String correo, String accion) {
        // TODO Auto-generated method stub
        return usuarioDao.RecuperarUsuario(correo, accion);
    }

    
    
}
