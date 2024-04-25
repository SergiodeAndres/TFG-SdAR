package es.proyecto.tfgfrontend.controller;

import es.proyecto.tfgfrontend.model.Miembroclub;
import es.proyecto.tfgfrontend.service.IMiembroclubService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/frontend")
public class MiembrosclubController {
    @Autowired
    IMiembroclubService miembroclubService;

    @GetMapping("/miembroclub")
    public String tarjetaCliente(Model model, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo != null && modo.equals("empleado"))
        {
            return "redirect:/frontend/homeEmpleado";
        }
        if (modo != null && modo.equals("admin"))
        {
            return "redirect:/frontend/debug";
        }
        Miembroclub miembroclub = new Miembroclub();
        model.addAttribute("miembroclub", miembroclub);
        return "paginas/miembroclub";
    }

    @PostMapping("/guardarMiembroclub")
    public String guardarMiembroclub(Model model, Miembroclub miembroclub, RedirectAttributes attributes, HttpSession session) {
        String modo = (String) session.getAttribute("modo");
        if (modo != null && modo.equals("empleado"))
        {
            return "redirect:/frontend/homeEmpleado";
        }
        if (modo != null && modo.equals("admin"))
        {
            return "redirect:/frontend/debug";
        }
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
