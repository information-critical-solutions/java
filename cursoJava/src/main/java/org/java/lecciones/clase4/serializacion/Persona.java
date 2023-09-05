package org.java.lecciones.clase4.serializacion;

import java.io.Serializable;

/**
 * Clase Persona que implementa la interfaz Serializable para permitir la
 * serialización de objetos de esta clase.
 *
 * @author Sebastian Godinez Borja
 */
public class Persona implements Serializable {

    private String nombre;
    private int edad;

    /**
     * Constructor de la clase Persona.
     *
     * @param nombre El nombre de la persona.
     * @param edad La edad de la persona.
     */
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    /**
     * Método para obtener el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener la edad de la persona.
     *
     * @return La edad de la persona.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Sobrescribe el método toString para mostrar información sobre la persona.
     *
     * @return Una representación en cadena de la persona.
     */
    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
    }
}
