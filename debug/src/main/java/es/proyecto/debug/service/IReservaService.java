package es.proyecto.debug.service;

import es.proyecto.debug.model.Reserva;

import java.util.List;

public interface IReservaService {
    List<Reserva> buscarTodos();

    void eliminarReserva(Integer idReserva);
}
