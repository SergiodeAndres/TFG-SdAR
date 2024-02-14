package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements IReservaService{
    @Autowired
    IReservaDAO reservaDAO;
}
