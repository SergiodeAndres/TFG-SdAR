package es.proyecto.tfgfrontend.controller;
import es.proyecto.tfgfrontend.model.*;
import es.proyecto.tfgfrontend.service.IAtraccionService;
import es.proyecto.tfgfrontend.service.IReservaService;
import es.proyecto.tfgfrontend.service.ISitioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/frontend")
public class ReservasController {
    @Autowired
    IReservaService reservaService;

    @Autowired
    ISitioService sitioService;

    @Autowired
    IAtraccionService atraccionService;

    private List<LocalTime> crearListaSesiones() {
        List<LocalTime> sesiones = new ArrayList<>();
        LocalTime horaInicial = LocalTime.of(12, 0);
        LocalTime horaFinal = LocalTime.of(21,30);
        LocalTime horaActual = horaInicial;
        while(horaActual.isBefore(horaFinal) || horaActual.equals(horaFinal)) {
            sesiones.add(horaActual);
            horaActual = horaActual.plusMinutes(30);
        }
        return sesiones;
    }
    @GetMapping("/datosReserva")
    public String datosReserva(Model model, HttpSession session) {
        ReservaRequest reservaRequest = new ReservaRequest();
        LocalDate fechaActual = LocalDate.now();
        List<Sitio> sitios = sitioService.buscarTodos();
        model.addAttribute("reserva", reservaRequest);
        session.setAttribute("reserva", reservaRequest);
        model.addAttribute("fechaActual", fechaActual);
        model.addAttribute("sitios", sitios);
        return "paginas/datosReserva";
    }

    @PostMapping("/cargarActividades")
    public String cargarActividades(Model model, HttpSession session, @RequestParam int personasReserva, @RequestParam int local,
                                       @RequestParam LocalDate fechaReserva) {
        Sitio sitio = sitioService.buscarPorId(local);
        session.setAttribute("sitioReserva", sitio);
        session.setAttribute("fechaReserva", fechaReserva);
        List<Atraccion> atracciones = atraccionService.buscarAtraccionesPorSitio(sitio);
        HashMap<Atraccion, List<LocalTime>> sesionesPorAtraccion = new HashMap<>();
        //CAMBIAR PARA TENER EN CUENTA RESERVAS YA HECHAS
        for (Atraccion a: atracciones) {
            sesionesPorAtraccion.put(a, crearListaSesiones());
        }
        HashMap<LocalTime, Atraccion> actividadesElegidas = new HashMap<>();
        session.setAttribute("sesionesPorAtraccion", sesionesPorAtraccion);
        session.setAttribute("actividadesElegidas", actividadesElegidas);
        return "redirect:/frontend/actividadesReserva";
    }

    @GetMapping("/addActividad/hora={hora}/atraccion={atraccion}")
    public String addActividad(Model model, HttpSession session,
                                   @PathVariable("hora") LocalTime hora, @PathVariable("atraccion") int atraccionID) {
        //Eliminamos la sesión elegida de las listas de sesiones de las actividades
        HashMap<Atraccion, List<LocalTime>> sesionesPorAtraccion;
        sesionesPorAtraccion = (HashMap<Atraccion, List<LocalTime>>) session.getAttribute("sesionesPorAtraccion");
        for (Atraccion a : sesionesPorAtraccion.keySet())
        {
            sesionesPorAtraccion.get(a).remove(hora);
        }
        session.setAttribute("sesionesPorAtraccion", sesionesPorAtraccion);
        //Guardamos la combinacion actividad-sesion
        Atraccion atraccion = atraccionService.buscarAtraccionPorId(atraccionID);
        HashMap<LocalTime, Atraccion> actividadesElegidas;
        actividadesElegidas = (HashMap<LocalTime, Atraccion>) session.getAttribute("actividadesElegidas");
        actividadesElegidas.put(hora, atraccion);
        session.setAttribute("actividadesElegidas", actividadesElegidas);
        return "redirect:/frontend/actividadesReserva";
    }

    @GetMapping("/eliminarActividad/{hora}")
    public String eliminarActividad(Model model, HttpSession session, @PathVariable("hora") LocalTime hora) {
        //eliminamos la combinacion actividad-sesion
        HashMap<LocalTime, Atraccion> actividadesElegidas;
        actividadesElegidas = (HashMap<LocalTime, Atraccion>) session.getAttribute("actividadesElegidas");
        actividadesElegidas.remove(hora);
        session.setAttribute("actividadesElegidas", actividadesElegidas);
        //Añadimos la sesión elegida a las listas de sesiones de las actividades
        HashMap<Atraccion, List<LocalTime>> sesionesPorAtraccion;
        sesionesPorAtraccion = (HashMap<Atraccion, List<LocalTime>>) session.getAttribute("sesionesPorAtraccion");
        for (Atraccion a : sesionesPorAtraccion.keySet())
        {
            int index = Collections.binarySearch(sesionesPorAtraccion.get(a), hora);
            index = -index - 1;
            sesionesPorAtraccion.get(a).add(index, hora);
        }
        session.setAttribute("sesionesPorAtraccion", sesionesPorAtraccion);
        return "redirect:/frontend/actividadesReserva";
    }

    @GetMapping("/actividadesReserva")
    public String actividadesReserva(Model model, HttpSession session) {
        model.addAttribute("sesionesPorAtraccion", session.getAttribute("sesionesPorAtraccion"));
        model.addAttribute("actividadesElegidas", session.getAttribute("actividadesElegidas"));
        return "paginas/actividadesReserva";
    }
    @GetMapping("/guardarActividades")
    public String guardarActividades(Model model, HttpSession session) {
        model.addAttribute("actividadesElegidas", session.getAttribute("actividadesElegidas"));
        return "paginas/contactoReserva";
    }
}
