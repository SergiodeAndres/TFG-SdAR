package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.Turno;
import es.proyecto.tfgbackend.model.TurnoId;

import java.time.LocalDate;
import java.util.List;
public interface ITurnoService {
    List<Turno> buscarTodos();

    boolean guardarTurno(Turno turno);

    boolean eliminarTurno(TurnoId turnoId);

    void actualizarTurno(Turno turno);

    List<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha);

    Turno buscarPorSitioYEmpleado(Sitio sitioID, Empleado empleado);

    Turno buscarPorId(TurnoId id);
}
