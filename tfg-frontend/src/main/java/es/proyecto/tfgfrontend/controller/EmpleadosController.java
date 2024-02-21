package es.proyecto.tfgfrontend.controller;
import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.model.Sitio;
import es.proyecto.tfgfrontend.paginator.PageRender;
import es.proyecto.tfgfrontend.service.IEmpleadoService;
import es.proyecto.tfgfrontend.service.ISitioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/frontend")
class EmpleadosController {
    @Autowired
    IEmpleadoService empleadoService;

    @GetMapping(value = {"/", "home", ""})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/inicioSesionEmpleado")
    public String inicioSesionEmpleado(Model model) {
        return "paginas/inicioSesionEmpleado";
    }

    @PostMapping("/comprobarEmpleado")
    public String comprobarEmpleado(Model model, @RequestParam String correoEmpleado, @RequestParam String passwordEmpleado,
                                    RedirectAttributes attributes, HttpSession session) {
        Empleado empleado = empleadoService.buscarPorEmail(correoEmpleado);
        if (empleado == null) {
            attributes.addFlashAttribute("msg", "Correo no existente.");
            return "redirect:/frontend/inicioSesionEmpleado";
        }
        empleado = empleadoService.buscarPorEmailYPassword(correoEmpleado, passwordEmpleado);
        if (empleado == null) {
            attributes.addFlashAttribute("msg", "Contraseña incorrecta.");
            return "redirect:/frontend/inicioSesionEmpleado";
        }
        List<Empleado> gerentes = empleadoService.buscarPorGerenteVerdadero();
        session.setAttribute("gerente", false);
        for (Empleado g: gerentes) {
            if (g.getDni().equals(empleado.getDni())) {
                session.setAttribute("gerente", true);
            }
        }
        Sitio sitioEmpleado = empleado.getSitioID();
        session.setAttribute("sitio", sitioEmpleado);
        return "redirect:/frontend/homeEmpleado";
    }

    @GetMapping("/homeEmpleado")
    public String homeEmpleado(Model model, HttpSession session) {
        Boolean gerente = (Boolean) session.getAttribute("gerente");
        model.addAttribute("gerente", gerente);
        return "paginas/homeEmpleado";
    }
}
