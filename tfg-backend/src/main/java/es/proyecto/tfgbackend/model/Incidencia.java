package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "incidencia", schema = "proyectodb")
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidenciaID", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 2500)
    private String descripcion;

    @Column(name = "cerrada", nullable = false)
    private Byte cerrada;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DNI_empleado", nullable = false)
    private Empleado dniEmpleado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getCerrada() {
        return cerrada;
    }

    public void setCerrada(Byte cerrada) {
        this.cerrada = cerrada;
    }

    public Empleado getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(Empleado dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incidencia incidencia = (Incidencia) o;
        return Objects.equals(id, incidencia.id) && Objects.equals(descripcion, incidencia.descripcion) && Objects.equals(cerrada, incidencia.cerrada) &&
                Objects.equals(dniEmpleado, incidencia.dniEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, cerrada, dniEmpleado);
    }

}