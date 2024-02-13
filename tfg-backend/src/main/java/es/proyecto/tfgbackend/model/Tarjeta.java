package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "tarjeta", schema = "proyectodb")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numeroID", nullable = false)
    private String numeroID;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "saldoMoneda", nullable = false)
    private Float saldoMoneda;

    @Column(name = "saldoTickets", nullable = false)
    private Integer saldoTickets;

    public String getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(String numeroID) {
        this.numeroID = numeroID;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Float getSaldoMoneda() {
        return saldoMoneda;
    }

    public void setSaldoMoneda(Float saldoMoneda) {
        this.saldoMoneda = saldoMoneda;
    }

    public Integer getSaldoTickets() {
        return saldoTickets;
    }

    public void setSaldoTickets(Integer saldoTickets) {
        this.saldoTickets = saldoTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarjeta tarjeta = (Tarjeta) o;
        return Objects.equals(numeroID, tarjeta.numeroID) && Objects.equals(pin, tarjeta.pin) &&
                Objects.equals(saldoMoneda, tarjeta.saldoMoneda) && Objects.equals(saldoTickets, tarjeta.saldoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroID, pin, saldoMoneda, saldoTickets);
    }

}