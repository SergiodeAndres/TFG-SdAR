package es.proyecto.tfgbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.Objects;
@Embeddable
public class TurnoId implements Serializable {
    private static final long serialVersionUID = 8367025430524025080L;
    @Column(name = "sitio", nullable = false)
    private Integer sitio;

    @Column(name = "empleado", nullable = false, length = 9)
    private String empleado;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TurnoId entity = (TurnoId) o;
        return Objects.equals(this.sitio, entity.sitio) &&
                Objects.equals(this.empleado, entity.empleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sitio, empleado);
    }

}