package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ISectorBotellaDao;
import com.example.demo.entity.SectorBotella;

@Service
public class SectorBotellaServiceImpl implements ISectorBotellaService{
    
    @Autowired
    private ISectorBotellaDao sectorBotellaDao;

    @Override
    public List<SectorBotella> findAll() {
        // TODO Auto-generated method stub
        return (List<SectorBotella>) sectorBotellaDao.findAll();
    }

    @Override
    public void save(SectorBotella sectorBotella) {
        // TODO Auto-generated method stub
        sectorBotellaDao.save(sectorBotella);
    }

    @Override
    public SectorBotella findOne(Long id) {
        // TODO Auto-generated method stub
        return sectorBotellaDao.findById(id).orElse(null);
    }

    @Override
    public String VO(Long id_sector, Integer cod, String accion) {
        // TODO Auto-generated method stub
        return sectorBotellaDao.VO(id_sector, cod, accion);
    }

    

    
}
