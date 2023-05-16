package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Sector;

public interface ISectorService {
    public List<Sector> findAll();

	public void save(Sector sector);

	public Sector findOne(Long i);

	public void delete(Long id);

	public Long getSector(Long id_evento,String fila,String columna);
}
