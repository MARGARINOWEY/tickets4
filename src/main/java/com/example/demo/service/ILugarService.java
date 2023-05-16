package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Lugar;

public interface ILugarService {
    public List<Lugar> findAll();

	public void save(Lugar lugar);

	public Lugar findOne(Long id);

	public void delete(Long id);
}
