package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Atraccion;
import es.proyecto.tfgfrontend.model.Sitio;
import java.util.List;

public interface IAtraccionService {
    List<Atraccion> buscarTodos();

    List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID);

    Atraccion buscarAtraccionPorId(Integer id);
}
