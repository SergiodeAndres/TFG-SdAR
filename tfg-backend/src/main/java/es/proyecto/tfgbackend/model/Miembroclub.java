package es.proyecto.tfgbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "miembroclub")
public class Miembroclub {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miembroclub miembroclub = (Miembroclub) o;
        return Objects.equals(email, miembroclub.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}