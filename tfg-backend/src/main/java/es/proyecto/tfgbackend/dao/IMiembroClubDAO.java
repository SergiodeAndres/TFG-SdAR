package es.proyecto.tfgbackend.dao;

import es.proyecto.tfgbackend.model.Miembroclub;

import java.util.List;

public interface IMiembroClubDAO {
    List<Miembroclub> buscarTodos();

    void guardarMiembroClub(Miembroclub miembroClub);

    void eliminarMiembroClub(String correo);

    void actualizarMiembroClub(Miembroclub miembroClub);

    Miembroclub buscarPorEmail (String email);

}
