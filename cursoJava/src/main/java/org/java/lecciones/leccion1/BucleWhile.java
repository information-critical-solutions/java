package org.java.lecciones.leccion1;

/**
 * Este programa utiliza un bucle while para realizar iteraciones y muestra un
 * mensaje en cada iteración.
 *
 * @author Sebastian Godinez Borja
 */
public class BucleWhile {

    public static void main(String[] args) {
        // Inicialización del contador
        int contador = 0;

        // Utilizando un bucle while para imprimir las iteraciones
        while (contador < 3) {
            // Imprime un mensaje con el valor actual del contador
            System.out.println("Contador: " + contador);

            // Incrementa el contador en 1 para la próxima iteración
            contador++;
        }
        System.out.println("Contador: " + contador);
    }
}
