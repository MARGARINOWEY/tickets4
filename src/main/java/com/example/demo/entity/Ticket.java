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
@Table(name = "ticket")
@Setter
@Getter
public class Ticket implements Serializable {
    
    private static final long serialVersionUID = 2629195288020321924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_ticket;
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private Date fecha_uso;
    private String valida;
    private String utilizada;
    private String estado;
    private Integer cod;
    private String crypt;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_compra")
    private Compra compra;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sector")
    private Sector sector;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evento")
    private Evento evento;
    
}
