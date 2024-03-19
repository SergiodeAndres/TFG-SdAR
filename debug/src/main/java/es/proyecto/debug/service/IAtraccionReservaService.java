package es.proyecto.debug.service;

import java.util.List;
import es.proyecto.debug.model.AtraccionReserva;
import es.proyecto.debug.model.AtraccionReservaId;

public interface IAtraccionReservaService {
    List<AtraccionReserva> buscarTodos();

    void eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId);
}
