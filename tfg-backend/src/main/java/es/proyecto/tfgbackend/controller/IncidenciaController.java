package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.Incidencia;
import es.proyecto.tfgbackend.model.IncidenciaRequest;
import es.proyecto.tfgbackend.service.ISitioService;
import es.proyecto.tfgbackend.service.IIncidenciaService;
import es.proyecto.tfgbackend.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class IncidenciaController {
    @Autowired
    ISitioService sitioService;

    @Autowired
    IIncidenciaService incidenciaService;

    @Autowired
    IEmpleadoService empleadoService;

    @GetMapping("/aplicacion/incidencias")
    public List<Incidencia> buscarTodos() {
        return incidenciaService.buscarTodos();
    }

    @GetMapping("/aplicacion/incidencias/id/{id}")
    public Incidencia buscarPorId(@PathVariable("id") Integer id) {
        return incidenciaService.buscarPorId(id);
    }

    @GetMapping("/aplicacion/incidencias/sitio/{sitio}")
    public List<Incidencia> buscarPorCerradaFalsoYDniEmpleado_SitioID (@PathVariable("sitio") Integer sitioId) {
        Sitio sitio = sitioService.buscarPorId(sitioId);
        return incidenciaService.buscarPorCerradaFalsoYDniEmpleado_SitioID(sitio);
    }

    @PostMapping(value = "/aplicacion/incidencias", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarIncidencia(@RequestBody IncidenciaRequest incidencia) {
        Incidencia incidenciaCompleta = new Incidencia();
        incidenciaCompleta.setCerrada(incidencia.getCerrada());
        incidenciaCompleta.setId(incidencia.getId());
        incidenciaCompleta.setDescripcion(incidencia.getDescripcion());
        incidenciaCompleta.setDniEmpleado(empleadoService.buscarPorId(incidencia.getDniEmpleado()));
        return String.valueOf(incidenciaService.guardarIncidencia(incidenciaCompleta));
    }

    @PutMapping("aplicacion/incidencias")
    public void actualizarEmpleado(@RequestBody IncidenciaRequest incidencia) {
        Incidencia incidenciaCompleta = new Incidencia();
        incidenciaCompleta.setCerrada(incidencia.getCerrada());
        incidenciaCompleta.setId(incidencia.getId());
        incidenciaCompleta.setDescripcion(incidencia.getDescripcion());
        incidenciaCompleta.setDniEmpleado(empleadoService.buscarPorId(incidencia.getDniEmpleado()));
        incidenciaService.actualizarIncidencia(incidenciaCompleta);
    }

    @DeleteMapping(value = "/aplicacion/incidencias/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarEmpleado(@PathVariable("id") Integer id) {
        return String.valueOf(incidenciaService.eliminarIncidencia(id));
    }
}
