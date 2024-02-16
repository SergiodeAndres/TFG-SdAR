package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;

import java.util.List;
public interface IEmpleadoService {
    List<Empleado> buscarTodos();

    boolean guardarEmpleado(Empleado empleado);

    boolean eliminarEmpleado(String idEmpleado);

    void actualizarEmpleado(Empleado empleado);

    List<Empleado> buscarPorSitioID(Integer sitioID);

    Empleado buscarPorEmail(String email);

    Empleado buscarPorEmailYPassword(String email, String password);

    List<Empleado> buscarPorGerenteVerdadero();

    Empleado buscarPorId (String id);
}
