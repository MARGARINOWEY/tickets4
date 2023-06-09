package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Evento;

public interface IEventoDao extends CrudRepository<Evento, Long>{
    @Query(value = "SELECT * FROM Evento e WHERE e.id_tipo_evento = ?1 AND e.estado = 'A' ", nativeQuery = true)
    public List<Evento> getAllEventosXTipoevento(Long id_tipoEvento);

    @Query(value = "exec Eventos @ID_evento = ?1, @accion = ?2 ", nativeQuery = true)
    public List<Evento> getAllEventosXTipoevento2(Long id_tipoEvento, String accion);

    @Query(value = "exec Eventos @ID_evento = ?1, @accion = ?2 ", nativeQuery = true)
    public void C2(Long id_tipoEvento, String accion);

}
