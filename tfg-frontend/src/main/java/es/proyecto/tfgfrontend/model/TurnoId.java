package es.proyecto.tfgfrontend.model;
public class TurnoId {
    private Integer sitio;

    private String empleado;

    public TurnoId(Integer sitio, String empleado) {
        this.sitio = sitio;
        this.empleado = empleado;
    }

    public TurnoId() {
    }

    public Integer getSitio() {
        return sitio;
    }

    public void setSitio(Integer sitio) {
        this.sitio = sitio;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
}
