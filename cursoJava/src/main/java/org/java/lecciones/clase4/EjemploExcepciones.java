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
            int resultado = dividir(10, 5);
            System.out.println("Resultado de la división: " + resultado);
            if (1 == 1) {
                throw new Exception("falla siempre");
            }
        } catch (NumeroException e) {
            // Capturamos la excepción ArithmeticException
            System.err.println("Error: División por cero.");
            e.printStackTrace(); // Imprimimos el rastreo de la excepción
        } catch (Exception e) {
            // aqui atrapamos todo lo demás
            System.err.println("Error inesperado");
            e.printStackTrace(); // Imprimimos el rastreo de la excepción
        } finally {
            // Este bloque se ejecuta siempre, haya o no haya excepción
            System.out.println("Fin del programa.");
        }
    }

    public static int dividir(int numerador, int denominador) throws NumeroException {
        // Intentamos realizar la división
        if (denominador == 0) {
            // Lanzamos una excepción de tipo ArithmeticException si el denominador es cero
            throw new NumeroException("Se intento dividir: " + numerador + " entre 0");
        }
        return numerador / denominador;
    }
}
