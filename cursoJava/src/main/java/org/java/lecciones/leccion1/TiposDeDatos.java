package org.java.lecciones.leccion1;

/**
 * Este programa demuestra el uso de diferentes tipos de datos en Java. Muestra
 * ejemplos de tipos numéricos, caracteres, decimales y cadenas, junto con sus
 * tamaños en bytes.
 *
 * @author Sebastian Godinez Borja
 */
public class TiposDeDatos {

    public static void main(String[] args) {
        
        // Tipos de datos numéricos
        short numeroShort = 1000; // 2 bytes
        int numeroInt = 2000000; // 4 bytes
        long numeroLong = 1234567890123L; // 8 bytes

        // Tipo de dato caracter
        char caracter = 'A'; // 2 bytes

        // Tipo de dato decimal
        double decimal = 3.14159; // 8 bytes

        // Tipo de dato cadena
        String texto = "¡Hola, mundo!"; // Variable

        // Impresión de variables
        System.out.println("Número short: " + numeroShort);
        System.out.println("Número int: " + numeroInt);
        System.out.println("Número long: " + numeroLong);
        System.out.println("Carácter: " + caracter);
        System.out.println("Número decimal: " + decimal);
        System.out.println("Texto: " + texto);
    }
}
