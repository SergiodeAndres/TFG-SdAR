package es.proyecto.debug.service;

import es.proyecto.debug.model.Empleado;
import es.proyecto.debug.model.EmpleadoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmpleadoService {
    Page<Empleado> buscarTodos(Pageable pageable);

    List<Empleado> buscarTodos();

    void guardarEmpleado(EmpleadoRequest empleado);

    void eliminarEmpleado(String idEmpleado);

    Empleado buscarPorId (String id);
}
