package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Evento;

public interface IEventoService {
    public List<Evento> findAll();

	public void save(Evento evento);

	public Evento findOne(Long id);

	public void delete(Long id);
}
