package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Miembroclub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMiembroClubJPA extends JpaRepository<Miembroclub, String> {
    Optional<Miembroclub> findByEmail(String email);
}