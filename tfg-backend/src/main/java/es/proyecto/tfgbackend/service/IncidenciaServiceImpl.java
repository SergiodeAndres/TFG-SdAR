package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IIncidenciaDAO;
import es.proyecto.tfgbackend.model.Incidencia;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService{
    @Autowired
    IIncidenciaDAO incidenciaDAO;

    @Override
    public List<Incidencia> buscarTodos() {
        return incidenciaDAO.buscarTodos();
    }

    @Override
    public boolean guardarIncidencia(Incidencia incidencia) {
        if (incidenciaDAO.buscarPorId(incidencia.getId()) == null)
        {
            incidenciaDAO.guardarIncidencia(incidencia);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarIncidencia(Integer idIncidencia) {
        if (incidenciaDAO.buscarPorId(idIncidencia) != null)
        {
            incidenciaDAO.eliminarIncidencia(idIncidencia);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarIncidencia(Incidencia incidencia) {
        if (incidenciaDAO.buscarPorId(incidencia.getId()) != null)
        {
            incidenciaDAO.guardarIncidencia(incidencia);
        }
    }

    @Override
    public List<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Sitio sitioID) {
        return incidenciaDAO.buscarPorCerradaFalsoYDniEmpleado_SitioID(sitioID);
    }

    @Override
    public Incidencia buscarPorId(Integer id) {
        return incidenciaDAO.buscarPorId(id);
    }
}
