package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SitioDAOImpl implements ISitioDAO {
    @Autowired
    ISitioJPA sitioJPA;

    @Override
    public List<Sitio> buscarTodos() {
        return sitioJPA.findAll();
    }

    @Override
    public void guardarSitio(Sitio sitio) {
        sitioJPA.save(sitio);
    }

    @Override
    public void eliminarSitio(Integer idSitio) {
        sitioJPA.deleteById(idSitio);
    }

    @Override
    public void actualizarSitio(Sitio sitio) {
        sitioJPA.save(sitio);
    }
}
