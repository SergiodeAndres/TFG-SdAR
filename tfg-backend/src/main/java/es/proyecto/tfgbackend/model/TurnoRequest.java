package es.proyecto.tfgbackend.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoRequest {
    private Integer sitio;

    private String empleado;

    private LocalDate fecha;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    public Integer getSitio() {
        return sitio;
    }

    public void setSitio(Integer sitio) {
        this.sitio = sitio;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
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
