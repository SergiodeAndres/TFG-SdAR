package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {
    List<Turno> buscarTodos();

    void guardarTurno(TurnoRequest turno);

    void eliminarTurno(TurnoId turnoId);

    void actualizarTurno(TurnoRequest turno);

    List<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha, Pageable pageable);

    Turno buscarPorSitioYEmpleado(Sitio sitioID, Empleado empleado, Pageable pageable);

    Turno buscarPorId(TurnoId id);
}
