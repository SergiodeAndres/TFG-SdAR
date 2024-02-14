package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Reserva;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface IReservaJPA extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findByIdAndEmailContacto(Integer id, String email);

    List<Reserva> findBySitioIDAndFechaReserva(Sitio sitioID, LocalDate fechaReserva);
}