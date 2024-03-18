package es.proyecto.debug.service;

import es.proyecto.debug.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReservaServiceImpl implements es.proyecto.debug.service.IReservaService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public List<Reserva> buscarTodos() {
        Reserva[] reservas = template.getForObject(url+"/reservas", Reserva[].class);
        List<Reserva> reservasList = null;
        if (reservas != null) {
            reservasList = Arrays.asList(reservas);
        }
        return reservasList;
    }

    @Override
    public void eliminarReserva(Integer idReserva) {
        template.delete(url+"/reservas/"+idReserva);
    }
}
