package org.java.lecciones.leccion1;

/**
 * Este programa verifica si una persona es mayor o menor de edad basado en su
 * edad. Muestra un mensaje en función del resultado de la verificación.
 *
 * @author Sebastian Godinez Borja
 */
public class VerificarEdad {

    public static void main(String[] args) {
        // Definición de la edad
        int edad = 20;

        // Verificar si la edad es mayor o igual a 18
        if (edad >= 18) {
            // Si la condición es verdadera, se ejecuta este bloque
            System.out.println("Eres mayor de edad");
        } else {
            // Si la condición es falsa, se ejecuta este bloque
            System.out.println("Eres menor de edad");
        }
    }
}
