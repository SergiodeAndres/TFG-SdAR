package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.Tarjeta;
import es.proyecto.tfgbackend.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TarjetaController {
    @Autowired
    ITarjetaService tarjetaService;

    @GetMapping("/aplicacion/tarjetas")
    public List<Tarjeta> buscarTodos() {
        return tarjetaService.buscarTodos();
    }

    @GetMapping("aplicacion/tarjetas/numero/{numero}")
    public Tarjeta buscarPorNumeroID(@PathVariable("numero") String numeroId) {
        return tarjetaService.buscarPorNumeroID(numeroId);
    }

    @PostMapping("aplicacion/tarjetas/numero/{numero}")
    public ArrayList<String> obtenerInfoTarjeta(@PathVariable("numero") String numeroId) {
        Tarjeta tarjeta = tarjetaService.buscarPorNumeroID(numeroId);
        ArrayList<String> datos = new ArrayList<>();
        datos.add(tarjeta.getNumeroID());
        datos.add(tarjeta.getSaldoTickets().toString());
        datos.add(tarjeta.getSaldoMoneda().toString());
        return datos;
    }

    @GetMapping("aplicacion/tarjetas/numero-pin/{numero}/{pin}")
    public Tarjeta buscarPorNumeroIDYPin(@PathVariable("numero") String numeroId, @PathVariable("pin") String pin) {
        return tarjetaService.buscarPorNumeroIDYPin(numeroId, pin);
    }

    @PostMapping("aplicacion/tarjetas/numero-pin/{numero}/{pin}")
    public String guardarTarjeta(@PathVariable("numero") String numeroId, @PathVariable("pin") String pin) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumeroID(numeroId);
        tarjeta.setPin(String.valueOf(pin.hashCode()));
        tarjeta.setSaldoMoneda(0.0F);
        tarjeta.setSaldoTickets(0);
        return String.valueOf(tarjetaService.guardarTarjeta(tarjeta));
    }
    @PostMapping(value = "/aplicacion/tarjetas", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarTarjeta(@RequestBody Tarjeta tarjeta) {
        tarjeta.setPin(String.valueOf(tarjeta.getPin().hashCode()));
        return String.valueOf(tarjetaService.guardarTarjeta(tarjeta));
    }

    @PutMapping("aplicacion/tarjetas")
    public void actualizarTarjeta(@RequestBody Tarjeta tarjeta) {
        tarjeta.setPin(String.valueOf(tarjeta.getPin().hashCode()));
        tarjetaService.actualizarTarjeta(tarjeta);
    }

    @PostMapping("aplicacion/tarjetas/numero-tickets/{numero}/{tickets}")
    public void cambiarTickets(@PathVariable("numero") String numeroId, @PathVariable("tickets") Integer tickets) {
        Tarjeta tarjeta = tarjetaService.buscarPorNumeroID(numeroId);
        int ticketsActuales = tarjeta.getSaldoTickets();
        if (ticketsActuales + tickets > 0)
        {
            tarjeta.setSaldoTickets(ticketsActuales + tickets);
            tarjetaService.actualizarTarjeta(tarjeta);
        }
    }

    @PostMapping("aplicacion/tarjetas/numero-dinero/{numero}/{dinero}")
    public void cambiarDinero(@PathVariable("numero") String numeroId, @PathVariable("dinero") float dinero) {
        Tarjeta tarjeta = tarjetaService.buscarPorNumeroID(numeroId);
        float dineroActual = tarjeta.getSaldoMoneda();
        if (dineroActual + dinero > 0)
        {
            tarjeta.setSaldoMoneda(dineroActual + dinero);
            tarjetaService.actualizarTarjeta(tarjeta);
        }
    }

    @DeleteMapping(value = "/aplicacion/tarjetas/{numero}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarTarjeta(@PathVariable("numero") String numeroId) {
        return String.valueOf(tarjetaService.eliminarTarjeta(numeroId));
    }
}
