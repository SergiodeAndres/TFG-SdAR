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
        template.delete(url+"/atraccionreservas/"+atraccionReservaId.getReservaID()+"/"+atraccionReservaId.getAtraccionID());
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
    public List<AtraccionReserva> buscarPorAtraccionID(Atraccion atraccionID) {
        AtraccionReserva[] atraccionReservas = template.getForObject(url+"/atraccionreservas/atraccion/"+atraccionID.getId(), AtraccionReserva[].class);
        List<AtraccionReserva> atraccionReservasList = null;
        if (atraccionReservas != null) {
            atraccionReservasList = Arrays.asList(atraccionReservas);
        }
        return atraccionReservasList;
    }

    @Override
    public AtraccionReserva buscarPorReservaIDYAtraccionID(Reserva reservaID, Atraccion atraccionID) {
        AtraccionReserva atraccionReserva = template.getForObject(
                url+"/atraccionreservas/reserva-atraccion/"+reservaID.getId()+"/"+atraccionID.getId(), AtraccionReserva.class);
        return atraccionReserva;
    }

    @Override
    public List<AtraccionReserva> buscarPorReservaID_FechaReserva(LocalDate fechaReserva) {
        AtraccionReserva[] atraccionReservas = template.getForObject(url+"/atraccionreservas/fecha/"+fechaReserva, AtraccionReserva[].class);
        List<AtraccionReserva> atraccionReservasList = null;
        if (atraccionReservas != null) {
            atraccionReservasList = Arrays.asList(atraccionReservas);
        }
        return atraccionReservasList;
    }

    @Override
    public List<AtraccionReserva> buscarPorAtraccionIDYReservaID_FechaReserva(Atraccion atraccionId, LocalDate fechaReserva) {
        AtraccionReserva[] atraccionReservas = template.getForObject(
                url+"/atraccionreservas/atraccion-fecha/"+atraccionId.getId()+"/"+fechaReserva, AtraccionReserva[].class);
        List<AtraccionReserva> atraccionReservasList = null;
        if (atraccionReservas != null) {
            atraccionReservasList = Arrays.asList(atraccionReservas);
        }
        return atraccionReservasList;
    }
}
