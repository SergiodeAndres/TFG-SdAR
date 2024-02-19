package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class ReservaRequest {
    private Integer id;

    private String nombreContacto;

    private String telefonoContacto;

    private String emailContacto;

    private LocalDate fechaReserva;

    private String datosPago;

    private Integer personas;

    private Integer sitioID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getDatosPago() {
        return datosPago;
    }

    public void setDatosPago(String datosPago) {
        this.datosPago = datosPago;
    }

    public Integer getPersonas() {
        return personas;
    }

    public void setPersonas(Integer personas) {
        this.personas = personas;
    }

    public Integer getSitioID() {
        return sitioID;
    }

    public void setSitioID(Integer sitioID) {
        this.sitioID = sitioID;
    }
}
