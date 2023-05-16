package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ILugarDao;
import com.example.demo.entity.Lugar;

@Service
public class LugarServiceImpl implements ILugarService{
    @Autowired
    private ILugarDao lugarDao;

    @Override
    public List<Lugar> findAll() {
        // TODO Auto-generated method stub
        return (List<Lugar>) lugarDao.findAll();
    }

    @Override
    public void save(Lugar lugar) {
        // TODO Auto-generated method stub
        lugarDao.save(lugar);
    }

    @Override
    public Lugar findOne(Long id) {
        // TODO Auto-generated method stub
        return lugarDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        lugarDao.deleteById(id);
    }
    
}
