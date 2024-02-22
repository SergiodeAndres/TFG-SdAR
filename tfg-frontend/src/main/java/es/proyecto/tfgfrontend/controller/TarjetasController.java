package es.proyecto.tfgfrontend.controller;

import es.proyecto.tfgfrontend.model.Tarjeta;
import es.proyecto.tfgfrontend.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/frontend")
public class TarjetasController {

    @Autowired
    ITarjetaService tarjetaService;

    @GetMapping("/tarjetasEmpleado")
    public String inicioSesionEmpleado(Model model) {
        Tarjeta tarjeta = new Tarjeta();
        model.addAttribute("tarjeta", tarjeta);
        return "paginas/tarjetasEmpleado";
    }
    @PostMapping("/eliminarTarjeta")
    public String eliminarTarjeta(Model model, @RequestParam String numeroTarjeta, RedirectAttributes attributes) {
        Tarjeta tarjeta = tarjetaService.buscarPorNumeroID(numeroTarjeta);
        if (tarjeta == null)
        {
            attributes.addFlashAttribute("msg_neg_eliminar", "Tarjeta no existente.");
        }
        else
        {
            tarjetaService.eliminarTarjeta(numeroTarjeta);
            attributes.addFlashAttribute("msg_pos_eliminar", "Tarjeta eliminada.");
        }
        return "redirect:/frontend/tarjetasEmpleado";
    }

    @PostMapping("/guardarTarjeta")
    public String guardarTarjeta(Model model, Tarjeta tarjeta, RedirectAttributes attributes) {
        Tarjeta tarjetaComprobacion = tarjetaService.buscarPorNumeroID(tarjeta.getNumeroID());
        if (tarjetaComprobacion != null)
        {
            attributes.addFlashAttribute("msg_neg_guardar", "Este número de tarjeta ya existe.");
        }
        else
        {
            tarjetaService.guardarTarjeta(tarjeta);
            attributes.addFlashAttribute("msg_pos_guardar", "Tarjeta creada.");
        }
        return "redirect:/frontend/tarjetasEmpleado";
    }
}
