package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.*;
import es.proyecto.tfgbackend.service.IEmpleadoService;
import es.proyecto.tfgbackend.service.ISitioService;
import es.proyecto.tfgbackend.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TurnoController {

    @Autowired
    ITurnoService turnoService;
    @Autowired
    ISitioService sitioService;
    @Autowired
    IEmpleadoService empleadoService;

    @GetMapping("/aplicacion/turnos")
    List<Turno> buscarTodos() {
        return turnoService.buscarTodos();
    }

    @GetMapping("/aplicacion/turnos/id/{id}")
    Turno buscarPorId(@PathVariable("id") Integer turnoId) {
        return turnoService.buscarPorId(turnoId);
    }

    @GetMapping("/aplicacion/turnos/sitio-empleado/{sitio}/{empleado}")
    Turno buscarPorSitioYEmpleado(@PathVariable("sitio") Integer sitioId, @PathVariable("empleado") String dni) {
        return turnoService.buscarPorSitioYEmpleado(sitioService.buscarPorId(sitioId), empleadoService.buscarPorId(dni));
    }

    @GetMapping("/aplicacion/turnos/sitio-fecha/{sitio}/{fecha}")
    List<Turno> buscarPorSitioYFecha(@PathVariable("sitio") Integer sitioID, @PathVariable("fecha") LocalDate fecha) {
        return turnoService.buscarPorSitioYFecha(sitioService.buscarPorId(sitioID), fecha);
    }

    @PostMapping(value = "/aplicacion/turnos", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarTurno(@RequestBody TurnoRequest turno) {
        Turno turnoCompleto = new Turno();
        turnoCompleto.setId(turno.getId());
        turnoCompleto.setFecha(turno.getFecha());
        turnoCompleto.setHoraEntrada(turno.getHoraEntrada());
        turnoCompleto.setHoraSalida(turno.getHoraSalida());
        turnoCompleto.setSitio(sitioService.buscarPorId(turno.getSitio()));
        turnoCompleto.setEmpleado(empleadoService.buscarPorId(turno.getEmpleado()));
        return String.valueOf(turnoService.guardarTurno(turnoCompleto));
    }

    @PutMapping("aplicacion/turnos")
    public void actualizarTurno(@RequestBody TurnoRequest turno) {
        Turno turnoCompleto = new Turno();
        turnoCompleto.setId(turno.getId());
        turnoCompleto.setFecha(turno.getFecha());
        turnoCompleto.setHoraEntrada(turno.getHoraEntrada());
        turnoCompleto.setHoraSalida(turno.getHoraSalida());
        turnoCompleto.setSitio(sitioService.buscarPorId(turno.getSitio()));
        turnoCompleto.setEmpleado(empleadoService.buscarPorId(turno.getEmpleado()));
        turnoService.actualizarTurno(turnoCompleto);
    }

    @DeleteMapping(value = "/aplicacion/turnos/eliminar/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarTurno(@PathVariable("id") Integer turnoId) {
        return String.valueOf(turnoService.eliminarTurno(turnoId));
    }
}
