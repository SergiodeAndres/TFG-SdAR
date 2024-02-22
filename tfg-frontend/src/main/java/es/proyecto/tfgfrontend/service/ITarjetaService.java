package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.Tarjeta;

import java.util.List;

public interface ITarjetaService {
    List<Tarjeta> buscarTodos();

    void guardarTarjeta(Tarjeta tarjeta);

    void eliminarTarjeta(String tarjetaID);

    Tarjeta buscarPorNumeroIDYPin(String numeroID, String pin);

    Tarjeta buscarPorNumeroID(String numeroID);
}
