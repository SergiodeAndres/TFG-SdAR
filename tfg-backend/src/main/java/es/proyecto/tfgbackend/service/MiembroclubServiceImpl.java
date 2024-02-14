package es.proyecto.tfgbackend.service;

import es.proyecto.tfgbackend.dao.IMiembroClubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiembroclubServiceImpl implements IMiembroclubService{
    @Autowired
    IMiembroClubDAO miembroClubDAO;
}
