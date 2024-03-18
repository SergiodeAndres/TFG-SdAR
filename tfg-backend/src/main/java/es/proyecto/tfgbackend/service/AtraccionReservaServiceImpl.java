package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IAtraccionReservaDAO;
import es.proyecto.tfgbackend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AtraccionReservaServiceImpl implements IAtraccionReservaService{
    @Autowired
    IAtraccionReservaDAO atraccionReservaDAO;

    @Override
    public List<AtraccionReserva> buscarTodos() {
        return atraccionReservaDAO.buscarTodos();
    }

    @Override
    public boolean guardarAtraccionReserva(AtraccionReserva atraccionReserva) {
        if (atraccionReservaDAO.buscarPorId(atraccionReserva.getId()) == null)
        {
            atraccionReservaDAO.guardarAtraccionReserva(atraccionReserva);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarAtraccionReserva(AtraccionReservaId atraccionReservaId) {
        if (atraccionReservaDAO.buscarPorId(atraccionReservaId) != null)
        {
            atraccionReservaDAO.eliminarAtraccionReserva(atraccionReservaId);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarAtraccionReserva(AtraccionReserva atraccionReserva) {
        if (atraccionReservaDAO.buscarPorId(atraccionReserva.getId()) != null)
        {
            atraccionReservaDAO.guardarAtraccionReserva(atraccionReserva);
        }
    }

    @Override
    public List<AtraccionReserva> buscarPorReservaID(Reserva reservaID) {
        return atraccionReservaDAO.buscarPorReservaID(reservaID);
    }

    @Override
    public List<AtraccionReserva> buscarPorAtraccionID_Id(Integer atraccionID) {
        return atraccionReservaDAO.buscarPorAtraccionID_Id(atraccionID);
    }

    @Override
    public List<AtraccionReserva> buscarPorSitioID_Id(Integer sitioID) {
        return atraccionReservaDAO.buscarPorSitioID_Id(sitioID);
    }

    @Override
    public AtraccionReserva buscarPorId(AtraccionReservaId atraccionReservaId) {
        return atraccionReservaDAO.buscarPorId(atraccionReservaId);
    }

    @Override
    public List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(LocalDate fechaReserva, Sitio sitioID) {
        return atraccionReservaDAO.buscarPorResevaID_FechaReservaYReservaID_SitioID(fechaReserva, sitioID);
    }
}
