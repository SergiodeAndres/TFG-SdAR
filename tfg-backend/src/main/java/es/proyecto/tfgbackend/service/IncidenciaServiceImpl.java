package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IIncidenciaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService{
    @Autowired
    IIncidenciaDAO incidenciaDAO;
}
