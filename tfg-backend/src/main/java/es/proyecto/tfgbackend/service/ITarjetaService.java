package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Tarjeta;
import java.util.List;
public interface ITarjetaService {
    List<Tarjeta> buscarTodos();

    boolean guardarTarjeta(Tarjeta tarjeta);

    boolean eliminarTarjeta(String tarjetaID);

    void actualizarTarjeta(Tarjeta tarjeta);

    Tarjeta buscarPorNumeroIDYPin(String numeroID, String pin);

    Tarjeta buscarPorNumeroID(String numeroID);
}
