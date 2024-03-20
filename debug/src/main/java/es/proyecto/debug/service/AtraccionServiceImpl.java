package es.proyecto.debug.service;

import es.proyecto.debug.model.Atraccion;
import es.proyecto.debug.model.AtraccionRequest;
import es.proyecto.debug.model.Empleado;
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
public class AtraccionServiceImpl implements es.proyecto.debug.service.IAtraccionService
{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public Page<Atraccion> buscarTodos(Pageable pageable) {
        Atraccion[] atracciones = template.getForObject(url+"/atracciones", Atraccion[].class);
        List<Atraccion> atraccionesList = Arrays.asList(atracciones);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Atraccion> list;

        if (atraccionesList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, atraccionesList.size());
            list = atraccionesList.subList(startItem, toIndex);
        }
        Page<Atraccion> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                atraccionesList.size());
        return page;
    }

    @Override
    public List<Atraccion> buscarTodos() {
        Atraccion[] atracciones = template.getForObject(url+"/atracciones", Atraccion[].class);
        List<Atraccion> atraccionesList = null;
        if (atracciones != null) {
            atraccionesList = Arrays.asList(atracciones);
        }
        return atraccionesList;
    }

    @Override
    public void guardarAtraccion(AtraccionRequest atraccion) {
        if (atraccion.getId() != null && atraccion.getId() > 0)
        {
            template.put(url+"/atracciones", atraccion);
        }
        else
        {
            atraccion.setId(0);
            template.postForObject(url+"/atracciones", atraccion, String.class);
        }
    }

    @Override
    public void eliminarAtraccion(Integer idAtraccion) {
        template.delete(url+"/atracciones/"+idAtraccion);
    }

    @Override
    public Atraccion buscarAtraccionPorId(Integer id) {
        Atraccion atraccion = template.getForObject(url+"/atracciones/id/"+id, Atraccion.class);
        return atraccion;
    }
}
