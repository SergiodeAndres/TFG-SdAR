package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Objects;
@Entity
@Table(name = "atraccion_reserva", schema = "proyectodb")
public class AtraccionReserva {
    @EmbeddedId
    private AtraccionReservaId id;

    @MapsId("atraccionID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atraccionID", nullable = false)
    private Atraccion atraccionID;

    @MapsId("reservaID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservaID", nullable = false)
    private Reserva reservaID;

    @Column(name = "sesion", nullable = false)
    private LocalTime sesion;

    public AtraccionReservaId getId() {
        return id;
    }

    public void setId(AtraccionReservaId id) {
        this.id = id;
    }

    public Atraccion getAtraccionID() {
        return atraccionID;
    }

    public void setAtraccionID(Atraccion atraccionID) {
        this.atraccionID = atraccionID;
    }

    public Reserva getReservaID() {
        return reservaID;
    }

    public void setReservaID(Reserva reservaID) {
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
        if (o == null || getClass() != o.getClass()) return false;
        AtraccionReserva atraccionReserva = (AtraccionReserva) o;
        return Objects.equals(id, atraccionReserva.id) && Objects.equals(atraccionID, atraccionReserva.atraccionID) &&
                Objects.equals(reservaID, atraccionReserva.reservaID) && Objects.equals(sesion, atraccionReserva.sesion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, atraccionID, reservaID, sesion);
    }

}