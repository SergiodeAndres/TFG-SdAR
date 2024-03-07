package es.proyecto.tfgfrontend.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private TurnoId id;

    private Sitio sitio;

    private Empleado empleado;

    private LocalDate fecha;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    public Turno(TurnoId id, Sitio sitio, Empleado empleado, LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida) {
        this.id = id;
        this.sitio = sitio;
        this.empleado = empleado;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public Turno() {
    }

    public TurnoId getId() {
        return id;
    }

    public void setId(TurnoId id) {
        this.id = id;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}
