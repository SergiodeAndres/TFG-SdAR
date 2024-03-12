package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.model.Sitio;
import es.proyecto.tfgfrontend.model.Turno;
import es.proyecto.tfgfrontend.model.TurnoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {
    List<Turno> buscarTodos();

    void guardarTurno(TurnoRequest turno);

    void eliminarTurno(Integer turnoId);

    Page<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha, Pageable pageable);

    Turno buscarPorId(Integer id);
}
