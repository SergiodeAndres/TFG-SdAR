package es.proyecto.tfgfrontend.service;

import es.proyecto.tfgfrontend.model.Reserva;
import es.proyecto.tfgfrontend.model.ReservaRequest;
import es.proyecto.tfgfrontend.model.Sitio;

import java.time.LocalDate;
import java.util.List;

public interface IReservaService {
    List<Reserva> buscarTodos();

    void guardarReserva(ReservaRequest reserva);

    void eliminarReserva(Integer idReserva);

    Reserva buscarPorIdYEmaildeContacto(Integer id, String email);

    List<Reserva> buscarPorSitioIDYFechaReserva(Sitio sitioID, LocalDate fechaReserva);

    Reserva buscarPorId(Integer id);
}
