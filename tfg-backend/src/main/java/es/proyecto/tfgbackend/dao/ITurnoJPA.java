package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Turno;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface ITurnoJPA extends JpaRepository<Turno, Integer> {
    List<Turno> findBySitioAndFecha(Sitio sitio, LocalDate fecha);

    Optional<Turno> findBySitioAndEmpleado(Sitio sitio, Empleado empleado);
}