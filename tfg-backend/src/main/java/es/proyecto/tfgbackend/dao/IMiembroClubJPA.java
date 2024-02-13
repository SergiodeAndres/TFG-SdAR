package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Miembroclub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMiembroClubJPA extends JpaRepository<Miembroclub, String> {
    Miembroclub findByEmail(String email);
}