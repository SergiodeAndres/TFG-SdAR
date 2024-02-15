package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Sitio;
import java.util.List;
public interface ISitioService {
    List<Sitio> buscarTodos();

    boolean guardarSitio(Sitio sitio);

    boolean eliminarSitio(Integer idSitio);

    void actualizarSitio(Sitio sitio);

    Sitio buscarPorId(Integer id);
}
