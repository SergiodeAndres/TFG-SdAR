package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.*;

import java.time.LocalDate;
import java.util.List;

public interface IAtraccionReservaDAO {
    List<AtraccionReserva> buscarTodos();

    void guardarAtraccionReserva(AtraccionReserva atraccionReserva);

    void eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId);

    void actualizarAtraccionReserva(AtraccionReserva atraccionReserva);

    List<AtraccionReserva> buscarPorReservaID(Reserva reservaID);

    List<AtraccionReserva> buscarPorAtraccionID_Id(Integer atraccionID);

    List<AtraccionReserva> buscarPorSitioID_Id(Integer sitioID);

    AtraccionReserva buscarPorId(AtraccionReservaId atraccionReservaId);

    List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID);

}
