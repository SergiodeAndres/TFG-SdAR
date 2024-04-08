package es.proyecto.debug.controller;

import es.proyecto.debug.model.*;
import es.proyecto.debug.paginator.PageRender;
import es.proyecto.debug.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/debug")
public class SitiosController {
    @Autowired
    ISitioService sitioService;

    @Autowired
    IIncidenciaService incidenciaService;

    @Autowired
    ITurnoService turnosService;

    @Autowired
    IAtraccionReservaService atraccionReservaService;

    @Autowired
    IReservaService reservaService;

    @Autowired
    IAtraccionService atraccionService;

    @Autowired
    IEmpleadoService empleadoService;

    @GetMapping("/sitiosLista")
    public String sitiosLista(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Sitio> listado = sitioService.buscarTodos(pageable);
        PageRender<Sitio> pageRender = new PageRender<>("/frontend/sitiosLista", listado);
        model.addAttribute("listadoSitios", listado);
        model.addAttribute("page", pageRender);
        return "paginas/sitiosLista";
    }

    @GetMapping("/sitiosEditar/{id}")
    public String sitiosEditar(Model model, @PathVariable("id") Integer id) {
        Sitio sitio = sitioService.buscarPorId(id);
        model.addAttribute("titulo", "Editar sitio");
        model.addAttribute("sitio", sitio);
        return "paginas/sitiosForm";
    }

    @GetMapping("/sitiosCrear")
    public String sitiosCrear(Model model) {
        Sitio sitio = new Sitio();
        model.addAttribute("titulo", "Crear sitio");
        model.addAttribute("sitio", sitio);
        return "paginas/sitiosForm";
    }

    @GetMapping("/sitiosEliminar/{id}")
    public String sitiosEliminar(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        List<Incidencia> incidenciasList = incidenciaService.buscarTodos();
        for (Incidencia incidencia: incidenciasList) {
            if (incidencia.getDniEmpleado().getSitioID().getId().equals(id)) {
                incidenciaService.eliminarIncidencia(incidencia.getId());
            }
        }
        List<Turno> turnosList = turnosService.buscarTodos();
        for (Turno turno: turnosList) {
            if (turno.getSitio().getId().equals(id)) {
                turnosService.eliminarTurno(turno.getId());
            }
        }
        List<AtraccionReserva> atraccionReservasList = atraccionReservaService.buscarTodos();
        for (AtraccionReserva atraccionReserva: atraccionReservasList) {
            if (atraccionReserva.getReservaID().getSitioID().getId().equals(id)) {
                atraccionReservaService.eliminarAtraccionReserva(atraccionReserva.getId());
            }
        }
        List<Reserva> reservasList = reservaService.buscarTodos();
        for (Reserva reserva: reservasList) {
            if (reserva.getSitioID().getId().equals(id)) {
                reservaService.eliminarReserva(reserva.getId());
            }
        }
        List<Atraccion> atraccionesList = atraccionService.buscarTodos();
        for (Atraccion atraccion: atraccionesList) {
            if (atraccion.getSitioID().getId().equals(id)) {
                atraccionService.eliminarAtraccion(atraccion.getId());
            }
        }
        List<Empleado> empleadosList = empleadoService.buscarTodos();
        for (Empleado empleado: empleadosList) {
            if (empleado.getSitioID().getId().equals(id)) {
                empleadoService.eliminarEmpleado(empleado.getDni());
            }
        }
        sitioService.eliminarSitio(id);
        attributes.addFlashAttribute("msg", "El sitio se ha eliminado");
        return "redirect:/frontend/home";
    }

    @PostMapping("/sitiosGuardar")
    public String sitiosGuardar(Model model, Sitio sitio, RedirectAttributes attributes) {
        if (sitio.getId() == null) {
            attributes.addFlashAttribute("msg", "El sitio se ha creado!");
        }
        else {
            attributes.addFlashAttribute("msg", "Los datos del sitio fueron actualizados!");
        }
        sitioService.guardarSitio(sitio);
        return "redirect:/frontend/home";
    }
}
