package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Reserva;
import es.proyecto.tfgbackend.model.Sitio;

import java.time.LocalDate;
import java.util.List;

public interface IReservaDAO {
    List<Reserva> buscarTodos();

    void guardarReserva(Reserva reserva);

    void eliminarReserva(Integer idReserva);

    void actualizarReserva(Reserva reserva);

    Reserva buscarPorIdYEmaildeContacto(Integer id, String email);

    List<Reserva> buscarPorSitioIDYFechaReserva(Sitio sitioID, LocalDate fechaReserva);

    Reserva buscarPorId(Integer id);

}
