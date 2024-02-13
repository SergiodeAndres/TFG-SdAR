package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ITarjetaJPA extends JpaRepository<Tarjeta, String> {
    Tarjeta findByNumeroIDAndPin(String numeroID, String pin);

    Tarjeta findByNumeroID(String numeroID);
}