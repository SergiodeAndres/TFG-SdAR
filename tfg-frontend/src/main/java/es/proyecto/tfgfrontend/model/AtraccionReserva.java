package es.proyecto.tfgfrontend.model;

import java.time.LocalTime;

public class AtraccionReserva {
    private AtraccionReservaId id;

    private Atraccion atraccionID;

    private Reserva reservaID;

    private LocalTime sesion;

    public AtraccionReserva() {
    }
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
}
