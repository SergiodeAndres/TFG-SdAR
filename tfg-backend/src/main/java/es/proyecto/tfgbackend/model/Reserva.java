package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name = "reserva", schema = "proyectodb")
public class Reserva {
    @Id
    @Column(name = "reservaID", nullable = false)
    private Integer id;

    @Column(name = "nombreContacto", nullable = false)
    private String nombreContacto;

    @Column(name = "telefonoContacto", nullable = false)
    private String telefonoContacto;

    @Column(name = "emailContacto", nullable = false)
    private String emailContacto;

    @Column(name = "fechaReserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "datosPago", nullable = false)
    private String datosPago;

    @Column(name = "personas", nullable = false)
    private Integer personas;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sitioID", nullable = false)
    private Sitio sitioID;

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

    public Sitio getSitioID() {
        return sitioID;
    }

    public void setSitioID(Sitio sitioID) {
        this.sitioID = sitioID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id) && Objects.equals(nombreContacto, reserva.nombreContacto) &&
                Objects.equals(telefonoContacto, reserva.telefonoContacto) &&
                Objects.equals(emailContacto, reserva.emailContacto) && Objects.equals(fechaReserva, reserva.fechaReserva)
                && Objects.equals(datosPago, reserva.datosPago) &&
                Objects.equals(personas, reserva.personas) && Objects.equals(sitioID, reserva.sitioID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreContacto, telefonoContacto, emailContacto, fechaReserva, datosPago, personas, sitioID);
    }

}