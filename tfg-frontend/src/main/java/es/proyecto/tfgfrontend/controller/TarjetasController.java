package es.proyecto.tfgfrontend.controller;

import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.model.Sitio;
import es.proyecto.tfgfrontend.model.Tarjeta;
import es.proyecto.tfgfrontend.service.ITarjetaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/frontend")
public class TarjetasController {

    @Autowired
    ITarjetaService tarjetaService;

    @GetMapping("/tarjetasEmpleado")
    public String inicioSesionEmpleado(Model model, HttpSession session) {
        Tarjeta tarjeta = new Tarjeta();
        model.addAttribute("tarjeta", tarjeta);
        Boolean gerente = (Boolean) session.getAttribute("gerente");
        model.addAttribute("gerente", gerente);
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

    @GetMapping("/credencialesTarjetaCliente")
    public String credencialesTarjetaCliente(Model model) {
        return "paginas/credencialesTarjetaCliente";
    }

    @PostMapping("/comprobarTarjetaCliente")
    public String comprobarEmpleado(Model model, @RequestParam String numeroTarjeta, @RequestParam String pinTarjeta,
                                    RedirectAttributes attributes, HttpSession session) {
        Tarjeta tarjeta = tarjetaService.buscarPorNumeroID(numeroTarjeta);
        if (tarjeta == null) {
            attributes.addFlashAttribute("msg", "Tarjeta no existente.");
            return "redirect:/frontend/credencialesTarjetaCliente";
        }
        tarjeta = tarjetaService.buscarPorNumeroIDYPin(numeroTarjeta, pinTarjeta);
        if (tarjeta == null) {
            attributes.addFlashAttribute("msg", "PIN incorrecto.");
            return "redirect:/frontend/credencialesTarjetaCliente";
        }
        session.setAttribute("tarjeta", tarjeta);
        return "redirect:/frontend/tarjetaCliente";
    }

    @GetMapping("/tarjetaCliente")
    public String tarjetaCliente(Model model, HttpSession session) {
        Tarjeta tarjeta = (Tarjeta) session.getAttribute("tarjeta");
        model.addAttribute("tarjeta", tarjeta);
        return "paginas/tarjetaCliente";
    }

    @PostMapping("/rellenarSaldoTarjeta")
    public String rellenarSaldoTarjeta(Model model, @RequestParam String numeroCuenta, @RequestParam float saldoTarjeta,
                                    RedirectAttributes attributes, HttpSession session) {
        Tarjeta tarjeta = (Tarjeta) session.getAttribute("tarjeta");
        if ((numeroCuenta.isEmpty()) || (saldoTarjeta == 0)) {
            attributes.addFlashAttribute("msg", "Introduzca los datos");
            return "redirect:/frontend/tarjetaCliente";
        }
        tarjeta.setSaldoMoneda(tarjeta.getSaldoMoneda() + saldoTarjeta);
        tarjetaService.modificarTarjeta(tarjeta);
        return "redirect:/frontend/tarjetaCliente";
    }
}
