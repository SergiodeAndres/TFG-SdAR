package es.proyecto.tfgfrontend.controller;
import es.proyecto.tfgfrontend.model.*;
import es.proyecto.tfgfrontend.service.IAtraccionReservaService;
import es.proyecto.tfgfrontend.service.IAtraccionService;
import es.proyecto.tfgfrontend.service.IReservaService;
import es.proyecto.tfgfrontend.service.ISitioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    IAtraccionReservaService atraccionReservaService;

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

    private HashMap<Integer, HashMap<LocalTime, Integer>> transformarListaReservas(List<AtraccionReserva> reservas) {
        HashMap<Integer, HashMap<LocalTime, Integer>> reservasPorAtraccion = new HashMap<>();
        for (AtraccionReserva atraccionReserva: reservas) {
            Atraccion atraccion = atraccionReserva.getAtraccionID();
            if (!reservasPorAtraccion.containsKey(atraccion.getId())) {
                reservasPorAtraccion.put(atraccion.getId(), new HashMap<>());
            }
            LocalTime hora = atraccionReserva.getSesion();
            if(!reservasPorAtraccion.get(atraccion.getId()).containsKey(hora))
            {
                reservasPorAtraccion.get(atraccion.getId()).put(hora,0);
            }
            Integer nuevaCantidad = reservasPorAtraccion.get(atraccion.getId()).get(hora) + atraccionReserva.getReservaID().getPersonas();
            reservasPorAtraccion.get(atraccion.getId()).replace(hora, nuevaCantidad);
        }
        return reservasPorAtraccion;
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
        List<Atraccion> atracciones = atraccionService.buscarAtraccionesPorSitio(sitio);
        HashMap<Atraccion, List<LocalTime>> sesionesPorAtraccion = new HashMap<>();
        //Carga de todas las sesiones posibles para todas las atracciones
        //Salvo para aquellas que tengan capacidad total menor que la cantidad de personas de la reserva
        for (Atraccion a: atracciones) {
            if (a.getCapacidad() >= personasReserva) {
                sesionesPorAtraccion.put(a, crearListaSesiones());
            }
            else
            {
                sesionesPorAtraccion.put(a, new ArrayList<>());
            }
        }
        //Quitar sesiones para las atracciones en las que no haya espacio para la reserva actual a esa hora
        List<AtraccionReserva> atraccionReservaList = atraccionReservaService.buscarPorResevaID_FechaReservaYReservaID_SitioID(fechaReserva, sitio);
        HashMap<Integer, HashMap<LocalTime, Integer>> reservasPorAtraccion = transformarListaReservas(atraccionReservaList);
        if (!reservasPorAtraccion.isEmpty())
        {
            for (Atraccion a: atracciones) {
                HashMap<LocalTime, Integer> mapReservas = reservasPorAtraccion.get(a.getId());
                if (mapReservas != null)
                {
                    for (LocalTime hora: mapReservas.keySet()) {
                        int capacidadRestante = a.getCapacidad() - mapReservas.get(hora);
                        if (capacidadRestante < personasReserva) {
                            sesionesPorAtraccion.get(a).remove(hora);
                        }
                    }
                }
            }
        }
        //Variables de sesión
        session.setAttribute("sitioReserva", sitio);
        session.setAttribute("fechaReserva", fechaReserva);
        session.setAttribute("personasReserva", personasReserva);
        session.setAttribute("sesionesPorAtraccion", sesionesPorAtraccion);
        HashMap<LocalTime, Atraccion> actividadesElegidas = new HashMap<>();
        session.setAttribute("actividadesElegidas", actividadesElegidas);
        session.setAttribute("precioTotal",0.0f);
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
        //Actualizamos el total a pagar
        float precioTotal = (float) session.getAttribute("precioTotal");
        precioTotal = precioTotal + (Integer) session.getAttribute("personasReserva") * atraccion.getPrecio();
        session.setAttribute("precioTotal", precioTotal);
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
        model.addAttribute("precioTotal",session.getAttribute("precioTotal"));
        return "paginas/actividadesReserva";
    }
    @GetMapping("/guardarActividades")
    public String guardarActividades(Model model, HttpSession session) {
        model.addAttribute("actividadesElegidas", session.getAttribute("actividadesElegidas"));
        model.addAttribute("precioTotal",session.getAttribute("precioTotal"));
        return "paginas/contactoReserva";
    }

    @PostMapping("/guardarReserva")
    public String guardarReserva(Model model, @RequestParam String nombreReserva, @RequestParam String telefonoReserva,
                                       @RequestParam String emailReserva, @RequestParam String datosPagoReserva,
                                       RedirectAttributes attributes, HttpSession session) {
        //Coger todos los datos de la reserva
        Sitio sitio = (Sitio) session.getAttribute("sitioReserva");
        LocalDate fecha = (LocalDate) session.getAttribute("fechaReserva");
        int personas = (Integer) session.getAttribute("personasReserva");
        HashMap<LocalTime, Atraccion> actividadesElegidas =
                (HashMap<LocalTime, Atraccion>) session.getAttribute("actividadesElegidas");
        //Crear ID para la reserva
        Integer reservaId = fecha.hashCode() + LocalTime.now().hashCode();
        reservaId = Math.abs(reservaId);
        //Crear reserva
        ReservaRequest reserva = new ReservaRequest(reservaId, nombreReserva, telefonoReserva, emailReserva, fecha,
                datosPagoReserva, personas, sitio.getId());
        //Guardar reserva
        reservaService.guardarReserva(reserva);
        //Crear y guardar las actividades de la reserva
        for (LocalTime sesion: actividadesElegidas.keySet()) {
            AtraccionReservaRequest actividad = new AtraccionReservaRequest(actividadesElegidas.get(sesion).getId(), reservaId, sesion);
            atraccionReservaService.guardarAtraccionReserva(actividad);
        }
        attributes.addFlashAttribute("msg", "Su reserva se realizó con éxito. Su código de reserva es: " + reservaId
        + ". Ya puede cerrar esta página.");
        return "redirect:/frontend/guardarActividades";
    }

    @GetMapping("/verReserva")
    public String verReserva(Model model) {
        return "paginas/credencialesReserva";
    }

    @PostMapping("/comprobarReserva")
    public String comprobarReserva(Model model, @RequestParam String emailContacto, @RequestParam int idReserva,
                                       RedirectAttributes attributes, HttpSession session) {
        Reserva reserva = reservaService.buscarPorIdYEmaildeContacto(idReserva, emailContacto);
        if (reserva == null) {
            attributes.addFlashAttribute("msg", "No existe reserva con estos datos.");
            return "redirect:/frontend/verReserva";
        }
        session.setAttribute("reserva", reserva);
        return "redirect:/frontend/verDatosReserva";
    }

    @GetMapping("/verDatosReserva")
    public String verDatosReserva(Model model, HttpSession session) {
        Reserva reserva = (Reserva) session.getAttribute("reserva");
        List<AtraccionReserva> actividadesReserva = atraccionReservaService.buscarPorReservaID(reserva);
        model.addAttribute("reserva", reserva);
        model.addAttribute("actividadesReserva", actividadesReserva);
        return "paginas/reservaCliente";
    }

    @GetMapping("/eliminarReserva")
    public String eliminarReserva(Model model, HttpSession session) {
        Reserva reserva = (Reserva) session.getAttribute("reserva");
        List<AtraccionReserva> actividadesReserva = atraccionReservaService.buscarPorReservaID(reserva);
        for (AtraccionReserva actividad: actividadesReserva) {
            atraccionReservaService.eliminarAtraccionReserva(actividad.getId());
        }
        reservaService.eliminarReserva(reserva.getId());
        return "redirect:/frontend";
    }

    @GetMapping("/info")
    public String infoEmpresa(Model model) {
        return "paginas/infoEmpresa";
    }

    @GetMapping("/reservasEmpleado")
    public String reservasEmpleado(Model model, HttpSession session) {
        LocalDate fechaActual = LocalDate.now();
        model.addAttribute("fechaActual", fechaActual);
        if (session.getAttribute("actividadesFecha") == null)
        {
            model.addAttribute("actividadesFecha", new ArrayList<>());
        }
        else {
            List<AtraccionReserva> actividadesFecha = (List<AtraccionReserva>) session.getAttribute("actividadesFecha");
            model.addAttribute("actividadesFecha", actividadesFecha);
        }
        return "paginas/verReservasEmpleado";
    }

    @PostMapping("/cargarActividadesFecha")
    public String cargarActividadesFecha(Model model, @RequestParam LocalDate fechaReserva, HttpSession session) {
        Sitio sitio = (Sitio) session.getAttribute("sitio");
        List<AtraccionReserva> actividadesFecha = atraccionReservaService.buscarPorResevaID_FechaReservaYReservaID_SitioID(fechaReserva,sitio);
        session.setAttribute("actividadesFecha", actividadesFecha);
        return "redirect:/frontend/reservasEmpleado";
    }
}
