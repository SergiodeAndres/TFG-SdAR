package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface IAtraccionReservaJPA extends JpaRepository<AtraccionReserva, AtraccionReservaId> {
    List<AtraccionReserva> findByReservaID(Reserva ReservaID);

    List<AtraccionReserva> findByAtraccionID_Id(Integer atraccionID);

    List<AtraccionReserva> findByReservaID_SitioID_Id(Integer sitioID);

    List<AtraccionReserva> findByReservaID_FechaReservaAndReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID);
}