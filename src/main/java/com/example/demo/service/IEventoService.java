package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Evento;

public interface IEventoService {
    public List<Evento> findAll();

	public void save(Evento evento);

	public Evento findOne(Long id);

	public void delete(Long id);

	public List<Evento> getAllEventosXTipoevento(Long id_tipoEvento);

	public List<Evento> getAllEventosXTipoevento2(Long id_tipoEvento, String accion);

	public void C2(Long id_tipoEvento, String accion);

	public void C3(Long id_tipoEvento, String accion);
}
