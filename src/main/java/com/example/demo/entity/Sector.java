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
@Table(name = "sector")
@Setter
@Getter
public class Sector implements Serializable {
    
    private static final long serialVersionUID = 2629195288020321924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_sector;
    private String desc_sector;
    private String precio_unitario;
    private Integer asientosDisponibles;
    private String img_sector;
    private Integer filas;
    private Integer columnas;
    private String habilitado;
    private Integer asientosIniciales;
    private String est_mesa_completa;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evento")
    private Evento evento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sector", fetch = FetchType.LAZY)
	private List<Ticket> tickets;
    
}
