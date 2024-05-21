package es.proyecto.tfgfrontend.controller;


import es.proyecto.tfgfrontend.model.*;
import es.proyecto.tfgfrontend.paginator.PageRender;
import es.proyecto.tfgfrontend.service.IEmpleadoService;
import es.proyecto.tfgfrontend.service.ITurnoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/frontend")
public class TurnosController {

    @Autowired
    ITurnoService turnoService;

    @Autowired
    IEmpleadoService empleadoService;

    @GetMapping("/turnos")
    public String turnosEmpleados (Model model, HttpSession session, @RequestParam(name="page", defaultValue="0") int page) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        LocalDate fechaActual = LocalDate.now();
        model.addAttribute("fechaActual", fechaActual);
        LocalDate fechaSemana = fechaActual.plusDays(7);
        model.addAttribute("fechaSemana", fechaSemana);
        Sitio sitio = (Sitio) session.getAttribute("sitio");
        if (session.getAttribute("fechaSeleccionada") == null)
        {
            model.addAttribute("turnosFechaDisplay", false);
        }
        else {
            Pageable pageable = PageRequest.of(page, 5);
            LocalDate fechaSeleccionada = (LocalDate) session.getAttribute("fechaSeleccionada");
            Page<Turno> listado = turnoService.buscarPorSitioYFecha(sitio, fechaSeleccionada, pageable);
            PageRender<Turno> pageRender = new PageRender<>("/frontend/turnos", listado);
            model.addAttribute("turnosFecha", listado);
            model.addAttribute("page", pageRender);
            model.addAttribute("turnosFechaDisplay", true);
            model.addAttribute("fechaSeleccionada", fechaSeleccionada);
        }
        if (session.getAttribute("empleados") == null)
        {
            List<Empleado> empleados = empleadoService.buscarPorSitioID(sitio.getId());
            model.addAttribute("empleados", empleados);
        }
        else
        {
            Empleado[] listaEmpleados = (Empleado[]) session.getAttribute("empleados");
            List<Empleado> empleados = Arrays.asList(listaEmpleados);
            model.addAttribute("empleados", empleados);
        }
        Boolean gerente = (Boolean) session.getAttribute("gerente");
        model.addAttribute("gerente", gerente);
        return "paginas/turnosEmpleados";
    }

    @PostMapping("/cargarTurnosFecha")
    public String cargarTurnosFecha(Model model, @RequestParam LocalDate fechaTurno, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        session.setAttribute("fechaSeleccionada", fechaTurno);
        return "redirect:/frontend/turnos";
    }

    @PostMapping("/addTurno")
    public String addTurno(Model model, @RequestParam String empleado, @RequestParam LocalDate fechaTurno
            , @RequestParam LocalTime horaEntradaTurno, @RequestParam LocalTime horaSalidaTurno,
                           RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        if (horaSalidaTurno.isBefore(horaEntradaTurno)) {
            attributes.addFlashAttribute("msgAddTurnoNeg", "La hora de salida tiene que ser posterior a la de entrada.");
            return "redirect:/frontend/turnos";
        }
        if (horaSalidaTurno.equals(horaEntradaTurno)) {
            attributes.addFlashAttribute("msgAddTurnoNeg", "Las horas de entrada y salida no pueden coincidir.");
            return "redirect:/frontend/turnos";
        }
        if(horaEntradaTurno.until(horaSalidaTurno, ChronoUnit.MINUTES) > 480) {
            attributes.addFlashAttribute("msgAddTurnoNeg", "El turno no puede durar más de 8 horas.");
            return "redirect:/frontend/turnos";
        }
        Sitio sitio = (Sitio) session.getAttribute("sitio");
        TurnoRequest turno = new TurnoRequest();
        turno.setEmpleado(empleado);
        turno.setSitio(sitio.getId());
        turno.setFecha(fechaTurno);
        turno.setHoraEntrada(horaEntradaTurno);
        turno.setHoraSalida(horaSalidaTurno);
        turnoService.guardarTurno(turno);
        attributes.addFlashAttribute("msgAddTurnoPos", "Turno añadido.");
        return "redirect:/frontend/turnos";
    }

    @GetMapping("/eliminarTurno/{id}")
    public String eliminarTurno(Model model, RedirectAttributes attributes,
                                   @PathVariable("id") Integer id, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        turnoService.eliminarTurno(id);
        attributes.addFlashAttribute("msgEliminarTurno", "Turno eliminado.");
        return "redirect:/frontend/turnos";
    }
}
