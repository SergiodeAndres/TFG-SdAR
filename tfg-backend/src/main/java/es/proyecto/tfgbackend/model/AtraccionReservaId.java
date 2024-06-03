package es.proyecto.tfgbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
public class AtraccionReservaId implements Serializable {
    private static final long serialVersionUID = 4878028600585718404L;
    @Column(name = "atraccionID", nullable = false)
    private Integer atraccionID;

    @Column(name = "reservaID", nullable = false)
    private Integer reservaID;

    @Column(name = "sesion", nullable = false)
    private LocalTime sesion;

    public Integer getAtraccionID() {
        return atraccionID;
    }

    public void setAtraccionID(Integer atraccionID) {
        this.atraccionID = atraccionID;
    }

    public Integer getReservaID() {
        return reservaID;
    }

    public void setReservaID(Integer reservaID) {
        this.reservaID = reservaID;
    }

    public LocalTime getSesion() {
        return sesion;
    }

    public void setSesion(LocalTime sesion) {
        this.sesion = sesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AtraccionReservaId entity = (AtraccionReservaId) o;
        return Objects.equals(this.reservaID, entity.reservaID) &&
                Objects.equals(this.sesion, entity.sesion) &&
                Objects.equals(this.atraccionID, entity.atraccionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservaID, sesion, atraccionID);
    }

}