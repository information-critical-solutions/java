package org.java.lecciones.leccion1;

/**
 * Este programa utiliza un bucle do-while para realizar iteraciones y muestra
 * un mensaje en cada iteración.
 *
 * @author Sebastian Godinez Borja
 */
public class BucleDoWhile {

    public static void main(String[] args) {
        // Inicialización del contador de intentos
        int intentos = 0;

        // Utilizando un bucle do-while para imprimir las iteraciones
        do {
            // Imprime un mensaje con el número de intento actual
            System.out.println("Intento " + intentos);

            // Incrementa el contador de intentos en 1 para la próxima iteración
            intentos++;
        } while (intentos < 3);

        System.out.println("intentos " + intentos);
    }
}
