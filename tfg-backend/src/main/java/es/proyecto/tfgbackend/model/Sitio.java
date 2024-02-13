package es.proyecto.tfgbackend.model;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "sitio", schema = "proyectodb")
public class Sitio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sitioID", nullable = false)
    private Integer id;

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "codigoPostal", nullable = false)
    private String codigoPostal;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sitio sitio = (Sitio) o;
        return Objects.equals(id, sitio.id) && Objects.equals(calle, sitio.calle) && Objects.equals(numero, sitio.numero) &&
                Objects.equals(codigoPostal, sitio.codigoPostal) && Objects.equals(ciudad, sitio.ciudad) &&
                Objects.equals(provincia, sitio.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, codigoPostal, ciudad, provincia);
    }

}