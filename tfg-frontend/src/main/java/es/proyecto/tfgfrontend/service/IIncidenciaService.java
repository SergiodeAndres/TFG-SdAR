package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Incidencia;
import es.proyecto.tfgfrontend.model.Sitio;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IIncidenciaService {
    List<Incidencia> buscarTodos();

    void guardarIncidencia(Incidencia incidencia);

    void eliminarIncidencia(Integer idIncidencia);

    void actualizarIncidencia(Incidencia incidencia);

    Page<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Sitio sitioID);

    Incidencia buscarPorId (Integer id);
}
