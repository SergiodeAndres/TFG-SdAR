package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.model.EmpleadoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IEmpleadoService {

    List<Empleado> buscarTodos();

    Page<Empleado> buscarTodos(Pageable pageable);

    List<Empleado> buscarPorSitioID(Integer sitioID);

    Empleado buscarPorEmail(String email);

    Empleado buscarPorEmailYPassword(String email, String password);

    List<Empleado> buscarPorGerenteVerdadero();

    Empleado buscarPorId (String id);

    void guardarEmpleado(EmpleadoRequest empleado);

    void eliminarEmpleado(String idEmpleado);
}
