package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface IAtraccionReservaJPA extends JpaRepository<AtraccionReserva, AtraccionReservaId> {
    List<AtraccionReserva> findByReservaID(Reserva ReservaID);

    List<AtraccionReserva> findByAtraccionID(Atraccion AtraccionID);

    AtraccionReserva findByReservaIDAndAtraccionID(Reserva reservaID, Atraccion AtraccionID);

    List<AtraccionReserva> findByReservaID_FechaReserva(LocalDate fechaReserva);

    List<AtraccionReserva> findByAtraccionIDAndReservaID_FechaReserva(Atraccion AtraccionID, LocalDate fechaReserva);

    List<AtraccionReserva> findByReservaID_FechaReservaAndReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID);
}