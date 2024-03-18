package es.proyecto.debug.service;

import es.proyecto.debug.model.Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class IncidenciaServiceImpl implements es.proyecto.debug.service.IIncidenciaService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public List<Incidencia> buscarTodos() {
        Incidencia[] incidencias = template.getForObject(url+"/incidencias", Incidencia[].class);
        List<Incidencia> incidenciasList = null;
        if (incidencias != null) {
            incidenciasList = Arrays.asList(incidencias);
        }
        return incidenciasList;
    }

    @Override
    public void eliminarIncidencia(Integer idIncidencia) {
        template.delete(url+"/incidencias/"+idIncidencia);
    }
}
