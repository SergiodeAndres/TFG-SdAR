package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Incidencia;
import es.proyecto.tfgbackend.model.Sitio;
import java.util.List;

public interface IIncidenciaDAO {
    List<Incidencia> buscarTodos();

    void guardarIncidencia(Incidencia incidencia);

    void eliminarIncidencia(Integer idIncidencia);

    void actualizarIncidencia(Incidencia incidencia);

    List<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Sitio sitioID);

    Incidencia buscarPorId(Integer id);
}
