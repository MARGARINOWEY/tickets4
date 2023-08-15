package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Evento;

public interface ICompraDao extends CrudRepository<Compra, Long>{
    @Query("SELECT c FROM Compra c LEFT JOIN c.usuario u WHERE u.id_usuario = ?1")
    public List<Compra> getCompraXusuario(Long id_usuario);

    @Query(value = "select getdate()", nativeQuery = true)
    public Date Date2222();
    
    @Transactional
    @Modifying
    @Query(value = "exec Eventos @ID_compra = ?1, @accion = ?2 ", nativeQuery = true)
    void CancelarCompra(Long id_compra, String accion);

    @Query(value = "exec Eventos @correo = ?1, @accion = ?2 ", nativeQuery = true)
    public List<Compra> BuscarTickets(String correo, String accion);

    @Query(value = "exec Eventos @correo = ?1, @id_sector = ?2,  @accion = ?3", nativeQuery = true)
    Integer Validar(String correo,Long id_sector, String accion);

    @Query(value = "exec Eventos @correo = ?1, @id_sector = ?2,  @accion = ?3", nativeQuery = true)
    Long InsertCompra(String correo,Long id_sector, String accion);

    @Query(value = "exec Eventos @correo = ?1, @id_sector = ?2, @Asientos_compra = ?3 ,  @accion = ?4", nativeQuery = true)
    Long InsertCompra2(String correo,Long id_sector,Integer asientos_compra, String accion);

    @Query(value = "exec Eventos @id_compra = ?1,  @accion = ?2", nativeQuery = true)
    Long obtenerEvento(Integer id_compra, String accion);

    @Query(value = "exec Eventos @id_compra = ?1,  @accion = ?2", nativeQuery = true)
    Long obtenerSector(Integer id_compra, String accion);

    @Query(value = "exec Eventos @ID_evento = ?1,  @accion = ?2", nativeQuery = true)
    public List<Object[]> prueba8(Integer id_evento, String accion);

    @Query(value = "exec Eventos @ID_compra = ?1,  @accion = ?2", nativeQuery = true)
    public String[] Qr(Integer id_compra, String accion);

    @Query(value = "exec Eventos @ID_compra = ?1,  @accion = ?2", nativeQuery = true)
    public String[] Qr2(Integer id_compra, String accion);



}
