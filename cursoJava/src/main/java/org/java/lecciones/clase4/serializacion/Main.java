package org.java.lecciones.clase4.serializacion;

import java.io.*;

/**
 * Clase principal que demuestra la serialización y deserialización de un objeto
 * Persona.
 *
 * @author Sebastian Godinez Borja
 */
public class Main {

    public static void main(String[] args) {
        // Crear una instancia de Persona
        Persona persona = new Persona("Juan", 30);

        // Serializar la instancia en un archivo
        try (OutputStream os = new FileOutputStream("persona.ser"); 
                ObjectOutputStream outputStream = new ObjectOutputStream(os);) {
            outputStream.writeObject(persona); // Se escribe la instancia en el archivo "persona.ser"
            System.out.println("Objeto serializado y guardado en 'persona.ser'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el objeto serializado desde el archivo
        try (InputStream is = new FileInputStream("persona.ser"); 
                ObjectInputStream inputStream = new ObjectInputStream(is)) {
            Persona personaDeserializada = (Persona) inputStream.readObject(); // Se lee la instancia desde el archivo
            System.out.println("Objeto deserializado: " + personaDeserializada);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
