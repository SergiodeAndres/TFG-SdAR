package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Reserva;
import es.proyecto.tfgbackend.model.Sitio;

import java.time.LocalDate;
import java.util.List;
public interface IReservaService {
    List<Reserva> buscarTodos();

    boolean guardarReserva(Reserva reserva);

    boolean eliminarReserva(Integer idReserva);

    void actualizarReserva(Reserva reserva);

    Reserva buscarPorIdYEmaildeContacto(Integer id, String email);

    List<Reserva> buscarPorSitioIDYFechaReserva(Sitio sitioID, LocalDate fechaReserva);

    Reserva buscarPorId(Integer id);
}
