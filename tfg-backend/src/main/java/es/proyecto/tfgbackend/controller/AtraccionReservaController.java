package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.service.IAtraccionService;
import es.proyecto.tfgbackend.service.IReservaService;
import es.proyecto.tfgbackend.service.IAtraccionReservaService;
import es.proyecto.tfgbackend.model.AtraccionReserva;
import es.proyecto.tfgbackend.model.AtraccionReservaId;
import es.proyecto.tfgbackend.model.AtraccionReservaRequest;
import es.proyecto.tfgbackend.service.ISitioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AtraccionReservaController {
    @Autowired
    IAtraccionReservaService atraccionReservaService;

    @Autowired
    IReservaService reservaService;

    @Autowired
    IAtraccionService atraccionService;

    @Autowired
    ISitioService sitioService;

    @GetMapping("aplicacion/atraccionreservas")
    public List<AtraccionReserva> buscarTodos() {
        return atraccionReservaService.buscarTodos();
    }

    @GetMapping("aplicacion/atraccionreservas/reserva/{id}")
    public List<AtraccionReserva> buscarPorReservaID(@PathVariable("id") Integer id) {
        return atraccionReservaService.buscarPorReservaID(reservaService.buscarPorId(id));
    }

    @GetMapping("aplicacion/atraccionreservas/atraccion/{id}")
    public List<AtraccionReserva> buscarPorAtraccionID(@PathVariable("id") Integer id) {
        return atraccionReservaService.buscarPorAtraccionID_Id(id);
    }

    @GetMapping("aplicacion/atraccionreservas/sitio/{id}")
    public List<AtraccionReserva> buscarPorSitioID(@PathVariable("id") Integer id) {
        return atraccionReservaService.buscarPorSitioID_Id(id);
    }

    @GetMapping("aplicacion/atraccionreservas/id/{reserva}/{atraccion}")
    public AtraccionReserva buscarPorId(@PathVariable("reserva") Integer reservaId, @PathVariable("atraccion") Integer atraccionId) {
        AtraccionReservaId id = new AtraccionReservaId();
        id.setAtraccionID(atraccionId);
        id.setReservaID(reservaId);
        return atraccionReservaService.buscarPorId(id);
    }

    @GetMapping("aplicacion/atraccionreservas/fecha-sitio/{fecha}/{sitioID}")
    public List<AtraccionReserva> buscarPorResevaID_FechaReservaYReservaID_SitioID(@PathVariable("fecha") LocalDate fecha, @PathVariable("sitioID") Integer sitioId) {
        return atraccionReservaService.buscarPorResevaID_FechaReservaYReservaID_SitioID(fecha, sitioService.buscarPorId(sitioId));
    }

    @PostMapping(value = "/aplicacion/atraccionreservas", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarAtraccionReserva(@RequestBody AtraccionReservaRequest atraccionReserva) {
        AtraccionReservaId id = new AtraccionReservaId();
        AtraccionReserva atraccionReservaCompleta = new AtraccionReserva();
        id.setAtraccionID(atraccionReserva.getAtraccionID());
        id.setReservaID(atraccionReserva.getReservaID());
        atraccionReservaCompleta.setId(id);
        atraccionReservaCompleta.setSesion(atraccionReserva.getSesion());
        atraccionReservaCompleta.setAtraccionID(atraccionService.buscarAtraccionPorId(atraccionReserva.getAtraccionID()));
        atraccionReservaCompleta.setReservaID(reservaService.buscarPorId(atraccionReserva.getReservaID()));
        return String.valueOf(atraccionReservaService.guardarAtraccionReserva(atraccionReservaCompleta));
    }

    @PutMapping("aplicacion/atraccionreservas")
    public void actualizarAtraccionReserva(@RequestBody AtraccionReservaRequest atraccionReserva) {
        AtraccionReservaId id = new AtraccionReservaId();
        AtraccionReserva atraccionReservaCompleta = new AtraccionReserva();
        id.setAtraccionID(atraccionReserva.getAtraccionID());
        id.setReservaID(atraccionReserva.getReservaID());
        atraccionReservaCompleta.setId(id);
        atraccionReservaCompleta.setSesion(atraccionReserva.getSesion());
        atraccionReservaCompleta.setAtraccionID(atraccionService.buscarAtraccionPorId(atraccionReserva.getAtraccionID()));
        atraccionReservaCompleta.setReservaID(reservaService.buscarPorId(atraccionReserva.getReservaID()));
        atraccionReservaService.actualizarAtraccionReserva(atraccionReservaCompleta);
    }

    @DeleteMapping(value = "/aplicacion/atraccionreservas/{reserva}/{atraccion}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarAtraccion(@PathVariable("reserva") Integer reservaId, @PathVariable("atraccion") Integer atraccionId) {
        AtraccionReservaId id = new AtraccionReservaId();
        id.setAtraccionID(atraccionId);
        id.setReservaID(reservaId);
        return String.valueOf(atraccionReservaService.eliminarAtraccionReserva(id));
    }

}
