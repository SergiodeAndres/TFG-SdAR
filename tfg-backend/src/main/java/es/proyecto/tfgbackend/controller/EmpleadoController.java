package es.proyecto.tfgbackend.controller;
import es.proyecto.tfgbackend.model.Empleado;
import es.proyecto.tfgbackend.model.Sitio;
import es.proyecto.tfgbackend.model.Tarjeta;
import es.proyecto.tfgbackend.service.IEmpleadoService;
import es.proyecto.tfgbackend.service.ISitioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmpleadoController {

    @Autowired
    IEmpleadoService empleadoService;

    @Autowired
    ISitioService sitioService;

    @GetMapping("/aplicacion/empleados")
    public List<Empleado> buscarTodos() {
        return empleadoService.buscarTodos();
    }

    @GetMapping("/aplicacion/empleados/sitio/{sitio}")
    public List<Empleado> buscarPorSitioID(@PathVariable("sitio") Integer sitioId)
    {
        Sitio sitio = sitioService.buscarPorId(sitioId);
        return empleadoService.buscarPorSitioID(sitio);
    }

    @GetMapping("/aplicacion/empleados/email/{email}")
    public Empleado buscarPorEmail(@PathVariable("email") String email)
    {
        return empleadoService.buscarPorEmail(email);
    }

    @GetMapping("/aplicacion/empleados/email-password/{email}/{password}")
    public Empleado buscarPorEmailYPassword(@PathVariable("email") String email, @PathVariable("password") String password)
    {
        return empleadoService.buscarPorEmailYPassword(email, password);
    }

    @GetMapping("/aplicacion/empleados/gerentes")
    public List<Empleado> buscarPorGerenteVerdadero()
    {
        return empleadoService.buscarPorGerenteVerdadero();
    }

    @GetMapping("aplicacion/tarjetas/{dni}")
    public Empleado buscarPorId(@PathVariable("dni") String dni) {
        return empleadoService.buscarPorId(dni);
    }

    @PostMapping(value = "/aplicacion/empleados", produces = MediaType.TEXT_PLAIN_VALUE)
    public String guardarEmpleado(@RequestBody Empleado empleado) {
        return String.valueOf(empleadoService.guardarEmpleado(empleado));
    }

    @PutMapping("aplicacion/empleados")
    public void actualizarEmpleado(@RequestBody Empleado empleado) {
        empleadoService.actualizarEmpleado(empleado);
    }

    @DeleteMapping(value = "/aplicacion/empleados/{dni}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eliminarEmpleado(@PathVariable("dni") String dni) {
        return String.valueOf(empleadoService.eliminarEmpleado(dni));
    }
}
