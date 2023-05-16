package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Evento;

public interface IEventoDao extends CrudRepository<Evento, Long>{
    
}
