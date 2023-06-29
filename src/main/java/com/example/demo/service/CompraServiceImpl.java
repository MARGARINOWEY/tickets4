package com.example.demo.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ICompraDao;
import com.example.demo.entity.Compra;

@Service
public class CompraServiceImpl implements ICompraService{
    @Autowired
    private ICompraDao compraDao;

    @Override
    public List<Compra> findAll() {
        // TODO Auto-generated method stub
        return (List<Compra>) compraDao.findAll();
    }

    @Override
    public void save(Compra compra) {
        // TODO Auto-generated method stub
        compraDao.save(compra);
    }

    @Override
    public Compra findOne(Long id) {
        // TODO Auto-generated method stub
        return compraDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        compraDao.deleteById(id);
    }

    @Override
    public List<Compra> getCompraXusuario(Long id_usuario) {
        // TODO Auto-generated method stub
        return compraDao.getCompraXusuario(id_usuario);
    }

    @Override
    public Date Date2222() {
        // TODO Auto-generated method stub
        return compraDao.Date2222();
    }

    @Override
    public void CancelarCompra(Long id_compra, String accion) {
        // TODO Auto-generated method stub
        compraDao.CancelarCompra(id_compra, accion);
    }

    @Override
    public List<Compra> BuscarTickets(String correo, String accion) {
        // TODO Auto-generated method stub
        return compraDao.BuscarTickets(correo, accion);
    }

    @Override
    public Integer Validar(String correo, Long id_sector, String accion) {
        // TODO Auto-generated method stub
        return compraDao.Validar(correo, id_sector, accion);
    }

    @Override
    public Long InsertCompra(String correo, Long id_sector, String accion) {
        // TODO Auto-generated method stub
        return compraDao.InsertCompra(correo, id_sector, accion);
    }

    @Override
    public Long obtenerEvento(Integer id_compra, String accion) {
        // TODO Auto-generated method stub
        return compraDao.obtenerEvento(id_compra, accion);
    }

    @Override
    public Long obtenerSector(Integer id_compra, String accion) {
        // TODO Auto-generated method stub
        return compraDao.obtenerSector(id_compra, accion);
    }
}
