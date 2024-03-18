package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.*;

import java.time.LocalDate;
import java.util.List;
public interface IAtraccionReservaService {
    List<AtraccionReserva> buscarTodos();

    boolean guardarAtraccionReserva(AtraccionReserva atraccionReserva);

    boolean eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId);

    void actualizarAtraccionReserva(AtraccionReserva atraccionReserva);

    List<AtraccionReserva> buscarPorReservaID(Reserva reservaID);

    List<AtraccionReserva> buscarPorAtraccionID_Id(Integer atraccionID);

    List<AtraccionReserva> buscarPorSitioID_Id(Integer sitioID);
    AtraccionReserva buscarPorId(AtraccionReservaId atraccionReservaId);

    List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID);
}
