package es.proyecto.debug.controller;

import es.proyecto.debug.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/debug")
public class EmpleadosController {
    @Autowired
    IEmpleadoService empleadoService;
}
