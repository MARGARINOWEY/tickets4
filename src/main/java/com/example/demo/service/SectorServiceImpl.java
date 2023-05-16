package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ISectorDao;
import com.example.demo.entity.Sector;

@Service
public class SectorServiceImpl implements ISectorService{
    @Autowired
    private ISectorDao sectorDao;

    @Override
    public List<Sector> findAll() {
        // TODO Auto-generated method stub
        return (List<Sector>) sectorDao.findAll();
    }

    @Override
    public void save(Sector sector) {
        // TODO Auto-generated method stub
        sectorDao.save(sector);
    }

    @Override
    public Sector findOne(Long id) {
        // TODO Auto-generated method stub
        return sectorDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        sectorDao.deleteById(id);
    }

    @Override
    public Long getSector(Long id_evento, String fila, String columna) {
        // TODO Auto-generated method stub
        return sectorDao.getSector(id_evento, fila, columna);
    }
}
