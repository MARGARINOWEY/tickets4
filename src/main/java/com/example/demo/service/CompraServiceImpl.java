package com.example.demo.service;

import java.util.List;

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
}
