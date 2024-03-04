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

    List<AtraccionReserva> buscarPorAtraccionID(Atraccion atraccionID);

    AtraccionReserva buscarPorReservaIDYAtraccionID(Reserva reservaID, Atraccion atraccionID);

    List<AtraccionReserva> buscarPorReservaID_FechaReserva(LocalDate fechaReserva);

    List<AtraccionReserva> buscarPorAtraccionIDYReservaID_FechaReserva(Atraccion atraccionId, LocalDate fechaReserva);

    AtraccionReserva buscarPorId(AtraccionReservaId atraccionReservaId);

    List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID);

}
