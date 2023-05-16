package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Lugar;

public interface ILugarDao extends CrudRepository<Lugar, Long>{
    
}
