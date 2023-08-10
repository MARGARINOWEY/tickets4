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

	void CancelarCompra(Long id_compra, String accion);

	public List<Compra> BuscarTickets(String correo, String accion);

	Integer Validar(String correo,Long id_sector, String accion);

	Long InsertCompra(String correo,Long id_sector, String accion);

	Long obtenerEvento(Integer id_compra, String accion);

	Long obtenerSector(Integer id_compra, String accion);

	public List<Object[]> prueba8(Integer id_evento, String accion);

	public String[] Qr(Integer id_compra, String accion);
}
