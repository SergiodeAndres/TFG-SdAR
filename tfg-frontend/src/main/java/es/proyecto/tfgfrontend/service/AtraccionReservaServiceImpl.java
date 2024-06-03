package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Service
public class AtraccionReservaServiceImpl implements es.proyecto.tfgfrontend.service.IAtraccionReservaService{
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
    public void guardarAtraccionReserva(AtraccionReservaRequest atraccionReserva) {
        template.postForObject(url+"/atraccionreservas", atraccionReserva, String.class);
    }

    @Override
    public void eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId) {
        template.delete(url+"/atraccionreservas/"+atraccionReservaId.getReservaID()+"/"+atraccionReservaId.getAtraccionID()+"/"+atraccionReservaId.getSesion());
    }

    @Override
    public List<AtraccionReserva> buscarPorReservaID(Reserva reservaID) {
        AtraccionReserva[] atraccionReservas = template.getForObject(url+"/atraccionreservas/reserva/"+reservaID.getId(), AtraccionReserva[].class);
        List<AtraccionReserva> atraccionReservasList = null;
        if (atraccionReservas != null) {
            atraccionReservasList = Arrays.asList(atraccionReservas);
        }
        return atraccionReservasList;
    }

    @Override
    public List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID) {
        AtraccionReserva[] atraccionReservas = template.getForObject(
                url+"/atraccionreservas/fecha-sitio/"+fechaReserva+"/"+sitioID.getId(), AtraccionReserva[].class);
        List<AtraccionReserva> atraccionReservasList = null;
        if (atraccionReservas != null) {
            atraccionReservasList = Arrays.asList(atraccionReservas);
        }
        return atraccionReservasList;
    }
}
