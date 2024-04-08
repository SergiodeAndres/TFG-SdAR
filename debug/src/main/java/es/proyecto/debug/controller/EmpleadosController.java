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
public class EmpleadosController {
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

    @GetMapping("/empleadosLista")
    public String empleadosLista(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Empleado> listado = empleadoService.buscarTodos(pageable);
        PageRender<Empleado> pageRender = new PageRender<>("/frontend/empleadosLista", listado);
        model.addAttribute("listadoEmpleados", listado);
        model.addAttribute("page", pageRender);
        return "paginas/empleadosLista";
    }

    @GetMapping("/empleadosEditar/{dni}")
    public String empleadosEditar(Model model, @PathVariable("dni") String dni, HttpSession session) {
        Empleado empleado = empleadoService.buscarPorId(dni);
        model.addAttribute("titulo", "Editar empleado");
        model.addAttribute("empleado", empleado);
        model.addAttribute("editarEmpleado", true);
        session.setAttribute("editarEmpleado", true);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/empleadosForm";
    }

    @GetMapping("/empleadosCrear")
    public String empleadosCrear(Model model, HttpSession session) {
        EmpleadoRequest empleado = new EmpleadoRequest();
        model.addAttribute("titulo", "Crear sitio");
        model.addAttribute("empleado", empleado);
        model.addAttribute("editarEmpleado", false);
        session.setAttribute("editarEmpleado", false);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/empleadosForm";
    }

    @GetMapping("/empleadosEliminar/{dni}")
    public String sitiosEliminar(Model model, @PathVariable("dni") String dni, RedirectAttributes attributes) {
        List<Incidencia> incidenciasList = incidenciaService.buscarTodos();
        for (Incidencia incidencia: incidenciasList) {
            if (incidencia.getDniEmpleado().getDni().equals(dni)) {
                incidenciaService.eliminarIncidencia(incidencia.getId());
            }
        }
        List<Turno> turnosList = turnosService.buscarTodos();
        for (Turno turno: turnosList) {
            if (turno.getEmpleado().getDni().equals(dni)) {
                turnosService.eliminarTurno(turno.getId());
            }
        }
        empleadoService.eliminarEmpleado(dni);
        attributes.addFlashAttribute("msg", "El empleado se ha eliminado");
        return "redirect:/frontend/home";
    }

    @PostMapping("/empleadosGuardar")
    public String empleadosGuardar(Model model, EmpleadoRequest empleado, RedirectAttributes attributes, HttpSession session) {
        Boolean editarEmpleado = (boolean) session.getAttribute("editarEmpleado");
        if (!editarEmpleado) {
            List<Empleado> empleadosList = empleadoService.buscarTodos();
            for (Empleado e: empleadosList) {
                if (e.getDni().equals(empleado.getDni())) {
                    attributes.addFlashAttribute("msg", "Este DNI ya está registrado.");
                    return "redirect:/frontend/empleadosCrear";
                }
            }
        }
        attributes.addFlashAttribute("msg", "Empleados actualizados!");
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/frontend/home";
    }
}
