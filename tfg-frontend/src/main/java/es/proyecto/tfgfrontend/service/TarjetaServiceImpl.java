package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class TarjetaServiceImpl implements es.proyecto.tfgfrontend.service.ITarjetaService {
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public List<Tarjeta> buscarTodos() {
        Tarjeta[] tarjetas = template.getForObject(url+"/tarjetas", Tarjeta[].class);
        List<Tarjeta> tarjetasList = null;
        if (tarjetas != null) {
            tarjetasList = Arrays.asList(tarjetas);
        }
        return tarjetasList;
    }

    @Override
    public void guardarTarjeta(Tarjeta tarjeta) {
        tarjeta.setSaldoMoneda(0.0F);
        tarjeta.setSaldoTickets(0);
        template.postForObject(url+"/tarjetas", tarjeta, String.class);
    }

    @Override
    public void modificarTarjeta(Tarjeta tarjeta) {
        template.put(url+"/tarjetas", tarjeta);
    }

    @Override
    public void eliminarTarjeta(String tarjetaID) {
        template.delete(url+"/tarjetas/"+tarjetaID);
    }

    @Override
    public Tarjeta buscarPorNumeroIDYPin(String numeroID, String pin) {
        Tarjeta tarjeta = template.getForObject(url+"/tarjetas/numero-pin/"+numeroID+"/"+pin, Tarjeta.class);
        return tarjeta;
    }

    @Override
    public Tarjeta buscarPorNumeroID(String numeroID) {
        Tarjeta tarjeta = template.getForObject(url+"/tarjetas/numero/"+numeroID, Tarjeta.class);
        return tarjeta;
    }
}
