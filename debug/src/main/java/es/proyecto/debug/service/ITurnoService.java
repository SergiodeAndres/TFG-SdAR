package es.proyecto.debug.service;

import es.proyecto.debug.model.Turno;

import java.util.List;

public interface ITurnoService {
    List<Turno> buscarTodos();

    void eliminarTurno(Integer turnoId);
}
