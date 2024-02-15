package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Reserva;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaDAOImpl implements IReservaDAO {
    @Autowired
    IReservaJPA reservaJPA;

    @Override
    public List<Reserva> buscarTodos() {
        return reservaJPA.findAll();
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        reservaJPA.save(reserva);
    }

    @Override
    public void eliminarReserva(Integer idReserva) {
        reservaJPA.deleteById(idReserva);
    }

    @Override
    public void actualizarReserva(Reserva reserva) {
        reservaJPA.save(reserva);
    }

    @Override
    public Reserva buscarPorIdYEmaildeContacto(Integer id, String email) {
        Optional<Reserva> optional = reservaJPA.findByIdAndEmailContacto(id ,email);
        return optional.orElse(null);
    }

    @Override
    public List<Reserva> buscarPorSitioIDYFechaReserva(Sitio sitioID, LocalDate fechaReserva) {
        return reservaJPA.findBySitioIDAndFechaReserva(sitioID, fechaReserva);
    }

    @Override
    public Reserva buscarPorId(Integer id) {
        Optional<Reserva> optional = reservaJPA.findById(id);
        return optional.orElse(null);
    }
}
