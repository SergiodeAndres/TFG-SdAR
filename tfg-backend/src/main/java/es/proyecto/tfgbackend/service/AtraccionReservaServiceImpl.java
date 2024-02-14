package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IAtraccionReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtraccionReservaServiceImpl implements IAtraccionReservaService{
    @Autowired
    IAtraccionReservaDAO atraccionReservaDAO;
}
