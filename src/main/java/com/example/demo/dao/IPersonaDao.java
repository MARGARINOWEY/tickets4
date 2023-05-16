package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long>{
    
}
