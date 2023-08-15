package com.example.demo.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "sectorbotella")
@Setter
@Getter
public class SectorBotella implements Serializable{
    private static final long serialVersionUID = 2629195288020321924L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_sectorBotella;
    private Integer num_sectorBotella;
    private String estado_sectorBotella;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sector")
    private Sector sector;
    
}
