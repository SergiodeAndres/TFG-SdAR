package es.proyecto.debug.controller;

import es.proyecto.debug.service.IAtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/debug")
public class AtraccionesController {
    @Autowired
    IAtraccionService atraccionService;

    @GetMapping(value = {"/", "home", ""})
    public String home(Model model) {
        return "home";
    }

    @GetMapping(value = {"/atracciones"})
    public String atracciones(Model model) {
        return "paginas/atracciones";
    }
}
