package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.ITarjetaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl implements ITarjetaService{
    @Autowired
    ITarjetaDAO tarjetaDAO;
}
