package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.Atraccion;
import es.proyecto.tfgfrontend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
@Service
public class AtraccionServiceImpl implements  es.proyecto.tfgfrontend.service.IAtraccionService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

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
    public List<Atraccion> buscarAtraccionesPorSitio(Sitio SitioID) {
        Atraccion[] atracciones = template.getForObject(url+"/atracciones/sitio/"+SitioID.getId(), Atraccion[].class);
        List<Atraccion> atraccionesList = null;
        if (atracciones != null) {
            atraccionesList = Arrays.asList(atracciones);
        }
        return atraccionesList;
    }

    @Override
    public Atraccion buscarAtraccionPorId(Integer id) {
        Atraccion atraccion = template.getForObject(url+"/atracciones/id/"+id, Atraccion.class);
        return atraccion;
    }
}
