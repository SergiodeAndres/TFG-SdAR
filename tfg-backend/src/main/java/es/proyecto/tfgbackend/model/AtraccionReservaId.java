package es.proyecto.tfgbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class AtraccionReservaId implements Serializable {
    private static final long serialVersionUID = -4890282766831238463L;
    @Column(name = "atraccionID", nullable = false)
    private Integer atraccionID;

    @Column(name = "reservaID", nullable = false)
    private Integer reservaID;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AtraccionReservaId entity = (AtraccionReservaId) o;
        return Objects.equals(this.reservaID, entity.reservaID) &&
                Objects.equals(this.atraccionID, entity.atraccionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservaID, atraccionID);
    }

}