package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Atraccion;
import es.proyecto.tfgfrontend.model.AtraccionRequest;
import es.proyecto.tfgfrontend.model.Sitio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAtraccionService {
    Page<Atraccion> buscarTodos(Pageable pageable);
    List<Atraccion> buscarTodos();

    List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID);

    void guardarAtraccion(AtraccionRequest atraccion);

    void eliminarAtraccion(Integer idAtraccion);

    Atraccion buscarAtraccionPorId(Integer id);
}
