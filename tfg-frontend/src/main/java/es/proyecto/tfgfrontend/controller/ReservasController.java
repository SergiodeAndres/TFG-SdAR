package es.proyecto.tfgfrontend.controller;
import es.proyecto.tfgfrontend.model.Empleado;
import es.proyecto.tfgfrontend.model.ReservaRequest;
import es.proyecto.tfgfrontend.model.Sitio;
import es.proyecto.tfgfrontend.paginator.PageRender;
import es.proyecto.tfgfrontend.service.IEmpleadoService;
import es.proyecto.tfgfrontend.service.IReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
@Controller
@RequestMapping("/frontend")
public class ReservasController {
    @Autowired
    IReservaService reservaService;

    @GetMapping("/datosReserva")
    public String datosReserva(Model model, HttpSession session) {
        ReservaRequest reservaRequest = new ReservaRequest();
        model.addAttribute("reserva", reservaRequest);
        session.setAttribute("reserva", reservaRequest);
        return "paginas/pruebaCalendario";
    }
}
