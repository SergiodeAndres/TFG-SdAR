package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import java.util.List;

public interface IEmpleadoDAO {
    List<Empleado> buscarTodos();

    void guardarEmpleado(Empleado empleado);

    void eliminarEmpleado(String idEmpleado);

    void actualizarEmpleado(Empleado empleado);

    List<Empleado> buscarPorSitioID(Sitio sitioID);

    Empleado buscarPorEmail(String email);

    Empleado buscarPorEmailYPassword(String email, String password);

    List<Empleado> buscarPorGerenteVerdadero();

}
