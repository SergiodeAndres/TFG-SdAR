package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.model.Miembroclub;
import java.util.List;
public interface IMiembroclubService {
    List<Miembroclub> buscarTodos();

    boolean guardarMiembroClub(Miembroclub miembroClub);

    boolean eliminarMiembroClub(String correo);

    void actualizarMiembroClub(Miembroclub miembroClub);

    Miembroclub buscarPorEmail (String email);
}
