package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.Reserva;
import es.proyecto.tfgfrontend.model.ReservaRequest;
import es.proyecto.tfgfrontend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Service
public class ReservaServiceImpl implements es.proyecto.tfgfrontend.service.IReservaService{
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
    public void guardarReserva(ReservaRequest reserva) {
        template.postForObject(url+"/reservas", reserva, String.class);
    }

    @Override
    public void eliminarReserva(Integer idReserva) {
        template.delete(url+"/reservas/"+idReserva);
    }

    @Override
    public Reserva buscarPorIdYEmaildeContacto(Integer id, String email) {
        Reserva reserva = template.getForObject(url+"/reservas/id-email/"+id+"/"+email, Reserva.class);
        return reserva;
    }

    @Override
    public List<Reserva> buscarPorSitioIDYFechaReserva(Sitio sitioID, LocalDate fechaReserva) {
        Reserva[] reservas = template.getForObject(url+"/reservas/sitio-fecha/"+sitioID.getId()+"/"+fechaReserva, Reserva[].class);
        List<Reserva> reservasList = null;
        if (reservas != null) {
            reservasList = Arrays.asList(reservas);
        }
        return reservasList;
    }

    @Override
    public Reserva buscarPorId(Integer id) {
        Reserva reserva = template.getForObject(url+"/reservas/id/"+id, Reserva.class);
        return reserva;
    }
}
