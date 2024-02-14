package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.Sitio;
import java.util.List;
public interface IAtraccionDAO {
    List<Atraccion> buscarTodos();

    void guardarAtraccion(Atraccion atraccion);

    void eliminarAtraccion(Integer idAtraccion);

    void actualizarAtraccion(Atraccion atraccion);

    List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID);

}
