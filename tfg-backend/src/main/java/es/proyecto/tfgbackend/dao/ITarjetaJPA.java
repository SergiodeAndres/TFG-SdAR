package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITarjetaJPA extends JpaRepository<Tarjeta, String> {
    Optional<Tarjeta> findByNumeroIDAndPin(String numeroID, String pin);

    Optional<Tarjeta> findByNumeroID(String numeroID);
}