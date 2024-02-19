package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
@Entity
@Table(name = "turno", schema = "proyectodb")
public class Turno {
    @EmbeddedId
    private TurnoId id;

    @MapsId("sitio")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sitio", nullable = false)
    private Sitio sitio;

    @MapsId("empleado")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "horaEntrada", nullable = false)
    private LocalTime horaEntrada;

    @Column(name = "horaSalida", nullable = false)
    private LocalTime horaSalida;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return Objects.equals(id, turno.id) && Objects.equals(sitio, turno.sitio) &&
                Objects.equals(empleado, turno.empleado) && Objects.equals(fecha, turno.fecha) &&
                Objects.equals(horaEntrada, turno.horaEntrada) && Objects.equals(horaSalida, turno.horaSalida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sitio, empleado, fecha, horaEntrada, horaSalida);
    }

}