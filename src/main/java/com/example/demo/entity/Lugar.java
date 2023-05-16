package com.example.demo.entity;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lugar")
@Setter
@Getter
public class Lugar implements Serializable {
    
    private static final long serialVersionUID = 2629195288020321924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_lugar;
    private String nom_lugar;
    private String direccion;
    private String capacidad_maxima;
    private String estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugar", fetch = FetchType.LAZY)
	private List<Evento> eventos;
}
