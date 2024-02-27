package es.proyecto.tfgfrontend.service;

import java.util.List;
import es.proyecto.tfgfrontend.model.Miembroclub;
public interface IMiembroclubService {
    List<Miembroclub> buscarTodos();

    void guardarMiembroClub(Miembroclub miembroClub);

    Miembroclub buscarPorEmail (String email);
}
