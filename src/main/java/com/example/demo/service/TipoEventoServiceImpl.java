package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ITipoEventoDao;
import com.example.demo.entity.TipoEvento;

@Service
public class TipoEventoServiceImpl implements ITipoEventoService{
    @Autowired
    private ITipoEventoDao tipoEventoDao;

    @Override
    public List<TipoEvento> findAll() {
        // TODO Auto-generated method stub
        return (List<TipoEvento>) tipoEventoDao.findAll();
    }

    @Override
    public void save(TipoEvento tipoEvento) {
        // TODO Auto-generated method stub
        tipoEventoDao.save(tipoEvento);
    }

    @Override
    public TipoEvento findOne(Long id) {
        // TODO Auto-generated method stub
        return tipoEventoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        tipoEventoDao.deleteById(id);
    }
}
