package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IAtraccionDAO;
import es.proyecto.tfgbackend.model.Atraccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AtraccionServiceImpl implements IAtraccionService{
    @Autowired
    IAtraccionDAO atraccionDAO;
}
