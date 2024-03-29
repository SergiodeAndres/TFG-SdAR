package es.proyecto.tfgfrontend.service;
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
import es.proyecto.tfgfrontend.model.Empleado;
@Service
public class EmpleadoServiceImpl implements es.proyecto.tfgfrontend.service.IEmpleadoService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";


    @Override
    public List<Empleado> buscarTodos() {
        Empleado[] empleados = template.getForObject(url+"/empleados", Empleado[].class);
        List<Empleado> empleadosList = null;
        if (empleados != null) {
            empleadosList = Arrays.asList(empleados);
        }
        return empleadosList;
    }

    @Override
    public List<Empleado> buscarPorSitioID(Integer sitioID) {
        Empleado[] empleados = template.getForObject(url+"/empleados/sitio/"+sitioID, Empleado[].class);
        List<Empleado> empleadosList = null;
        if (empleados != null) {
            empleadosList = Arrays.asList(empleados);
        }
        return empleadosList;
    }

    @Override
    public Empleado buscarPorEmail(String email) {
        Empleado empleado = template.getForObject(url+"/empleados/email/"+email, Empleado.class);
        return empleado;
    }

    @Override
    public Empleado buscarPorEmailYPassword(String email, String password) {
        Empleado empleado = template.getForObject(url+"/empleados/email-password/"+email+"/"+password, Empleado.class);
        return empleado;
    }

    @Override
    public List<Empleado> buscarPorGerenteVerdadero() {
        Empleado[] empleados = template.getForObject(url+"/empleados/gerentes", Empleado[].class);
        List<Empleado> empleadosList = null;
        if (empleados != null) {
            empleadosList = Arrays.asList(empleados);
        }
        return empleadosList;
    }

    @Override
    public Empleado buscarPorId(String id) {
        return null;
    }
}
