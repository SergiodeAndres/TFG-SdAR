package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Sitio;
import es.proyecto.tfgfrontend.model.Turno;
import es.proyecto.tfgfrontend.model.TurnoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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
        if(turno.getId() != null && turno.getId() > 0)
        {
            template.put(url+"/turnos", turno);
        }
        else {
            turno.setId(0);
            template.postForObject(url+"/turnos", turno, String.class);
        }
    }

    @Override
    public void eliminarTurno(Integer turnoId) {
        template.delete(url+"/turnos/eliminar/"+turnoId);
    }

    @Override
    public Page<Turno> buscarPorSitioYFecha(Sitio sitioID, LocalDate fecha, Pageable pageable) {
        Turno[] turnos = template.getForObject(url+"/turnos/sitio-fecha/"+sitioID.getId()+"/"+fecha, Turno[].class);
        List<Turno> turnosList = Arrays.asList(turnos);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Turno> list;

        if (turnosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, turnosList.size());
            list = turnosList.subList(startItem, toIndex);
        }
        Page<Turno> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                turnosList.size());
        return page;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turno = template.getForObject(url+"/turnos/id/"+id, Turno.class);
        return turno;
    }
}
