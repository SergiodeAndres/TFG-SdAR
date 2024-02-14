package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IEmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    @Autowired
    IEmpleadoDAO empleadoDAO;
}
