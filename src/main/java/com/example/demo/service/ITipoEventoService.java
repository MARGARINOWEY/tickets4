package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TipoEvento;

public interface ITipoEventoService {
    public List<TipoEvento> findAll();

	public void save(TipoEvento tipoEvento);

	public TipoEvento findOne(Long id);

	public void delete(Long id);
}
