package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TipoEvento;

public interface ITipoEventoDao extends CrudRepository<TipoEvento, Long>{
    
}
