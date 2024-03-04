package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.*;

import java.time.LocalDate;
import java.util.List;

public interface IAtraccionReservaService {
    List<AtraccionReserva> buscarTodos();

    void guardarAtraccionReserva(AtraccionReservaRequest atraccionReserva);

    void eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId);

    List<AtraccionReserva> buscarPorReservaID(Reserva reservaID);

    List<AtraccionReserva> buscarPorAtraccionID(Atraccion atraccionID);

    AtraccionReserva buscarPorReservaIDYAtraccionID(Reserva reservaID, Atraccion atraccionID);

    List<AtraccionReserva> buscarPorReservaID_FechaReserva(LocalDate fechaReserva);

    List<AtraccionReserva> buscarPorAtraccionIDYReservaID_FechaReserva(Atraccion atraccionId, LocalDate fechaReserva);

    List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID);
}
