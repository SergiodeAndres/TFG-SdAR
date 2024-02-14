package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Atraccion;
import es.proyecto.tfgbackend.model.AtraccionReserva;
import es.proyecto.tfgbackend.model.AtraccionReservaId;
import es.proyecto.tfgbackend.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AtraccionReservaDAOImpl implements IAtraccionReservaDAO {
    @Autowired
    IAtraccionReservaJPA atraccionReservaJPA;

    @Override
    public List<AtraccionReserva> buscarTodos() {
        return atraccionReservaJPA.findAll();
    }

    @Override
    public void guardarAtraccionReserva(AtraccionReserva atraccionReserva) {
        atraccionReservaJPA.save(atraccionReserva);
    }

    @Override
    public void eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId) {
        atraccionReservaJPA.deleteById(atraccionReservaId);
    }

    @Override
    public void actualizarAtraccionReserva(AtraccionReserva atraccionReserva) {
        atraccionReservaJPA.save(atraccionReserva);
    }

    @Override
    public List<AtraccionReserva> buscarPorReservaID(Reserva reservaID) {
        return atraccionReservaJPA.findByReservaID(reservaID);
    }

    @Override
    public List<AtraccionReserva> buscarPorAtraccionID(Atraccion atraccionID) {
        return atraccionReservaJPA.findByAtraccionID(atraccionID);
    }

    @Override
    public AtraccionReserva buscarPorReservaIDYAtraccionID(Reserva reservaID, Atraccion atraccionID) {
        return atraccionReservaJPA.findByReservaIDAndAtraccionID(reservaID, atraccionID);
    }

    @Override
    public List<AtraccionReserva> buscarPorReservaID_FechaReserva(LocalDate fechaReserva) {
        return atraccionReservaJPA.findByReservaID_FechaReserva(fechaReserva);
    }

    @Override
    public List<AtraccionReserva> buscarPorAtraccionIDYReservaID_FechaReserva(Atraccion atraccionId, LocalDate fechaReserva) {
        return atraccionReservaJPA.findByAtraccionIDAndReservaID_FechaReserva(atraccionId, fechaReserva);
    }
}
