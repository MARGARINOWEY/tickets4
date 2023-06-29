package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Ticket;

public interface ITicketDao extends CrudRepository<Ticket, Long>{
    @Query(value = "exec Eventos @cod_ticket = ?1, @accion = ?2", nativeQuery = true)
    Long imprimirTicket(Integer correo, String accion);
}
