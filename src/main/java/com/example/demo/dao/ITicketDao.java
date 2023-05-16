package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Ticket;

public interface ITicketDao extends CrudRepository<Ticket, Long>{
    
}
