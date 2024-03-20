package es.proyecto.debug.service;

import es.proyecto.debug.model.Atraccion;
import es.proyecto.debug.model.AtraccionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAtraccionService {
    Page<Atraccion> buscarTodos(Pageable pageable);

    List<Atraccion> buscarTodos();

    void guardarAtraccion(AtraccionRequest atraccion);

    void eliminarAtraccion(Integer idAtraccion);

    Atraccion buscarAtraccionPorId(Integer id);
}
