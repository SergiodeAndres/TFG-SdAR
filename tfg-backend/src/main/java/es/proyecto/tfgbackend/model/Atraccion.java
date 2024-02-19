package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "atraccion", schema = "proyectodb")
public class Atraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atraccionID", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sitioID", nullable = false)
    private Sitio sitioID;

    @Column(name = "precio", nullable = false)
    private Float precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Sitio getSitioID() {
        return sitioID;
    }

    public void setSitioID(Sitio sitioID) {
        this.sitioID = sitioID;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atraccion atraccion = (Atraccion) o;
        return Objects.equals(id, atraccion.id) && Objects.equals(nombre, atraccion.nombre) && Objects.equals(capacidad, atraccion.capacidad)
                && Objects.equals(sitioID, atraccion.sitioID) && Objects.equals(precio, atraccion.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, capacidad, sitioID, precio);
    }


}