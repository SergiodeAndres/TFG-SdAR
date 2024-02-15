package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IMiembroClubDAO;
import es.proyecto.tfgbackend.model.Miembroclub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroclubServiceImpl implements IMiembroclubService{
    @Autowired
    IMiembroClubDAO miembroClubDAO;

    @Override
    public List<Miembroclub> buscarTodos() {
        return miembroClubDAO.buscarTodos();
    }

    @Override
    public boolean guardarMiembroClub(Miembroclub miembroClub) {
        if (miembroClubDAO.buscarPorEmail(miembroClub.getEmail()) == null)
        {
            miembroClubDAO.guardarMiembroClub(miembroClub);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarMiembroClub(String correo) {
        if (miembroClubDAO.buscarPorEmail(correo) != null)
        {
            miembroClubDAO.eliminarMiembroClub(correo);
            return true;
        }
        return false;
    }

    @Override
    public void actualizarMiembroClub(Miembroclub miembroClub) {
        if (miembroClubDAO.buscarPorEmail(miembroClub.getEmail()) != null)
        {
            miembroClubDAO.guardarMiembroClub(miembroClub);
        }
    }

    @Override
    public Miembroclub buscarPorEmail(String email) {
        return miembroClubDAO.buscarPorEmail(email);
    }
}
