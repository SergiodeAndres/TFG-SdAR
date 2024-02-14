package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.Sitio;

import java.util.List;
public interface IAtraccionService {
    List<Atraccion> buscarTodos();

    boolean guardarAtraccion(Atraccion atraccion);

    boolean eliminarAtraccion(Integer idAtraccion);

    void actualizarAtraccion(Atraccion atraccion);

    List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID);
}
