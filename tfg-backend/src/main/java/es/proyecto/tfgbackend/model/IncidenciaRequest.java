package es.proyecto.tfgbackend.model;
public class IncidenciaRequest {
    private Integer id;

    private String descripcion;

    private Byte cerrada;

    private String dniEmpleado;

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

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }
}
