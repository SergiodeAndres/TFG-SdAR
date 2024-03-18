package es.proyecto.debug.service;

import es.proyecto.debug.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TurnoServiceImpl implements es.proyecto.debug.service.ITurnoService {
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";


    @Override
    public List<Turno> buscarTodos() {
        Turno[] turnos = template.getForObject(url+"/incidencias", Turno[].class);
        List<Turno> turnosList = null;
        if (turnos != null) {
            turnosList = Arrays.asList(turnos);
        }
        return turnosList;
    }

    @Override
    public void eliminarTurno(Integer turnoId) {
        template.delete(url+"/turnos/"+turnoId);
    }
}
