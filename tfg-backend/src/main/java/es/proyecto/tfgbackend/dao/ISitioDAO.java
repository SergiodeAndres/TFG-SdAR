package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Sitio;

import java.util.List;

public interface ISitioDAO {
    List<Sitio> buscarTodos();

    void guardarSitio(Sitio sitio);

    void eliminarSitio(Integer idSitio);

    void actualizarSitio(Sitio sitio);

    Sitio buscarPorId(Integer id);

}
