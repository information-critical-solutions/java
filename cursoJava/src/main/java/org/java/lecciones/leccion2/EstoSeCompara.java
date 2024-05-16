package org.java.lecciones.leccion2;

import java.util.Objects;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class EstoSeCompara implements Comparable<EstoSeCompara> {

    private String nombre;
    private String apellido;

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

    @Override
    public String toString() {
        return "EstoSeCompara{" + "nombre=" + nombre + ", apellido=" + apellido + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.apellido);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstoSeCompara other = (EstoSeCompara) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.apellido, other.apellido);
    }

    @Override
    public int compareTo(EstoSeCompara o) {
        String a = nombre + "-" + apellido;
        String b = o.nombre + "-" + o.apellido;
        return a.compareTo(b);
    }

}
