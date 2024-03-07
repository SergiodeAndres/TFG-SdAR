package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TurnoServiceImpl implements es.proyecto.tfgfrontend.service.ITurnoService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";
    @Override
    public List<Turno> buscarTodos() {
        Turno[] turnos = template.getForObject(url+"/turnos", Turno[].class);
        List<Turno> turnosList = null;
        if (turnos != null) {
            turnosList = Arrays.asList(turnos);
        }
        return turnosList;
    }

    @Override
    public void guardarTurno(TurnoRequest turno) {

    }

    @Override
    public void eliminarTurno(TurnoId turnoId) {

    }

    @Override
    public void actualizarTurno(TurnoRequest turno) {

    }

    @Override
    public List<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha, Pageable pageable) {
        return null;
    }

    @Override
    public Turno buscarPorSitioYEmpleado(Sitio sitioID, Empleado empleado, Pageable pageable) {
        return null;
    }

    @Override
    public Turno buscarPorId(TurnoId id) {
        Turno turno = template.getForObject(url+"/turnos/id/"+id.getSitio() + "/" + id.getEmpleado(), Turno.class);
        return turno;
    }
}
