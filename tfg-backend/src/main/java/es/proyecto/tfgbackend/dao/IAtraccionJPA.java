package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IAtraccionJPA extends JpaRepository<Atraccion, Integer> {
    List<Atraccion> findBySitioID(Sitio sitioID);
}