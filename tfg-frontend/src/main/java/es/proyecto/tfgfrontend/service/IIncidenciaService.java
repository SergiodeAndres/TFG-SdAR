package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Incidencia;
import es.proyecto.tfgfrontend.model.IncidenciaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IIncidenciaService {
    Page<Incidencia> buscarTodos(Pageable pageable);

    void guardarIncidencia(IncidenciaRequest incidencia);

    Page<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Integer sitioID, Pageable pageable);

    Incidencia buscarPorId (Integer id);
}
