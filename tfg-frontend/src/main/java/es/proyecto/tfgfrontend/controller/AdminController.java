package es.proyecto.tfgfrontend.controller;

import es.proyecto.tfgfrontend.model.*;
import es.proyecto.tfgfrontend.paginator.PageRender;
import es.proyecto.tfgfrontend.service.*;
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
@RequestMapping("/frontend")
public class AdminController {
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
    public String sitiosLista(Model model, @RequestParam(name="page", defaultValue="0") int page, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Sitio> listado = sitioService.buscarTodos(pageable);
        PageRender<Sitio> pageRender = new PageRender<>("/frontend/sitiosLista", listado);
        model.addAttribute("listadoSitios", listado);
        model.addAttribute("page", pageRender);
        return "paginas/sitiosLista";
    }

    @GetMapping("/sitiosEditar/{id}")
    public String sitiosEditar(Model model, @PathVariable("id") Integer id, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        Sitio sitio = sitioService.buscarPorId(id);
        model.addAttribute("titulo", "Editar sitio");
        model.addAttribute("sitio", sitio);
        return "paginas/sitiosForm";
    }

    @GetMapping("/sitiosCrear")
    public String sitiosCrear(Model model, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        Sitio sitio = new Sitio();
        model.addAttribute("titulo", "Crear sitio");
        model.addAttribute("sitio", sitio);
        return "paginas/sitiosForm";
    }

    @GetMapping("/sitiosEliminar/{id}")
    public String sitiosEliminar(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
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
        return "redirect:/frontend/debug";
    }

    @PostMapping("/sitiosGuardar")
    public String sitiosGuardar(Model model, Sitio sitio, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        if (sitio.getId() == null) {
            attributes.addFlashAttribute("msg", "El sitio se ha creado!");
        }
        else {
            attributes.addFlashAttribute("msg", "Los datos del sitio fueron actualizados!");
        }
        sitioService.guardarSitio(sitio);
        return "redirect:/frontend/debug";
    }

    @GetMapping("/atraccionesLista")
    public String atraccionesLista(Model model, @RequestParam(name="page", defaultValue="0") int page, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Atraccion> listado = atraccionService.buscarTodos(pageable);
        PageRender<Atraccion> pageRender = new PageRender<>("/frontend/atraccionesLista", listado);
        model.addAttribute("listadoAtracciones", listado);
        model.addAttribute("page", pageRender);
        return "paginas/atraccionesLista";
    }

    @GetMapping("/atraccionesEditar/{id}")
    public String atraccionesEditar(Model model, @PathVariable("id") Integer id, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        Atraccion atraccion = atraccionService.buscarAtraccionPorId(id);
        model.addAttribute("titulo", "Editar atracciones");
        model.addAttribute("atraccion", atraccion);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/atraccionesForm";
    }

    @GetMapping("/atraccionesCrear")
    public String atraccionesCrear(Model model, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        AtraccionRequest atraccion = new AtraccionRequest();
        model.addAttribute("titulo", "Crear atraccion");
        model.addAttribute("atraccion", atraccion);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/atraccionesForm";
    }

    @GetMapping("/atraccionesEliminar/{id}")
    public String atraccionesEliminar(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        List<AtraccionReserva> atraccionReservasList = atraccionReservaService.buscarTodos();
        for (AtraccionReserva atraccionReserva: atraccionReservasList) {
            if (atraccionReserva.getAtraccionID().getId().equals(id)) {
                atraccionReservaService.eliminarAtraccionReserva(atraccionReserva.getId());
            }
        }
        atraccionService.eliminarAtraccion(id);
        attributes.addFlashAttribute("msg", "La atraccion se ha eliminado");
        return "redirect:/frontend/debug";
    }

    @PostMapping("/atraccionesGuardar")
    public String atraccionesGuardar(Model model, AtraccionRequest atraccion, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        attributes.addFlashAttribute("msg", "Atracciones actualizadas!");
        atraccionService.guardarAtraccion(atraccion);
        return "redirect:/frontend/debug";
    }

    @GetMapping("/empleadosLista")
    public String empleadosLista(Model model, @RequestParam(name="page", defaultValue="0") int page, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Empleado> listado = empleadoService.buscarTodos(pageable);
        PageRender<Empleado> pageRender = new PageRender<>("/frontend/empleadosLista", listado);
        model.addAttribute("listadoEmpleados", listado);
        model.addAttribute("page", pageRender);
        return "paginas/empleadosLista";
    }

    @GetMapping("/empleadosEditar/{dni}")
    public String empleadosEditar(Model model, @PathVariable("dni") String dni, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
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
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        EmpleadoRequest empleado = new EmpleadoRequest();
        model.addAttribute("titulo", "Crear empleado");
        model.addAttribute("empleado", empleado);
        model.addAttribute("editarEmpleado", false);
        session.setAttribute("editarEmpleado", false);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/empleadosForm";
    }

    @GetMapping("/empleadosEliminar/{dni}")
    public String sitiosEliminar(Model model, @PathVariable("dni") String dni, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
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
        return "redirect:/frontend/debug";
    }

    @PostMapping("/empleadosGuardar")
    public String empleadosGuardar(Model model, EmpleadoRequest empleado, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("admin"))
        {
            return "redirect:/frontend/";
        }
        boolean editarEmpleado = (boolean) session.getAttribute("editarEmpleado");
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
        return "redirect:/frontend/debug";
    }
}
