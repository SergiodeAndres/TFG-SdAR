package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public List<AtraccionReserva> buscarPorAtraccionID_Id(Integer atraccionID) {
        return atraccionReservaJPA.findByAtraccionID_Id(atraccionID);
    }

    @Override
    public List<AtraccionReserva> buscarPorSitioID_Id(Integer sitioID) {
        return atraccionReservaJPA.findByReservaID_SitioID_Id(sitioID);
    }

    @Override
    public AtraccionReserva buscarPorId(AtraccionReservaId atraccionReservaId) {
        Optional<AtraccionReserva> optional = atraccionReservaJPA.findById(atraccionReservaId);
        return optional.orElse(null);
    }

    @Override
    public List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID) {
        return atraccionReservaJPA.findByReservaID_FechaReservaAndReservaID_SitioID(fechaReserva, sitioID);
    }
}
