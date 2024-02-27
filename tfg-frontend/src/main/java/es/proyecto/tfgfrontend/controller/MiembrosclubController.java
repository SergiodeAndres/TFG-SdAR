package es.proyecto.tfgfrontend.controller;

import es.proyecto.tfgfrontend.model.Miembroclub;
import es.proyecto.tfgfrontend.model.Tarjeta;
import es.proyecto.tfgfrontend.service.IMiembroclubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import es.proyecto.tfgfrontend.paginator.PageRender;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/frontend")
public class MiembrosclubController {
    @Autowired
    IMiembroclubService miembroclubService;

    @GetMapping("/miembroclub")
    public String tarjetaCliente(Model model) {
        Miembroclub miembroclub = new Miembroclub();
        model.addAttribute("miembroclub", miembroclub);
        return "paginas/miembroclub";
    }

    @PostMapping("/guardarMiembroclub")
    public String guardarMiembroclub(Model model, Miembroclub miembroclub, RedirectAttributes attributes) {
        Miembroclub miembroclubComprobacion = miembroclubService.buscarPorEmail(miembroclub.getEmail());
        if (miembroclubComprobacion != null)
        {
            attributes.addFlashAttribute("msg_neg_guardar", "Este correo ya está registrado.");
        }
        else
        {
            miembroclubService.guardarMiembroClub(miembroclub);
            attributes.addFlashAttribute("msg_pos_guardar", "Correo registrado correctamente.");
        }
        return "redirect:/frontend/miembroclub";
    }
}
