package es.proyecto.tfgfrontend.controller;
import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.paginator.PageRender;
import es.proyecto.tfgfrontend.service.IEmpleadoService;
import es.proyecto.tfgfrontend.service.ISitioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String comprobarEmpleado(Model model, @RequestParam String correoEmpleado, @RequestParam String passwordEmpleado) {
        Empleado empleado = empleadoService.buscarPorEmail(correoEmpleado);
        if (empleado == null) {
            model.addAttribute("error", "Correo no existente");
            return "paginas/inicioSesionEmpleado";
        }
        else {
            empleado = empleadoService.buscarPorEmailYPassword(correoEmpleado, passwordEmpleado);
            if (empleado == null) {
                model.addAttribute("error", "Contraseña incorrecta");
                return "paginas/inicioSesionEmpleado";
            }
        }
        return "paginas/inicioSesionEmpleado";
    }
}
