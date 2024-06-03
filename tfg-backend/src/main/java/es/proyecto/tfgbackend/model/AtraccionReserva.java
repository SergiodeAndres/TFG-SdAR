package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "atraccion_reserva")
public class AtraccionReserva {
    @EmbeddedId
    private AtraccionReservaId id;

    @MapsId("atraccionID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "atraccionID", nullable = false)
    private Atraccion atraccionID;

    @MapsId("reservaID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "reservaID", nullable = false)
    private Reserva reservaID;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtraccionReserva atraccionReserva = (AtraccionReserva) o;
        return Objects.equals(id, atraccionReserva.id) && Objects.equals(atraccionID, atraccionReserva.atraccionID)
                && Objects.equals(reservaID, atraccionReserva.reservaID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, atraccionID, reservaID);
    }

}