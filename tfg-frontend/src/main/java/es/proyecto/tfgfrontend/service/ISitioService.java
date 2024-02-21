package es.proyecto.tfgfrontend.service;
import es.proyecto.tfgfrontend.model.Sitio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISitioService {

    List<Sitio> buscarTodos();

    Sitio buscarPorId(Integer id);
}
