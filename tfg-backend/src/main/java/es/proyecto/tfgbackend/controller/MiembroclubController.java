package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.Miembroclub;
import es.proyecto.tfgbackend.service.IMiembroclubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MiembroclubController {
    @Autowired
    IMiembroclubService miembroclubService;

    @GetMapping("/aplicacion/miembrosclub")
    public List<Miembroclub> buscarTodos() {
        return miembroclubService.buscarTodos();
    }

    @GetMapping("/aplicacion/miembrosclub/{correo}")
    public Miembroclub buscarPorEmail(@PathVariable("correo") String email) {
        return miembroclubService.buscarPorEmail(email);
    }

    @PostMapping(value = "/aplicacion/miembrosclub", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarMiembroclub(@RequestBody Miembroclub miembroclub) {
        return String.valueOf(miembroclubService.guardarMiembroClub(miembroclub));
    }

    @PutMapping("aplicacion/miembrosclub")
    public void actualizarMiembroclub(@RequestBody Miembroclub miembroclub) {
        miembroclubService.actualizarMiembroClub(miembroclub);
    }

    @DeleteMapping(value = "/aplicacion/miembrosclub/{correo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarMiembroclub(@PathVariable("correo") String correo) {
        return String.valueOf(miembroclubService.eliminarMiembroClub(correo));
    }
}
