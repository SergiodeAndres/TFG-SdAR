package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IAtraccionDAO;
import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AtraccionServiceImpl implements IAtraccionService{
    @Autowired
    IAtraccionDAO atraccionDAO;

    @Override
    public List<Atraccion> buscarTodos() {
        return atraccionDAO.buscarTodos();
    }

    @Override
    public boolean guardarAtraccion(Atraccion atraccion) {
        if (atraccionDAO.buscarAtraccionPorId(atraccion.getId()) == null)
        {
            atraccionDAO.guardarAtraccion(atraccion);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarAtraccion(Integer idAtraccion) {
        if (atraccionDAO.buscarAtraccionPorId(idAtraccion) != null)
        {
            atraccionDAO.eliminarAtraccion(idAtraccion);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarAtraccion(Atraccion atraccion) {
        if (atraccionDAO.buscarAtraccionPorId(atraccion.getId()) != null)
        {
            atraccionDAO.guardarAtraccion(atraccion);
        }
    }

    @Override
    public List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID) {
        return atraccionDAO.buscarAtraccionesPorSitio(SitioID);
    }

    @Override
    public Atraccion buscarAtraccionPorId(Integer id) {
        return atraccionDAO.buscarAtraccionPorId(id);
    }
}
