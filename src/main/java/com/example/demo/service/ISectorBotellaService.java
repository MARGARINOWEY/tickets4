package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SectorBotella;

public interface ISectorBotellaService {
    public List<SectorBotella> findAll();

	public void save(SectorBotella sectorBotella);

	public SectorBotella findOne(Long id);
}
