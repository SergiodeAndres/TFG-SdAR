package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Turno;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.TurnoId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
public interface ITurnoJPA extends JpaRepository<Turno, TurnoId> {
    List<Turno> findBySitioAndFecha(Sitio sitio, LocalDate fecha);
}