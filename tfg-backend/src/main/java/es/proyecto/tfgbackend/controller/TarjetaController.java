package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.Tarjeta;
import es.proyecto.tfgbackend.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TarjetaController {
    @Autowired
    ITarjetaService tarjetaService;

    @GetMapping("/aplicacion/tarjetas")
    public List<Tarjeta> buscarTodos() {
        return tarjetaService.buscarTodos();
    }

    @GetMapping("aplicacion/tarjetas/{numero}")
    public Tarjeta buscarPorNumeroID(@PathVariable("numero") String numeroId) {
        return tarjetaService.buscarPorNumeroID(numeroId);
    }

    @GetMapping("aplicacion/tarjetas/{numero}/{pin}")
    public Tarjeta buscarPorNumeroIDYPin(@PathVariable("numero") String numeroId, @PathVariable("pin") String pin) {
        return tarjetaService.buscarPorNumeroIDYPin(numeroId, pin);
    }

    @PostMapping(value = "/aplicacion/tarjetas", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarTarjeta(@RequestBody Tarjeta tarjeta) {
        return String.valueOf(tarjetaService.guardarTarjeta(tarjeta));
    }

    @PutMapping("aplicacion/tarjetas")
    public void actualizarTarjeta(@RequestBody Tarjeta tarjeta) {
        tarjetaService.actualizarTarjeta(tarjeta);
    }

    @DeleteMapping(value = "/aplicacion/tarjetas/{numero}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarTarjeta(@PathVariable("numero") String numeroId) {
        return String.valueOf(tarjetaService.eliminarTarjeta(numeroId));
    }
}
