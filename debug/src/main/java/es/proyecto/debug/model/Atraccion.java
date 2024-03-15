package es.proyecto.debug.model;
public class Atraccion {
    private Integer id;

    private String nombre;

    private Integer capacidad;

    private Sitio sitioID;

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



}