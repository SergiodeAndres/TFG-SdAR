package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IEmpleadoDAO;
import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    @Autowired
    IEmpleadoDAO empleadoDAO;

    @Override
    public List<Empleado> buscarTodos() {
        return empleadoDAO.buscarTodos();
    }

    @Override
    public boolean guardarEmpleado(Empleado empleado) {
        if (empleadoDAO.buscarPorId(empleado.getDni()) == null)
        {
            empleadoDAO.guardarEmpleado(empleado);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleado(String idEmpleado) {
        if (empleadoDAO.buscarPorId(idEmpleado) != null)
        {
            empleadoDAO.eliminarEmpleado(idEmpleado);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        if (empleadoDAO.buscarPorId(empleado.getDni()) != null)
        {
            empleadoDAO.guardarEmpleado(empleado);
        }
    }

    @Override
    public List<Empleado> buscarPorSitioID(Sitio sitioID) {
        return empleadoDAO.buscarPorSitioID(sitioID);
    }

    @Override
    public Empleado buscarPorEmail(String email) {
        return empleadoDAO.buscarPorEmail(email);
    }

    @Override
    public Empleado buscarPorEmailYPassword(String email, String password) {
        return empleadoDAO.buscarPorEmailYPassword(email, password);
    }

    @Override
    public List<Empleado> buscarPorGerenteVerdadero() {
        return empleadoDAO.buscarPorGerenteVerdadero();
    }

    @Override
    public Empleado buscarPorId(String id) {
        return empleadoDAO.buscarPorId(id);
    }
}
