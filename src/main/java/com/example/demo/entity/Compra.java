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
@Table(name = "compra")
@Setter
@Getter
public class Compra implements Serializable {
    
    private static final long serialVersionUID = 2629195288020321924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_compra;
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private Date fecha_compra;
    private Date fecha_pago;
    private String monto_pagar;
    private String estado;
    private String img_comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra", fetch = FetchType.LAZY)
	private List<Ticket> tickets;
}
