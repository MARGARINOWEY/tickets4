package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Sector;

public interface ISectorDao extends CrudRepository<Sector, Long>{
    //@Query("select cc(?1,?2,?3)")
    //public int getSector(Long id_evento,String fila,String columna);
    @Query("select s.id_sector from Sector s left join s.evento e where e.id_evento = ?1 and s.filas = ?2 and s.columnas = ?3")
    public Long getSector(Long id_evento,String fila,String columna);
}
