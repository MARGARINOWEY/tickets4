package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IEventoDao;
import com.example.demo.entity.Evento;

@Service
public class EventoServiceImpl implements IEventoService{
    @Autowired
    private IEventoDao eventoDao;

    @Override
    public List<Evento> findAll() {
        // TODO Auto-generated method stub
        return (List<Evento>) eventoDao.findAll();
    }

    @Override
    public void save(Evento evento) {
        // TODO Auto-generated method stub
        eventoDao.save(evento);
    }

    @Override
    public Evento findOne(Long id) {
        // TODO Auto-generated method stub
        return eventoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        eventoDao.deleteById(id);
    }

    @Override
    public List<Evento> getAllEventosXTipoevento(Long id_tipoEvento) {
        // TODO Auto-generated method stub
        return (List<Evento>) eventoDao.getAllEventosXTipoevento(id_tipoEvento);
    }

    @Override
    public List<Evento> getAllEventosXTipoevento2(Long id_tipoEvento, String accion) {
        // TODO Auto-generated method stub
        return (List<Evento>) eventoDao.getAllEventosXTipoevento2(id_tipoEvento, accion);
    }

    @Override
    public void C2(Long id_tipoEvento, String accion) {
        // TODO Auto-generated method stub
        eventoDao.C2(id_tipoEvento, accion);
    }
}
