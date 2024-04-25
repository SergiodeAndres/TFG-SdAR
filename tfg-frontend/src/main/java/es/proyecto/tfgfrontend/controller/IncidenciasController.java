package es.proyecto.tfgfrontend.controller;

import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.model.Incidencia;
import es.proyecto.tfgfrontend.model.IncidenciaRequest;
import es.proyecto.tfgfrontend.model.Sitio;
import es.proyecto.tfgfrontend.paginator.PageRender;
import es.proyecto.tfgfrontend.service.IIncidenciaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/frontend")
public class IncidenciasController {
    @Autowired
    IIncidenciaService incidenciaService;

    @GetMapping("/incidencias")
    public String incidencias(Model model, HttpSession session, @RequestParam(name="page", defaultValue="0") int page) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        IncidenciaRequest incidencia = new IncidenciaRequest();
        model.addAttribute("incidencia", incidencia);
        Boolean gerente = (Boolean) session.getAttribute("gerente");
        model.addAttribute("gerente", gerente);
        Sitio sitio = (Sitio) session.getAttribute("sitio");
        Pageable pageable = PageRequest.of(page, 5);
        Page<Incidencia> listado = incidenciaService.buscarPorCerradaFalsoYDniEmpleado_SitioID(sitio.getId(),pageable);
        PageRender<Incidencia> pageRender = new PageRender<>("/frontend/incidencias", listado);
        model.addAttribute("listadoIncidencias", listado);
        model.addAttribute("page", pageRender);
        return "paginas/incidencias";
    }

    @PostMapping("/guardarIncidencia")
    public String guardarIncidencia(Model model, RedirectAttributes attributes,
                                    HttpSession session, IncidenciaRequest incidencia) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        Empleado empleado = (Empleado) session.getAttribute("empleado");
        incidencia.setDniEmpleado(empleado.getDni());
        incidenciaService.guardarIncidencia(incidencia);
        attributes.addFlashAttribute("msg_pos_guardar", "Incidencia guardada.");
        return "redirect:/frontend/incidencias";
    }

    @GetMapping("/cerrarIncidencia/{id}")
    public String cerrarIncidencia(Model model, RedirectAttributes attributes,
                                   @PathVariable("id") Integer id, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo == null || !modo.equals("empleado"))
        {
            return "redirect:/frontend/";
        }
        Incidencia incidencia = incidenciaService.buscarPorId(id);
        IncidenciaRequest incidenciaRequest = new IncidenciaRequest();
        incidenciaRequest.setId(incidencia.getId());
        incidenciaRequest.setDescripcion(incidencia.getDescripcion());
        incidenciaRequest.setDniEmpleado(incidencia.getDniEmpleado().getDni());
        incidenciaRequest.setCerrada((byte) 1);
        incidenciaService.guardarIncidencia(incidenciaRequest);
        attributes.addFlashAttribute("msg_pos_cerrar", "Incidencia cerada.");
        return "redirect:/frontend/incidencias";
    }
}
