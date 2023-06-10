package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Compra;

public interface ICompraDao extends CrudRepository<Compra, Long>{
    @Query("SELECT c FROM Compra c LEFT JOIN c.usuario u WHERE u.id_usuario = ?1")
    public List<Compra> getCompraXusuario(Long id_usuario);

    @Query(value = "select getdate()", nativeQuery = true)
    public Date Date();


}
