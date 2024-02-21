package es.proyecto.tfgfrontend.model;

public class Empleado {
    private String dni;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private Sitio sitioID;

    private Byte gerente;

    public Empleado(String dni, String nombre, String apellido, String email, String password, Sitio sitioID, Byte gerente) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.sitioID = sitioID;
        this.gerente = gerente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sitio getSitioID() {
        return sitioID;
    }

    public void setSitioID(Sitio sitioID) {
        this.sitioID = sitioID;
    }

    public Byte getGerente() {
        return gerente;
    }

    public void setGerente(Byte gerente) {
        this.gerente = gerente;
    }
}
