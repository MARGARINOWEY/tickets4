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
@Table(name = "evento")
@Setter
@Getter
public class Evento implements Serializable {
    
    private static final long serialVersionUID = 2629195288020321924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_evento;
    private String desc_evento;
    private String habilitado;
    private String img_evento;
    private String img_sala;
    private String estado;
    private String filas;
    private String columnas;
    private Integer descuento_mc;
    private String est_mesa_completa_evento;
    

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_lugar")
    private Lugar lugar;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipoEvento")
    private TipoEvento tipoEvento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento", fetch = FetchType.LAZY)
	private List<Ticket> tickets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento", fetch = FetchType.LAZY)
	private List<Sector> sectors;
}
