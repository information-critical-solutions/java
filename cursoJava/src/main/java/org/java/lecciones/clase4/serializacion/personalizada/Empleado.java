package org.java.lecciones.clase4.serializacion.personalizada;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Declaración de la clase Empleado, que implementa la interfaz Serializable
 *
 * @author Sebastian Godinez Borja
 */
public class Empleado implements Serializable {

    // Declaración de atributos de la clase Empleado
    private String nombre;
    private transient String contrasena; // Marcar como transient para no serializar
    private int edad;

    // Constructor de la clase Empleado
    public Empleado(String nombre, String contrasena, int edad) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
    }

    // Método getter para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // Método getter para obtener la contraseña
    public String getContrasena() {
        return contrasena;
    }

    // Método getter para obtener la edad
    public int getEdad() {
        return edad;
    }

    // Método personalizado para la serialización
    private void writeObject(ObjectOutputStream out) throws IOException {
        // Escribir los campos necesarios en la secuencia
        out.defaultWriteObject();
        // Realizar modificaciones a la edad antes de serializarla (doble)
        out.writeInt(edad * 2);
    }

    // Método personalizado para la deserialización
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Leer los campos necesarios de la secuencia
        in.defaultReadObject();
        // Restaurar la edad al valor original después de deserializar (mitad)
        edad = in.readInt() / 2;
    }
}
