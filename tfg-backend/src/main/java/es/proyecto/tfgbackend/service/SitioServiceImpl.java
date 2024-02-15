package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.ISitioDAO;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitioServiceImpl implements ISitioService{
    @Autowired
    ISitioDAO sitioDAO;

    @Override
    public List<Sitio> buscarTodos() {
        return sitioDAO.buscarTodos();
    }

    @Override
    public boolean guardarSitio(Sitio sitio) {
        if (sitioDAO.buscarPorId(sitio.getId()) == null)
        {
            sitioDAO.guardarSitio(sitio);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarSitio(Integer idSitio) {
        if (sitioDAO.buscarPorId(idSitio) != null)
        {
            sitioDAO.eliminarSitio(idSitio);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarSitio(Sitio sitio) {
        if (sitioDAO.buscarPorId(sitio.getId()) != null)
        {
            sitioDAO.guardarSitio(sitio);
        }
    }

    @Override
    public Sitio buscarPorId(Integer id) {
        return sitioDAO.buscarPorId(id);
    }
}
