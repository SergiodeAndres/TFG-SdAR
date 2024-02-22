package es.proyecto.tfgfrontend.model;


public class Tarjeta {

    private String numeroID;

    private String pin;

    private Float saldoMoneda;

    private Integer saldoTickets;

    public Tarjeta(String numeroID, String pin, Float saldoMoneda, Integer saldoTickets) {
        this.numeroID = numeroID;
        this.pin = pin;
        this.saldoMoneda = saldoMoneda;
        this.saldoTickets = saldoTickets;
    }

    public Tarjeta() {

    }

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
}
