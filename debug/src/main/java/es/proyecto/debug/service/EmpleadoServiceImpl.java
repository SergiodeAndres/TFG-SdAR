package es.proyecto.debug.service;

import es.proyecto.debug.model.Empleado;
import es.proyecto.debug.model.EmpleadoRequest;
import es.proyecto.debug.model.Sitio;
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
public class EmpleadoServiceImpl implements es.proyecto.debug.service.IEmpleadoService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/aplicacion";

    @Override
    public Page<Empleado> buscarTodos(Pageable pageable) {
        Empleado[] empleados = template.getForObject(url+"/empleados", Empleado[].class);
        List<Empleado> empleadosList = Arrays.asList(empleados);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Empleado> list;

        if (empleadosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, empleadosList.size());
            list = empleadosList.subList(startItem, toIndex);
        }
        Page<Empleado> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                empleadosList.size());
        return page;
    }

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
    public void guardarEmpleado(EmpleadoRequest empleado) {
        Empleado empleadoTest = template.getForObject(url+"/empleados/dni/"+empleado.getDni(), Empleado.class);
        if (empleadoTest != null)
        {
            template.put(url+"/empleados", empleado);
        }
        else
        {
            template.postForObject(url+"/empleados", empleado, String.class);
        }
    }

    @Override
    public void eliminarEmpleado(String idEmpleado) {
        template.delete(url+"/empleados/"+idEmpleado);
    }

    @Override
    public Empleado buscarPorId(String id) {
        Empleado empleado = template.getForObject(url+"/empleados/dni/"+id, Empleado.class);
        return empleado;
    }
}
