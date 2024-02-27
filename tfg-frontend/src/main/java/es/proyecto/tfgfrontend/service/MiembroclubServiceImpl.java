package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.Miembroclub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.List;

@Service
public class MiembroclubServiceImpl implements  es.proyecto.tfgfrontend.service.IMiembroclubService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public List<Miembroclub> buscarTodos() {
        Miembroclub[] miembrosClub = template.getForObject(url+"/miembrosclub", Miembroclub[].class);
        List<Miembroclub> miembroclubList = null;
        if (miembrosClub != null) {
            miembroclubList = Arrays.asList(miembrosClub);
        }
        return miembroclubList;
    }

    @Override
    public void guardarMiembroClub(Miembroclub miembroClub) {
        template.postForObject(url+"/miembrosclub", miembroClub, String.class);
    }

    @Override
    public Miembroclub buscarPorEmail(String email) {
        return template.getForObject(url+"/miembrosclub/correo/"+email, Miembroclub.class);
    }
}
