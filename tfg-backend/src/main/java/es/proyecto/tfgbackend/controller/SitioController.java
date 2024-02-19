package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.service.ISitioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SitioController {
    @Autowired
    ISitioService sitioService;

    @GetMapping("/aplicacion/sitios")
    public List<Sitio> buscarTodos() {
        return sitioService.buscarTodos();
    }

    @GetMapping("/aplicacion/sitios/id/{id}")
    public Sitio buscarPorId(@PathVariable("id") Integer id) {
        return sitioService.buscarPorId(id);
    }

    @PostMapping(value = "/aplicacion/sitios", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarSitio(@RequestBody Sitio sitio) {
        return String.valueOf(sitioService.guardarSitio(sitio));
    }

    @PutMapping("aplicacion/sitios")
    public void actualizarSitio(@RequestBody Sitio sitio) {
        sitioService.actualizarSitio(sitio);
    }

    @DeleteMapping(value = "/aplicacion/sitios/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarSitio(@PathVariable("id") Integer id) {
        return String.valueOf(sitioService.eliminarSitio(id));
    }
}
