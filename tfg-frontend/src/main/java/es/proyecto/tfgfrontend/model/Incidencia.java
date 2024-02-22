package es.proyecto.tfgfrontend.model;

public class Incidencia {
    private Integer id;

    private String descripcion;

    private Byte cerrada;

    private Empleado dniEmpleado;

    public Incidencia(Integer id, String descripcion, Byte cerrada, Empleado dniEmpleado) {
        this.id = id;
        this.descripcion = descripcion;
        this.cerrada = cerrada;
        this.dniEmpleado = dniEmpleado;
    }

    public Incidencia() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getCerrada() {
        return cerrada;
    }

    public void setCerrada(Byte cerrada) {
        this.cerrada = cerrada;
    }

    public Empleado getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(Empleado dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }
}
