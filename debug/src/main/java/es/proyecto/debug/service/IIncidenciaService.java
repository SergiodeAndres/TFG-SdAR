package es.proyecto.debug.service;

import es.proyecto.debug.model.Incidencia;

import java.util.List;

public interface IIncidenciaService {
    List<Incidencia> buscarTodos();

    void eliminarIncidencia(Integer idIncidencia);
}
