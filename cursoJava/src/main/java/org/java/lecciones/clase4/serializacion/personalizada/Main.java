package org.java.lecciones.clase4.serializacion.personalizada;

import java.io.*;

/**
 * Clase principal para la serialización y deserialización de objetos Empleado.
 *
 * @author Sebastian Godinez Borja
 */
public class Main {

    public static void main(String[] args) {
        // Crear un objeto Empleado
        Empleado empleado = new Empleado("Alice", "claveSecreta", 25);

        // Serializar el objeto a un archivo
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("empleado.ser"))) {
            out.writeObject(empleado);
            System.out.println("Objeto Empleado serializado con éxito. " + empleado);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializar el objeto desde el archivo
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream("empleado.ser"))) {
            Empleado empleadoDeserializado = (Empleado) in.readObject();
            System.out.println("Objeto Empleado deserializado con éxito.");
            System.out.println("Nombre: " + empleadoDeserializado.getNombre());
            System.out.println("Edad: " + empleadoDeserializado.getEdad());
            System.out.println("Contraseña: " + empleadoDeserializado.getContrasena());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
