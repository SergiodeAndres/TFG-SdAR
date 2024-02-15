package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.Turno;
import es.proyecto.tfgbackend.model.TurnoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TurnoDAOImpl implements ITurnoDAO {
    @Autowired
    ITurnoJPA turnoJPA;

    @Override
    public List<Turno> buscarTodos() {
        return turnoJPA.findAll();
    }

    @Override
    public void guardarTurno(Turno turno) {
        turnoJPA.save(turno);
    }

    @Override
    public void eliminarTurno(TurnoId turnoId) {
        turnoJPA.deleteById(turnoId);
    }

    @Override
    public void actualizarTurno(Turno turno) {
        turnoJPA.save(turno);
    }

    @Override
    public List<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha) {
        return turnoJPA.findBySitioAndFecha(sitioID, fecha);
    }

    @Override
    public Turno buscarPorSitioYEmpleado(Sitio sitioID, Empleado empleado) {
        Optional<Turno> optional = turnoJPA.findBySitioAndEmpleado(sitioID, empleado);
        return optional.orElse(null);
    }

    @Override
    public Turno buscarPorId(TurnoId id) {
        Optional<Turno> optional = turnoJPA.findById(id);
        return optional.orElse(null);
    }
}
