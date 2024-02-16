package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IEmpleadoJPA extends JpaRepository<Empleado, String> {
    List<Empleado> findBySitioID(Integer sitioID);

    Optional<Empleado> findByEmail(String email);

    Optional<Empleado> findByEmailAndPassword(String email, String password);

    List<Empleado> findByGerenteTrue();
}