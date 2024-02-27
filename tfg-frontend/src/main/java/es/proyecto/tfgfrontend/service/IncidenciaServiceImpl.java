package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class IncidenciaServiceImpl implements es.proyecto.tfgfrontend.service.IIncidenciaService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";
    @Override
    public Page<Incidencia> buscarTodos(Pageable pageable) {
        Incidencia[] incidencias = template.getForObject(url+"/incidencias", Incidencia[].class);
        List<Incidencia> incidenciaList = Arrays.asList(incidencias);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Incidencia> list;

        if (incidenciaList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, incidenciaList.size());
            list = incidenciaList.subList(startItem, toIndex);
        }
        Page<Incidencia> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                incidenciaList.size());
        return page;
    }

    @Override
    public void guardarIncidencia(IncidenciaRequest incidencia) {
        if(incidencia.getId() != null && incidencia.getId() > 0)
        {
            template.put(url+"/incidencias", incidencia);
        }
        else {
            incidencia.setId(0);
            incidencia.setCerrada((byte) 0);
            template.postForObject(url+"/incidencias", incidencia, String.class);
        }
    }

    @Override
    public Page<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID(Integer sitioID, Pageable pageable) {
        Incidencia[] incidencias = template.getForObject(url+"/incidencias/sitio/"+sitioID, Incidencia[].class);
        List<Incidencia> incidenciaList = Arrays.asList(incidencias);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Incidencia> list;

        if (incidenciaList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, incidenciaList.size());
            list = incidenciaList.subList(startItem, toIndex);
        }
        Page<Incidencia> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                incidenciaList.size());
        return page;
    }

    @Override
    public Incidencia buscarPorId(Integer id) {
        Incidencia incidencia = template.getForObject(url+"/incidencias/id/"+id, Incidencia.class);
        return incidencia;
    }
}
