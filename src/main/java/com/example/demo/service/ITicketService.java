package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Ticket;

public interface ITicketService {
    public List<Ticket> findAll();

	public void save(Ticket ticket);

	public Ticket findOne(Long id);

	public void delete(Long id);

	Long imprimirTicket(Integer correo, String accion);
}
