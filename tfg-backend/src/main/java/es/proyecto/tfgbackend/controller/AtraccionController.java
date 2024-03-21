package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.*;
import es.proyecto.tfgbackend.service.IAtraccionService;
import es.proyecto.tfgbackend.service.ISitioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AtraccionController {
   @Autowired
    IAtraccionService atraccionService;

   @Autowired
    ISitioService sitioService;

   @GetMapping("aplicacion/atracciones")
   public List<Atraccion> buscarTodos() {
       return atraccionService.buscarTodos();
   }

   @GetMapping("aplicacion/atracciones/id/{id}")
   public Atraccion buscarAtraccionPorId(@PathVariable("id") Integer id) {
       return atraccionService.buscarAtraccionPorId(id);
   }

   @GetMapping("aplicacion/atracciones/sitio/{sitio}")
   public List<Atraccion> buscarAtraccionesPorSitio(@PathVariable("sitio") Integer sitioID) {
       return atraccionService.buscarAtraccionesPorSitio(sitioService.buscarPorId(sitioID));
   }

    @PostMapping(value = "/aplicacion/atracciones", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarAtraccion(@RequestBody AtraccionRequest atraccion) {
        Atraccion atraccionCompleta = new Atraccion();
        atraccionCompleta.setId(atraccion.getId());
        atraccionCompleta.setNombre(atraccion.getNombre());
        atraccionCompleta.setCapacidad(atraccion.getCapacidad());
        atraccionCompleta.setPrecio(atraccion.getPrecio());
        atraccionCompleta.setSitioID(sitioService.buscarPorId(atraccion.getSitioID()));
        return String.valueOf(atraccionService.guardarAtraccion(atraccionCompleta));
    }

    @PutMapping("aplicacion/atracciones")
    public void actualizarAtraccion(@RequestBody AtraccionRequest atraccion) {
        Atraccion atraccionCompleta = new Atraccion();
        atraccionCompleta.setId(atraccion.getId());
        atraccionCompleta.setNombre(atraccion.getNombre());
        atraccionCompleta.setCapacidad(atraccion.getCapacidad());
        atraccionCompleta.setPrecio(atraccion.getPrecio());
        atraccionCompleta.setSitioID(sitioService.buscarPorId(atraccion.getSitioID()));
        atraccionService.actualizarAtraccion(atraccionCompleta);
    }

    @DeleteMapping(value = "/aplicacion/atracciones/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarAtraccion(@PathVariable("id") Integer id) {
        return String.valueOf(atraccionService.eliminarAtraccion(id));
    }
}
