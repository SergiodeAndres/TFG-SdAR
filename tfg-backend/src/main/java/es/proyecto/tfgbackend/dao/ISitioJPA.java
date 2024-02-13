package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISitioJPA extends JpaRepository<Sitio, Integer> {
}