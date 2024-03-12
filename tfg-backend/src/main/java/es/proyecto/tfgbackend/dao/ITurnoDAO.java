package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.Turno;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoDAO {
    List<Turno> buscarTodos();

    void guardarTurno(Turno turno);

    void eliminarTurno(Integer turnoId);

    void actualizarTurno(Turno turno);

    List<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha);

    Turno buscarPorSitioYEmpleado(Sitio sitioID, Empleado empleado);

    Turno buscarPorId(Integer id);

}
