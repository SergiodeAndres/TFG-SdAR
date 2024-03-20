package es.proyecto.debug.controller;

import es.proyecto.debug.model.*;
import es.proyecto.debug.paginator.PageRender;
import es.proyecto.debug.service.*;
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
public class AtraccionesController {
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

    @GetMapping(value = {"/", "home", ""})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/atraccionesLista")
    public String atraccionesLista(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Atraccion> listado = atraccionService.buscarTodos(pageable);
        PageRender<Atraccion> pageRender = new PageRender<>("/debug/atraccionesLista", listado);
        model.addAttribute("listadoAtracciones", listado);
        model.addAttribute("page", pageRender);
        return "paginas/atraccionesLista";
    }

    @GetMapping("/atraccionesEditar/{id}")
    public String atraccionesEditar(Model model, @PathVariable("id") Integer id) {
        Atraccion atraccion = atraccionService.buscarAtraccionPorId(id);
        model.addAttribute("titulo", "Editar atracciones");
        model.addAttribute("atraccion", atraccion);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/atraccionesForm";
    }

    @GetMapping("/atraccionesCrear")
    public String atraccionesCrear(Model model) {
        AtraccionRequest atraccion = new AtraccionRequest();
        model.addAttribute("titulo", "Crear atraccion");
        model.addAttribute("atraccion", atraccion);
        model.addAttribute("sitios", sitioService.buscarTodos());
        return "paginas/atraccionesForm";
    }

    @GetMapping("/atraccionesEliminar/{id}")
    public String atraccionesEliminar(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        List<AtraccionReserva> atraccionReservasList = atraccionReservaService.buscarTodos();
        for (AtraccionReserva atraccionReserva: atraccionReservasList) {
            if (atraccionReserva.getAtraccionID().getId().equals(id)) {
                atraccionReservaService.eliminarAtraccionReserva(atraccionReserva.getId());
            }
        }
        atraccionService.eliminarAtraccion(id);
        attributes.addFlashAttribute("msg", "La atraccion se ha eliminado");
        return "redirect:/debug/home";
    }

    @PostMapping("/atraccionesGuardar")
    public String atraccionesGuardar(Model model, AtraccionRequest atraccion, RedirectAttributes attributes) {
        attributes.addFlashAttribute("msg", "Atracciones actualizadas!");
        atraccionService.guardarAtraccion(atraccion);
        return "redirect:/debug/home";
    }
}
