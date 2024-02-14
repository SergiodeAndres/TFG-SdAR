package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.ISitioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SitioServiceImpl implements ISitioService{
    @Autowired
    ISitioDAO sitioDAO;
}
