package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Persona;

public interface IPersonaService {
    public List<Persona> findAll();

	public void save(Persona persona);

	public Persona findOne(Long id);

	public void delete(Long id);
}
