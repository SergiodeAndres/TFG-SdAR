package es.proyecto.tfgfrontend.model;

import java.time.LocalTime;

public class AtraccionReservaRequest {
    private Integer atraccionID;
    private Integer reservaID;
    private LocalTime sesion;

    public AtraccionReservaRequest() {
    }

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
}
