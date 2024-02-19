package es.proyecto.tfgbackend.controller;

import es.proyecto.tfgbackend.model.*;
import es.proyecto.tfgbackend.service.ISitioService;
import es.proyecto.tfgbackend.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReservaController {
    @Autowired
    IReservaService reservaService;

    @Autowired
    ISitioService sitioService;

    @GetMapping("aplicacion/reservas")
    public List<Reserva> buscarTodos() {
        return reservaService.buscarTodos();
    }

    @GetMapping("aplicacion/reservas/id/{id}")
    public Reserva buscarPorId(@PathVariable("id") Integer id) {
        return reservaService.buscarPorId(id);
    }

    @GetMapping("aplicacion/reservas/id-email/{id}/{email}")
    public Reserva buscarPorIdYEmaildeContacto(@PathVariable("id") Integer id, @PathVariable("email") String email) {
        return reservaService.buscarPorIdYEmaildeContacto(id, email);
    }

    @GetMapping("aplicacion/reservas/sitio-fecha/{sitio}/{fecha}")
    public List<Reserva> buscarPorSitioIDYFechaReserva(@PathVariable("sitio") Integer sitioId, @PathVariable("fecha") LocalDate fecha) {
        return reservaService.buscarPorSitioIDYFechaReserva(sitioService.buscarPorId(sitioId), fecha);
    }

    @PostMapping(value = "/aplicacion/reservas", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarIncidencia(@RequestBody ReservaRequest reserva) {
        Reserva reservaCompleta = new Reserva();
        reservaCompleta.setId(reserva.getId());
        reservaCompleta.setNombreContacto(reserva.getNombreContacto());
        reservaCompleta.setTelefonoContacto(reserva.getTelefonoContacto());
        reservaCompleta.setEmailContacto(reserva.getEmailContacto());
        reservaCompleta.setFechaReserva(reserva.getFechaReserva());
        reservaCompleta.setDatosPago(reserva.getDatosPago());
        reservaCompleta.setPersonas(reserva.getPersonas());
        reservaCompleta.setSitioID(sitioService.buscarPorId(reserva.getSitioID()));
        return String.valueOf(reservaService.guardarReserva(reservaCompleta));
    }

    @PutMapping("aplicacion/reservas")
    public void actualizarReserva(@RequestBody ReservaRequest reserva) {
        Reserva reservaCompleta = new Reserva();
        reservaCompleta.setId(reserva.getId());
        reservaCompleta.setNombreContacto(reserva.getNombreContacto());
        reservaCompleta.setTelefonoContacto(reserva.getTelefonoContacto());
        reservaCompleta.setEmailContacto(reserva.getEmailContacto());
        reservaCompleta.setFechaReserva(reserva.getFechaReserva());
        reservaCompleta.setDatosPago(reserva.getDatosPago());
        reservaCompleta.setPersonas(reserva.getPersonas());
        reservaCompleta.setSitioID(sitioService.buscarPorId(reserva.getSitioID()));
        reservaService.actualizarReserva(reservaCompleta);
    }

    @DeleteMapping(value = "/aplicacion/reservas/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarReserva(@PathVariable("id") Integer id) {
        return String.valueOf(reservaService.eliminarReserva(id));
    }
}
