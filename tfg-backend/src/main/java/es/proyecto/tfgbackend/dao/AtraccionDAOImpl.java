package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class AtraccionDAOImpl implements IAtraccionDAO {
    @Autowired
    IAtraccionJPA atraccionJPA;

    @Override
    public List<Atraccion> buscarTodos() {
        return atraccionJPA.findAll();
    }

    @Override
    public void guardarAtraccion(Atraccion atraccion) {
        atraccionJPA.save(atraccion);
    }

    @Override
    public void eliminarAtraccion(Integer idAtraccion) {
        atraccionJPA.deleteById(idAtraccion);
    }

    @Override
    public void actualizarAtraccion(Atraccion atraccion) {
        atraccionJPA.save(atraccion);
    }

    @Override
    public List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID) {
        return atraccionJPA.findBySitioID(SitioID);
    }
}
