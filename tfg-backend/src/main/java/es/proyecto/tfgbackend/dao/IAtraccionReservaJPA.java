package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.AtraccionReserva;
import es.proyecto.tfgbackend.model.AtraccionReservaId;
import es.proyecto.tfgbackend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface IAtraccionReservaJPA extends JpaRepository<AtraccionReserva, AtraccionReservaId> {
    List<AtraccionReserva> findByReservaID(Reserva ReservaID);

    List<AtraccionReserva> findByAtraccionID(Atraccion AtraccionID);

    List<AtraccionReserva> findByReservaID_FechaReserva(LocalDate fechaReserva);

    List<AtraccionReserva> findByAtraccionIDAndReservaID_FechaReserva(Atraccion AtraccionID, LocalDate fechaReserva);
}