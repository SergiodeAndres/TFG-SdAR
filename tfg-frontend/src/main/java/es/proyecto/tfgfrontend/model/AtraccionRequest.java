package es.proyecto.tfgfrontend.model;

public class AtraccionRequest {

    private Integer id;

    private String nombre;

    private Integer capacidad;

    private Integer sitioID;

    private Float precio;

    public AtraccionRequest(Integer id, String nombre, Integer capacidad, Integer sitioID, Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.sitioID = sitioID;
        this.precio = precio;
    }

    public AtraccionRequest() {
    }

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

    public Integer getSitioID() {
        return sitioID;
    }

    public void setSitioID(Integer sitioID) {
        this.sitioID = sitioID;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
