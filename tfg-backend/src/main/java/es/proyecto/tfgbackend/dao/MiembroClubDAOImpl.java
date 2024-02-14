package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Miembroclub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MiembroClubDAOImpl implements IMiembroClubDAO {
    @Autowired
    IMiembroClubJPA miembroClubJPA;

    @Override
    public List<Miembroclub> buscarTodos() {
        return miembroClubJPA.findAll();
    }

    @Override
    public void guardarMiembroClub(Miembroclub miembroClub) {
        miembroClubJPA.save(miembroClub);
    }

    @Override
    public void eliminarMiembroClub(String correo) {
        miembroClubJPA.deleteById(correo);
    }

    @Override
    public void actualizarMiembroClub(Miembroclub miembroClub) {
        miembroClubJPA.save(miembroClub);
    }

    @Override
    public Miembroclub buscarPorEmail(String email) {
        Optional<Miembroclub> optional = miembroClubJPA.findByEmail(email);
        return optional.orElse(null);
    }
}
