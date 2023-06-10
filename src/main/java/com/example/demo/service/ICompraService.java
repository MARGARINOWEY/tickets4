package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Compra;

public interface ICompraService {
    public List<Compra> findAll();

	public void save(Compra compra);

	public Compra findOne(Long id);

	public void delete(Long id);

	public List<Compra> getCompraXusuario(Long id_usuario);
	
	public Date Date2222();
}
