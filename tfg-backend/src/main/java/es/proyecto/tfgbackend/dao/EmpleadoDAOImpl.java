package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class EmpleadoDAOImpl implements IEmpleadoDAO {
    @Autowired
    IEmpleadoJPA empleadoJPA;

    @Override
    public List<Empleado> buscarTodos() {
        return empleadoJPA.findAll();
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        empleadoJPA.save(empleado);
    }

    @Override
    public void eliminarEmpleado(String idEmpleado) {
        empleadoJPA.deleteById(idEmpleado);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        empleadoJPA.save(empleado);
    }

    @Override
    public List<Empleado> buscarPorSitioID(Integer sitioID) {
        return empleadoJPA.findBySitioID(sitioID);
    }

    @Override
    public Empleado buscarPorEmail(String email) {
        Optional<Empleado> optional = empleadoJPA.findByEmail(email);
        return optional.orElse(null);
    }

    @Override
    public Empleado buscarPorEmailYPassword(String email, String password) {
        Optional<Empleado> optional = empleadoJPA.findByEmailAndPassword(email, password);
        return optional.orElse(null);
    }

    @Override
    public List<Empleado> buscarPorGerenteVerdadero() {
        return empleadoJPA.findByGerenteTrue();
    }

    @Override
    public Empleado buscarPorId(String id) {
        Optional<Empleado> optional = empleadoJPA.findById(id);
        return optional.orElse(null);
    }
}
