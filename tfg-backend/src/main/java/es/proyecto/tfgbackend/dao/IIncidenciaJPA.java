package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Incidencia;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IIncidenciaJPA extends JpaRepository<Incidencia, Integer> {

    List<Incidencia> findByCerradaFalseAndDniEmpleado_SitioID(Sitio SitioID);
}