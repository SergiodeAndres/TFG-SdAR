package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IEmpleadoJPA extends JpaRepository<Empleado, String> {
    List<Empleado> findBySitioID(Sitio sitioID);

    Empleado findByEmail(String email);

    Empleado findByEmailAndPassword(String email, String password);

    List<Empleado> findByGerenteTrue();
}