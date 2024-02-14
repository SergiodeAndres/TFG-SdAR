package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.ITurnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoServiceImpl implements ITurnoService{
    @Autowired
    ITurnoDAO turnoDAO;
}
