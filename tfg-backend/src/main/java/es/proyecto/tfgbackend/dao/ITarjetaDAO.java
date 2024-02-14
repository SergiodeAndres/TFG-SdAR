package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Tarjeta;

import java.util.List;

public interface ITarjetaDAO {
    List<Tarjeta> buscarTodos();

    void guardarTarjeta(Tarjeta tarjeta);

    void eliminarTarjeta(String tarjetaID);

    void actualizarTarjeta(Tarjeta tarjeta);

    Tarjeta buscarPorNumeroIDYPin(String numeroID, String pin);

    Tarjeta buscarPorNumeroID(String numeroID);

}
