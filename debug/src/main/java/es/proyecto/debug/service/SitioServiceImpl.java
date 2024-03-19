package es.proyecto.debug.service;

import es.proyecto.debug.model.Sitio;
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
public class SitioServiceImpl implements es.proyecto.debug.service.ISitioService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public Page<Sitio> buscarTodos(Pageable pageable) {
        Sitio[] sitios = template.getForObject(url+"/sitios", Sitio[].class);
        List<Sitio> sitiosList = Arrays.asList(sitios);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Sitio> list;

        if (sitiosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, sitiosList.size());
            list = sitiosList.subList(startItem, toIndex);
        }
        Page<Sitio> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                sitiosList.size());
        return page;
    }

    @Override
    public void guardarSitio(Sitio sitio) {
        if (sitio.getId() != null && sitio.getId() > 0)
        {
            template.put(url+"/sitios", sitio);
        }
        else
        {
            sitio.setId(0);
            template.postForObject(url+"/sitios", sitio, String.class);
        }
    }

    @Override
    public void eliminarSitio(Integer idSitio) {
        template.delete(url+"/sitios/"+idSitio);
    }

    @Override
    public Sitio buscarPorId(Integer sitioId) {
        Sitio sitio = template.getForObject(url+"/sitios/id/"+sitioId, Sitio.class);
        return sitio;
    }
}
