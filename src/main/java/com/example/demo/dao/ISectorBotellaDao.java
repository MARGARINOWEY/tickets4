package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.SectorBotella;

public interface ISectorBotellaDao extends CrudRepository<SectorBotella, Long>{
    
    @Query(value = "exec Eventos @id_sector = ?1, @cod = ?2, @accion = ?3", nativeQuery = true)
    String VO(Long id_sector, Integer cod, String accion);
}
