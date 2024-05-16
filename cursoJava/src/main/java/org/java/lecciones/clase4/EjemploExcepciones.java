package org.java.lecciones.clase4;

/**
 * Clase de ejemplo para el manejo de excepciones en Java.
 *
 * @author Sebastian Godinez Borja
 */
public class EjemploExcepciones {

    public static void main(String[] args) {
        try {
            // Intentamos realizar una operación que puede lanzar una excepción
            int resultado = dividir(10, 0);
            System.out.println("Resultado de la división: " + resultado);
        } catch (ArithmeticException e) {
            // Capturamos la excepción ArithmeticException
            System.err.println("Error: División por cero.");
            e.printStackTrace(); // Imprimimos el rastreo de la excepción
        } finally {
            // Este bloque se ejecuta siempre, haya o no haya excepción
            System.out.println("Fin del programa.");
        }
    }

    public static int dividir(int numerador, int denominador) {
        // Intentamos realizar la división
//        if (denominador == 0) {
//            // Lanzamos una excepción de tipo ArithmeticException si el denominador es cero
//            throw new ArithmeticException("División por cero no permitida.");
//        }
        return numerador / denominador;
    }
}
