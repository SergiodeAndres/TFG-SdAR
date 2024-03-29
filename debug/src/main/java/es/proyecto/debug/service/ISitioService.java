package es.proyecto.debug.service;

import es.proyecto.debug.model.Sitio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISitioService {
    Page<Sitio> buscarTodos(Pageable pageable);

    List<Sitio> buscarTodos();

    void guardarSitio(Sitio sitio);

    void eliminarSitio(Integer idSitio);

    Sitio buscarPorId(Integer sitioId);
}
