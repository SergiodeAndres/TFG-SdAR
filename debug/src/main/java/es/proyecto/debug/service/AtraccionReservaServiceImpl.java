package es.proyecto.debug.service;

import es.proyecto.debug.model.AtraccionReserva;
import es.proyecto.debug.model.AtraccionReservaId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AtraccionReservaServiceImpl implements es.proyecto.debug.service.IAtraccionRequestService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public List<AtraccionReserva> buscarTodos() {
        AtraccionReserva[] atraccionReservas = template.getForObject(url+"/atraccionreservas", AtraccionReserva[].class);
        List<AtraccionReserva> atraccionReservasList = null;
        if (atraccionReservas != null) {
            atraccionReservasList = Arrays.asList(atraccionReservas);
        }
        return atraccionReservasList;
    }

    @Override
    public void eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId) {
        template.delete(url+"/atraccionreservas/"+atraccionReservaId.getReservaID()+"/"+atraccionReservaId.getAtraccionID());
    }
}
