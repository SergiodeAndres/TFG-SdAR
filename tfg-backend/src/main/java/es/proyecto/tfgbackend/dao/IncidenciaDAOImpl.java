package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Incidencia;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class IncidenciaDAOImpl implements IIncidenciaDAO {
    @Autowired
    IIncidenciaJPA incidenciaJPA;

    @Override
    public List<Incidencia> buscarTodos() {
        return incidenciaJPA.findAll();
    }

    @Override
    public void guardarIncidencia(Incidencia incidencia) {
        incidenciaJPA.save(incidencia);
    }

    @Override
    public void eliminarIncidencia(Integer idIncidencia) {
        incidenciaJPA.deleteById(idIncidencia);
    }

    @Override
    public void actualizarIncidencia(Incidencia incidencia) {
        incidenciaJPA.save(incidencia);
    }

    @Override
    public List<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Sitio sitioID) {
        return incidenciaJPA.findByCerradaFalseAndDniEmpleado_SitioID(sitioID);
    }
}
