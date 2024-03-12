package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.ITurnoDAO;
import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoServiceImpl implements ITurnoService{
    @Autowired
    ITurnoDAO turnoDAO;

    @Override
    public List<Turno> buscarTodos() {
        return turnoDAO.buscarTodos();
    }

    @Override
    public boolean guardarTurno(Turno turno) {
        if (turnoDAO.buscarPorId(turno.getId()) == null)
        {
            turnoDAO.guardarTurno(turno);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarTurno(Integer turnoId) {
        if (turnoDAO.buscarPorId(turnoId) != null)
        {
            turnoDAO.eliminarTurno(turnoId);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarTurno(Turno turno) {
        if (turnoDAO.buscarPorId(turno.getId()) != null)
        {
            turnoDAO.guardarTurno(turno);
        }
    }

    @Override
    public List<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha) {
        return turnoDAO.buscarPorSitioYFecha(sitioID, fecha);
    }

    @Override
    public Turno buscarPorSitioYEmpleado(Sitio sitioID, Empleado empleado) {
        return turnoDAO.buscarPorSitioYEmpleado(sitioID, empleado);
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return turnoDAO.buscarPorId(id);
    }
}
