package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ITicketDao;
import com.example.demo.entity.Ticket;

@Service
public class TicketServiceImpl implements ITicketService{
    @Autowired
    private ITicketDao ticketDao;

    @Override
    public List<Ticket> findAll() {
        // TODO Auto-generated method stub
        return (List<Ticket>) ticketDao.findAll();
    }

    @Override
    public void save(Ticket ticket) {
        // TODO Auto-generated method stub
        ticketDao.save(ticket);
    }

    @Override
    public Ticket findOne(Long id) {
        // TODO Auto-generated method stub
        return ticketDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        ticketDao.deleteById(id);
    }
    
}
