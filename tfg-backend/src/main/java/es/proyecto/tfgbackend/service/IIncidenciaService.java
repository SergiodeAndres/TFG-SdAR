package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Incidencia;
import es.proyecto.tfgbackend.model.Sitio;

import java.util.List;
public interface IIncidenciaService {
    List<Incidencia> buscarTodos();

    boolean guardarIncidencia(Incidencia incidencia);

    boolean eliminarIncidencia(Integer idIncidencia);

    void actualizarIncidencia(Incidencia incidencia);

    List<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Sitio sitioID);
}
