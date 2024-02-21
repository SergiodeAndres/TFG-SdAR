package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Sitio;
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
public class SitioServiceImpl implements es.proyecto.tfgfrontend.service.ISitioService {
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public List<Sitio> buscarTodos() {
        Sitio[] sitios = template.getForObject(url+"/sitios", Sitio[].class);
        List<Sitio> sitiosList = null;
        if (sitios != null) {
            sitiosList = Arrays.asList(sitios);
        }
        return sitiosList;
    }

    @Override
    public Sitio buscarPorId(Integer id) {
        Sitio sitio = template.getForObject(url+"/sitios/id/"+id, Sitio.class);
        return sitio;
    }
}
