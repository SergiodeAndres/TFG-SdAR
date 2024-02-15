package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.ITarjetaDAO;
import es.proyecto.tfgbackend.model.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServiceImpl implements ITarjetaService{
    @Autowired
    ITarjetaDAO tarjetaDAO;

    @Override
    public List<Tarjeta> buscarTodos() {
        return tarjetaDAO.buscarTodos();
    }

    @Override
    public boolean guardarTarjeta(Tarjeta tarjeta) {
        if (tarjetaDAO.buscarPorNumeroID(tarjeta.getNumeroID()) == null)
        {
            tarjetaDAO.guardarTarjeta(tarjeta);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarTarjeta(String tarjetaID) {
        if (tarjetaDAO.buscarPorNumeroID(tarjetaID) != null)
        {
            tarjetaDAO.eliminarTarjeta(tarjetaID);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarTarjeta(Tarjeta tarjeta) {
        if (tarjetaDAO.buscarPorNumeroID(tarjeta.getNumeroID()) != null)
        {
            tarjetaDAO.guardarTarjeta(tarjeta);
        }
    }

    @Override
    public Tarjeta buscarPorNumeroIDYPin(String numeroID, String pin) {
        return tarjetaDAO.buscarPorNumeroIDYPin(numeroID, pin);
    }

    @Override
    public Tarjeta buscarPorNumeroID(String numeroID) {
        return tarjetaDAO.buscarPorNumeroID(numeroID);
    }
}
