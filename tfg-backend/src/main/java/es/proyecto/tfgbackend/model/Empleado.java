package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sitioID", nullable = false)
    private Sitio sitioID;

    @Column(name = "gerente", nullable = false)
    private Byte gerente;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(dni, empleado.dni) && Objects.equals(nombre, empleado.nombre) &&
                Objects.equals(apellido, empleado.apellido) && Objects.equals(email, empleado.email) &&
                Objects.equals(password, empleado.password) && Objects.equals(sitioID, empleado.sitioID) &&
                Objects.equals(gerente, empleado.gerente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido, email, password, sitioID, gerente);
    }

}