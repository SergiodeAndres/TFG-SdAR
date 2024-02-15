package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IReservaDAO;
import es.proyecto.tfgbackend.model.Reserva;
import es.proyecto.tfgbackend.model.Sitio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaServiceImpl implements IReservaService{
    @Autowired
    IReservaDAO reservaDAO;

    @Override
    public List<Reserva> buscarTodos() {
        return reservaDAO.buscarTodos();
    }

    @Override
    public boolean guardarReserva(Reserva reserva) {
        if (reservaDAO.buscarPorId(reserva.getId()) == null)
        {
            reservaDAO.guardarReserva(reserva);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarReserva(Integer idReserva) {
        if (reservaDAO.buscarPorId(idReserva) != null)
        {
            reservaDAO.eliminarReserva(idReserva);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarReserva(Reserva reserva) {
        if (reservaDAO.buscarPorId(reserva.getId()) != null)
        {
            reservaDAO.guardarReserva(reserva);
        }
    }

    @Override
    public Reserva buscarPorIdYEmaildeContacto(Integer id, String email) {
        return reservaDAO.buscarPorIdYEmaildeContacto(id, email);
    }

    @Override
    public List<Reserva> buscarPorSitioIDYFechaReserva(Sitio sitioID, LocalDate fechaReserva) {
        return reservaDAO.buscarPorSitioIDYFechaReserva(sitioID, fechaReserva);
    }

    @Override
    public Reserva buscarPorId(Integer id) {
        return reservaDAO.buscarPorId(id);
    }
}
