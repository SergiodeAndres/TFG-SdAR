package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class TarjetaDAOImpl implements ITarjetaDAO {
    @Autowired
    ITarjetaJPA tarjetaJPA;

    @Override
    public List<Tarjeta> buscarTodos() {
        return tarjetaJPA.findAll();
    }

    @Override
    public void guardarTarjeta(Tarjeta tarjeta) {
        tarjetaJPA.save(tarjeta);
    }

    @Override
    public void eliminarTarjeta(String tarjetaID) {
        tarjetaJPA.deleteById(tarjetaID);
    }

    @Override
    public void actualizarTarjeta(Tarjeta tarjeta) {
        tarjetaJPA.save(tarjeta);
    }

    @Override
    public Tarjeta buscarPorNumeroIDYPin(String numeroID, String pin) {
        Optional<Tarjeta> optional = tarjetaJPA.findByNumeroIDAndPin(numeroID, pin);
        return optional.orElse(null);
    }

    @Override
    public Tarjeta buscarPorNumeroID(String numeroID) {
        Optional<Tarjeta> optional = tarjetaJPA.findByNumeroID(numeroID);
        return optional.orElse(null);
    }
}
